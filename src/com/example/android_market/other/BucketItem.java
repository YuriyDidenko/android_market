package com.example.android_market.other;


public class BucketItem {
    private int id;
    private int goodId;
    private int count;
    private int price;

    public BucketItem(int id, int goodId, int price, int count) {
        this.id = id;
        this.goodId = goodId;
        this.price = price;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public int getGoodId() {
        return goodId;
    }

    public int getCount() {
        return count;
    }

    public int getPrice() {
        return price;
    }
}
