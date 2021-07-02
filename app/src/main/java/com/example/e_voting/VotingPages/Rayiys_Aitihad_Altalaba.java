package com.example.e_voting.VotingPages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import com.example.e_voting.Adapters.RecyclerView_InterFace;
import com.example.e_voting.Adapters.VotingAdapter;
import com.example.e_voting.Home_Student;
import com.example.e_voting.Model.Condidate;
import com.example.e_voting.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Rayiys_Aitihad_Altalaba extends AppCompatActivity implements RecyclerView_InterFace {

    private RecyclerView recyclerView;
    private FirebaseDatabase db;
    private ArrayList <Condidate> list;
    private VotingAdapter votingAdapter;

    TextView tv_name;
    public String SSN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voting_design);

        getSupportActionBar().setTitle("التصويت");


        Bundle extra = getIntent().getExtras();
        if (extra!=null){
            SSN = extra.getString("SSN");
        }

        recyclerView=findViewById(R.id.rv_vote);
        db =FirebaseDatabase.getInstance();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tv_name = findViewById(R.id.tv_name);

        tv_name.setText("رئيس اتحاد الطلبة");

        getList();

    }
    private void getList() {
        db.getReference("المرشحون").child("رئيس اتحاد الطلبة").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list=new ArrayList <>();
                votingAdapter = new VotingAdapter(Rayiys_Aitihad_Altalaba.this,list,Rayiys_Aitihad_Altalaba.this);
                recyclerView.setAdapter(votingAdapter);
                for (DataSnapshot child :snapshot.getChildren())
                {
                    Condidate condidate =child.getValue(Condidate.class);
                    list.add(condidate);
                    votingAdapter.notifyItemInserted(list.size()-1);
                    votingAdapter.getItemCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Rayiys_Aitihad_Altalaba.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

//    public void btnNext(View view) {
//            Intent intent = new Intent(Rayiys_Aitihad_Altalaba.this, Nayib_Rayiys_Aitihad_Altalaba.class);
//            intent.putExtra("رئيس اتحاد الطلبة", votingAdapter.getRadioButtonValue);
//            startActivity(intent);
//    }

    @Override
    public void onItemClick(final int position) {
        new AlertDialog.Builder(Rayiys_Aitihad_Altalaba.this)
                .setTitle("تاكيد")
                .setMessage("هل تريد التصويت؟")
                .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Rayiys_Aitihad_Altalaba.this, Nayib_Rayiys_Aitihad_Altalaba.class);
                        intent.putExtra("رئيس اتحاد الطلبة", votingAdapter.getRadioButtonValue);
                        intent.putExtra("SSN",SSN);
                        finish();
                        startActivity(intent);

                    }
                })
                .setNegativeButton("لا", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.AB_home:
                Intent intent = new Intent(Rayiys_Aitihad_Altalaba.this, Home_Student.class);
                intent.putExtra("SSN",SSN);
                startActivity(intent);
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}