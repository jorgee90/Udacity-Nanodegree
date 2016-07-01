package com.samsrutidash.habittracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHandler db = new DBHandler(this);
        Log.v("DB","OK");

        ListView listView = (ListView) findViewById(R.id.listView);
//
//
//        db.addHabit(new Inventory("asfasf", 5, 1,12.4124));
//        db.addHabit(new Inventory("asfasf", 5, 1,12.4124));
//        db.addHabit(new Inventory("asfasf", 5, 1,12.4124));
//
//
//        ArrayList<Inventory> listArray = db.listAllItems();
//        for (Inventory H : listArray) {
//            String log = "Id: "+H.getId()+" ,Title: " + H.getProductName();
//            // Writing Contacts to log
//
//            Log.d("Name: ", log);
//
//        }
//
//
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addHabit(new HabitDetails("SO lol"));
        Log.d("Reading: ", "Reading all contacts..");
        db.addHabit(new HabitDetails("Playing"));
        db.deleteHabitRow(2);
        db.updateHabitRow(3,"Samsruti");

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<HabitDetails> habitLists = db.listAllHabits();

        for (HabitDetails H : habitLists) {
            String log = "Id: "+H.getHabitID()+" ,Title: " + H.getHabitTitle();
            // Writing Contacts to log

            Log.d("Name: ", log);

        }

//        ListViewAdapter customAdapter = new ListViewAdapter(listArray);
//
//        listView.setAdapter(customAdapter);
    }

}