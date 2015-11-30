package com.example.romain.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;
import utils.DataListAdapter;
import utils.TimeView;

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

            String[] firstNames=new String[actors.length()];
            String[] lastNames=new String[actors.length()];
            String[] photos =new String[actors.length()];

            for(int i = 0; i < actors.length(); i++)
            {
                JSONObject element = actors.getJSONObject(i);
                firstNames[i] = element.getString("firstname");
                lastNames[i] = element.getString("lastname");
                photos[i] = element.getString("image");
            }

            listeActors.setAdapter(new DataListAdapter(getLayoutInflater(), firstNames, lastNames, photos));
            TimeView timeViewDebut = (TimeView)findViewById(R.id.viewDebut);
            timeViewDebut.setTime(json.getString("date_start"));

            TimeView timeViewEnd = (TimeView)findViewById(R.id.viewFin);
            timeViewEnd.setTime(json.getString("date_end"));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }


    }
}
