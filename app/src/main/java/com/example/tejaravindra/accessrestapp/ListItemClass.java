package com.example.tejaravindra.accessrestapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.health.SystemHealthManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by tejaravindra on 3/13/17.
 */

public class ListItemClass extends AppCompatActivity {

    public static final int MY_URI = 1<<1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_layout);
        Toolbar toolbar=(Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        System.out.println("uri is ---- ");
    }

    public void goGoogleClick(View view){


//        Intent sendIntent = new Intent();
//        sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
//        sendIntent.setType("text/plain");
//        startActivity(sendIntent);

        Intent intent=new Intent(Intent.ACTION_DEFAULT, Uri.parse("https://www.facebook.com"));
        startActivity(intent);
    }
}
