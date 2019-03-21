package nju.calabash_boy.assigment.controller;

import nju.calabash_boy.assigment.entity.Administer;
import nju.calabash_boy.assigment.entity.Associator;
import nju.calabash_boy.assigment.entity.Restaurant;
import nju.calabash_boy.assigment.jpa.AdministerRepository;
import nju.calabash_boy.assigment.jpa.AssociatorRepository;
import nju.calabash_boy.assigment.jpa.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private AssociatorRepository associator_dao;
    @Autowired
    private RestaurantRepository restaurant_dao;
    @Autowired
    private AdministerRepository administer_dao;
    @RequestMapping("/associator")
    public String associator_login(@RequestParam("username")String username,
                                   @RequestParam("password")String password,
                                   HttpServletRequest request){
        Associator user = associator_dao.findByUsernameAndPasswordAndDeletedIsFalse(username,password);
        if (user!= null) {
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            session.setAttribute("role","associator");
            return "login";
        } else{
            return "fail";
        }

    }
    @RequestMapping("/restaurant")
    public String restaurant_login(@RequestParam("username")String username,
                                   @RequestParam("password")String password,
                                   HttpServletRequest request){
        if (username.length()!= 7){
            return "fail";
        }
        Integer id;
        try{
            id = Integer.parseInt(username);
        }catch(Exception e){
            return "fail";
        }
        Restaurant user = restaurant_dao.findByIdAndPasswordAndDeletedIsFalse(id,password);
        if (user != null){
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            session.setAttribute("role","restaurant");
            return "login";
        }else{
            return "fail";
        }

    }
    @RequestMapping("/administer")
    public String administer_login(@RequestParam("username")String username,
                                   @RequestParam("password")String password,
                                   HttpServletRequest request){
        if (username.length()!=7){
            return "fail";
        }
        Integer id;
        try{
            id = Integer.parseInt(username);
        }catch(Exception e){
            return "fail";
        }
        Administer user = administer_dao.findByIdAndPasswordAndDeletedIsFalse(id,password);
        if (user != null){
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            session.setAttribute("role","administer");
            return "login";
        }else{
            return "fail";
        }
    }
}
