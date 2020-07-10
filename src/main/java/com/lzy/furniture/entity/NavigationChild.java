package com.lzy.furniture.entity;

import javax.persistence.*;

@Entity
public class NavigationChild {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String naviName;
    private Integer fatherId;
    @Transient
    private String fatherName;


    @Transient
    private Integer count;

    @Override
    public String toString() {
        return "NavigationChild{" +
                "id=" + id +
                ", naviName='" + naviName + '\'' +
                ", fatherId=" + fatherId +
                ", fatherName='" + fatherName + '\'' +
                ", count=" + count +
                '}';
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public NavigationChild() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaviName() {
        return naviName;
    }

    public void setNaviName(String naviName) {
        this.naviName = naviName;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

}
