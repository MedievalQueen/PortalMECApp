package com.example.hednisk.portalmec2;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by hednisk on 27/11/15.
 */
public class ListCellCollection extends ArrayAdapter<String> {

    private final Activity context;
    private final List<String> col;
    private final List<String> img_links;
    private final Integer[] imageId;

    public ListCellCollection(Activity context, List<String> refri, Integer[] imageId, List<String> img_links) {
        super(context, R.layout.button_coll, refri);
        this.context = context;
        this.col = refri;
        this.imageId = imageId;
        this.img_links = img_links;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.button_coll, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        ImageView imageV=(ImageView) rowView.findViewById(R.id.img);
        //TextView subtitle = (TextView) rowView.findViewById(R.id.id);
        txtTitle.setText(col.get(0));
        //imageV.setImageResource(imageId[position]);
        if(img_links!=null)
            new ImageLoadTask(img_links.get(position), imageV).execute();
        return rowView;
    }
    public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {
        private String url;
        private ImageView imageView;

        public ImageLoadTask(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            imageView.setImageBitmap(result);
        }
    }
}
