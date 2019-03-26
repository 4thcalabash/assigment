package nju.calabash_boy.assigment.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FinanceStatistic {
    public static final int DAY_LIMIT = 5;
    class FinanceDaily{
        LocalDate time;
        double amount;

        public LocalDate getTime() {
            return time;
        }

        public void setTime(LocalDate time) {
            this.time = time;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }
    }
    ArrayList<FinanceDaily> financeDailies;
    public FinanceStatistic(List<Order> order_list){
        this.financeDailies = new ArrayList<>();
        LocalDate now = LocalDate.now();
        ArrayList<FinanceDaily> list = new ArrayList<>();
        for (int i=0;i < DAY_LIMIT;i++,now = now.minusDays(1)){
            FinanceDaily daily = new FinanceDaily();
            daily.time = now;
            for (Order order :order_list){
                if (order.getSubmitTime().toLocalDate().compareTo(daily.time) ==0){
                    daily.amount += order.getAmount();
                }
            }
            list.add(daily);
        }
        for (int i=list.size()-1;i >=0;i--){
            this.financeDailies.add(list.get(i));
        }
    }

    public ArrayList<FinanceDaily> getFinanceDailies() {
        return financeDailies;
    }

    public void setFinanceDailies(ArrayList<FinanceDaily> financeDailies) {
        this.financeDailies = financeDailies;
    }
}
