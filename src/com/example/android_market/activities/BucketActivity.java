package com.example.android_market.activities;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import com.example.android_market.R;
import com.example.android_market.adapters.BucketAdapter;
import com.example.android_market.other.BucketItem;
import com.example.android_market.dbhelp.DBHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BucketActivity extends Activity {

    private List<BucketItem> list = new ArrayList<BucketItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bucket);

        ListView listView = (ListView)findViewById(R.id.list_bucket);
        BucketAdapter adapter = new BucketAdapter(this, list);
        Button btnPayAll = (Button)findViewById(R.id.btn_pay_all);

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query(DBHelper.BUCKET_TABLE_NAME, null, null, null, null, null, null);
        if(c != null)
        {
            if(c.moveToFirst())
            {
                String str;
                do
                {
                    str = "";
                    for(String col : c.getColumnNames())
                        str = str.concat(c.getString(c.getColumnIndex(col)) + ":");
                    Log.d(DBHelper.TAG, "str = " + str);

                    String[] obj = str.split(":");
                    int[] real = new int[obj.length];
                    Log.d(DBHelper.TAG, "obj.length = "+obj.length + " obj = "+Arrays.toString(obj));

                    for(int i=0;i<real.length;i++)
                        real[i] = Integer.parseInt(obj[i]);
                    Log.d(DBHelper.TAG, "real = "+ Arrays.toString(real));

                    list.add(new BucketItem(real[0], real[1], real[2], real[3]));
                }
                while (c.moveToNext());
            }
            c.close();
        }
        dbHelper.close();
        listView.setAdapter(adapter);
    }
}
