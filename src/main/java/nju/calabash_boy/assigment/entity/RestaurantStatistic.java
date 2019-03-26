package nju.calabash_boy.assigment.entity;

import nju.calabash_boy.assigment.jpa.OrderRepository;
import nju.calabash_boy.assigment.jpa.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantStatistic {
    class RestaurantDaily{
        LocalDate time;
        int restaurant_cnt;
        int order_cnt;

        public LocalDate getTime() {
            return time;
        }

        public void setTime(LocalDate time) {
            this.time = time;
        }

        public int getRestaurant_cnt() {
            return restaurant_cnt;
        }

        public void setRestaurant_cnt(int restaurant_cnt) {
            this.restaurant_cnt = restaurant_cnt;
        }

        public int getOrder_cnt() {
            return order_cnt;
        }

        public void setOrder_cnt(int order_cnt) {
            this.order_cnt = order_cnt;
        }
    }
    public static final int DAY_LIMIT = 5;
    ArrayList<RestaurantDaily> restaurantDailies;

    public ArrayList<RestaurantDaily> getRestaurantDailies() {
        return restaurantDailies;
    }

    public void setRestaurantDailies(ArrayList<RestaurantDaily> restaurantDailies) {
        this.restaurantDailies = restaurantDailies;
    }

    public RestaurantStatistic(List<Restaurant> all_rests,
                               List<Order> all_orders){
        this.restaurantDailies = new ArrayList<>();
        LocalDate now = LocalDate.now();
        ArrayList<RestaurantDaily> list = new ArrayList<>();
//        int restaurant_tot = 0;
        for (int i = 0;i < DAY_LIMIT ; i ++,now = now.minusDays(1)){
            RestaurantDaily daily = new RestaurantDaily();
            daily.time = now;
            for (Restaurant rest : all_rests){
                if (rest.getRegister_time().toLocalDate().compareTo(daily.time)<=0){
                    daily.restaurant_cnt ++;
                }
            }
            for (Order order : all_orders){
                if (order.getSubmitTime().toLocalDate().compareTo(daily.time)<=0){
                    daily.order_cnt++;
                }
            }
            list.add(daily);
        }
        for (int i=list.size()-1;i>=0;i--){
            this.restaurantDailies.add(list.get(i));
        }
    }
    public static void main(String args[]){
        LocalDate now = LocalDate.now();
        System.out.println(now.toString());
    }
}
