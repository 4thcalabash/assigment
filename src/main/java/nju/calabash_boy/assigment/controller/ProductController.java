package nju.calabash_boy.assigment.controller;

import nju.calabash_boy.assigment.entity.Product;
import nju.calabash_boy.assigment.jpa.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository product_dao;
    @RequestMapping("/all")
    List<Product> get_all(@RequestParam("restaurant_id")Integer restaurant_id){
        return product_dao.findAllByRestaurantId(restaurant_id);
    }
    @RequestMapping("/get_one")
    Product get_one(@RequestParam("product_id")Integer id){
        return product_dao.getById(id);
    }
}
