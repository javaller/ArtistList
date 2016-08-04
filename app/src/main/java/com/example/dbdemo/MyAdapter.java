package com.example.dbdemo;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Student1 on 21.04.2016.
 */
public class MyAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private Cursor cursor;
    private int nameIdx;

    public MyAdapter(Context context) {
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
        if (cursor != null) {
            nameIdx = cursor.getColumnIndex("name");
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (cursor == null) return 0;
        return cursor.getCount();
    }

    @Override
    public String getItem(int position) {
        cursor.moveToPosition(position);
        return cursor.getString(nameIdx);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        MyHolder holder;
        if (convertView == null) {
            Log.d("happy", "new " + position);

            view = inflater.inflate(
                    R.layout.list_item,
                    parent,
                    false);

            TextView itemName = (TextView)view.findViewById(R.id.item_name);

            holder = new MyHolder(itemName);
            view.setTag(holder); // положили холдер в карман
        } else {
            Log.d("happy", "reused " + position);

            view = convertView;
            holder = (MyHolder)view.getTag(); // достали холдер из кармана
        }

        holder.itemName.setText("" + getItem(position));

        return view;
    }

    public static class MyHolder {
        // Alt-Ins
        public MyHolder(TextView itemName) {
            this.itemName = itemName;
        }

        public TextView itemName;
    }
}
