package com.example.hednisk.portalmec2;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;

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

public class User_collections extends BaseActivity {

    BootstrapButton bt_menu_user;
    private Operations ops;
    ListView list;

    //temporario
    String[] coll ={"titulo 1", "Titulo 2"};
     List<String> col=new ArrayList<String>();
    Integer[] imageId ={R.drawable.colecao, R.drawable.colecao};
    List<String> filesIds=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_collections);
        isLogged(true);
        float_menu();

        bt_menu_user=(BootstrapButton)findViewById(R.id.bt_mycollections);
        bt_menu_user.setTypeface(null, Typeface.BOLD);

        //pega todas coleções de usuário
       // List values=ops.getAllCollections();
        new PortalMecJSONTask().execute(
                "http://private-f95cb-portalm2.apiary-mock.com/collections");


    }

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

    private class PortalMecJSONTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            return readJSONFeed(urls[0]);
        }

        protected void onPostExecute(String result) {
            try {

                JSONObject json = new JSONObject(result);
                JSONArray jsonAray = new JSONArray(json.getString("result"));
                for (int i = 0; i < jsonAray.length(); i++) {
                    JSONObject jsonOb =  jsonAray.getJSONObject(i);
                    col.add(jsonOb.getString("name"));
 /*  JSONArray files = new JSONArray(jsonObject.getString("learning_objects"));

                    for ( i = 0; i < files.length(); i++) {
                        JSONObject jsonO = files.getJSONObject(i);
                        filesIds.add(jsonO.toString());//

                    }

                          }*/
                }
                ListCell adapter= new ListCell(User_collections.this, col, imageId);
                list=(ListView)findViewById(R.id.list);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TextView tv = (TextView) view.findViewById(R.id.txt);
                        String title = tv.getText().toString();
                        //go to collection page
                        Intent it = new Intent(User_collections.this, Collection_page.class);
                        it.putExtra("title", title);
                        startActivity(it);
                        finish();
                    }
                });

            } catch (Exception e) {
                Log.d("PortalMecJSONTask", e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        int[] location = new int[2];
        Button button = (Button) findViewById(R.id.btuser);

        // Get the x, y location and store it in the location[] array
        // location[0] = x, location[1] = y.
        button.getLocationOnScreen(location);

        //Initialize the Point with x, and y positions
        p = new Point();
        p.x = location[0];
        p.y = location[1];
    }


}
