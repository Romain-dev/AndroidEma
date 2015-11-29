package com.example.romain.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button okButton;
        okButton = (Button) findViewById(R.id.button);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePicker date = (DatePicker) findViewById(R.id.datePicker);
                int year = date.getYear();
                int month = date.getMonth();
                int day = date.getDayOfMonth();

                Calendar cal = GregorianCalendar.getInstance();
                cal.set(year, month, day);

                SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");


                Intent intent = new Intent(getApplicationContext(), Activity2.class);
                intent.putExtra("date", day + "-" + month + "-" + year);
                startActivity(intent);
            }
        });

        Button toastButton;
        toastButton = (Button) findViewById(R.id.buttonSimple);

        toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePicker date = (DatePicker) findViewById(R.id.datePicker);
                int year = date.getYear();
                int month = date.getMonth();
                int day = date.getDayOfMonth();

                Calendar cal = GregorianCalendar.getInstance();
                cal.set(year, month, day);

                Toast.makeText(getApplicationContext(),day + "-" + month + "-" + year, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
