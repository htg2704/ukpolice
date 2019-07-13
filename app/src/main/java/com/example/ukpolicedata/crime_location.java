package com.example.ukpolicedata;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class crime_location extends AppCompatActivity implements OnClickListener {
    Button button;
    String date = BuildConfig.FLAVOR;
    EditText editText_lat;
    EditText editText_lng;
    EditText editText_month;
    EditText editText_year;
    String lat;
    String lng;
    String month;
    private Toolbar toolbar;
    String year;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        this.editText_lat = (EditText) findViewById(R.id.lat);
        this.editText_lng = (EditText) findViewById(R.id.lon);
        this.editText_month = (EditText) findViewById(R.id.month);
        this.editText_year = (EditText) findViewById(R.id.year);
        this.button = (Button) findViewById(R.id.button);
        this.button.setOnClickListener(this);
    }

    public void onClick(View v) {
        this.lat = this.editText_lat.getText().toString();
        this.lng = this.editText_lng.getText().toString();
        this.year = this.editText_year.getText().toString();
        this.month = this.editText_month.getText().toString();
        String str = this.lat;
        String str2 = BuildConfig.FLAVOR;
        String str3 = "Enter correct details";
        if (str.matches(str2) || this.lng.matches(str2) || this.year.matches(str2) || this.month.matches(str2)) {
            Toast.makeText(getApplicationContext(), str3,Toast.LENGTH_SHORT).show();
            return;
        }
        int m = Integer.parseInt(this.month);
        if (m < 0 || m > 12) {
            Toast.makeText(getApplicationContext(), str3, Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.year);
        sb.append("-");
        sb.append(this.month);
        this.date = sb.toString();
        Intent intent = new Intent(getApplicationContext(), DisplayCrimeWithLocation.class);
        intent.putExtra("lat", this.lat);
        intent.putExtra("lng", this.lng);
        intent.putExtra("date", this.date);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        OnMenuItemClickListener menuItemClickListener = new OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                crime_location crimeWithLocation = crime_location.this;
                crimeWithLocation.startActivity(new Intent(crimeWithLocation.getApplicationContext(), DisplayFavourites.class));
                return true;
            }
        };
        MenuItem favouriteItem = menu.findItem(R.id.favourite);
        menu.findItem(R.id.search).setVisible(false);
        favouriteItem.setOnMenuItemClickListener(menuItemClickListener);
        return true;
    }
}