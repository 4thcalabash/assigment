package nju.calabash_boy.assigment.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
@Entity
@Table(name = "associator")
public class Associator {
    @Id
    @GeneratedValue
    private int id;
    private String username,password;
    private LocalDateTime register_time;
    private String mail;
    private String phone;
    private String name;
    private String vip_level;
    private Boolean deleted;
    public void print(){
        System.out.println("ID = "+ id);
        System.out.println("username = " + username + " password = "+ password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getRegister_time() {
        return register_time;
    }

    public void setRegister_time(LocalDateTime register_time) {
        this.register_time = register_time;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVip_level() {
        return vip_level;
    }

    public void setVip_level(String vip_level) {
        this.vip_level = vip_level;
    }
}
