package com.example.ukpolicedata;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayCrimeWithLocation extends AppCompatActivity {
    CrimeDetailsWithLocation apiInterface;
    List<CrimeDetails> crimeDetails;
    String date;
    String lat;
    LayoutManager layoutManager;
    String lng;
    CrimeRecyclerAdapter recyclerAdapter;
    RecyclerView recyclerView;
    TextView textView;
    private Toolbar toolbar;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_display);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        Intent intent = getIntent();
        this.lat = intent.getStringExtra("lat");
        this.lng = intent.getStringExtra("lng");
        this.date = intent.getStringExtra("date");
        this.textView = (TextView) findViewById(R.id.hints);
        this.textView.setText("click the crimes to see there description\nSwipe to add to your favourite");
        this.recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.layoutManager = new LinearLayoutManager(this);
        this.recyclerView.setLayoutManager(this.layoutManager);
        this.recyclerView.setHasFixedSize(true);
        this.apiInterface = (CrimeDetailsWithLocation) api.getApiClient().create(CrimeDetailsWithLocation.class);
        this.apiInterface.getCrimeDetails(this.date, this.lat, this.lng).enqueue(new Callback<List<CrimeDetails>>() {
            public void onResponse(Call<List<CrimeDetails>> call, Response<List<CrimeDetails>> response) {
                DisplayCrimeWithLocation.this.crimeDetails = (List) response.body();
                DisplayCrimeWithLocation displayCrimeWithLocation = DisplayCrimeWithLocation.this;
                displayCrimeWithLocation.recyclerAdapter = new CrimeRecyclerAdapter(displayCrimeWithLocation.crimeDetails, DisplayCrimeWithLocation.this.getApplicationContext());
                DisplayCrimeWithLocation.this.recyclerView.setAdapter(DisplayCrimeWithLocation.this.recyclerAdapter);
            }

            public void onFailure(Call<List<CrimeDetails>> call, Throwable t) {
                Log.e("message", "Api call failed");
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        OnMenuItemClickListener menuItemClickListener = new OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                DisplayCrimeWithLocation displayCrimeWithLocation = DisplayCrimeWithLocation.this;
                displayCrimeWithLocation.startActivity(new Intent(displayCrimeWithLocation.getApplicationContext(), DisplayFavourites.class));
                return true;
            }
        };
        MenuItem favouriteItem = menu.findItem(R.id.favourite);
        menu.findItem(R.id.search).setVisible(false);
        favouriteItem.setOnMenuItemClickListener(menuItemClickListener);
        return true;
    }
}