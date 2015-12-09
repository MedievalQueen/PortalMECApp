package com.example.hednisk.portalmec2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Collection_page extends BaseActivity {

    private Operations ops;
    ListView list;
    TextView coll_n;

    //temporario
    List<String> col=new ArrayList<String>();
    Integer[] imageId ={R.drawable.colecao, R.drawable.colecao};
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_page);
        isLogged(true);

        Button btDown=(Button)findViewById(R.id.btDownload);
        btDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        new ReadWeatherJSONFeedTask().execute(
                "http://private-f95cb-portalm2.apiary-mock.com/objects");

/*
        ops=new Operations(this);
        try {
            ops.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
    }
    public void downloadCollection(View v){
        //get name and files of collection then insert, for now its just name
        ops.downloadCollection(coll_n.getText().toString());
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


    public String readJSONFeed(String URL) {
        StringBuilder stringBuilder = new StringBuilder();
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URL);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                inputStream.close();
            } else {
                Log.d("JSON", "Failed to download file");
            }
        } catch (Exception e) {
            Log.d("readJSONFeed", e.getLocalizedMessage());
        }
        return stringBuilder.toString();
    }

    private class ReadWeatherJSONFeedTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            return readJSONFeed(urls[0]);
        }

        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonAray = new JSONArray(jsonObject.getString("result"));
                JSONObject jsonObj = jsonAray.getJSONObject(0);
                col.add(jsonObj.getString("name"));
               // col.add(jsonObj.getString("type"));

                JSONArray bitstreams=new JSONArray(jsonObj.getString("bitstreams"));
                JSONObject obj_bistr= bitstreams.getJSONObject(0);
                obj_bistr.getString("link");

                Toast.makeText(getBaseContext(),
                        jsonObj.getString("name"),
                        Toast.LENGTH_SHORT).show();

                ListCell adapter= new ListCell(Collection_page.this, col, imageId);
                list=(ListView)findViewById(R.id.list_files);
                list.setAdapter(adapter);
              /*  list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TextView tv = (TextView) view.findViewById(R.id.txt);
                        String title = tv.getText().toString();
                        //go to collection page
                        Intent it = new Intent(Collection_page.this, Collection_page.class);
                        it.putExtra("title", title);
                        startActivity(it);
                        finish();
                    }
                });*/

            } catch (Exception e) {
                Log.d("ReadWeatherJSONFeedTask", e.getLocalizedMessage());
            }
        }
    }


}
