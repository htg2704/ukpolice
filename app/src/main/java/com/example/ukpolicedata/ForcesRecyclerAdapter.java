package com.example.ukpolicedata;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.ArrayList;
import java.util.List;

public class ForcesRecyclerAdapter extends Adapter<ForcesRecyclerAdapter.MyViewHolder> {
    static Context context;
    static List<PoliceForces> policeForces;

    protected static class MyViewHolder extends ViewHolder implements OnClickListener {
        TextView textView_1;
        TextView textView_2;
        TextView textView_3;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView_1 = (TextView) itemView.findViewById(R.id.text1);
            this.textView_2 = (TextView) itemView.findViewById(R.id.text2);
            this.textView_3 = (TextView) itemView.findViewById(R.id.text3);
            itemView.setOnClickListener(this);
        }

        public void onClick(View v) {
            Intent intent = new Intent(ForcesRecyclerAdapter.context, SpecificForceInfo.class);
            intent.putExtra("ForceName", ((PoliceForces) ForcesRecyclerAdapter.policeForces.get(getAdapterPosition())).getId());
            ForcesRecyclerAdapter.context.startActivity(intent);
        }
    }

    public ForcesRecyclerAdapter(List<PoliceForces> policeForces2, Context context2) {
        policeForces = policeForces2;
        for (int i = 0; i < policeForces2.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("\n Force Name : ");
            sb.append(((PoliceForces) policeForces2.get(i)).getName());
            Log.e("message", sb.toString());
        }
        context = context2;
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        viewHolder.textView_1.setText(((PoliceForces) policeForces.get(i)).getId());
        viewHolder.textView_2.setText(((PoliceForces) policeForces.get(i)).getName());
        viewHolder.textView_3.setText(BuildConfig.FLAVOR);
    }

    public int getItemCount() {
        return policeForces.size();
    }

    public void updateList(List<PoliceForces> force) {
        policeForces = new ArrayList();
        policeForces.addAll(force);
        notifyDataSetChanged();
    }
}

