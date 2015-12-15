package com.example.hednisk.portalmec2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Collection_page extends BaseActivity {

    private Operations ops;
    ListView list;
    TextView coll_n;

    //
    List<String> col=new ArrayList<String>();
    Integer[] imageId ={R.drawable.colecao, R.drawable.colecao};
    List<String> img_links = new ArrayList<>();
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_page);
        isLogged();

        Button btDown=(Button)findViewById(R.id.btDownload);
        btDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            /*      ops=new Operations(this);
                    try {
                        ops.open();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
            */

                //saveIMG();
            }
        });

        list=(ListView)findViewById(R.id.list_files);
        coll_n=(TextView)findViewById(R.id.coll_name);
        //get data from selected collection
        Bundle b = getIntent().getExtras();
        String title = b.getString("title");
        coll_n.setText("Arquivos da coleção " + title);
        //get files from webservice passing id of collection

        new ReadObjectsJSON().execute(
                "http://private-f95cb-portalm2.apiary-mock.com/objects");
    }
    public void downloadCollection(View v){
        //get name and files of collection then insert, for now its just name
        Collection coll= ops.downloadCollection(coll_n.getText().toString());
       // if(coll.getId().) {
            Toast.makeText(getBaseContext(), coll.getName(),Toast.LENGTH_SHORT).show();
       // }
    }
/*
    public void insertImage(Drawable dbDrawable, String imageId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(IMAGE_ID, imageId);//col name
        Bitmap bitmap = ((BitmapDrawable)dbDrawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        values.put(IMAGE_BITMAP, stream.toByteArray());
        db.insert(TABLE_IMAGE, null, values);
        db.close();
    }
*/

    private class ReadObjectsJSON extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            return readJSONFeed(urls[0]);
        }

        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonAray = new JSONArray(jsonObject.getString("result"));

                for (int i = 0; i < jsonAray.length(); i++) {
                    JSONObject jsonObj = jsonAray.getJSONObject(i);
                    col.add(jsonObj.getString("name"));
                    // col.add(jsonObj.getString("type"));

                    JSONArray bitstreams = new JSONArray(jsonObj.getString("bitstreams"));
                    for (int j = 0; j < bitstreams.length(); j++) {
                        JSONObject obj_bistr = bitstreams.getJSONObject(j);
                        if (obj_bistr.getString("bundleName").equals("THUMBNAIL"))
                            img_links.add("https://mecdb3.c3sl.ufpr.br:8443" + obj_bistr.getString("link"));
                        //   new ImageLoadTask("http://images.clipartpanda.com/clipart-star-dcrpxazc9.jpeg", im).execute();
                    }
                }

                ListCellFile adapter= new ListCellFile(Collection_page.this, col, imageId, img_links);
                list=(ListView)findViewById(R.id.list_files);
                list.setAdapter(adapter);

            } catch (Exception e) {
                Log.d("ReadObjectsJSON", e.getLocalizedMessage());
            }
        }
    }

    public void open(View v, String s){

    }

    public void download(View v){

    }

}
