package com.example.eggproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EggAdapter extends RecyclerView.Adapter<EggAdapter.MyViewHolder> {
    private ArrayList<Player> playerList;

    public EggAdapter(ArrayList<Player> playerList){
        this.playerList = playerList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Player currentUser = playerList.get(position);
        holder.tvName.setText(currentUser.getName());
        holder.tvScore.setText(String.valueOf(currentUser.getScore()));
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvScore;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_row_name);
            tvScore = itemView.findViewById(R.id.tv_row_score);
        }
    }

}
