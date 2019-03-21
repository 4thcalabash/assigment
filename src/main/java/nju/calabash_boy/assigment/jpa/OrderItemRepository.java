package nju.calabash_boy.assigment.jpa;

import nju.calabash_boy.assigment.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
    OrderItem save(OrderItem orderItem);
    List<OrderItem> findAllByOrderId(Integer order_id);
    List<OrderItem> findAllByProductId(Integer product_id);
}
