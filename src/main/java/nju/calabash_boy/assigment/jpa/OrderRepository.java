package nju.calabash_boy.assigment.jpa;

import nju.calabash_boy.assigment.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    Order save(Order order);
    Order getById(Integer id);
    List<Order> findAllByAssociatorId(Integer id);
    List<Order> findAllByAssociatorIdAndState(Integer id,String state);
    List<Order> findAllByRestaurantId(Integer id);
    List<Order> findAllByRestaurantIdAndState(Integer id,String state);
    @Modifying
    @Transactional
    @Query(value = "update `order` set state = \"delivering\" where id = ?1 and state = \"confirming\"",nativeQuery = true)
    void confirm(Integer id);
    @Modifying
    @Transactional
    @Query(value = "update `order` set state = \"declined\" where id = ?1 and state = \"confirming\"",nativeQuery = true)
    void decline(Integer id);
    @Modifying
    @Transactional
    @Query(value = "update `order` set state = \"finished\" where id = ?1 and state = \"delivering\"",nativeQuery = true)
    void confirm_associator(Integer id);
}
