package com.example.ukpolicedata;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayFavourites extends AppCompatActivity {
    TextView textView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_favourite);
        this.textView = (TextView) findViewById(R.id.details);
        CrimeDbHelper crimeDbHelper = new CrimeDbHelper(getApplicationContext());
        Cursor cursor = crimeDbHelper.readContacts(crimeDbHelper.getReadableDatabase());
        String s = BuildConfig.FLAVOR;
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String location = cursor.getString(cursor.getColumnIndex("Location_Type"));
            String persistent_id = cursor.getString(cursor.getColumnIndex("context"));
            String context = cursor.getString(cursor.getColumnIndex("Persistent_id"));
            String category = cursor.getString(cursor.getColumnIndex("category"));
            String latitude = cursor.getString(cursor.getColumnIndex("latitude"));
            String longitude = cursor.getString(cursor.getColumnIndex("longitude"));
            String streetId = cursor.getString(cursor.getColumnIndex("StreetId"));
            String streetName = cursor.getString(cursor.getColumnIndex("StreetName"));
            StringBuilder sb = new StringBuilder();
            sb.append(s);
            sb.append("ID : ");
            sb.append(id);
            sb.append("\nCategory : ");
            sb.append(category);
            sb.append("\nLocation Subtype : ");
            sb.append(location);
            sb.append("\nPersistent Id : ");
            sb.append(persistent_id);
            sb.append("\nContext : ");
            sb.append(context);
            sb.append("\nLocation : \n   Latitude : ");
            sb.append(latitude);
            sb.append("\n   Longitude : ");
            sb.append(longitude);
            sb.append("\n   Street Id : ");
            sb.append(streetId);
            sb.append("\n   Street Name : ");
            sb.append(streetName);
            sb.append("\n\n\n");
            s = sb.toString();
        }
        this.textView.setText(s);
    }
}