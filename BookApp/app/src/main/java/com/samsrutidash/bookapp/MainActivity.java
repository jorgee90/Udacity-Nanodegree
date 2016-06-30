package com.samsrutidash.bookapp;

import org.json.JSONException;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.AsyncTask;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class MainActivity extends AppCompatActivity {
    ListView list;
    public TextView emptyView;
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
        assert btn != null;
        emptyView = (TextView) findViewById(R.id.empty);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = (EditText) findViewById(R.id.input);
                String inputQuery = input.getText().toString();
                inputQuery = inputQuery.replace(" ","+");
                String APIKEY = "&key=AIzaSyCvvuHSz0nYnU5awMlCij1IhN-sHX1Q1O4";
                urlString = "https://www.googleapis.com/books/v1/volumes?q="+inputQuery+"&orderBy=newest";
                Log.v("URL:",urlString);
                new ProcessJSON().execute(urlString);
            }
        });
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
            list = (ListView) findViewById(R.id.list);
            if (stream != null) {
                try {
                    // Get the full HTTP Data as JSONObject
                    JSONObject reader = new JSONObject(stream);
                    int totalItems = reader.getInt("totalItems");
                    if (totalItems==0){
                        list.setVisibility(View.INVISIBLE);

                        Toast.makeText(MainActivity.this, "No Results Found", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        emptyView.setText("");
                        // Get the JSONArray weather
                        JSONArray bookArray = reader.getJSONArray("items");
                        // Get the weather array first JSONObject
                        for (int i = 0; i < bookArray.length(); i++) {

                            JSONObject BookObject = bookArray.getJSONObject(i);
                            String title, authors;
                            JSONObject BookDetails= BookObject.getJSONObject("volumeInfo");
                            if (BookDetails.has("authors")){
                                authors = (BookDetails.getString("authors"));
                                authors = authors.replace("[", "");
                                authors = authors.replace("]", "");
                            }
                            else{
                                authors = "";

                            }
                            title = BookDetails.getString("title");

                            Log.v(TAG_TITLE,title);
                            Log.v(TAG_AUTHORS,authors);


                            // Adding value HashMap key => value

                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put(TAG_TITLE, title);
                            map.put(TAG_AUTHORS, authors);

                            booklist.add(map);

                            ListAdapter adapter = new SimpleAdapter(MainActivity.this,
                                    booklist,
                                    R.layout.listview_layout,
                                    new String[]{TAG_TITLE, TAG_AUTHORS},
                                    new int[]{ R.id.bookTitle, R.id.bookAuthors}
                            );

                            list.setAdapter(adapter);
                            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view,
                                                        int position, long id) {
                                    Toast.makeText(MainActivity.this, "Description:\n  " + booklist.get(+position), Toast.LENGTH_SHORT).show();
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