package utils;

import android.content.Context;
import android.content.Intent;
import android.media.tv.TvContract;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.romain.myapplication.Activity2;
import com.example.romain.myapplication.Formulaire;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 * Created by romain on 24/11/2015.
 */
public class Utils {
    private static class GetProgramTask extends AsyncTask<String,Integer,String> {

        private Context context;
        public GetProgramTask(Context context) {
            this.context = context;
        }
        @Override
        protected String doInBackground(String... params) {
            return getDocument(params[0]);
        }

        @Override
        public void onPostExecute(String result) {
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, Formulaire.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("data", result);
            context.startActivity(intent);
        }
    }

    public static void get(Context context, String serviceUrl)
    {
        new GetProgramTask(context).execute(serviceUrl);
    }

    public static String getDocument(String serviceUrl) {
        HttpURLConnection urlConnection = null;
        try {
            URL urlToRequest = new URL(serviceUrl);
            urlConnection = (HttpURLConnection)
                    urlToRequest.openConnection();

            int statusCode = urlConnection.getResponseCode();
            if(statusCode != HttpURLConnection.HTTP_OK &&
                statusCode != HttpURLConnection.HTTP_CREATED &&
                statusCode != HttpURLConnection.HTTP_ACCEPTED) {
                return null;
            }

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return getResponseText(in);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally {
            if(urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return "";
    }

    // convert InputStream to String
    private static String getResponseText(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }
}
