package com.example.hednisk.portalmec2;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;

public class MainActivity extends BaseActivity {
    BootstrapButton bt_menu_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isLogged(false);
    //   LinearLayout bt_menu_ma=(LinearLayout)findViewById(R.id.header);
//bt_menu_ma.is
        bt_menu_main=(BootstrapButton)findViewById(R.id.bt_main);
        bt_menu_main.setTypeface(null, Typeface.BOLD);
/*
//SETAR THUMBANIL DE COLEÇÕES
        ImageView thumbnail_mini = (ImageView)findViewById(R.id.thumb_coll);
        Bitmap bmThumbnail;
        bmThumbnail = ThumbnailUtils.createVideoThumbnail(filepath,  MediaStore.Images.Thumbnails.MICRO_KIND);
        thumbnail_mini.setImageBitmap(bmThumbnail);
  */

    }

    public void login(View v){
        Intent it=new Intent(this, Login.class);
        startActivity(it);
        finish();
    }

}
