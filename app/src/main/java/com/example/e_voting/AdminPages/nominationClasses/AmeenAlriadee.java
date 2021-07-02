package com.example.e_voting.AdminPages.nominationClasses;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_voting.Adapters.RecyclerView_InterFace;
import com.example.e_voting.Adapters.ShowNominationForAdminAdapter;
import com.example.e_voting.Model.Condidate;
import com.example.e_voting.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AmeenAlriadee extends AppCompatActivity implements RecyclerView_InterFace {

    private RecyclerView recyclerView;
    private FirebaseDatabase db;
    private ArrayList <Condidate> list;
    private ShowNominationForAdminAdapter showNominationForAdminAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.raees_at7ad_for_nomination);
        getSupportActionBar().setTitle("الاطلاع علي الترشحات");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView=findViewById(R.id.rv);
        db =FirebaseDatabase.getInstance();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(AmeenAlriadee.this));

        getList();
    }

    private void getList() {

        db.getReference("المرشحون").child("امين لجنة النشاط الرياضي").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list=new ArrayList <>();
                showNominationForAdminAdapter = new ShowNominationForAdminAdapter(AmeenAlriadee.this,list, AmeenAlriadee.this);
                recyclerView.setAdapter(showNominationForAdminAdapter);
                for (DataSnapshot child :snapshot.getChildren())
                {
                    Condidate contractorModel =child.getValue(Condidate.class);
                    list.add(contractorModel);
                    showNominationForAdminAdapter.notifyItemInserted(list.size()-1);
                    showNominationForAdminAdapter.getItemCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AmeenAlriadee.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onItemClick(int position) {
        Intent intent  = new Intent(AmeenAlriadee.this, DeleteNomination.class);
        intent.putExtra("SSN",list.get(position).getId());
        intent.putExtra("name",list.get(position).getName());
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return true;
    }
}
