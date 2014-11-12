package com.example.android_market.other;


public interface TablesInfo {
    public static final String DB_NAME = "my_shop";

    // goods
    public static final String GOODS_TABLE_NAME = "table_goods";
    public static final String GOODS_COL_ID = "id";
    public static final String GOODS_COL_NAME = "_name";
    public static final String GOODS_COL_PIC = "pic_id";
    public static final String GOODS_COL_DESC = "description";
    public static final String GOODS_COL_PRICE = "price";
    public static final String GOODS_COL_MANUFACTURER = "manufacturer";

    // bucket
    public static final String BUCKET_TABLE_NAME = "table_bucket";
    public static final String BUCKET_COL_ID = GOODS_COL_ID;
    public static final String BUCKET_COL_GOOD_ID = "good_id";
    public static final String BUCKET_COL_PRICE = GOODS_COL_PRICE;
    public static final String BUCKET_COL_COUNT = "count";

    // orders

    public static final String TAG = "log";

}
