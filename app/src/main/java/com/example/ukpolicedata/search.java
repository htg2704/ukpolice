package com.example.ukpolicedata;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class search extends AppCompatActivity {
Spinner spinner;
Button button;
EditText lon,lat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        lon = findViewById(R.id.lon);
        lat = findViewById(R.id.lat);
        button = findViewById(R.id.button);
        String[] months = new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, months);
        spinner.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),crime_location.class);
                startActivity(intent);
            }
        });
    }
}
