package nju.calabash_boy.assigment.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`order`")
/*
confirming delivering finished
 */
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
    @Column(name ="address_id")
    private int addressId;
    @Transient
    private String restaurantName;
    @Transient
    private List<OrderItem> item_list;
    @Transient
    private String associatorName;
    @Transient
    private String addressContent;
    @Transient
    private Associator associator;

    public Associator getAssociator() {
        return associator;
    }

    public void setAssociator(Associator associator) {
        this.associator = associator;
    }

    public String getAddressContent() {
        return addressContent;
    }

    public void setAddressContent(String addressContent) {
        this.addressContent = addressContent;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getAssociatorName() {
        return associatorName;
    }

    public void setAssociatorName(String associatorName) {
        this.associatorName = associatorName;
    }

    public List<OrderItem> getItem_list() {
        return item_list;
    }

    public void setItem_list(List<OrderItem> item_list) {
        this.item_list = item_list;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
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
