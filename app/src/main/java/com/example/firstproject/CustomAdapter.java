package com.example.firstproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList title_id;
    private ArrayList title_name;
    private ArrayList title_type;
    CustomAdapter(Context context,
                  ArrayList title_id,
                  ArrayList title_name,
                  ArrayList title_type){

        this.context = context;
        this.title_id = title_id;
        this.title_name = title_name;
        this.title_type = title_type;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title_id_txt.setText(String.valueOf(title_id.get(position)));
        holder.title_name_txt.setText(String.valueOf(title_name.get(position)));
        holder.title_type_txt.setText(String.valueOf(title_type.get(position)));
    }

    @Override
    public int getItemCount() {
        return title_id.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title_id_txt, title_name_txt, title_type_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title_id_txt = itemView.findViewById(R.id.title_id_txt);
            title_name_txt = itemView.findViewById(R.id.title_name_txt);
            title_type_txt = itemView.findViewById(R.id.title_type_txt);
        }
    }
}
