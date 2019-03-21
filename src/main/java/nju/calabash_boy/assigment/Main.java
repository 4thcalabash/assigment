package nju.calabash_boy.assigment;

import nju.calabash_boy.assigment.entity.Associator;
import nju.calabash_boy.assigment.jpa.AssociatorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Main {
    @Autowired
    public AssociatorRepository dao;
    @Test
    public void main_test(){
        LocalDateTime now = LocalDateTime.now();
        LocalDate now2 = now.toLocalDate();
        Date now3 = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(now3.toString());
    }
}
