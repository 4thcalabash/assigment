package nju.calabash_boy.assigment.controller;

import nju.calabash_boy.assigment.entity.Order;
import nju.calabash_boy.assigment.entity.OrderItem;
import nju.calabash_boy.assigment.entity.OrderItem_VO;
import nju.calabash_boy.assigment.entity.Order_VO;
import nju.calabash_boy.assigment.jpa.OrderItemRepository;
import nju.calabash_boy.assigment.jpa.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderRepository order_dao;
    @Autowired
    private OrderItemRepository order_item_dao;
    @RequestMapping("/add")
    public String order_add(@RequestParam("order") Order_VO order){
        Order o = order.get();
        order_dao.save(o);
        for (OrderItem_VO vo : order.getOrder_item()){
            order_item_dao.save(vo.get());
        }
        return "success";
    }
    @RequestMapping("/getone")
    public Order_VO getone(@RequestParam("id")Integer id){
        List<OrderItem> list = order_item_dao.findAllByOrderId(id);
        List<OrderItem_VO> list_ = new ArrayList<>();
        for (OrderItem item : list){
            list_.add(item.get());
        }
        Order order  = order_dao.getById(id);
        return order.get(list_);
    }
}
