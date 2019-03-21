package nju.calabash_boy.assigment.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Order_VO {
    private int id;
    private int associator_id;
    private int restaurant_id;
    private String state;
    private LocalDateTime submit_time;
    private LocalDateTime pay_time;
    private LocalDateTime confirm_time;
    private LocalDateTime finish_time;
    private double amount;
    private List<OrderItem_VO> order_item;
    public Order get(){
        Order order = new Order();
        order.setId(id);
        order.setAssociatorId(associator_id);
        order.setRestaurantId(restaurant_id);
        order.setState(state);
        order.setSubmitTime(submit_time);
        order.setPayTime(pay_time);
        order.setConfirmTime(confirm_time);
        order.setFinishTime(finish_time);
        order.setAmount(amount);
        return order;
    }

    public List<OrderItem_VO> getOrder_item() {
        return order_item;
    }

    public void setOrder_item(List<OrderItem_VO> order_item) {
        this.order_item = order_item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAssociator_id() {
        return associator_id;
    }

    public void setAssociator_id(int associator_id) {
        this.associator_id = associator_id;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDateTime getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(LocalDateTime submit_time) {
        this.submit_time = submit_time;
    }

    public LocalDateTime getPay_time() {
        return pay_time;
    }

    public void setPay_time(LocalDateTime pay_time) {
        this.pay_time = pay_time;
    }

    public LocalDateTime getConfirm_time() {
        return confirm_time;
    }

    public void setConfirm_time(LocalDateTime confirm_time) {
        this.confirm_time = confirm_time;
    }

    public LocalDateTime getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(LocalDateTime finish_time) {
        this.finish_time = finish_time;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
