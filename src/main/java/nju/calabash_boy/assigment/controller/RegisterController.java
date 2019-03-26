package nju.calabash_boy.assigment.controller;

import nju.calabash_boy.assigment.entity.Associator;
import nju.calabash_boy.assigment.jpa.AssociatorRepository;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Random;

@RestController()
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    AssociatorRepository associator_dao;
    HashMap<String,String> auth_code = new HashMap<>();
    public static final int CODE_LENGTH = 6;
    private char get_random_char(){
        Random random = new Random();
        int flag = random.nextInt();
        if (flag %2 == 0){
            return (char)('a' + Math.abs(random.nextInt())%26);
        }else{
            return (char)('0'+Math.abs(random.nextInt())%10);
        }
    }
    private String get_random_code(){
        String code = "";
        for (int i=0;i<CODE_LENGTH;i++){
            code += get_random_char();
        }
        return code;
    }
    @RequestMapping("/send_auth")
    public String send_auth(@RequestParam("username")String username,
                          @RequestParam("mail")String mail) throws EmailException {
        if (associator_dao.findByUsername(username)== null){
            HtmlEmail email = new HtmlEmail();
            email.setHostName("smtp.qq.com");
            email.setCharset("utf-8");
            email.addTo(mail);
            email.setFrom("751188973@qq.com");
            email.setAuthentication("751188973@qq.com","lygsqqhxrbjabbif");
            email.setSubject("注册验证码");
            String code = get_random_code();
            System.out.println(code);
            auth_code.put(mail,code);
            email.setMsg(code);
            email.send();
            return "success";
        }else{
            return "user_already_exist";
        }
    }
    @RequestMapping("/confirm_auth")
    public String confirm_auth(@RequestParam("mail")String mail,
                               @RequestParam("auth_code")String code){
        if (auth_code.containsKey(mail) && auth_code.get(mail).equals(code)){
            return "success";
        }else{
            return "fail";
        }
    }

}
