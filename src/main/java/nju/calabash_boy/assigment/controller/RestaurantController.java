package nju.calabash_boy.assigment.controller;

import nju.calabash_boy.assigment.entity.Restaurant;
import nju.calabash_boy.assigment.jpa.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
