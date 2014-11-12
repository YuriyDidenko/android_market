package com.example.android_market.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.example.android_market.R;
import com.example.android_market.adapters.GoodsActivityListAdapter;
import com.example.android_market.dbhelp.DBHelper;
import com.example.android_market.other.Good;

import java.util.ArrayList;
import java.util.List;

public class GoodsActivity extends Activity {

    private String[] spinnerData;
    private List<Good> list = new ArrayList<Good>();
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods);

        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();

        final ListView listView = (ListView)findViewById(R.id.list_goods);
        final GoodsActivityListAdapter listAdapter = new GoodsActivityListAdapter(this, list);
        Spinner spin = (Spinner)findViewById(R.id.spinner);
        spinnerData = getSpinnerData();
        ArrayAdapter<String> sAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.goods_spinner_item, spinnerData);
        sAdapter.setDropDownViewResource(R.layout.goods_spinner_item);
        spin.setAdapter(sAdapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // select */manufacturer from table_goods;
                showItems(spinnerData[position]);
                listView.setAdapter(listAdapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetailsGoodActivity.class);
                intent.putExtra("selected item", list.get(position));
                startActivity(intent);
            }
        });
    }
    public void showItems(String manufacturer)
    {
        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        list.clear();
        Cursor cursor;
        if("All".equals(manufacturer))
            cursor = db.query(DBHelper.GOODS_TABLE_NAME, null, null, null, null, null, null);
        else
            cursor = db.query(DBHelper.GOODS_TABLE_NAME, null, DBHelper.GOODS_COL_MANUFACTURER + " = ?", new String[]{manufacturer}, null, null, null);
        if(cursor != null)
        {
            if (cursor.moveToFirst())
            {
                String str;
                do
                {
                    str = "";
                    for(String column : cursor.getColumnNames())
                        // получаем кортеж вида attr_1:attr_2:...:attr_n
                        str = str.concat(cursor.getString(cursor.getColumnIndex(column)) + ":");
                    //Log.d(DBHelper.TAG, str);
                    // формируем массив данных - {attr_1, attr_2, ..., attr_n};
                    String[] obj = str.split(":");
                    // теперь эту ебалу надо превратить в новый объект, учитывая, что первый элемент - это id, а в Good - это name
                    // нужно переделывать класс Good либо делать другой, чтобы  в точности совпадал с полями в таблице.
                    list.add(new Good(Integer.parseInt(obj[0]), obj[1], Integer.parseInt(obj[2]), obj[3], obj[4], obj[5]));
                }
                while (cursor.moveToNext());
            }
            cursor.close();
        }
        else
            Log.d(DBHelper.TAG, "Cursor is null");
        dbHelper.close();
    }
    private String[] getSpinnerData()
    {
        ArrayList<String> data = new ArrayList<String>();
        data.add("All");
        Cursor cursor = db.query(true, DBHelper.GOODS_TABLE_NAME, new String[]{DBHelper.GOODS_COL_MANUFACTURER},
                                 null, null, null, null, DBHelper.GOODS_COL_MANUFACTURER, null);
        if(cursor != null)
        {
            if(cursor.moveToFirst())
                do
                    data.add(cursor.getString(0));
                while (cursor.moveToNext());
            cursor.close();
        }
        dbHelper.close();
        String[] ret = new String[data.size()];
        for(int i=0;i<ret.length;i++)
            ret[i] = data.get(i);
        return ret;
    }
}












