package nju.calabash_boy.assigment.controller;

import nju.calabash_boy.assigment.entity.Associator;
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
public class LogoutController {
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("user") == null){
            return "null";
        }else{
            session.removeAttribute("user");
            session.removeAttribute("role");
            return "success";
        }
    }

}
