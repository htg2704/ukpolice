package com.example.ukpolicedata;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CrimeDbHelper extends SQLiteOpenHelper {
    static final String CREATE_TABLE = "create table crime_record(id text,category text,Location_Type text,context text,Persistent_id text,latitude text,longitude text,StreetId text,StreetName text)";
    static final String DROP_TABLE = "drop table if exists crime_record";
    static final String Database_Name = "crime_db";
    static final int Database_Version = 1;

    public CrimeDbHelper(Context context) {
        super(context, Database_Name, null, 1);
        Log.e("message", "Dtabase created");
    }

    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE);
        Log.e("message", "table created");
    }

    public void onUpgrade(SQLiteDatabase database, int OldVersion, int NewVersion) {
        database.execSQL(DROP_TABLE);
        onCreate(database);
    }

    public void add(CrimeDetails crimeDetails, SQLiteDatabase database) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", crimeDetails.getId());
        contentValues.put("category", crimeDetails.getCategory());
        contentValues.put("Location_Type", crimeDetails.getLocation_type());
        contentValues.put("Persistent_id", crimeDetails.getPersistent_id());
        contentValues.put("context", crimeDetails.getContext());
        contentValues.put("latitude", crimeDetails.getLocation().getLatitude());
        contentValues.put("longitude", crimeDetails.getLocation().getLongitude());
        contentValues.put("StreetId", crimeDetails.getLocation().getStreet().getId());
        contentValues.put("StreetName", crimeDetails.getLocation().getStreet().getName());
        database.insert("crime_record", null, contentValues);
        Log.d("message", "a row inserted");
    }

    public Cursor readContacts(SQLiteDatabase database) {
        return database.query("crime_record", new String[]{"id", "category", "Location_Type", "Persistent_id", "context", "latitude", "longitude", "StreetId", "StreetName"}, null, null, null, null, "id");
    }}