package com.example.hednisk.portalmec2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

abstract class BaseActivity extends AppCompatActivity {
    Point p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void isLogged(boolean b){
        //if logged include header, if not include header_notlogged
        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);

        if(b==true) {
            stub.setLayoutResource(R.layout.header);
            stub.inflate();
        }
        else  {
            stub.setLayoutResource(R.layout.header_notlogged);
            stub.inflate();
        }

        init_menu();
    }
    public void init_menu(){
        TextView t1 = (TextView) findViewById(R.id.linkbrasil);
        t1.setMovementMethod(LinkMovementMethod.getInstance());

        TextView t2 = (TextView) findViewById(R.id.linkacesso);
        t2.setMovementMethod(LinkMovementMethod.getInstance());

        TextView t3 = (TextView) findViewById(R.id.linkparticipe);
        t3.setMovementMethod(LinkMovementMethod.getInstance());

        TextView t4 = (TextView) findViewById(R.id.linkservices);
        t4.setMovementMethod(LinkMovementMethod.getInstance());

        TextView t5 = (TextView) findViewById(R.id.linklegislation);
        t5.setMovementMethod(LinkMovementMethod.getInstance());

        TextView t6 = (TextView) findViewById(R.id.linkcanais);
        t6.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void toMain(View v){
        Intent it=new Intent(this, MainActivity.class);
        startActivity(it);
        finish();
    }

    public void toMyCollections(View v){
        Intent it=new Intent(this, User_collections.class);
        startActivity(it);
        finish();
    }

    public void toSavedCollections(View v){
        Intent it=new Intent(this, Saved_collections.class);
        startActivity(it);
        finish();
    }

    public void float_menu(){
        Button btn_show = (Button) findViewById(R.id.btuser);
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //Open popup window
                if (p != null)
                    showPopup(BaseActivity.this, p);
            }
        });
    }
    private void showPopup(final AppCompatActivity context, Point p) {
        int popupWidth = 500;
        int popupHeight = 550;

        // Inflate the popup_layout.xml
        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.popup);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup, viewGroup);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(true);

        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
        int OFFSET_X = 30;
        int OFFSET_Y = 100;

        // Clear the default translucent background
        popup.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);

        // Getting a reference to Close button, and close the popup when clicked.
        Button close = (Button) layout.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_base, menu);
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
