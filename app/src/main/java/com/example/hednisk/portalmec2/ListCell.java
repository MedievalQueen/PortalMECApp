package com.example.hednisk.portalmec2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hednisk on 27/11/15.
 */
public class ListCell extends ArrayAdapter<String> {

    private final Activity context;
    //private final String[] coll;
    private final List<String> col;
    private final Integer[] imageId;

    public ListCell(Activity context,  List<String> refri, Integer[] imageId) {
        super(context, R.layout.button_coll, refri);
        this.context = context;
        this.col = refri;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.button_coll, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        ImageView imageV=(ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(col.get(0));
        imageV.setImageResource(imageId[position]);
        return rowView;
    }
}
