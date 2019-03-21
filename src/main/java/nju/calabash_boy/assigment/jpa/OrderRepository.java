package nju.calabash_boy.assigment.jpa;

import nju.calabash_boy.assigment.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    Order save(Order order);
    Order getById(Integer id);
    List<Order> findAllByAssociatorId(Integer id);
    List<Order> findAllByAssociatorIdAndState(Integer id,String state);
    List<Order> findAllByRestaurantId(Integer id);
    List<Order> findAllByRestaurantIdAndState(Integer id,String state);
}
