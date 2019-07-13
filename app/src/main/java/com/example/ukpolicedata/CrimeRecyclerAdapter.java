package com.example.ukpolicedata;
import android.content.Context;
import android.content.Intent;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.List;

public class CrimeRecyclerAdapter extends Adapter<CrimeRecyclerAdapter.MyViewHolder> {
    Context context;
    List<CrimeDetails> crimeDetails;
    TextView textView_1;
    TextView textView_2;
    TextView textView_3;

    protected class MyViewHolder extends ViewHolder implements OnTouchListener, OnGestureListener {
        GestureDetector gestureDetector = new GestureDetector(this);

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            CrimeRecyclerAdapter.this.textView_1 = (TextView) itemView.findViewById(R.id.text1);
            CrimeRecyclerAdapter.this.textView_2 = (TextView) itemView.findViewById(R.id.text2);
            CrimeRecyclerAdapter.this.textView_3 = (TextView) itemView.findViewById(R.id.text3);
            itemView.setOnTouchListener(this);
        }

        public boolean onTouch(View v, MotionEvent event) {
            this.gestureDetector.onTouchEvent(event);
            return true;
        }

        public boolean onDown(MotionEvent e) {
            return false;
        }

        public void onShowPress(MotionEvent e) {
        }

        public boolean onSingleTapUp(MotionEvent e) {
            Toast.makeText(CrimeRecyclerAdapter.this.context, "view pressed", 0).show();
            CrimeDetails crime = (CrimeDetails) CrimeRecyclerAdapter.this.crimeDetails.get(getAdapterPosition());
            StringBuilder sb = new StringBuilder();
            sb.append("Id : ");
            sb.append(crime.getId());
            sb.append("\nCategory : ");
            sb.append(crime.getCategory());
            sb.append("\nLocation Type : ");
            sb.append(crime.getLocation_type());
            sb.append("\nLocation Subtype : ");
            sb.append(crime.getLocationSubtype());
            sb.append("\nPersistent Id : ");
            sb.append(crime.getPersistent_id());
            sb.append("\nMonth : ");
            sb.append(crime.getMonth());
            sb.append("\nContext : ");
            sb.append(crime.getContext());
            sb.append("\n\n\nLocation : \n   Latitude : ");
            sb.append(crime.getLocation().getLatitude());
            sb.append("\n   Longitude : ");
            sb.append(crime.getLocation().getLongitude());
            sb.append("\n\n   Street : \n     Street Id : ");
            sb.append(crime.getLocation().getStreet().getId());
            sb.append("\n     Street Name : ");
            sb.append(crime.getLocation().getStreet().getName());
            String message = sb.toString();
            Intent intent = new Intent(CrimeRecyclerAdapter.this.context, SpecificCrimeDetails.class);
            intent.putExtra("CrimeDetails", message);
            intent.putExtra("latitude", crime.getLocation().getLatitude());
            intent.putExtra("longitude", crime.getLocation().getLongitude());
            intent.putExtra("location", crime.getLocation().getStreet().getName());
            CrimeRecyclerAdapter.this.context.startActivity(intent);
            return true;
        }

        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        public void onLongPress(MotionEvent e) {
        }

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            CrimeDetails crimes = (CrimeDetails) CrimeRecyclerAdapter.this.crimeDetails.get(getAdapterPosition());
            CrimeDbHelper crimeDbHelper = new CrimeDbHelper(CrimeRecyclerAdapter.this.context);
            crimeDbHelper.add(crimes, crimeDbHelper.getWritableDatabase());
            crimeDbHelper.close();
            Toast.makeText(CrimeRecyclerAdapter.this.context, "Crime added to favourites", 0).show();
            return true;
        }
    }

    public CrimeRecyclerAdapter(List<CrimeDetails> crimeDetails2, Context context2) {
        this.crimeDetails = crimeDetails2;
        this.context = context2;
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(this.context).inflate(R.layout.list, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        this.textView_1.setText(((CrimeDetails) this.crimeDetails.get(i)).getId());
        this.textView_2.setText(((CrimeDetails) this.crimeDetails.get(i)).getCategory());
        this.textView_3.setText(((CrimeDetails) this.crimeDetails.get(i)).getLocation_type());
    }

    public int getItemCount() {
        return this.crimeDetails.size();
    }
}