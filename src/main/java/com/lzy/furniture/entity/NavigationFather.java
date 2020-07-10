package com.lzy.furniture.entity;

import javax.persistence.*;

@Entity
public class NavigationFather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String naviName;
    @Transient
    private Integer count;

    @Override
    public String toString() {
        return "NavigationFather{" +
                "id=" + id +
                ", naviName='" + naviName + '\'' +
                ", count=" + count +
                '}';
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public NavigationFather() {
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

    public NavigationFather(String naviName) {

        this.naviName = naviName;
    }
}
