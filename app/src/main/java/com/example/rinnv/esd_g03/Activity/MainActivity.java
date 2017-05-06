package com.example.rinnv.esd_g03.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.rinnv.esd_g03.Models.Phonetic;
import com.example.rinnv.esd_g03.R;
import com.example.rinnv.esd_g03.Ultility.Config;
import com.example.rinnv.esd_g03.Ultility.LogUtility;
import com.example.rinnv.esd_g03.Ultility.SQLiteDataController;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static SQLiteDataController db;
    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new SQLiteDataController(MainActivity.this.getApplicationContext());
        createDb();
        String time = DateFormat.getDateTimeInstance().format(new Date()).toString() ;
        new LogUtility().writeLog("Open app in: "+ time,MainActivity.this);

        ArrayList<Phonetic> phonetics = db.getListPhonetic("2");

        //lay phonetic va pronounce cua phonetic thu nhat
        Phonetic phonetic1 = phonetics.get(0);
        String phonetic1Title = phonetic1.getPhonetic();
        Button couple1 = (Button) findViewById(R.id.button1);
        Button couple2 = (Button) findViewById(R.id.button2);
        couple2.setText("/"+phonetic1Title+"/"+" and /u:/");
        couple1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TabActivity.class);
                Config.PHEONIC_GROUP_ID ="1";
                startActivity(intent);
            }
        });

        couple2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*ArrayList<Phonetic> wordArrayList = db.getListPhonetic("1");
                // co khi no ko lay dc phan tu nao nen no ko in
                // ok
                Log.d(TAG, "onClick: So luong example dc tim thay "+wordArrayList.size());
                for (Phonetic phonetic : wordArrayList) {
                    // ghi ra file log vs tag la onClick, neu muon xem lai gia tri log thi vao tab
                    // android monitor nhap tag
                    // binh thuong ma
                    //luc nay khong chay

                    Log.d(TAG, "onClick: " + phonetic.getLink());
                }*/

                Intent intent = new Intent(MainActivity.this, TabActivity.class);
                Config.PHEONIC_GROUP_ID ="2";
                startActivity(intent);

                /*Intent intent = new Intent(MainActivity.this,YouTubeActivity.class);
                intent.putExtra(Config.YOUTUBE_LINK,"fhWaJi1Hsfo");
                startActivity(intent);*/
            }
        });

    }

    private void createDb() {
        SQLiteDataController sql = new SQLiteDataController(getApplicationContext());
        try {
            sql.isCreatedDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        new LogUtility().writeLog("Resume app at: "+ DateFormat.getDateTimeInstance().format(new Date()).toString(),MainActivity.this);
    }
}
