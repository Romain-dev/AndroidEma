package com.example.romain.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import utils.Utils;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        String date = getIntent().getStringExtra("date");
        Toast.makeText(getApplicationContext(), date, Toast.LENGTH_SHORT).show();

        Button okButton;
        okButton = (Button) findViewById(R.id.button);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.get(getApplicationContext(), "http://billygirboux.fr/mines/JAVA-ANDROID/getProgram.php?date=2015-11-24");
            }
        });

        Button callButton;
        callButton = (Button) findViewById(R.id.buttonCall);

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(+33)12345789")); startActivity(intent);
                startActivity(intent);
            }
        });
    }
}
