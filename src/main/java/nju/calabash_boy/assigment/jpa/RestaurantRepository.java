package nju.calabash_boy.assigment.jpa;

import nju.calabash_boy.assigment.entity.Restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {
    Restaurant findByIdAndPasswordAndDeletedIsFalse(Integer id,String password);
    Restaurant save(Restaurant restaurant);
    Restaurant findByIdAndDeletedIsFalse(Integer id);
    @Modifying
    @Transactional
    @Query(value = "update restaurant set name = ?2, type = ?3,address=?4 where id = ?1 ",nativeQuery = true)
    void update(Integer id,String name,String Type,String address);
}
