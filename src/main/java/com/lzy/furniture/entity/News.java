package com.lzy.furniture.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String address;
    private String addressMini;
    private String addressMiddle;
    private String addressLarge;
    private String name;
    private Integer state;

    public String getAddressMiddle() {
        return addressMiddle;
    }

    public void setAddressMiddle(String addressMiddle) {
        this.addressMiddle = addressMiddle;
    }

    public String getAddressMini() {
        return addressMini;
    }

    public void setAddressMini(String addressMini) {
        this.addressMini = addressMini;
    }

    public String getAddressLarge() {
        return addressLarge;
    }

    public void setAddressLarge(String addressLarge) {
        this.addressLarge = addressLarge;
    }

    public News() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }


    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", state=" + state +
                '}';
    }
}
