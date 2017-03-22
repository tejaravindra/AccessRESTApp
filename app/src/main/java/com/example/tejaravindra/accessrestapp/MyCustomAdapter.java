package com.example.tejaravindra.accessrestapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by tejaravindra on 2/27/17.
 */

public class MyCustomAdapter extends ArrayAdapter {
    Context context;
    List le;


    public MyCustomAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.context=context;
        this.le=objects;
    }

    @Override
    public int getCount() {
        return  le.size();
    }

    @Override
    public Object getItem(int position) {
        return le.get(position);
    }

    @Override
    public long getItemId(int position) {
        return le.indexOf(getItem(position));
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li= (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        RelativeLayout parentViewById=(RelativeLayout) (parent.findViewById(R.id.activity_main) );
        if(null==convertView)
            convertView = li.inflate(R.layout.list_layout, parentViewById);

        ListElements newle = (ListElements)le.get(position);


        ((TextView) convertView.findViewById(R.id.name)).setText(newle.getName());
        ((TextView) convertView.findViewById(R.id.phone)).setText(newle.getPhoneNumber());
        ((ImageView) convertView.findViewById(R.id.myImage)).setImageBitmap(newle.getImageURL());
//        ((TextView) convertView.findViewById(R.id.add)).setText((String) newle.get("add"));

        return convertView;
    }


}
