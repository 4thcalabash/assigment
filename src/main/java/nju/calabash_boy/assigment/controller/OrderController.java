package nju.calabash_boy.assigment.controller;

import nju.calabash_boy.assigment.entity.*;
import nju.calabash_boy.assigment.jpa.*;
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
    @Autowired
    private AssociatorRepository associator_dao;
    @Autowired
    private AddressRepository address_dao;
    private final static int AUTO_DECLINE_MINUTE = 1;
    private Order check(Order order){
        int address_id = order.getAddressId();
        order.setAddressContent(address_dao.getById(address_id).getAddress_content());
        Associator ass = associator_dao.getById(order.getAssociatorId());
        order.setAssociator(ass);
        if (order.getState().equals("confirming")){
            LocalDateTime submit_time = order.getSubmitTime();
            LocalDateTime now_time = LocalDateTime.now();
            System.out.println("now_time:"+now_time.toString());
            now_time = now_time.minusMinutes(AUTO_DECLINE_MINUTE);
            System.out.println("limit_time:"+now_time.toString());
            System.out.println("order_time:"+submit_time.toString());
            if (submit_time.compareTo(now_time)<0){
                order_dao.decline(order.getId());
            }
            order.setState("declined");
            return order;
        }else{
            return order;
        }
    }
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
            ans.add(check(order));
        }
        return ans;
    }
    @RequestMapping("/get_all_associator_now")
    public List<Order> get_all_now(@RequestParam("user_id")Integer id){
        List<Order> list = order_dao.findAllByAssociatorIdAndState(id,"delivering");
        List<Order> ans = new ArrayList<>();
        for (Order order : list){
            order.setRestaurantName(restaurant_dao.findByIdAndDeletedIsFalse(order.getRestaurantId()).getName());
            ans.add(check(order));
        }
        return ans;
    }

    @RequestMapping("/get_all_restaurant")
    public List<Order> get_all_rest(@RequestParam("user_id")Integer id){
        List<Order> list = order_dao.findAllByRestaurantId(id);

        List<Order> ans = new ArrayList<>();
        for (Order order : list){
            order.setRestaurantName(restaurant_dao.findByIdAndDeletedIsFalse(order.getRestaurantId()).getName());
            order.setAssociatorName(associator_dao.getById(order.getAssociatorId()).getName());
            ans.add(check(order));
        }
        return ans;
    }
    @RequestMapping("/get_now_restaurant")
    public List<Order> get_now_rest(@RequestParam("user_id")Integer id){
        List<Order> list = order_dao.findAllByRestaurantIdAndState(id,"delivering");

        List<Order> ans = new ArrayList<>();
        for (Order order : list){
            order.setRestaurantName(restaurant_dao.findByIdAndDeletedIsFalse(order.getRestaurantId()).getName());
            order.setAssociatorName(associator_dao.getById(order.getAssociatorId()).getName());
            ans.add(check(order));
        }
        return ans;
    }
    @RequestMapping("/get_new_restaurant")
    public List<Order> get_new_rest(@RequestParam("user_id")Integer id){
        List<Order> list = order_dao.findAllByRestaurantIdAndState(id,"confirming");

        List<Order> ans = new ArrayList<>();
        for (Order order : list){
            order.setRestaurantName(restaurant_dao.findByIdAndDeletedIsFalse(order.getRestaurantId()).getName());
            order.setAssociatorName(associator_dao.getById(order.getAssociatorId()).getName());
            ans.add(check(order));
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
    @RequestMapping("/confirm_associator")
    public void confirm_associator(@RequestParam("order_id")Integer order_id){
        order_dao.confirm_associator(order_id);
    }
    @RequestMapping("/confirm")
    public void confirm_order(@RequestParam("order_id")Integer order_id){
        order_dao.confirm(order_id);
    }
}
