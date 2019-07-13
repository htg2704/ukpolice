package com.example.ukpolicedata;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    Button button_crime;
    Button button_force;
    private Toolbar toolbar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        this.button_crime = (Button) findViewById(R.id.crime_info);
        this.button_force = (Button) findViewById(R.id.data);
        this.button_force.setOnClickListener(this);
        this.button_crime.setOnClickListener(this);
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.crime_info) {
            startActivity(new Intent(this, crime_location.class));
        } else if (id == R.id.data) {
            startActivity(new Intent(this, data_force_activity.class));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        OnMenuItemClickListener onMenuItemClickListener = new OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(MainActivity.this, "MenuItem clicked",Toast.LENGTH_SHORT).show();
                MainActivity mainActivity = MainActivity.this;
                mainActivity.startActivity(new Intent(mainActivity.getApplicationContext(), DisplayFavourites.class));
                return true;
            }
        };
        MenuItem searchItem = menu.findItem(R.id.search);
        MenuItem favouroiteItem = menu.findItem(R.id.favourite);
        searchItem.setVisible(false);
        favouroiteItem.setOnMenuItemClickListener(onMenuItemClickListener);
        return true;
    }
}

