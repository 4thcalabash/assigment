package nju.calabash_boy.assigment.entity;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String address_content;
    @Column(name = "associator_id")
    private Integer associatorId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress_content() {
        return address_content;
    }

    public void setAddress_content(String address_content) {
        this.address_content = address_content;
    }

    public Integer getAssociatorId() {
        return associatorId;
    }

    public void setAssociatorId(Integer associatorId) {
        this.associatorId = associatorId;
    }
}
