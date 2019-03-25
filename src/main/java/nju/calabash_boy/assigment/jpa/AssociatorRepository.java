package nju.calabash_boy.assigment.jpa;

import nju.calabash_boy.assigment.entity.Associator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface AssociatorRepository extends JpaRepository<Associator,Integer>{
    @Modifying
    Associator save(Associator associator);
    Associator findByUsernameAndPasswordAndDeletedIsFalse(String username, String password);
    @Modifying
    @Transactional
    @Query(value = "update associator set phone = ?1,mail = ?2, name = ?3 where username = ?4 and password = ?5",nativeQuery = true)
    void update(String phone,String mail,String name,String username,String password);
    @Modifying
    @Transactional
    @Query(value = "update associator set deleted = 1 where username = ?1 and id = ?2 and deleted = 0",nativeQuery = true)
    void delete(String username,Integer id);
    Associator getById(Integer id);
}
