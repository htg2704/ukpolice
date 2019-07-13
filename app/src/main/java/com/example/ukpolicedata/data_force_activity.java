package com.example.ukpolicedata;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class data_force_activity extends AppCompatActivity implements OnQueryTextListener {
    private ForcesInterface apiInterface;
    /* access modifiers changed from: private */
    public List<PoliceForces> forces;
    private LayoutManager layoutManager;
    /* access modifiers changed from: private */
    public ForcesRecyclerAdapter recyclerAdapter;
    /* access modifiers changed from: private */
    public RecyclerView recyclerView;
    TextView textView;
    private Toolbar toolbar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_force);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        this.textView = (TextView) findViewById(R.id.hints);
        this.textView.setText("click the force to see there description\nuse the search in toolbar to search any force");
        this.recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.layoutManager = new LinearLayoutManager(this);
        this.recyclerView.setLayoutManager(this.layoutManager);
        this.recyclerView.setHasFixedSize(false);
        this.apiInterface = (ForcesInterface) api.getApiClient().create(ForcesInterface.class);
        this.apiInterface.getPoliceForces().enqueue(new Callback<List<PoliceForces>>() {
            public void onResponse(Call<List<PoliceForces>> call, Response<List<PoliceForces>> response) {
                data_force_activity.this.forces = (List) response.body();
                StringBuilder sb = new StringBuilder();
                sb.append("Size of police forces id : ");
                sb.append(data_force_activity.this.forces.size());
                Log.e("message", sb.toString());
                data_force_activity force = data_force_activity.this;
                force.recyclerAdapter = new ForcesRecyclerAdapter(force.forces, data_force_activity.this.getApplicationContext());
                data_force_activity.this.recyclerView.setAdapter(data_force_activity.this.recyclerAdapter);
            }

            public void onFailure(Call<List<PoliceForces>> call, Throwable t) {
                Log.e("message", "API call failed");
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        OnMenuItemClickListener onMenuItemClickListener = new OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                data_force_activity force = data_force_activity.this;
                force.startActivity(new Intent(force.getApplicationContext(), DisplayFavourites.class));
                return true;
            }
        };
        MenuItem searchItem = menu.findItem(R.id.search);
        menu.findItem(R.id.favourite).setOnMenuItemClickListener(onMenuItemClickListener);
        ((SearchView) searchItem.getActionView()).setOnQueryTextListener(this);
        return true;
    }

    public boolean onQueryTextSubmit(String s) {
        this.recyclerAdapter.updateList(this.forces);
        return true;
    }

    public boolean onQueryTextChange(String s) {
        String userInput = s.toLowerCase();
        StringBuilder sb = new StringBuilder();
        sb.append("input text : ");
        sb.append(userInput);
        Log.e("message", sb.toString());
        List<PoliceForces> newList = new ArrayList<>();
        for (int i = 0; i < this.forces.size(); i++) {
            if (((PoliceForces) this.forces.get(i)).getName().toLowerCase().contains(userInput)) {
                newList.add(this.forces.get(i));
            }
        }
        this.recyclerAdapter.updateList(newList);
        return true;
    }
}
