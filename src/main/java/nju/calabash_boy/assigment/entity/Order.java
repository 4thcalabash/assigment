package nju.calabash_boy.assigment.entity;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue
    private int id;
    @Column(name= "associator_id")
    private int associatorId;
    @Column(name = "restaurant_id")
    private int restaurantId;
    private String state;
    @Column(name = "submit_time")
    private LocalDateTime submitTime;
    @Column(name = "pay_time")
    private LocalDateTime payTime;
    @Column(name = "confirm_time")
    private LocalDateTime confirmTime;
    @Column(name = "finish_time")
    private LocalDateTime finishTime;
    private double amount;
    public Order_VO get(List<OrderItem_VO> item_list){
        Order_VO vo = new Order_VO();
        vo.setId(id);
        vo.setAmount(amount);
        vo.setOrder_item(item_list);
        vo.setAssociator_id(associatorId);
        vo.setConfirm_time(confirmTime);
        vo.setFinish_time(finishTime);
        vo.setPay_time(payTime);
        vo.setRestaurant_id(restaurantId);
        vo.setState(state);
        vo.setSubmit_time(submitTime);
        return vo;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAssociatorId() {
        return associatorId;
    }

    public void setAssociatorId(int associatorId) {
        this.associatorId = associatorId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDateTime getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(LocalDateTime submitTime) {
        this.submitTime = submitTime;
    }

    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }

    public LocalDateTime getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(LocalDateTime confirmTime) {
        this.confirmTime = confirmTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
