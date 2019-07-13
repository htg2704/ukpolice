package com.example.ukpolicedata;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpecificForceInfo extends AppCompatActivity {
    Button button;
    String forceName;
    SpecificForce specificForce;
    SpecificForceInterface specificForceApiInterface;
    TextView textView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        this.textView = (TextView) findViewById(R.id.details);
        this.button = (Button) findViewById(R.id.map);
        this.button.setVisibility(View.VISIBLE);
        this.forceName = getIntent().getStringExtra("ForceName");
        this.specificForceApiInterface = (SpecificForceInterface) api.getApiClient().create(SpecificForceInterface.class);
        this.specificForceApiInterface.getSpecificForce(this.forceName).enqueue(new Callback<SpecificForce>() {
            public void onResponse(Call<SpecificForce> call, Response<SpecificForce> response) {
                String message = BuildConfig.FLAVOR;
                SpecificForceInfo.this.specificForce = (SpecificForce) response.body();
                StringBuilder sb = new StringBuilder();
                sb.append(message);
                sb.append("Name : ");
                sb.append(SpecificForceInfo.this.specificForce.getName());
                sb.append("\nId : ");
                sb.append(SpecificForceInfo.this.specificForce.getId());
                sb.append("\nDescription : ");
                sb.append(SpecificForceInfo.this.specificForce.getDescription());
                sb.append("\nURL : ");
                sb.append(SpecificForceInfo.this.specificForce.getUrl());
                sb.append("\nEngagement Methods : ");
                String message2 = sb.toString();
                for (int i = 0; i < SpecificForceInfo.this.specificForce.getEngagementMethods().size(); i++) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(message2);
                    sb2.append("\n   Title : ");
                    sb2.append(((SpecificForce.EngagementMethods) SpecificForceInfo.this.specificForce.getEngagementMethods().get(i)).title);
                    sb2.append("\n   URL : ");
                    sb2.append(((SpecificForce.EngagementMethods) SpecificForceInfo.this.specificForce.getEngagementMethods().get(i)).url);
                    sb2.append("\n   Description : ");
                    sb2.append(((SpecificForce.EngagementMethods) SpecificForceInfo.this.specificForce.getEngagementMethods().get(i)).description);
                    sb2.append("\n");
                    message2 = sb2.toString();
                }
                SpecificForceInfo.this.textView.setText(message2);
            }

            public void onFailure(Call<SpecificForce> call, Throwable t) {
                Log.e("message", "Api call Failed");
            }
        });
    }
}