package utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.romain.myapplication.R;

/**
 * Created by romain on 30/11/2015.
 */
public class DataListAdapter extends BaseAdapter {
    String[] Title, Detail;
    String[] imge;
    LayoutInflater inflater;

    DataListAdapter() {
        Title = null;
        Detail = null;
        imge=null;
        inflater = null;
    }

    public DataListAdapter(LayoutInflater inflater, String[] text, String[] text1,String[] text3) {
        Title = text;
        Detail = text1;
        imge = text3;
        this.inflater = inflater;
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
