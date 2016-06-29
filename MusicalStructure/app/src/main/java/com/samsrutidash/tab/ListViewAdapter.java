package com.samsrutidash.tab;

import android.widget.BaseAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {
    private Context mContext;
    private final String[] songTitle;
    private final int[] Imageid;
    public ListViewAdapter(Context c, String[] title, int[] Imageid) {
        mContext = c;
        this.Imageid = Imageid;
        this.songTitle = title;
    }
    @Override
    public int getCount() { // TODO Auto-generated method stub

        return songTitle.length;
    }
    @Override
    public Object getItem(int position) { // TODO Auto-generated method stub

        return null;
    }
    @Override public long getItemId(int position) { // TODO Auto-generated method stub

        return 0;
    }
    @Override public View getView(int position, View convertView, ViewGroup parent) { // TODO Auto-generated method stub

        View list;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            list = new View(mContext);
            list = inflater.inflate(R.layout.listview_layout, null);
            TextView textView = (TextView) list.findViewById(R.id.list_text);
            ImageView imageView = (ImageView) list.findViewById(R.id.list_image);
            textView.setText(songTitle[position]);
            imageView.setImageResource(Imageid[position]);


        } else {
            list = (View) convertView;
        }
        return list;
    }
}