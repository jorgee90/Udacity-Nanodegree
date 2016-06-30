package com.samsrutidash.habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHandler db = new DBHandler(this);
        Log.v("DB","OK");
        /**
         * CRUD Operations
         * */
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



    }
}
