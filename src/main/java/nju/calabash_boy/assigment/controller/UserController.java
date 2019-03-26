package nju.calabash_boy.assigment.controller;

import com.mysql.cj.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 用户控制器
 */
@Controller
public class UserController {
    @RequestMapping("/home")
    public String goHome() {
        return "home";
    }
    @RequestMapping("/index")
    public String goIndex(HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session.getAttribute("role")!=null){
            String role = (String) session.getAttribute("role");
            return "index_"+role;
        }else{
            return "home";
        }
    }
    @RequestMapping("/test")
    String go_test(){
        return "test";
    }
}
