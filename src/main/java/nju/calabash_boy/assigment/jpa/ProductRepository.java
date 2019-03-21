package nju.calabash_boy.assigment.jpa;

import nju.calabash_boy.assigment.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product save(Product product);
    List<Product> findAllByRestaurantId(Integer id);
    @Modifying
    @Transactional
    @Query(value = "update product set number = ?2 where id = ?1",nativeQuery = true)
    void update_number(Integer id,Integer number);
    @Modifying
    @Transactional
    @Query(value = "update product set price = ?2 where id = ?1",nativeQuery = true)
    void update_price(Integer id,double price);
    @Modifying
    @Transactional
    @Query(value = "update product set name = ?2 where id = ?1", nativeQuery = true)
    void update_name(Integer id,String name);
    Product getById(Integer id);
}
