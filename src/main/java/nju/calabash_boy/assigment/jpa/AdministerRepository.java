package nju.calabash_boy.assigment.jpa;

import nju.calabash_boy.assigment.entity.Administer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministerRepository extends JpaRepository<Administer,Integer> {
    Administer save(Administer administer);
    Administer findByIdAndPasswordAndDeletedIsFalse(Integer id,String password);
}
