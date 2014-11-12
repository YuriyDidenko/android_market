package com.example.android_market.dbhelp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.android_market.R;
import com.example.android_market.other.TablesInfo;

public class DBHelper extends SQLiteOpenHelper implements TablesInfo
{
    private Context context;
    private int[] id = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};
    private String[] name = {"iPhone","iPhone 3G","iPhone 3GS","iPhone 4","iPhone 4S","iPhone 5","iPhone 5c",
                             "iPhone 5S","Sony Xperia Z1","Sony Xperia Z2","Sony Xperia Z3","Galaxy Win","Galaxy S3 Mini",
                             "Galaxy S4 Mini","Galaxy S3 Neo","Google Nexus 5","LG G3 32GB","L60 Dual X135","G3s Dual D724"};
    private int[] pic = {R.drawable.iphone, R.drawable.iphone_3g,R.drawable.iphone_3gs, R.drawable.iphone_4, R.drawable.iphone_4s,
                         R.drawable.iphone_5,R.drawable.iphone_5c,R.drawable.iphone_5s,R.drawable.xperia_z1,R.drawable.xperia_z2,
                         R.drawable.xperia_z3, R.drawable.galaxy_win, R.drawable.galaxy_s3m, R.drawable.galaxy_s4, R.drawable.galaxy_s3n,
                         R.drawable.lg_nexus_5, R.drawable.lg_g3, R.drawable.lg_l60, R.drawable.lg_g3s};
    private int[] desc = {R.string.iphone, R.string.iphone_3g, R.string.iphone_3gs, R.string.iphone_4, R.string.iphone_4s,
                          R.string.iphone_5,R.string.iphone_5c,R.string.iphone_5s,R.string.sony_z1, R.string.sony_z2, R.string.sony_z3,
                          R.string.galaxy_win,R.string.galaxy_s3m,R.string.galaxy_s4,R.string.galaxy_s3n,R.string.lg_nexus_5,
                          R.string.lg_g3, R.string.lg_l60, R.string.lg_g3s};
    private int[] price = {100,200,300,400,500,600,650,700,450,600,808,167,153,300,276,385,653,100,270};
    private String[] manufacturer = {"Apple","Apple","Apple","Apple","Apple","Apple","Apple","Apple","Sony","Sony","Sony",
                                     "Samsung","Samsung","Samsung","Samsung","LG","LG","LG","LG"};

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreateDatabase");
        createTableGoods(db);
        createTableBucket(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    private void createTableGoods(SQLiteDatabase db)
    {
        String sql = "create table "+ GOODS_TABLE_NAME +"(" +
                GOODS_COL_ID + " int primary key," +
                GOODS_COL_NAME + " char(25)," +
                GOODS_COL_PIC + " int," +
                GOODS_COL_DESC + " varchar," +
                GOODS_COL_PRICE + " char(10)," +
                GOODS_COL_MANUFACTURER + " char(20)" +
                ");";
        db.execSQL(sql);
        ContentValues cv = new ContentValues();
        for(int i=0;i<id.length;i++)
        {
            cv.put(GOODS_COL_ID, id[i]);
            cv.put(GOODS_COL_NAME, name[i]);
            cv.put(GOODS_COL_PIC, pic[i]);
            cv.put(GOODS_COL_DESC, context.getResources().getString(desc[i]));
            cv.put(GOODS_COL_PRICE, price[i]+"$");
            cv.put(GOODS_COL_MANUFACTURER, manufacturer[i]);
            db.insert(GOODS_TABLE_NAME, null, cv);
        }
    }
    private void createTableBucket(SQLiteDatabase db)
    {
        String sql = "create table " + BUCKET_TABLE_NAME + "(" +
                     BUCKET_COL_ID + " int primary key," +
                     BUCKET_COL_GOOD_ID + " int references " + GOODS_TABLE_NAME + "(" + GOODS_COL_ID + ")," +
                     BUCKET_COL_PRICE + " int, " +
                     BUCKET_COL_COUNT + " int" +
                     ");";
        db.execSQL(sql);
    }
    private void createTableOrders(SQLiteDatabase db){

    }
}
