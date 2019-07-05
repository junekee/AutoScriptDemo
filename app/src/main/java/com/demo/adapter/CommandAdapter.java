package com.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.demo.autoscript.R;

import java.util.ArrayList;
import java.util.LinkedList;

public class CommandAdapter extends BaseAdapter {
    private ArrayList<String> mData;
    private Context mContext;

    public CommandAdapter(ArrayList<String> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_command,viewGroup,false);
        TextView commandStr= (TextView) convertView.findViewById(R.id.item_text);
        commandStr.setText(mData.get(position));
        return convertView;
    }
}
