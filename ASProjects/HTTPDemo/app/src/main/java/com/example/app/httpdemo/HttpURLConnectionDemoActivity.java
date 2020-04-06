package com.example.app.httpdemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionDemoActivity extends AppCompatActivity {

    TextView displayResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_httpurlconnection_demo);
        displayResult = (TextView) findViewById(R.id.display);

        try {
            URL url = new URL("http://www.nptu.edu.tw/files/502-1000-1000-1.php?Lang=zh-tw");
            new AccessInternet().execute(url);
        } catch (Exception e) {
            displayResult.setText(e.toString());
        }
    }

    private class AccessInternet extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls) {
            BufferedReader reader = null;
            StringBuilder stringBuilder;

            try {
                HttpURLConnection connection = (HttpURLConnection) urls[0].openConnection();

                connection.setRequestMethod("GET");
                connection.setReadTimeout(15 * 1000);
                connection.connect();

                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                stringBuilder = new StringBuilder();

                String line = null;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                return stringBuilder.toString();
            } catch (Exception e) {
                return e.toString();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException ioe) {
                        return ioe.toString();
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(String result) {
            displayResult.setText(result);
        }

    }
}
