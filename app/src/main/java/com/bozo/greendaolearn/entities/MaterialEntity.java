package com.bozo.greendaolearn.entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

@Entity(nameInDb = "material")
public class MaterialEntity {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "name")
    private String name;

    @Property(nameInDb = "price")
    private double price;

    @Property(nameInDb = "number")
    private int number;

    @Property(nameInDb = "store")
    private String store;

    @Generated(hash = 1709040430)
    public MaterialEntity(Long id, String name, double price, int number,
            String store) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.number = number;
        this.store = store;
    }

    @Generated(hash = 184609841)
    public MaterialEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
