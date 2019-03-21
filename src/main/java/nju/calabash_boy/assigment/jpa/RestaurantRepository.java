package nju.calabash_boy.assigment.jpa;

import nju.calabash_boy.assigment.entity.Restaurant;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {
    Restaurant findByIdAndPasswordAndDeletedIsFalse(Integer id,String password);
    Restaurant save(Restaurant restaurant);
    Restaurant findByIdAndDeletedIsFalse(Integer id);
}
