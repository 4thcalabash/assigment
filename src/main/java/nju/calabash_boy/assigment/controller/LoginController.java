package nju.calabash_boy.assigment.controller;

import nju.calabash_boy.assigment.entity.Administer;
import nju.calabash_boy.assigment.entity.Associator;
import nju.calabash_boy.assigment.entity.Order;
import nju.calabash_boy.assigment.entity.Restaurant;
import nju.calabash_boy.assigment.jpa.AdministerRepository;
import nju.calabash_boy.assigment.jpa.AssociatorRepository;
import nju.calabash_boy.assigment.jpa.OrderRepository;
import nju.calabash_boy.assigment.jpa.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private AssociatorRepository associator_dao;
    @Autowired
    private RestaurantRepository restaurant_dao;
    @Autowired
    private AdministerRepository administer_dao;
    @Autowired
    private OrderRepository order_dao;
    public static final double [] VIP_LEVEL_LIMIT = new double[]{200,1000,5000,20000,1e18};
    public static final double [] PRICE_OFF = new double[]{1,0.99,0.97,0.95,0.9,0};
    public static final String [] VIP_NAME = new String []{"准会员","一星会员","二星会员","三星会员","四星会员","牛顿"};
    private void check(Associator user){
        List<Order> list = order_dao.findAllByAssociatorId(user.getId());
        double sum = 0;
        for (Order order : list){
            sum += order.getAmount();
        }
        for (int i=0;i<VIP_LEVEL_LIMIT.length;i++){
            if (VIP_LEVEL_LIMIT[i] > sum){
                user.setDiscount(PRICE_OFF[i]);
                user.setVip_level(VIP_NAME[i]);
                break;
            }
        }
    }
    @RequestMapping("/associator")
    public String associator_login(@RequestParam("username")String username,
                                   @RequestParam("password")String password,
                                   HttpServletRequest request){
        Associator user = associator_dao.findByUsernameAndPasswordAndDeletedIsFalse(username,password);
        check(user);
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
