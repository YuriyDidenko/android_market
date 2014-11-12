package com.example.android_market.activities;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.example.android_market.R;
import com.example.android_market.dbhelp.DBHelper;
import com.example.android_market.other.Good;

public class DetailsGoodActivity extends Activity {
    private Good selectedGood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_good);

        ImageView image = (ImageView)findViewById(R.id.image_of_good);
        TextView details = (TextView)findViewById(R.id.tv_details),
                 price = (TextView)findViewById(R.id.tv_price);
        final EditText etCount = (EditText)findViewById(R.id.et_count);
        Button btnBuy = (Button)findViewById(R.id.btn_buy);

        selectedGood = (Good)getIntent().getSerializableExtra("selected item");
        try {
            image.setImageResource(selectedGood.getPicture());
        }
        catch (Exception e) {
            image.setImageResource(R.drawable.ic_launcher);
        }
        details.setText(selectedGood.getDescription());
        price.setText(selectedGood.getPrice());

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //добавление в таблицу Bucket
                DBHelper dbHelper = new DBHelper(getApplicationContext());
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query(DBHelper.BUCKET_TABLE_NAME, null, null, null, null, null, null);
                ContentValues cv = new ContentValues();

                int id = cursor.getCount()+1; // ибо мы берем текущее число кортежей +1
                int good_id = selectedGood.getId();
                int count = Integer.parseInt(etCount.getText().toString());
                // такая ебала, потому что нам нужно сделать типа price * count, при этом price вида - "число+$"
                // мы отрезаем последний символ и парсим в int
                int price =  count *
                             Integer.parseInt(selectedGood.getPrice().substring(0, selectedGood.getPrice().length()-1));
                cv.put(DBHelper.BUCKET_COL_ID, id);
                cv.put(DBHelper.BUCKET_COL_GOOD_ID, good_id);
                cv.put(DBHelper.BUCKET_COL_PRICE, price);
                cv.put(DBHelper.BUCKET_COL_COUNT, count);
                db.insert(DBHelper.BUCKET_TABLE_NAME, null, cv);
                Log.d(DBHelper.TAG, id+" "+" "+good_id+" "+price+" "+count);
                cursor.close();
                dbHelper.close();
                finish();
            }
        });
    }
}
