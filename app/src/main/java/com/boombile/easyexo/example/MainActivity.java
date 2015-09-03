package com.boombile.easyexo.example;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.boombile.easyexo.ui.PlayerActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playButtonClicked(View view) {
        Intent intent = new Intent(this, PlayerActivity.class)
                .setData(Uri.parse("http://playready.directtaps.net/smoothstreaming/SSWSS720H264/SuperSpeedway_720.ism"))
                .putExtra(PlayerActivity.CONTENT_ID_EXTRA, "PlayWithSS")
                .putExtra(PlayerActivity.CONTENT_TYPE_EXTRA, PlayerActivity.TYPE_SS);
        startActivity(intent);
    }

}
