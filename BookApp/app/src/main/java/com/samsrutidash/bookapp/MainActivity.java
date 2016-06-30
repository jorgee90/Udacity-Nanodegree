package com.samsrutidash.bookapp;

import org.json.JSONException;

import android.os.Bundle;
import android.os.AsyncTask;

import android.app.Activity;
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
import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {
    ListView list;
    private static String urlString;
    //JSON Node Names
    private static final String TAG_TITLE = "title";
    private static final String TAG_AUTHORS = "authors";
    private static final String TAG_DESCRIPTION = "description";

    ArrayList<HashMap<String, String>> booklist = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = (EditText) findViewById(R.id.input);
                String inputQuery = input.getText().toString();

                String APIKEY = "&key=AIzaSyCvvuHSz0nYnU5awMlCij1IhN-sHX1Q1O4";
                urlString = "https://www.googleapis.com/books/v1/volumes?q="+inputQuery+"&orderBy=newest";
                Log.v("URL:",urlString);
                new ProcessJSON().execute(urlString);
            }
        });
    }

    private class ProcessJSON extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... strings) {
            String stream = null;
            String urlString = strings[0];

            HTTPDataHandler hh = new HTTPDataHandler();
            stream = hh.GetHTTPData(urlString);

            // Return the data from specified url
            return stream;
        }

        protected void onPostExecute(String stream) {
//            Log.v("URL: ", stream);

            //tv.setText(stream);

            /*
                Important in JSON DATA
                -------------------------
                * Square bracket ([) represents a JSON array
                * Curly bracket ({) represents a JSON object
                * JSON object contains key/value pairs
                * Each key is a String and value may be different data types
             */

            //..........Process JSON DATA................
            if (stream != null) {
                try {
                    // Get the full HTTP Data as JSONObject
                    JSONObject reader = new JSONObject(stream);


                    // Get the JSONArray weather
                    JSONArray weatherArray = reader.getJSONArray("items");
                    // Get the weather array first JSONObject
                    for (int i = 0; i < weatherArray.length(); i++) {

                        JSONObject weather_object_0 = weatherArray.getJSONObject(i);
                        String weather_0_id = weather_object_0.getString("id");

                        JSONObject BookDetails= weather_object_0.getJSONObject("volumeInfo");
                        String title = BookDetails.getString("title");
                        String description = BookDetails.getString("description");
                        String authors = BookDetails.getString("authors");
                        authors = authors.replace("[", "");
                        authors = authors.replace("]", "");
                        Log.v(TAG_TITLE,title);

                        Log.v(TAG_AUTHORS,authors);

                        // Adding value HashMap key => value

                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(TAG_TITLE, title);
                        map.put(TAG_AUTHORS, authors);
                        map.put(TAG_DESCRIPTION,description);

                        booklist.add(map);
                        list = (ListView) findViewById(R.id.list);

                        ListAdapter adapter = new SimpleAdapter(MainActivity.this,
                                booklist,
                                R.layout.listview_layout,
                                new String[]{TAG_TITLE, TAG_AUTHORS,TAG_DESCRIPTION},
                                new int[]{ R.id.bookTitle, R.id.bookAuthors,}
                        );

                        list.setAdapter(adapter);
                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view,
                                                    int position, long id) {
                                Toast.makeText(MainActivity.this, "Description:\n  " + booklist.get(+position).get("description"), Toast.LENGTH_LONG).show();
                            }
                        });

                    }


                    // process other data as this way..............

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } // if statement end
        } // onPostExecute() end
    } // ProcessJSON class end
} // Activity end