package com.lzy.furniture.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String compName;
    private String address1;
    private String address2;
    private String phone1User;
    private String phone1;
    private String phone2User;
    private String phone2;
    private String email;
    private String batch;
    private String gaode;

    public String getGaode() {
        return gaode;
    }

    public void setGaode(String gaode) {
        this.gaode = gaode;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", compName='" + compName + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", phone1User='" + phone1User + '\'' +
                ", phone1='" + phone1 + '\'' +
                ", phone2User='" + phone2User + '\'' +
                ", phone2='" + phone2 + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPhone1User() {
        return phone1User;
    }

    public void setPhone1User(String phone1User) {
        this.phone1User = phone1User;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2User() {
        return phone2User;
    }

    public void setPhone2User(String phone2User) {
        this.phone2User = phone2User;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Company() {

    }
}
