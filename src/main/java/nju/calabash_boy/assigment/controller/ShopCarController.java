package nju.calabash_boy.assigment.controller;

import nju.calabash_boy.assigment.entity.Product;
import nju.calabash_boy.assigment.entity.ShopItem;
import nju.calabash_boy.assigment.jpa.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/shop_car")
public class ShopCarController {
    @Autowired
    private ProductRepository product_dao;
    @RequestMapping("/change_shop")
    void change_shop(@RequestParam("shop_id")Integer id,
                     HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session.getAttribute("shop_id") != null && (Integer)session.getAttribute("shop_id") == id){
            return;
        }
        session.setAttribute("shop_id",id);
        //item_id number
        session.setAttribute("shop_list",new HashMap<Integer,Integer>());
    }
    @RequestMapping("/add_item")
    void add_item(@RequestParam("item_id")Integer id,
                  @RequestParam("number")Integer number,
                  HttpServletRequest request){
        System.out.println(id+"   "+number);
        HttpSession session = request.getSession();
        HashMap<Integer,Integer> mp = (HashMap)session.getAttribute("shop_list");
        int now_number = 0;
        if (mp.containsKey(id)){
            now_number = mp.get(id);
        }
        now_number += number;
        now_number = Math.min(now_number,product_dao.getById(id).getNumber());
        if (!mp.containsKey(id)){
            mp.put(id,0);
        }
        mp.replace(id,now_number);
        for (Integer key : mp.keySet()){
            System.out.println("shop_list"+key+mp.get(key));
        }
        session.setAttribute("shop_list",mp);
    }
    @RequestMapping("/delete_item")
    void delete_item(@RequestParam("item_id")Integer id,
                  @RequestParam("number")Integer number,
                  HttpServletRequest request){
        HttpSession session = request.getSession();
        HashMap<Integer,Integer> mp = (HashMap)session.getAttribute("shop_list");
        int now_number = mp.get(id);
        now_number -= number;
        now_number = Math.max(0,now_number);
        if (now_number == 0){
            mp.remove(id);
        }else{
            mp.replace(id,now_number);
        }
        session.setAttribute("shop_list",mp);
    }
    @RequestMapping("/shop_list")
    ArrayList<ShopItem> get_shop_list(HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session.getAttribute("shop_list") == null)return new ArrayList<>();
        HashMap<Integer,Integer> mp = (HashMap)session.getAttribute("shop_list");
        ArrayList<ShopItem> list = new ArrayList<>();
        for (Integer id : mp.keySet()){
            Product p = product_dao.getById(id);
            list.add(new ShopItem(id,p.getName(),mp.get(id),p.getPrice()));
        }
        return list;
    }
}
