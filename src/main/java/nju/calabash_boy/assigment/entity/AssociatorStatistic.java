package nju.calabash_boy.assigment.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AssociatorStatistic {
    public static final int DAY_LIMIT = 5;
    class AssociatorDaily{
        LocalDate time;
        int associator_cnt;
        public LocalDate getTime() {
            return time;
        }

        public void setTime(LocalDate time) {
            this.time = time;
        }

        public int getAssociator_cnt() {
            return associator_cnt;
        }

        public void setAssociator_cnt(int associator_cnt) {
            this.associator_cnt = associator_cnt;
        }
    }
    private ArrayList<AssociatorDaily> associatorDailies;

    public AssociatorStatistic(List<Associator> all_user){
        this.associatorDailies = new ArrayList<>();
        LocalDate now = LocalDate.now();
        ArrayList<AssociatorDaily> list = new ArrayList<>();
        for (int i=0;i<DAY_LIMIT;i++,now = now.minusDays(1)){
            AssociatorDaily daily = new AssociatorDaily();
            daily.time = now;
            for (Associator ass:all_user){
                if (ass.getRegister_time().toLocalDate().compareTo(daily.time) <=0){
                    daily.associator_cnt++;
                }
            }
            list.add(daily);
        }
        for (int i=list.size()-1;i>=0;i--){
            this.associatorDailies.add(list.get(i));
        }
    }
    public ArrayList<AssociatorDaily> getAssociatorDailies() {
        return associatorDailies;
    }

    public void setAssociatorDailies(ArrayList<AssociatorDaily> associatorDailies) {
        this.associatorDailies = associatorDailies;
    }
}
