package nju.calabash_boy.assigment.entity;

public class OrderItem_VO {
    private int id;
    private int order_id;
    private int product_id;
    private int number;
    private double amount;
    public OrderItem get(){
        OrderItem item = new OrderItem();
        item.setId(id);
        item.setOrderId(order_id);
        item.setProductId(product_id);
        item.setNumber(number);
        item.setAmount(amount);
        return item;
    }
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
