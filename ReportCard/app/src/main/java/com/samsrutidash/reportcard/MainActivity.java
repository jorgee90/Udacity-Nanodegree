package com.samsrutidash.reportcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ReportCard weather_data[] = new ReportCard[]
                {
                        new ReportCard(R.mipmap.ic_launcher, "Mathematics" , "A"),
                        new ReportCard(R.mipmap.ic_launcher, "CS" , "A+"),
                        new ReportCard(R.mipmap.ic_launcher, "English" , "A"),
                        new ReportCard(R.mipmap.ic_launcher, "Electronics" , "B"),
                        new ReportCard(R.mipmap.ic_launcher, "Physics" , "B"),
                        new ReportCard(R.mipmap.ic_launcher, "Chemistry" , "A")
                };

        ReportCardAdapter adapter = new ReportCardAdapter(this,
                R.layout.report_card_listview_layout, weather_data);


        ListView listView1 = (ListView)findViewById(R.id.reportCardView);
        listView1.setAdapter(adapter);
        listView1.setTextFilterEnabled(true);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // When clicked, show a toast with the TextView text
                Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}