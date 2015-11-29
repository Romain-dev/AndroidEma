package com.example.romain.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import utils.Utils;

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
            // Define a new Adapter
            // First parameter - Context
            // Second parameter - Layout for the row
            // Third parameter - ID of the TextView to which the data is written
            // Forth - the Array of data
            // Assign adapter to ListView
            listeActors.setAdapter(new DataListAdapter(firstNames,lastNames,photos));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }


    }

    class DataListAdapter extends BaseAdapter {
        String[] Title, Detail;
        String[] imge;

        DataListAdapter() {
            Title = null;
            Detail = null;
            imge=null;
        }

        public DataListAdapter(String[] text, String[] text1,String[] text3) {
            Title = text;
            Detail = text1;
            imge = text3;

        }

        public int getCount() {
            // TODO Auto-generated method stub
            return Title.length;
        }

        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View row;
            row = inflater.inflate(R.layout.date_list_adapter, parent, false);
            TextView title, detail;
            ImageView i1;
            title = (TextView) row.findViewById(R.id.title);
            detail = (TextView) row.findViewById(R.id.detail);
            i1=(ImageView)row.findViewById(R.id.img);
            new Utils.LoadImageFromUrl().execute(i1, imge[position]);
            title.setText(Title[position]);
            detail.setText(Detail[position]);

            return (row);
        }
    }
}
