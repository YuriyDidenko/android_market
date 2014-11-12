package com.example.android_market.adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.android_market.other.BucketItem;
import com.example.android_market.dbhelp.DBHelper;
import com.example.android_market.R;

import java.util.List;

public class BucketAdapter extends ArrayAdapter<BucketItem> {
    private final Context context;
    private final List<BucketItem> items;
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private Cursor c;
    private Button btnBuy;

    public BucketAdapter(Context context, List<BucketItem> items) {
        super(context, R.layout.bucket_list_item, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.bucket_list_item, parent, false);

        TextView tvName = (TextView)row.findViewById(R.id.tv_bucket_item_name),
                 tvCount = (TextView)row.findViewById(R.id.tv_bucket_item_count),
                 tvPrice = (TextView)row.findViewById(R.id.tv_bucket_item_price);
        btnBuy = (Button)row.findViewById(R.id.btn_bucket_item_buy);
        Button btnDelete = (Button)row.findViewById(R.id.btn_bucket_item_delete);

        tvName.setText(getName(items.get(position).getGoodId()));
        tvCount.setText(items.get(position).getCount()+"");
        tvPrice.setText(items.get(position).getPrice()+"");

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "well done", Toast.LENGTH_SHORT).show();
            }
        });
        final int currentPosition = position;
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
                deleteRow(currentPosition);
            }
        });
        return row;
    }
    private String getName(int goodId) {
        String name = null;
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        c = db.query(DBHelper.GOODS_TABLE_NAME, new String[]{DBHelper.GOODS_COL_NAME}, DBHelper.GOODS_COL_ID + " = ?", new String[]{goodId+""}, null, null, null );
        if(c != null)
        {
            if(c.moveToFirst())
                name = c.getString(0);
            c.close();
        }
        dbHelper.close();
        return name;
    }
    private void deleteRow(int rowID)
    {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        // удаляем из базы
        db.delete(DBHelper.BUCKET_TABLE_NAME, "id = " + items.get(rowID).getId(), null);
        Log.d(DBHelper.TAG, "deleted row with\nid = "+ items.get(rowID).getId()+"\ngood_id = "+items.get(rowID).getGoodId()+
                            "\nprice = "+items.get(rowID).getPrice() + "\ncount = "+items.get(rowID).getCount());
        // удаляем из списка
        items.remove(items.get(rowID));
        // обновляем адаптер
        notifyDataSetChanged();
    }
}
