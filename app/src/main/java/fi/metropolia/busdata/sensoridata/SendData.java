package fi.metropolia.busdata.sensoridata;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

/*
* org.apache.http pois käytöstä
* build.gradleen lisätty: useLibrary "org.apache.http.legacy"
* -> toimii, mutta deprecated varoituksia
*/
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SendData extends AppCompatActivity {

    // Response
    String responseServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data);
    }

    // Kokeillaan aluksi saada Andoid app lähettämään dataa palvelimelle JSON-objektina

    /*
    * Method joka käynnistyy kun Käynnistä-nappia painetaan.
    * Suorittaa AsyncT classin 1s välein.
    */
    public void sendData(View view) {
        while (0 == 0) {
            AsyncT asyncT = new AsyncT();
            asyncT.execute();
            Thread.sleep(100);
        }
    }

    // https://trinitytuts.com/post-json-data-to-server-in-android/
    /* Inner class to get response */
    class AsyncT extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://busdata.metropolia.fi/"); // YOUR_SERVICE_URL

            try {
                // luodaan JSON-objekti
                JSONObject jsonobj = new JSONObject();

                jsonobj.put("id", "1");
                jsonobj.put("nopeus", "10");

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("req", jsonobj.toString()));

                Log.e("mainToPost", "mainToPost" + nameValuePairs.toString());

                // Use UrlEncodedFormEntity to send in proper format which we need
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);
                InputStream inputStream = response.getEntity().getContent();
                InputStreamToStringExample str = new InputStreamToStringExample();
                responseServer = str.getStringFromInputStream(inputStream);
                Log.e("response", "response -----" + responseServer);


            } catch (Exception e) {
                e.printStackTrace();
            }
                return null;
            }

        }
    }