package com.example.hednisk.portalmec2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Login extends BaseActivity {
   // Session s;
/*
    private class ReadWeatherJSONFeedTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            return readJSONFeed(urls[0]);
        }

        protected void onPostExecute(String result) {
            try {
                JSONArray jsonAray = new JSONArray(result);
                JSONObject jsonObject = jsonAray.getJSONObject(0);
              //  JSONObject jsonObject = new JSONObject(result);

                Toast.makeText(getBaseContext(),
                        jsonObject.getString("Collection"),
                        Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                Log.d("ReadWeatherJSONFeedTask", e.getLocalizedMessage());
            }
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       isLogged();
      //  ImageView im=(ImageView)findViewById(R.id.im);
    }

    public void logar(View v){

      /*  new ReadWeatherJSONFeedTask().execute(
                "http://private-b1077-portalm.apiary-mock.com/collections");*/

        TextView email=(TextView)findViewById(R.id.email);
        TextView pass=(TextView)findViewById(R.id.pass);

        //if email and pass correct
        s.setusername(email.getText().toString());

        Intent it=new Intent(this, User_collections.class);
        startActivity(it);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
