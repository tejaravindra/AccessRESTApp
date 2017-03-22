package com.example.tejaravindra.accessrestapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by tejaravindra on 2/25/17.
 */

public class TestClass extends AsyncTask<URL,Object,Object> {
    Context context;

    TestClass(Context context){
        this.context=context;

    }

        @Override
        protected Object doInBackground(URL... url) {
            Map map=null;
            try {
              map = connect(url[0]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return map;
        }



    public Map connect(URL url) throws JSONException {
        HttpURLConnection httpURLConnection;
        String response;
        Map hashMap=null;

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + httpURLConnection.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (httpURLConnection.getInputStream())));

            String output;
            StringBuilder sb = new StringBuilder();

            while ((output = br.readLine()) != null) {
                sb = sb.append(output);
            }

            JSONObject c = new JSONObject(sb.toString());
            String id = c.getString("Name");
            String name = c.getString("Phone");
            String email = c.getString("email");
            String address = c.getString("add");
             hashMap=new HashMap();
            hashMap.put("name",id);
            hashMap.put("phone",name);
            hashMap.put("email",email);
            hashMap.put("add",address);
            System.out.println(id + " --" + name + "--" + email + "-- " + address);
            httpURLConnection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Map hashMap=new HashMap();
        hashMap=(HashMap)o;

    }


}
