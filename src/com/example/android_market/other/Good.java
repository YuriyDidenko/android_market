package com.example.android_market.other;

import java.io.Serializable;

public class Good implements Serializable {
    private int id;
    private String name;
    private int picture;
    private String description;
    private String price;
    private String manufacturer;

    public Good(int id, String name, int picture, String description, String price, String manufacturer) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.description = description;
        this.price = price;
        this.manufacturer = manufacturer;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPicture() {
        return picture;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getManufacturer() {
        return manufacturer;
    }
}
