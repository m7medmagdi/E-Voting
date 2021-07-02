package com.example.e_voting.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_voting.Model.MessageModel;
import com.example.e_voting.R;

import java.util.List;

public class NewsMessages extends RecyclerView.Adapter <NewsMessages.ViewHolder> {

    private Context context;
    private List <MessageModel> list;

    public NewsMessages(Context context, List <MessageModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NewsMessages.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsMessages.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_news, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsMessages.ViewHolder holder, int position) {

        final MessageModel raeesAt7adModel = list.get(position);
        holder.tv_name.setText(raeesAt7adModel.getMessage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_nameNews);

        }
    }

}
