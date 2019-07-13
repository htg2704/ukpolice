package com.example.ukpolicedata;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SpecificCrimeDetails extends AppCompatActivity implements OnClickListener {
    Button button;
    String details;
    String lat;
    String lng;
    String location;
    TextView textView;
    Toolbar toolbar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        this.textView = (TextView) findViewById(R.id.details);
        this.button = (Button) findViewById(R.id.map);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        this.button.setOnClickListener(this);
        Intent intent = getIntent();
        this.details = intent.getStringExtra("CrimeDetails");
        this.lat = intent.getStringExtra("latitude");
        this.lng = intent.getStringExtra("longitude");
        this.location = intent.getStringExtra("location");
        this.textView.setText(this.details);
    }

    public void onClick(View v) {
      /*  Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        intent.putExtra("latitude", this.lat);
        intent.putExtra("longitude", this.lng);
        intent.putExtra("location", this.location);
        startActivity(intent);*/
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        OnMenuItemClickListener menuItemClickListener = new OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                SpecificCrimeDetails specificCrimeDetails = SpecificCrimeDetails.this;
                specificCrimeDetails.startActivity(new Intent(specificCrimeDetails.getApplicationContext(), DisplayFavourites.class));
                return true;
            }
        };
        MenuItem favouriteItem = menu.findItem(R.id.favourite);
        menu.findItem(R.id.search).setVisible(false);
        favouriteItem.setOnMenuItemClickListener(menuItemClickListener);
        return true;
    }
}