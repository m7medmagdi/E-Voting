package com.example.e_voting.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_voting.Model.Condidate;
import com.example.e_voting.R;

import java.util.List;

public class ShowNominationForAdminAdapter extends RecyclerView.Adapter <ShowNominationForAdminAdapter.ViewHolder> {

    private Context context;
    private List <Condidate> list;
    private RecyclerView_InterFace recyclerView_interFace;

    public ShowNominationForAdminAdapter(Context context, List <Condidate> list, RecyclerView_InterFace recyclerView_interFace) {
        this.context = context;
        this.list = list;
        this.recyclerView_interFace = recyclerView_interFace;
    }

    @NonNull
    @Override
    public ShowNominationForAdminAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShowNominationForAdminAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShowNominationForAdminAdapter.ViewHolder holder, int position) {

        final Condidate raeesAt7adModel = list.get(position);
        holder.tv_name.setText(raeesAt7adModel.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            imageView = itemView.findViewById(R.id.image);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerView_interFace.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
