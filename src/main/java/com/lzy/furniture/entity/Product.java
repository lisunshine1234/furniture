package com.lzy.furniture.entity;

import javax.persistence.*;

@Entity
public class Product {
    public String getAddressMini1() {
        return addressMini1;
    }

    public void setAddressMini1(String addressMini1) {
        this.addressMini1 = addressMini1;
    }

    public String getAddressMini2() {
        return addressMini2;
    }

    public void setAddressMini2(String addressMini2) {
        this.addressMini2 = addressMini2;
    }

    public String getAddressMini3() {
        return addressMini3;
    }

    public void setAddressMini3(String addressMini3) {
        this.addressMini3 = addressMini3;
    }

    public String getAddressMini4() {
        return addressMini4;
    }

    public void setAddressMini4(String addressMini4) {
        this.addressMini4 = addressMini4;
    }

    public String getAddressMini5() {
        return addressMini5;
    }

    public void setAddressMini5(String addressMini5) {
        this.addressMini5 = addressMini5;
    }

    public String getAddressMini6() {
        return addressMini6;
    }

    public void setAddressMini6(String addressMini6) {
        this.addressMini6 = addressMini6;
    }

    public String getAddressMini7() {
        return addressMini7;
    }

    public void setAddressMini7(String addressMini7) {
        this.addressMini7 = addressMini7;
    }

    public String getAddressMini8() {
        return addressMini8;
    }

    public void setAddressMini8(String addressMini8) {
        this.addressMini8 = addressMini8;
    }

    public String getAddressLarge1() {
        return addressLarge1;
    }

    public void setAddressLarge1(String addressLarge1) {
        this.addressLarge1 = addressLarge1;
    }

    public String getAddressLarge2() {
        return addressLarge2;
    }

    public void setAddressLarge2(String addressLarge2) {
        this.addressLarge2 = addressLarge2;
    }

    public String getAddressLarge3() {
        return addressLarge3;
    }

    public void setAddressLarge3(String addressLarge3) {
        this.addressLarge3 = addressLarge3;
    }

    public String getAddressLarge4() {
        return addressLarge4;
    }

    public void setAddressLarge4(String addressLarge4) {
        this.addressLarge4 = addressLarge4;
    }

    public String getAddressLarge5() {
        return addressLarge5;
    }

    public void setAddressLarge5(String addressLarge5) {
        this.addressLarge5 = addressLarge5;
    }

    public String getAddressLarge6() {
        return addressLarge6;
    }

    public void setAddressLarge6(String addressLarge6) {
        this.addressLarge6 = addressLarge6;
    }

    public String getAddressLarge7() {
        return addressLarge7;
    }

    public void setAddressLarge7(String addressLarge7) {
        this.addressLarge7 = addressLarge7;
    }

    public String getAddressLarge8() {
        return addressLarge8;
    }

    public void setAddressLarge8(String addressLarge8) {
        this.addressLarge8 = addressLarge8;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String prodName;
    private String material;
    private Integer length;
    private Integer width;
    private Integer height;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String address5;
    private String address6;
    private String address7;
    private String address8;
    @Transient
    private String addressMini1;
    @Transient
    private String addressMini2;
    @Transient
    private String addressMini3;
    @Transient
    private String addressMini4;
    @Transient
    private String addressMini5;
    @Transient
    private String addressMini6;
    @Transient
    private String addressMini7;
    @Transient
    private String addressMini8;
    @Transient
    private String addressMiddle1;
    @Transient
    private String addressMiddle2;
    @Transient
    private String addressMiddle3;
    @Transient
    private String addressMiddle4;
    @Transient
    private String addressMiddle5;
    @Transient
    private String addressMiddle6;
    @Transient
    private String addressMiddle7;
    @Transient
    private String addressMiddle8;
    @Transient
    private String addressLarge1;
    @Transient
    private String addressLarge2;
    @Transient
    private String addressLarge3;
    @Transient
    private String addressLarge4;
    @Transient
    private String addressLarge5;
    @Transient
    private String addressLarge6;
    @Transient
    private String addressLarge7;
    @Transient
    private String addressLarge8;

    private String uploadTime;
    private String prodDescribe;
    private Integer fatherId;
    @Transient
    private String fatherName;
    private Integer childId;
    @Transient
    private String childName;
    private Integer visitCount;

    public Product() {
    }

    public String getAddressMiddle1() {
        return addressMiddle1;
    }

    public void setAddressMiddle1(String addressMiddle1) {
        this.addressMiddle1 = addressMiddle1;
    }

    public String getAddressMiddle2() {
        return addressMiddle2;
    }

    public void setAddressMiddle2(String addressMiddle2) {
        this.addressMiddle2 = addressMiddle2;
    }

    public String getAddressMiddle3() {
        return addressMiddle3;
    }

    public void setAddressMiddle3(String addressMiddle3) {
        this.addressMiddle3 = addressMiddle3;
    }

    public String getAddressMiddle4() {
        return addressMiddle4;
    }

    public void setAddressMiddle4(String addressMiddle4) {
        this.addressMiddle4 = addressMiddle4;
    }

    public String getAddressMiddle5() {
        return addressMiddle5;
    }

    public void setAddressMiddle5(String addressMiddle5) {
        this.addressMiddle5 = addressMiddle5;
    }

    public String getAddressMiddle6() {
        return addressMiddle6;
    }

    public void setAddressMiddle6(String addressMiddle6) {
        this.addressMiddle6 = addressMiddle6;
    }

    public String getAddressMiddle7() {
        return addressMiddle7;
    }

    public void setAddressMiddle7(String addressMiddle7) {
        this.addressMiddle7 = addressMiddle7;
    }

    public String getAddressMiddle8() {
        return addressMiddle8;
    }

    public void setAddressMiddle8(String addressMiddle8) {
        this.addressMiddle8 = addressMiddle8;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
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

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    public String getAddress5() {
        return address5;
    }

    public void setAddress5(String address5) {
        this.address5 = address5;
    }

    public String getAddress6() {
        return address6;
    }

    public void setAddress6(String address6) {
        this.address6 = address6;
    }

    public String getAddress7() {
        return address7;
    }

    public void setAddress7(String address7) {
        this.address7 = address7;
    }

    public String getAddress8() {
        return address8;
    }

    public void setAddress8(String address8) {
        this.address8 = address8;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getProdDescribe() {
        return prodDescribe;
    }

    public void setProdDescribe(String prodDescribe) {
        this.prodDescribe = prodDescribe;
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

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", prodName='" + prodName + '\'' +
                ", material='" + material + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", address3='" + address3 + '\'' +
                ", address4='" + address4 + '\'' +
                ", address5='" + address5 + '\'' +
                ", address6='" + address6 + '\'' +
                ", address7='" + address7 + '\'' +
                ", address8='" + address8 + '\'' +
                ", uploadTime='" + uploadTime + '\'' +
                ", prodDescribe='" + prodDescribe + '\'' +
                ", fatherId=" + fatherId +
                ", fatherName='" + fatherName + '\'' +
                ", childId=" + childId +
                ", childName='" + childName + '\'' +
                ", visitCount=" + visitCount +
                '}';
    }
}
