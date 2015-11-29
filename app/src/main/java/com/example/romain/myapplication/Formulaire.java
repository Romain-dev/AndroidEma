package com.example.romain.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Formulaire extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);

        String datas = getIntent().getStringExtra("data");
        try
        {
            JSONObject json = new JSONObject(datas);

            TextView id = (TextView) findViewById(R.id.date);
            TextView name = (TextView) findViewById(R.id.nom);
            TextView description = (TextView) findViewById(R.id.description);
            TextView dateDebut = (TextView) findViewById(R.id.dateDebut);
            TextView dateFin = (TextView) findViewById(R.id.dateFin);

            id.setText(json.getString("id"));
            name.setText(json.getString("name"));
            description.setText(json.getString("description"));
            dateDebut.setText(json.getString("date_start"));
            dateFin.setText(json.getString("date_end"));

            JSONArray actors = json.getJSONArray("actors");

            ListView listeActors = (ListView) findViewById(R.id.listView);


            String[] values = new String[actors.length()];

            for(int i = 0; i < actors.length(); i++)
            {
                JSONObject element = actors.getJSONObject(i);
                values[i] = element.getString("firstname") + "\n" + element.getString("lastname");
            }
            // Define a new Adapter
            // First parameter - Context
            // Second parameter - Layout for the row
            // Third parameter - ID of the TextView to which the data is written
            // Forth - the Array of data

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, values);

            // Assign adapter to ListView
            listeActors.setAdapter(adapter);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }


    }

}
