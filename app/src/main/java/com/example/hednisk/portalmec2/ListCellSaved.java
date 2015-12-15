package com.example.hednisk.portalmec2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hednisk on 27/11/15.
 */
public class ListCellSaved extends ArrayAdapter<String> {

    private final Activity context;
    //private final String[] coll;
    private final List<String> col;
    private final Integer[] imageId;

    public ListCellSaved(Activity context, List<String> refri, Integer[] imageId) {
        super(context, R.layout.item_downloaded, refri);
        this.context = context;
        this.col = refri;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.item_downloaded, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        ImageView imageV=(ImageView) rowView.findViewById(R.id.img);
        Button open=(Button) rowView.findViewById(R.id.open);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open file
            }
        });

        Button del=(Button) rowView.findViewById(R.id.delete);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //calls for Operations to delete from sqlite
            }
        });

        txtTitle.setText(col.get(0));
        imageV.setImageResource(imageId[position]);
        return rowView;
    }
}
