package com.example.hednisk.portalmec2;

import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.util.ArrayList;
import java.util.List;

public class Saved_collections extends BaseActivity {

    List<String> col=new ArrayList<String>();
    Integer[] imageId ={R.drawable.colecao, R.drawable.colecao};
    BootstrapButton bt_menu_downloaded;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_collections);
        isLogged();

        bt_menu_downloaded=(BootstrapButton)findViewById(R.id.bt_downloaded);
        bt_menu_downloaded.setTypeface(null, Typeface.BOLD);

        //retrieve from phone and insert in listView
        ListCellSaved adapter= new ListCellSaved(Saved_collections.this, col, imageId);
        list=(ListView)findViewById(R.id.listSaved);
        list.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_saved_collections, menu);
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
