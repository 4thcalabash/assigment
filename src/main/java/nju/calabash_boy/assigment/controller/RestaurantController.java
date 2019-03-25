package nju.calabash_boy.assigment.controller;

import nju.calabash_boy.assigment.entity.Restaurant;
import nju.calabash_boy.assigment.jpa.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantRepository restaurant_dao;
    @RequestMapping("/all")
    public List<Restaurant> get_all(){
        return restaurant_dao.findAll();
    }
    @RequestMapping("one")
    public Restaurant get_one(@RequestParam("id") Integer id){
        return restaurant_dao.findByIdAndDeletedIsFalse(id);
    }
    @RequestMapping("/update")
    public String update_info(@RequestParam("name")String name,
                              @RequestParam("type")String type,
                              @RequestParam("address")String address,
                              @RequestParam("id")Integer id,
                              @RequestParam("password")String password,
                              HttpServletRequest request){
        Restaurant rest = (Restaurant)request.getSession().getAttribute("user");
        if (password.equals(rest.getPassword())){
            restaurant_dao.update(id,name,type,address);
            return "success";
        }else{
            return "password_wrong";
        }
    }
}
