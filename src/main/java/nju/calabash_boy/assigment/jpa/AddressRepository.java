package nju.calabash_boy.assigment.jpa;

import nju.calabash_boy.assigment.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Integer> {
    Address save(Address address);
    List<Address> findAllByAssociatorId(Integer associatorId);
    @Transactional
    @Modifying
    @Query(value = "update address set address_content = ?2 where id = ?1",nativeQuery = true)
    void update(Integer id,String address_content);
    void deleteById(Integer id);
    Address getById(Integer id);
}
