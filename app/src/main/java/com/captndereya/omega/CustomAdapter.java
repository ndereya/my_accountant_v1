package com.captndereya.omega;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private Context context;
    private ArrayList tx_date,tx_amount,tx_details,tx_type;

    public CustomAdapter(Context context, ArrayList<String> tx_type, ArrayList tx_date, ArrayList tx_amount, ArrayList tx_details) {
        this.context = context;
        this.tx_date = tx_date;
        this.tx_amount = tx_amount;
        this.tx_details = tx_details;
        this.tx_type = tx_type;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.date.setText(String.valueOf(tx_date.get(position)));
       holder.type.setText(String.valueOf(tx_type.get(position)));
        holder.details.setText(String.valueOf(tx_details.get(position)));
        holder.amount.setText(String.valueOf(tx_amount.get(position)));

    }

    @Override
    public int getItemCount() {
        return tx_date.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date,details,amount,type;
        public MyViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            details = itemView.findViewById(R.id.notes);
            amount = itemView.findViewById(R.id.amount);
            type  = itemView.findViewById(R.id.transactiontype);

        }
    }
}
