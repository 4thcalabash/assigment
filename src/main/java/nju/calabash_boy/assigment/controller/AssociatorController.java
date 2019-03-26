package nju.calabash_boy.assigment.controller;

import nju.calabash_boy.assigment.entity.Associator;
import nju.calabash_boy.assigment.jpa.AddressRepository;
import nju.calabash_boy.assigment.jpa.AssociatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import nju.calabash_boy.assigment.entity.Address;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/associator")
public class AssociatorController {
    @Autowired
    private AssociatorRepository associator_dao;
    @Autowired
    private AddressRepository address_dao;
    @RequestMapping("/update")
    public String update(@RequestParam("mail") String mail,
                         @RequestParam("phone") String phone,
                         @RequestParam("name") String name,
                         @RequestParam("username") String username,
                         @RequestParam("password") String password,
                         HttpServletRequest request){
        Associator ass = associator_dao.findByUsernameAndPasswordAndDeletedIsFalse(username,password);
        if (ass == null){
            return "密码错误";
        }else{
            associator_dao.update(phone,mail,name,username,password);
            Associator new_ass = associator_dao.findByUsernameAndPasswordAndDeletedIsFalse(username,password);
            request.getSession().setAttribute("user",new_ass);
            return "success";
        }
    }
    @RequestMapping("/dispose")
    public String dispose(@RequestParam("username")String username,
                          @RequestParam("id")Integer id,
                          HttpServletRequest request){
        associator_dao.delete(username,id);
        HttpSession session = request.getSession();
        session.removeAttribute("role");
        session.removeAttribute("user");
        return "success";
    }
    @RequestMapping("/address/add")
    public String address_add(@RequestParam("name") String name,
                              @RequestParam("address_content") String address_content,
                              HttpServletRequest request){
        Integer associator_id = ((Associator)request.getSession().getAttribute("user")).getId();
        Address address = new Address();
        address.setName(name);
        address.setAddress_content(address_content);
        address.setAssociatorId(associator_id);
        Address result = address_dao.save(address);
        if (result != null){
            return "success";
        }else{
            return "fail";
        }
    }
    @RequestMapping("/address/update")
    public String address_update(@RequestParam("id")Integer id,@RequestParam("address_content")String address_content){
        address_dao.update(id,address_content);
        return "success";
    }
    @RequestMapping("/address/delete")
    public String address_delete(@RequestParam("id")Integer id){
        address_dao.deleteById(id);
        return "success";
    }
    @RequestMapping("/address/get")
    public List<Address> address_get(@RequestParam("associator_id")Integer associator_id){
        //return null;
        return address_dao.findAllByAssociatorId(associator_id);
    }
    @RequestMapping("/add")
    public void add(@RequestParam("username")String username,
                    @RequestParam("password")String password,
                    @RequestParam("mail")String mail,
                    @RequestParam("phone")String phone,
                    @RequestParam("name")String name){
        Associator ass = new Associator();
        ass.setUsername(username);
        ass.setPassword(password);
        ass.setMail(mail);
        ass.setPhone(phone);
        ass.setName(name);
        ass.setRegister_time(LocalDateTime.now());
        ass.setVip_level("1星会员");
        ass.setDeleted(false);
        associator_dao.save(ass);
    }
}
