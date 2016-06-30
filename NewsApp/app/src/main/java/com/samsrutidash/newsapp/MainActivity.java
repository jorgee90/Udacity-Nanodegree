package com.samsrutidash.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    public ListView list;
    public TextView emptyView;
    String APIKEY = "&api-key=981a0d3b-96f0-4267-9476-1ef62a049ffb";
    String pageSize = "&page-size=20";
    public String urlString = "https://content.guardianapis.com/search?"+APIKEY+pageSize;
    public String urlFinal;
    public int pageCount=1;
    //JSON Node Names
    private static final String TAG_ID = "id";
    private static final String TAG_SECTION_NAME = "sectionName";
    private static final String TAG_TITLE = "webTitle";
    private static final String TAG_WEB_URL = "webUrl";
    private static final String TAG_DATE = "webPublicationDate";







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.list);
        emptyView = (TextView) findViewById(R.id.empty);
        assert emptyView != null;
        emptyView.setText("");
        Log.v("Before Click:",urlString);
        Button btn = (Button) findViewById(R.id.btn);
        assert btn != null;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText input = (EditText) findViewById(R.id.input);
                String inputQuery = input.getText().toString();
                inputQuery = inputQuery.replace(" ","+");

                urlFinal = urlString + "&q="+inputQuery;
                Log.v("After Click URL:",urlFinal);
                new ProcessJSON().execute(urlFinal);
            }
        });
        new ProcessJSON().execute(urlString);
    }


    private class ProcessJSON extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Please Wait.. Loading...", Toast.LENGTH_SHORT).show();
        }

        protected String doInBackground(String... strings) {
            String stream = null;
            String urlString = strings[0];

            HTTPDataHandler hh = new HTTPDataHandler();
            stream = hh.GetHTTPData(urlString);

            // Return the data from specified url
            return stream;
        }

        protected void onPostExecute(String stream) {
            final ArrayList<HashMap<String, String>> newsList = new ArrayList<HashMap<String, String>>();
            if (stream != null) {
                try {
                    // Get the full HTTP Data as JSONObject
                    JSONObject reader = new JSONObject(stream);
                    JSONObject newsArray = reader.getJSONObject("response");
                    int total = newsArray.getInt("total");
                    if (total==0){
                        list.setVisibility(View.INVISIBLE);
                        emptyView.setText(R.string.empty);
                        Toast.makeText(MainActivity.this, "No Results Found", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        emptyView.setText("");
                        // Get the JSONArray weather
                        JSONArray newsResults = newsArray.getJSONArray("results");
                        // Get the weather array first JSONObject

                        Log.v("Count: ", String.valueOf(newsArray.length()));
                        for (int i = 0; i < 20; i++) {

                            JSONObject BookObject = newsResults.getJSONObject(i);
                            String title, URL, section, date, ID;

                            title = BookObject.getString(TAG_TITLE);
                            URL = BookObject.getString(TAG_WEB_URL);
                            section = BookObject.getString(TAG_SECTION_NAME);
                            date = BookObject.getString(TAG_DATE);
                            ID = String.valueOf(i+1);
                            ID = ID+".";
                            Log.v(TAG_TITLE, title);

                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put(TAG_ID, ID);
                            map.put(TAG_TITLE, title);
                            map.put(TAG_SECTION_NAME, section);
                            map.put(TAG_WEB_URL, URL);
                            map.put(TAG_DATE, date);
                            map.put(TAG_TITLE, title);
                            newsList.add(map);
                            // Adding Load More button to lisview at bottom


                            ListAdapter adapter = new SimpleAdapter(MainActivity.this,
                                    newsList,
                                    R.layout.listview_layout,
                                    new String[]{TAG_TITLE,TAG_DATE,TAG_SECTION_NAME,TAG_WEB_URL},
                                    new int[]{R.id.newsTitle,R.id.newsDate,R.id.newsSection}
                            );

                            list.setAdapter(adapter);
                            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view,
                                                        int position, long id) {
                                    String url = newsList.get(+position).get(TAG_WEB_URL);
                                    Intent i = new Intent(Intent.ACTION_VIEW);
                                    i.setData(Uri.parse(url));
                                    startActivity(i);
                                }
                            });

                        }

                    }
                    // process other data as this way..............
                }catch (JSONException e) {
                    e.printStackTrace();

                }

            } // if statement end
        } // onPostExecute() end
    } // ProcessJSON class end
} // Activity end