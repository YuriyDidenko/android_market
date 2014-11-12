package com.example.android_market.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.android_market.R;
import com.example.android_market.other.Good;
import java.util.List;

public class GoodsActivityListAdapter  extends ArrayAdapter<Good> {
    private final Context context;
    private final List<Good> list;

    public GoodsActivityListAdapter(Context context, List<Good> list) {
        super(context, R.layout.goods_list_item, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.goods_list_item, parent, false);

        TextView tv = (TextView)row.findViewById(R.id.tv_list);
        ImageView img = (ImageView)row.findViewById(R.id.img_list);

        tv.setText(list.get(position).getName());
        try {
            img.setImageResource(list.get(position).getPicture());
        }
        catch (Exception e){
            img.setImageResource(R.drawable.ic_launcher);
        }
        return row;
    }
}
