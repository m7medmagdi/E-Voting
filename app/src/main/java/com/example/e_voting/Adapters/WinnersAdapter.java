package com.example.e_voting.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_voting.Model.Condidate;
import com.example.e_voting.R;

import java.util.List;

public class WinnersAdapter extends RecyclerView.Adapter <WinnersAdapter.ViewHolder>{
    private Context context;
    private List <Condidate> list;

    public WinnersAdapter(Context context, List <Condidate> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public WinnersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WinnersAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_candidate_results, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WinnersAdapter.ViewHolder holder, int position) {

        final Condidate raeesAt7adModel = list.get(position);
        holder.tv_name.setText(raeesAt7adModel.getName());
        holder.count.setText(raeesAt7adModel.getCount().toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name, count;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_candidateName);
            count = itemView.findViewById(R.id.tv_count);

        }
    }
}
