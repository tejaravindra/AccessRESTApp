package com.example.tejaravindra.accessrestapp;





import android.app.job.JobInfo;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class MainActivity extends AppCompatActivity {

    ListView lv;
    MyCustomAdapter myCustomAdapter;
    List<ListElements> le;
    Context context;
    static final String JSON_URL_LIST="http://hackerearth.0x10.info/api/gyanmatrix?type=json&query=list_player";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            context=getApplicationContext();
//            URL url = new URL(JSON_URL_LIST);
//            TestClass1 testClass = new TestClass1();
//            testClass.execute(url);
//
            ForMessages forMessages=new ForMessages();
            forMessages.readMessages();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_search,menu);
        MenuItem menuItem= menu.findItem(R.id.searchId);
        SearchView searchView=(SearchView)menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myCustomAdapter.getFilter().filter(newText);
                System.out.println("new String is ------"+newText);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    public class TestClass1 extends AsyncTask<URL, Object, Object> {

        @Override
        protected Object doInBackground(URL... url) {
            List<ListElements>  map = null;
            try {
                map = connect(url[0]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return map;
        }


        public List<ListElements>  connect(URL url) throws JSONException {
            HttpURLConnection httpURLConnection;
            String response;
            Map hashMap = null;

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
                JSONObject jSONOb = new JSONObject(sb.toString());
                JSONArray jSONArray = jSONOb.getJSONArray("records");
                le=new ArrayList<ListElements>() ;
                for(int i=0; i<jSONArray.length(); i++){
                    JSONObject c=jSONArray.getJSONObject(i);
                    ListElements listElements =new ListElements(c.getString("id"),c.getString("name"),getImageView(c.getString("image")));
                    System.out.println("-------"+c.getString("id") + "  ----- phone "+c.getString("name"));
                    le.add(listElements);

                }
                httpURLConnection.disconnect();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return le;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            lv= (ListView)findViewById(R.id.myCustomList);
            myCustomAdapter=new MyCustomAdapter(context,R.layout.list_layout,(List)o);
            lv.setAdapter(myCustomAdapter);
        }

        public Bitmap getImageView(String imageURL) {
            Bitmap bitmap = null;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            try {
                URL url=new URL(imageURL);
                bitmap = BitmapFactory.decodeStream((InputStream)url.getContent(),
                        null, bmOptions);
                System.out.println("bitmap is "+bitmap.getByteCount());
            } catch (Exception e) {
                e.getMessage();
            }
            return bitmap;
        }
    }

    public class ForPostRequest extends AsyncTask<URL, Object, Object> {

        @Override
        protected Object doInBackground(URL... url) {
            List<ListElements>  map = null;
            try {
                map = connect(url[0]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return map;
        }


        public List<ListElements>  connect(URL url) throws JSONException {
            HttpURLConnection httpURLConnection;
            String response;
            Map hashMap = null;
            try {
                URL newurl=new URL("http://demo5675053.mockable.io/suryaa");
                httpURLConnection = (HttpURLConnection) newurl.openConnection();
                httpURLConnection.setRequestMethod("POST");
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
                JSONObject jsonObject = new JSONObject(sb.toString());
                System.out.println("-------"+jsonObject.getString("Name") + "  ----- phone "+jsonObject.getString("Number"));
                httpURLConnection.disconnect();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return le;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

        }

    }

    public class ForMessages  {
        private static final String CONTENT_STR="content://sms/inbox";
        public void readMessages(){
            le=new ArrayList<ListElements>();
            Uri messageQuery=Uri.parse(CONTENT_STR);

            JobInfo.TriggerContentUri triggerContentUri = new JobInfo.TriggerContentUri(messageQuery, JobInfo.TriggerContentUri.FLAG_NOTIFY_FOR_DESCENDANTS);

           String[] reqCols = new String[] { "_id", "address", "body" };
            Cursor cursor = context.getContentResolver().query(triggerContentUri.getUri(),null,null,null,null);
            while (cursor.moveToNext()){
                int bodyIndex= cursor.getColumnIndexOrThrow(Telephony.TextBasedSmsColumns.BODY);
                String body = cursor.getString(bodyIndex);
                int creatorIndex= cursor.getColumnIndexOrThrow(Telephony.TextBasedSmsColumns.SUBJECT);
                String creator = cursor.getString(creatorIndex);
                int dateIndex= cursor.getColumnIndexOrThrow(Telephony.TextBasedSmsColumns.DATE);
                String date= cursor.getString(dateIndex);
                System.out.println("------ date -----"+date);
                ListElements listElements=new ListElements(body,creator,null);
                le.add(listElements);
            }
            cursor.close();
            lv= (ListView)findViewById(R.id.myCustomList);
            myCustomAdapter=new MyCustomAdapter(context,R.layout.list_layout,(List)le);
            lv.setAdapter(myCustomAdapter);
        }

    }



}




