package com.example.project.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project.Folder;
import com.example.project.R;
import java.util.ArrayList;
import java.util.Map;

public class FoldersAdapter extends BaseAdapter {
    private final ArrayList<Folder> mData;

    public FoldersAdapter(ArrayList<Folder> mData) {
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
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        } else {
            result = convertView;
        }



        ((TextView) result.findViewById(R.id.label)).setText(mData.get(position).getName());
        ((TextView) result.findViewById(R.id.label1)).setText("");

        return result;
    }
}
