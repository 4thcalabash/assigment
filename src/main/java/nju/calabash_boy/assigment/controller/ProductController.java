package nju.calabash_boy.assigment.controller;

import nju.calabash_boy.assigment.entity.Product;
import nju.calabash_boy.assigment.entity.Restaurant;
import nju.calabash_boy.assigment.jpa.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @RequestMapping("/add")
    String product_add(@RequestParam("name")String name,
                       @RequestParam("price")Double price,
                       @RequestParam("number")Integer number,
                       HttpServletRequest request){
        HttpSession session = request.getSession();
        Restaurant rest = (Restaurant)session.getAttribute("user");
        Product p = new Product();
        p.setName(name);
        p.setNumber(number);
        p.setPrice(price);
        p.setRestaurantId(rest.getId());
        product_dao.save(p);
        return "success";
    }
}
