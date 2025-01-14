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

public class Nayib_Amin_Lajnat_Alnashat_Alfnyi extends AppCompatActivity implements RecyclerView_InterFace {

    private RecyclerView recyclerView;
    private FirebaseDatabase db;
    private ArrayList <Condidate> list;
    private VotingAdapter votingAdapter;

    String Rayiys_Aitihad_Altalaba, Nayib_Rayiys_Aitihad_Altalaba, Amin_Lajnat_Alusar, Nayib_Amin_Lajnat_Alusar,
            Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih, Nayib_Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih, Amin_Lajnat_Alnashat
            ,Nayib_Amin_Lajnat_Alnashat, Amin_Lajnat_Alnashat_Alaijtimaeii_Walrihlat,Nayib_Amin_Lajnat_Alnashat_Alaijtimaeii_Walrihlat
            ,Amin_Lajnat_Alnashat_Althaqafii_Waliielamii, Nayib_Amin_Lajnat_Alnashat_Althaqafii_Waliielamii,Amin_Lajnat_Alnashat_Alriyadii
            ,Nayib_Amin_Lajnat_Alnashat_Alriyadii, Amin_Lajnat_Alnashat_Alfnyi,SSN;
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

        tv_name.setText("نائب امين لجنة النشاط الفني");

        Bundle extra = getIntent().getExtras();
        if (extra!=null){
            Rayiys_Aitihad_Altalaba = extra.getString("رئيس اتحاد الطلبة");
            Nayib_Rayiys_Aitihad_Altalaba = extra.getString("نائب رئيس اتحاد الطلبة");
            Amin_Lajnat_Alusar  = extra.getString("امين لجنة الاسر");
            Nayib_Amin_Lajnat_Alusar = extra.getString("نائب امين لجنة الاسر");
            Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih = extra.getString("امين لجنة الجواله والخدمة العامه");
            Nayib_Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih = extra.getString("نائب امين لجنة الجواله والخدمة العامه");
            Amin_Lajnat_Alnashat = extra.getString("امين لجنة النشاط");
            Nayib_Amin_Lajnat_Alnashat= extra.getString("نائب امين لجنة النشاط");
            Amin_Lajnat_Alnashat_Alaijtimaeii_Walrihlat = extra.getString("امين لجنة النشاط الاجتماعي والرحلات");
            Nayib_Amin_Lajnat_Alnashat_Alaijtimaeii_Walrihlat = extra.getString("نائب امين لجنة النشاط الاجتماعي والرحلات");
            Amin_Lajnat_Alnashat_Althaqafii_Waliielamii = extra.getString("امين لجنة النشاط الثقافي والاعلامي");
            Nayib_Amin_Lajnat_Alnashat_Althaqafii_Waliielamii = extra.getString("نائب امين لجنة النشاط الثقافي والاعلامي");
            Amin_Lajnat_Alnashat_Alriyadii = extra.getString("امين لجنة النشاط الرياضي");
            Nayib_Amin_Lajnat_Alnashat_Alriyadii= extra.getString("نائب امين لجنة النشاط الرياضي");
            Amin_Lajnat_Alnashat_Alfnyi = extra.getString("امين لجنة النشاط الفني");
            SSN = extra.getString("SSN");

        }
        getList();
    }
    private void getList() {
        db.getReference("المرشحون").child("نائب امين لجنة النشاط الفني").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list=new ArrayList <>();
                votingAdapter = new VotingAdapter(Nayib_Amin_Lajnat_Alnashat_Alfnyi.this,list, Nayib_Amin_Lajnat_Alnashat_Alfnyi.this);
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
                Toast.makeText(Nayib_Amin_Lajnat_Alnashat_Alfnyi.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

//    public void btnNext(View view) {
//        Intent intent = new Intent(Nayib_Amin_Lajnat_Alnashat_Alfnyi.this, FinalConfirmationActivity.class);
//        intent.putExtra("رئيس اتحاد الطلبة",Rayiys_Aitihad_Altalaba);
//        intent.putExtra("نائب رئيس اتحاد الطلبة",Nayib_Rayiys_Aitihad_Altalaba);
//        intent.putExtra("امين لجنة الاسر",Amin_Lajnat_Alusar);
//        intent.putExtra("نائب امين لجنة الاسر",Nayib_Amin_Lajnat_Alusar);
//        intent.putExtra("امين لجنة الجواله والخدمة العامه",Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih);
//        intent.putExtra("نائب امين لجنة الجواله والخدمة العامه",Nayib_Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih);
//        intent.putExtra("امين لجنة النشاط",Amin_Lajnat_Alnashat);
//        intent.putExtra("نائب امين لجنة النشاط",Nayib_Amin_Lajnat_Alnashat);
//        intent.putExtra("امين لجنة النشاط الاجتماعي والرحلات",Amin_Lajnat_Alnashat_Alaijtimaeii_Walrihlat);
//        intent.putExtra("نائب امين لجنة النشاط الاجتماعي والرحلات",Nayib_Amin_Lajnat_Alnashat_Alaijtimaeii_Walrihlat);
//        intent.putExtra("امين لجنة النشاط الثقافي والاعلامي",Amin_Lajnat_Alnashat_Althaqafii_Waliielamii);
//        intent.putExtra("نائب امين لجنة النشاط الثقافي والاعلامي",Nayib_Amin_Lajnat_Alnashat_Althaqafii_Waliielamii);
//        intent.putExtra("امين لجنة النشاط الرياضي",Amin_Lajnat_Alnashat_Alriyadii);
//        intent.putExtra("نائب امين لجنة النشاط الرياضي",Nayib_Amin_Lajnat_Alnashat_Alriyadii);
//        intent.putExtra("امين لجنة النشاط الفني",Amin_Lajnat_Alnashat_Alfnyi);
//        intent.putExtra("نائب امين لجنة النشاط الفني",votingAdapter.getRadioButtonValue);
//        startActivity(intent);
//    }

    @Override
    public void onItemClick(final int position) {
        new AlertDialog.Builder(Nayib_Amin_Lajnat_Alnashat_Alfnyi.this)
                .setTitle("تاكيد")
                .setMessage("هل تريد التصويت؟")
                .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Nayib_Amin_Lajnat_Alnashat_Alfnyi.this, FinalConfirmationActivity.class);
                        intent.putExtra("رئيس اتحاد الطلبة",Rayiys_Aitihad_Altalaba);
                        intent.putExtra("نائب رئيس اتحاد الطلبة",Nayib_Rayiys_Aitihad_Altalaba);
                        intent.putExtra("امين لجنة الاسر",Amin_Lajnat_Alusar);
                        intent.putExtra("نائب امين لجنة الاسر",Nayib_Amin_Lajnat_Alusar);
                        intent.putExtra("امين لجنة الجواله والخدمة العامه",Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih);
                        intent.putExtra("نائب امين لجنة الجواله والخدمة العامه",Nayib_Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih);
                        intent.putExtra("امين لجنة النشاط",Amin_Lajnat_Alnashat);
                        intent.putExtra("نائب امين لجنة النشاط",Nayib_Amin_Lajnat_Alnashat);
                        intent.putExtra("امين لجنة النشاط الاجتماعي والرحلات",Amin_Lajnat_Alnashat_Alaijtimaeii_Walrihlat);
                        intent.putExtra("نائب امين لجنة النشاط الاجتماعي والرحلات",Nayib_Amin_Lajnat_Alnashat_Alaijtimaeii_Walrihlat);
                        intent.putExtra("امين لجنة النشاط الثقافي والاعلامي",Amin_Lajnat_Alnashat_Althaqafii_Waliielamii);
                        intent.putExtra("نائب امين لجنة النشاط الثقافي والاعلامي",Nayib_Amin_Lajnat_Alnashat_Althaqafii_Waliielamii);
                        intent.putExtra("امين لجنة النشاط الرياضي",Amin_Lajnat_Alnashat_Alriyadii);
                        intent.putExtra("نائب امين لجنة النشاط الرياضي",Nayib_Amin_Lajnat_Alnashat_Alriyadii);
                        intent.putExtra("امين لجنة النشاط الفني",Amin_Lajnat_Alnashat_Alfnyi);
                        intent.putExtra("نائب امين لجنة النشاط الفني",votingAdapter.getRadioButtonValue);
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
                Intent intent2 = new Intent(Nayib_Amin_Lajnat_Alnashat_Alfnyi.this, Home_Student.class);
                intent2.putExtra("SSN",SSN);
                startActivity(intent2);
                finish();
                return true;
            case android.R.id.home:
                Intent intent = new Intent(Nayib_Amin_Lajnat_Alnashat_Alfnyi.this,Amin_Lajnat_Alnashat_Alfnyi.class);
                intent.putExtra("رئيس اتحاد الطلبة",Rayiys_Aitihad_Altalaba);
                intent.putExtra("نائب رئيس اتحاد الطلبة",Nayib_Rayiys_Aitihad_Altalaba);
                intent.putExtra("امين لجنة الاسر",Amin_Lajnat_Alusar);
                intent.putExtra("نائب امين لجنة الاسر",Nayib_Amin_Lajnat_Alusar);
                intent.putExtra("امين لجنة الجواله والخدمة العامه",Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih);
                intent.putExtra("نائب امين لجنة الجواله والخدمة العامه",Nayib_Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih);
                intent.putExtra("امين لجنة النشاط",Amin_Lajnat_Alnashat);
                intent.putExtra("نائب امين لجنة النشاط",Nayib_Amin_Lajnat_Alnashat);
                intent.putExtra("امين لجنة النشاط الاجتماعي والرحلات",Amin_Lajnat_Alnashat_Alaijtimaeii_Walrihlat);
                intent.putExtra("نائب امين لجنة النشاط الاجتماعي والرحلات",Nayib_Amin_Lajnat_Alnashat_Alaijtimaeii_Walrihlat);
                intent.putExtra("امين لجنة النشاط الثقافي والاعلامي",Amin_Lajnat_Alnashat_Althaqafii_Waliielamii);
                intent.putExtra("نائب امين لجنة النشاط الثقافي والاعلامي",Nayib_Amin_Lajnat_Alnashat_Althaqafii_Waliielamii);
                intent.putExtra("امين لجنة النشاط الرياضي",Amin_Lajnat_Alnashat_Alriyadii);
                intent.putExtra("نائب امين لجنة النشاط الرياضي",Nayib_Amin_Lajnat_Alnashat_Alriyadii);
                intent.putExtra("SSN",SSN);
                startActivity(intent);
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}