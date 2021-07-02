package com.example.e_voting.CandidateResult;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_voting.Adapters.WinnersAdapter;
import com.example.e_voting.Model.Condidate;
import com.example.e_voting.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CandidateResults_Amin_Lajnat_Alnashat_Alfnyi extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FirebaseDatabase db;
    private ArrayList <Condidate> list;
    private WinnersAdapter winnersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.candidate_results);
        getSupportActionBar().setTitle("نتائج الانتخابات");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView=findViewById(R.id.rv_candidateResult);
        db =FirebaseDatabase.getInstance();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getList();
    }

    private void getList() {

        db.getReference("المرشحون").child("امين لجنة النشاط الفني").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list=new ArrayList <>();
                winnersAdapter = new WinnersAdapter(CandidateResults_Amin_Lajnat_Alnashat_Alfnyi.this,list);
                recyclerView.setAdapter(winnersAdapter);
                for (DataSnapshot child :snapshot.getChildren())
                {
                    Condidate contractorModel =child.getValue(Condidate.class);
                    list.add(contractorModel);
                    winnersAdapter.notifyItemInserted(list.size()-1);
                    winnersAdapter.getItemCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CandidateResults_Amin_Lajnat_Alnashat_Alfnyi.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

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
