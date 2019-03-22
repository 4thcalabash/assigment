package nju.calabash_boy.assigment.controller;

import nju.calabash_boy.assigment.entity.*;
import nju.calabash_boy.assigment.jpa.OrderItemRepository;
import nju.calabash_boy.assigment.jpa.OrderRepository;
import nju.calabash_boy.assigment.jpa.ProductRepository;
import nju.calabash_boy.assigment.jpa.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private ProductRepository product_dao;
    @Autowired
    private OrderRepository order_dao;
    @Autowired
    private OrderItemRepository order_item_dao;
    @Autowired
    private RestaurantRepository restaurant_dao;
    @RequestMapping("/make_deal")
    public void make_deal(HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session.getAttribute("shop_id") == null)return;
        Integer shop_id = (Integer)session.getAttribute("shop_id");
        HashMap<Integer,Integer> mp = (HashMap)session.getAttribute("shop_list");
        Order order = new Order();
        order.setAssociatorId(((Associator)session.getAttribute("user")).getId());
        order.setRestaurantId(shop_id);
        order.setState("confirming");
        order.setSubmitTime(LocalDateTime.now());
        double amount = 0;
        for (Integer key : mp.keySet()){
            Product p = product_dao.getById(key);
            amount += mp.get(key) * p.getPrice();
        }
        order.setAmount(amount);
        Order result = order_dao.save(order);
        System.out.println(result.getId());
        for (Integer key :mp.keySet()){
            Product p = product_dao.getById(key);
            product_dao.update_number(key,p.getNumber() - mp.get(key));
            OrderItem item = new OrderItem();
            item.setOrderId(result.getId());
            item.setProductId(key);
            item.setNumber(mp.get(key));
            item.setAmount(p.getPrice() * mp.get(key));
            order_item_dao.save(item);
        }
        session.removeAttribute("shop_id");
        session.removeAttribute("shop_list");
    }
    @RequestMapping("/get_all_associator")
    public List<Order> get_all(@RequestParam("user_id")Integer id){
        List<Order> list = order_dao.findAllByAssociatorId(id);
        List<Order> ans = new ArrayList<>();
        for (Order order : list){
            order.setRestaurantName(restaurant_dao.findByIdAndDeletedIsFalse(order.getRestaurantId()).getName());
            ans.add(order);
        }
        return ans;
    }
    @RequestMapping("/get_all_associator_now")
    public List<Order> get_all_now(@RequestParam("user_id")Integer id){
        List<Order> list = order_dao.findAllByAssociatorIdAndState(id,"delivering");

        List<Order> ans = new ArrayList<>();
        for (Order order : list){
            order.setRestaurantName(restaurant_dao.findByIdAndDeletedIsFalse(order.getRestaurantId()).getName());
            ans.add(order);
        }
        return ans;
    }
    @RequestMapping("/get_detail")
    public Order get_detail(@RequestParam("order_id")Integer id){
        Order order = order_dao.getById(id);
        order.setItem_list(order_item_dao.findAllByOrderId(id));
        List<OrderItem> ans = new ArrayList<>();
        for (OrderItem item : order.getItem_list()){
            item.setName(product_dao.getById(item.getProductId()).getName());
            ans.add(item);
        }
        order.setItem_list(ans);
        return order;
    }
}
