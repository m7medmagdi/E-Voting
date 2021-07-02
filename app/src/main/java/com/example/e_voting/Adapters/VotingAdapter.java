package com.example.e_voting.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.e_voting.Model.Condidate;
import com.example.e_voting.R;

import java.util.List;

public class VotingAdapter extends RecyclerView.Adapter <VotingAdapter.ViewHolder> {

    private Context context;
    private List <Condidate> list;
    private int lastSelectedPosition = -1;
    public String getRadioButtonValue;
    private RecyclerView_InterFace recyclerView_interFace;


    public VotingAdapter(Context context, List <Condidate> list, RecyclerView_InterFace recyclerView_interFace) {
        this.context = context;
        this.list = list;
        this.recyclerView_interFace = recyclerView_interFace;
    }

    @NonNull
    @Override
    public VotingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VotingAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_voting_radio_button, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final Condidate raeesAt7adModel = list.get(position);

        holder.radioButton.setText(raeesAt7adModel.getName());
        holder.radioButton.setChecked(lastSelectedPosition == position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RadioButton radioButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            radioButton = itemView.findViewById(R.id.votingRadioButton);

            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastSelectedPosition = getAdapterPosition();
                    getRadioButtonValue = radioButton.getText().toString();
                    notifyDataSetChanged();
                    recyclerView_interFace.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
