package com.example.project.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project.Card;
import com.example.project.Module;
import com.example.project.R;

import java.util.ArrayList;
import java.util.Map;

public class ChooseModuleAdapter extends BaseAdapter {
    private final ArrayList<Module> mData;

    public ChooseModuleAdapter(ArrayList<Module> mData) {
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Map.Entry<String, String> getItem(int position) {
        return (Map.Entry) mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO implement you own logic with ID
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View result;

        if (convertView == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.chose_element, parent, false);
        } else {
            result = convertView;
        }



        ((TextView) result.findViewById(R.id.textView5)).setText(mData.get(position).getName());

        return result;
    }
}
