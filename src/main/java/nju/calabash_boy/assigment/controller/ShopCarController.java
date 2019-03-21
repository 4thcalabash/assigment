package nju.calabash_boy.assigment.controller;

import nju.calabash_boy.assigment.jpa.ProductRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("/shopcar")
public class ShopCarController {
    private ProductRepository product_dao;
    @RequestMapping("/change_shop")
    void change_shop(@RequestParam("shop_id")Integer id,
                     HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("shop_id",id);
        //item_id number
        session.setAttribute("shop_list",new HashMap<Integer,Integer>());
    }
    @RequestMapping("/add_item")
    void add_item(@RequestParam("item_id")Integer id,
                  @RequestParam("number")Integer number,
                  HttpServletRequest request){
        HttpSession session = request.getSession();
        HashMap<Integer,Integer> mp = (HashMap)session.getAttribute("shop_list");
        int now_number = 0;
        if (mp.containsKey(id)){
            now_number = mp.get(id);
        }
        now_number += number;
        now_number = Math.min(number,product_dao.getById(id).getNumber());
        mp.replace(id,now_number);
    }
}
