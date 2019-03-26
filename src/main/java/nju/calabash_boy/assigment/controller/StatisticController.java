package nju.calabash_boy.assigment.controller;

import nju.calabash_boy.assigment.entity.*;
import nju.calabash_boy.assigment.jpa.AssociatorRepository;
import nju.calabash_boy.assigment.jpa.OrderRepository;
import nju.calabash_boy.assigment.jpa.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/statistic")
public class StatisticController {
    @Autowired
    private RestaurantRepository restaurant_dao;
    @Autowired
    private OrderRepository order_dao;
    @Autowired
    private AssociatorRepository associator_dao;
    @RequestMapping("/restaurant")
    RestaurantStatistic restaurant_statistic(){
        return new RestaurantStatistic(restaurant_dao.findAll(),order_dao.findAll());
    }
    @RequestMapping("/finance")
    FinanceStatistic finance_statistic(){
        return new FinanceStatistic(order_dao.findAll());
    }
    @RequestMapping("/associator")
    AssociatorStatistic associator_statistic(){
        return new AssociatorStatistic(associator_dao.findAll());
    }
}
