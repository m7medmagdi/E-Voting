package com.example.e_voting.VotingPages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
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

public class Nayib_Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih extends AppCompatActivity implements RecyclerView_InterFace {

    private RecyclerView recyclerView;
    private FirebaseDatabase db;
    private ArrayList <Condidate> list;
    private VotingAdapter votingAdapter;

    String Rayiys_Aitihad_Altalaba, Nayib_Rayiys_Aitihad_Altalaba, Amin_Lajnat_Alusar, Nayib_Amin_Lajnat_Alusar,
            Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih,SSN;
    TextView tv_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voting_design);
        getSupportActionBar().setTitle("التصويت");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        recyclerView=findViewById(R.id.rv_vote);
        db =FirebaseDatabase.getInstance();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tv_name = findViewById(R.id.tv_name);

        tv_name.setText("نائب امين لجنة الجواله والخدمة العامه");

        Bundle extra = getIntent().getExtras();
        if (extra!=null){
            Rayiys_Aitihad_Altalaba = extra.getString("رئيس اتحاد الطلبة");
            Nayib_Rayiys_Aitihad_Altalaba = extra.getString("نائب رئيس اتحاد الطلبة");
            Amin_Lajnat_Alusar  = extra.getString("امين لجنة الاسر");
            Nayib_Amin_Lajnat_Alusar = extra.getString("نائب امين لجنة الاسر");
            Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih = extra.getString("امين لجنة الجواله والخدمة العامه");
            SSN  = extra.getString("SSN");

        }

        getList();
    }

    private void getList() {
        db.getReference("المرشحون").child("نائب امين لجنة الجواله والخدمة العامه").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list=new ArrayList <>();
                votingAdapter = new VotingAdapter(Nayib_Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih.this,list, Nayib_Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih.this);
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
                Toast.makeText(Nayib_Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

//    public void btnNext(View view) {
//        Intent intent = new Intent(Nayib_Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih.this, Amin_Lajnat_Alnashat.class);
//        intent.putExtra("رئيس اتحاد الطلبة",Rayiys_Aitihad_Altalaba);
//        intent.putExtra("نائب رئيس اتحاد الطلبة",Nayib_Rayiys_Aitihad_Altalaba);
//        intent.putExtra("امين لجنة الاسر",Amin_Lajnat_Alusar);
//        intent.putExtra("نائب امين لجنة الاسر",Nayib_Amin_Lajnat_Alusar);
//        intent.putExtra("امين لجنة الجواله والخدمة العامه",Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih);
//        intent.putExtra("نائب امين لجنة الجواله والخدمة العامه",votingAdapter.getRadioButtonValue);
//        startActivity(intent);
//    }

    @Override
    public void onItemClick(final int position) {
        new AlertDialog.Builder(Nayib_Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih.this)
                .setTitle("تاكيد")
                .setMessage("هل تريد التصويت؟")
                .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Nayib_Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih.this, Amin_Lajnat_Alnashat.class);
                        intent.putExtra("رئيس اتحاد الطلبة",Rayiys_Aitihad_Altalaba);
                        intent.putExtra("نائب رئيس اتحاد الطلبة",Nayib_Rayiys_Aitihad_Altalaba);
                        intent.putExtra("امين لجنة الاسر",Amin_Lajnat_Alusar);
                        intent.putExtra("نائب امين لجنة الاسر",Nayib_Amin_Lajnat_Alusar);
                        intent.putExtra("امين لجنة الجواله والخدمة العامه",Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih);
                        intent.putExtra("نائب امين لجنة الجواله والخدمة العامه",votingAdapter.getRadioButtonValue);
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
                Intent intent2 = new Intent(Nayib_Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih.this, Home_Student.class);
                intent2.putExtra("SSN",SSN);
                startActivity(intent2);
                finish();
                return true;
            case android.R.id.home:
                Intent intent = new Intent(Nayib_Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih.this,Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih.class);
                intent.putExtra("رئيس اتحاد الطلبة",Rayiys_Aitihad_Altalaba);
                intent.putExtra("نائب رئيس اتحاد الطلبة",Nayib_Rayiys_Aitihad_Altalaba);
                intent.putExtra("امين لجنة الاسر",Amin_Lajnat_Alusar);
                intent.putExtra("نائب امين لجنة الاسر",Nayib_Amin_Lajnat_Alusar);
                intent.putExtra("SSN",SSN);
                startActivity(intent);
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}