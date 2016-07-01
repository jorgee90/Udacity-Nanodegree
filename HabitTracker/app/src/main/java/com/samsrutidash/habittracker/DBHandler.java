package com.samsrutidash.habittracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samsrutidash on 6/30/2016.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "HabitTracker";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBContract.Table1.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // If a table with same name is already existed or modified like updating the table name or anything, this will delete the previous one and will create again
        //
        db.execSQL(DBContract.Table1.DELETE_TABLE);
        //It will create the tables again.
        onCreate(db);
    }

    /**
     CRUD(Create, Read, Update, Delete)
     **/
    void addHabit(HabitDetails newHabit){
        //Create a Database Connection
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBContract.Table1.KEY_TITLE, newHabit.getHabitTitle());

        // Inserting Row
        db.insert(DBContract.Table1.TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    public List<HabitDetails> listAllHabits(){
        List<HabitDetails> habitList = new ArrayList<HabitDetails>();
        String query = "SELECT * FROM "+DBContract.Table1.TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                HabitDetails habit = new HabitDetails();
                habit.setHabitID(Integer.parseInt(cursor.getString(0)));
                habit.setHabitTitle(cursor.getString(1));
                habitList.add(habit);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return habitList;
    }

    // Updating single contact
    public void updateHabitRow(double rowId, String newContent) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBContract.Table1.KEY_TITLE,newContent);
        db.update(DBContract.Table1.TABLE_NAME, values,DBContract.Table1.KEY_ID + "=" + rowId,null);



    }

    // Deleting single contact
    public boolean deleteHabitRow(double rowId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DBContract.Table1.TABLE_NAME, DBContract.Table1.KEY_ID + "=" + rowId, null) > 0;

    }


}
