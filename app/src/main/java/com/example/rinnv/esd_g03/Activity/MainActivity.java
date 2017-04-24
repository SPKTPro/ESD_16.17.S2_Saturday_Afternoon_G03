package com.example.rinnv.esd_g03.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.rinnv.esd_g03.Models.Example;
import com.example.rinnv.esd_g03.R;
import com.example.rinnv.esd_g03.Ultility.SQLiteDataController;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static SQLiteDataController db;
    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new SQLiteDataController(MainActivity.this.getApplicationContext());
        createDb();
        Button couple1 = (Button) findViewById(R.id.button1);
        Button couple2 = (Button) findViewById(R.id.button2);
        couple1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TabActivity.class);
                startActivity(intent);
            }
        });

        couple2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Example> wordArrayList = db.getListExample();
                for (Example example : wordArrayList) {
                    Log.d(TAG, "onClick: " + example.getPhonetic());
                }
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
}
