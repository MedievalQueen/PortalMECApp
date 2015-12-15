package com.example.hednisk.portalmec2;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.beardedhen.androidbootstrap.BootstrapButton;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class User_collections extends BaseActivity {

    BootstrapButton bt_menu_user;
    private Operations ops;
    ListView list;

     List<String> col=new ArrayList<String>();
    Integer[] imageId ={R.drawable.colecao, R.drawable.colecao};
    List<String> filesIds=new ArrayList<String>();//rids list to pass in 'select from LearningObject where @rid=?' when getting the files
    List<String> img_links = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_collections);
       isLogged();

        bt_menu_user=(BootstrapButton)findViewById(R.id.bt_mycollections);
        bt_menu_user.setTypeface(null, Typeface.BOLD);

        //pega todas coleções de usuário
       // List values=ops.getAllCollections();
        new CollectionsJSONTask().execute(
                "http://private-f95cb-portalm2.apiary-mock.com/collections");
    }

    private class CollectionsJSONTask extends AsyncTask<String, Void, String> {
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
                    /*JSONArray files = new JSONArray(jsonOb.getString("learning_objects"));
                    for ( i = 0; i < files.length(); i++) {
                        JSONObject jsonO = files.getJSONObject(i);
                        filesIds.add(jsonO.toString());
                    }*/
                }
                ListCellCollection adapter= new ListCellCollection(User_collections.this, col, imageId, null);
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
                Log.d("CollectionsJSONTask", e.getLocalizedMessage());
            }
        }
    }

}
