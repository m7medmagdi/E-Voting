package com.example.e_voting.VotingPages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.e_voting.Home_Student;
import com.example.e_voting.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FinalConfirmationActivity extends AppCompatActivity {

    String Rayiys_Aitihad_Altalaba, Nayib_Rayiys_Aitihad_Altalaba, Amin_Lajnat_Alusar, Nayib_Amin_Lajnat_Alusar,
            Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih, Nayib_Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih, Amin_Lajnat_Alnashat
            ,Nayib_Amin_Lajnat_Alnashat, Amin_Lajnat_Alnashat_Alaijtimaeii_Walrihlat,Nayib_Amin_Lajnat_Alnashat_Alaijtimaeii_Walrihlat
            ,Amin_Lajnat_Alnashat_Althaqafii_Waliielamii, Nayib_Amin_Lajnat_Alnashat_Althaqafii_Waliielamii,Amin_Lajnat_Alnashat_Alriyadii
            ,Nayib_Amin_Lajnat_Alnashat_Alriyadii, Amin_Lajnat_Alnashat_Alfnyi,Nayib_Amin_Lajnat_Alnashat_Alfnyi;

    String SSN;
    DatabaseReference db,users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_confirmation_voting_activity);
        getSupportActionBar().setTitle("التصويت");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        db = FirebaseDatabase.getInstance().getReference("المرشحون");
        users = FirebaseDatabase.getInstance().getReference("Users");

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
            Nayib_Amin_Lajnat_Alnashat_Alfnyi = extra.getString("نائب امين لجنة النشاط الفني");
            SSN = extra.getString("SSN");

//            SSN1 = extra.getString("SSN1");
//            SSN2 = extra.getString("SSN2");
//            SSN3 = extra.getString("SSN3");
//            SSN4 = extra.getString("SSN4");
//            SSN5 = extra.getString("SSN5");
//            SSN6 = extra.getString("SSN6");
//            SSN7 = extra.getString("SSN7");
//            SSN8 = extra.getString("SSN8");
//            SSN9 = extra.getString("SSN9");
//            SSN10 = extra.getString("SSN10");
//            SSN11 = extra.getString("SSN11");
//            SSN12 = extra.getString("SSN12");
//            SSN13 = extra.getString("SSN13");
//            SSN14 = extra.getString("SSN14");
//            SSN15 = extra.getString("SSN15");
//            SSN16 = extra.getString("SSN16");
//

        }
    }

    public void btnVote(View view) {
        new AlertDialog.Builder(this)
                .setTitle("تاكيد")
                .setMessage("هل تريد التصويت؟")
                .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        raees_at7ad_saveValue1();
                        naeeb_raees_at7ad_saveValue();
                        lgnt_elaser_saveValue();
                        naeeb_lgnt_elaser_saveValue();
                        lgnt_elgoala_and_khdma_3ama_saveValue();
                        naeeb_lgnt_elgoala_and_khdma_3ama_saveValue();
                        lgnt_elnshat_saveValue();
                        naeeb_lgnt_elnshat_saveValue();
                        lgnt_elnshat_elagtma3e_and_r7lat_saveValue();
                        naeeb_lgnt_elnshat_elagtma3e_and_r7lat_saveValue();
                        lgnt_elnshat_elthakafe_and_a3lame();
                        naeeb_lgnt_elnshat_elthakafe_and_a3lame();
                        lgnt_elnshat_elreade_saveValue();
                        naeeb_lgnt_elnshat_elreade_saveValue();
                        lgnt_elnshat_elfnee_saveValue();
                        naeeb_lgnt_elnshat_elfnee_saveValue();


                        users.child(SSN).child("userConditionVote").setValue("0");
                        Intent intent = new Intent(FinalConfirmationActivity.this, Home_Student.class);
                        intent.putExtra("SSN",SSN);
                        Toast.makeText(FinalConfirmationActivity.this, "تم التصويت", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("لا", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void raees_at7ad_saveValue1() {
        db.child("رئيس اتحاد الطلبة").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(Rayiys_Aitihad_Altalaba)) {

                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void naeeb_raees_at7ad_saveValue() {
        db.child("نائب رئيس اتحاد الطلبة").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(Nayib_Rayiys_Aitihad_Altalaba)) {
                        Integer count = child.child("count").getValue(Integer.class);
                        child.child("count").getRef().setValue(count + 1);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void lgnt_elaser_saveValue() {
        db.child("امين لجنة الاسر").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(Amin_Lajnat_Alusar)) {
                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void naeeb_lgnt_elaser_saveValue() {
        db.child("نائب امين لجنة الاسر").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(Nayib_Amin_Lajnat_Alusar)) {
                         Integer count = child.child("count").getValue(Integer.class);
                         child.child("count").getRef().setValue(count + 1);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void lgnt_elgoala_and_khdma_3ama_saveValue() {
        db.child("امين لجنة الجواله والخدمة العامه").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih)) {

                        Integer count = child.child("count").getValue(Integer.class);
                        child.child("count").getRef().setValue(count + 1);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void naeeb_lgnt_elgoala_and_khdma_3ama_saveValue() {
        db.child("نائب امين لجنة الجواله والخدمة العامه").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(Nayib_Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih)) {

                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void lgnt_elnshat_saveValue() {
        db.child("امين لجنة النشاط العلمي والتكنولوجي").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(Amin_Lajnat_Alnashat)) {
                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void naeeb_lgnt_elnshat_saveValue() {
        db.child("نائب امين لجنة النشاط العلمي والتكنولوجي").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(Nayib_Amin_Lajnat_Alnashat)) {
                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void lgnt_elnshat_elagtma3e_and_r7lat_saveValue() {
        db.child("امين لجنة النشاط الاجتماعي والرحلات").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(Amin_Lajnat_Alnashat_Alaijtimaeii_Walrihlat)) {
                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void naeeb_lgnt_elnshat_elagtma3e_and_r7lat_saveValue() {
        db.child("نائب امين لجنة النشاط الاجتماعي والرحلات").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(Nayib_Amin_Lajnat_Alnashat_Alaijtimaeii_Walrihlat)) {
                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);
                        }
                    }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void lgnt_elnshat_elthakafe_and_a3lame() {
        db.child("امين لجنة النشاط الثقافي والاعلامي").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(Amin_Lajnat_Alnashat_Althaqafii_Waliielamii)) {
                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);
                        }
                    }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void naeeb_lgnt_elnshat_elthakafe_and_a3lame() {
        db.child("نائب امين لجنة النشاط الثقافي والاعلامي").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(Nayib_Amin_Lajnat_Alnashat_Althaqafii_Waliielamii)) {
                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);
                        }
                    }
                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void lgnt_elnshat_elreade_saveValue() {
        db.child("امين لجنة النشاط الرياضي").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(Amin_Lajnat_Alnashat_Alriyadii)) {
                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);
                        }
                    }
                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void naeeb_lgnt_elnshat_elreade_saveValue() {
        db.child("نائب امين لجنة النشاط الرياضي").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(Nayib_Amin_Lajnat_Alnashat_Alriyadii)) {
                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void lgnt_elnshat_elfnee_saveValue() {
        db.child("امين لجنة النشاط الفني").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(Amin_Lajnat_Alnashat_Alfnyi)) {
                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void naeeb_lgnt_elnshat_elfnee_saveValue() {
        db.child("نائب امين لجنة النشاط الفني").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(Nayib_Amin_Lajnat_Alnashat_Alfnyi)) {
                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);
                        }
                    }
                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
                Intent intent2 = new Intent(FinalConfirmationActivity.this, Home_Student.class);
                intent2.putExtra("SSN",SSN);
                startActivity(intent2);
                finish();
                return true;
            case android.R.id.home:
                Intent intent = new Intent(FinalConfirmationActivity.this,Nayib_Amin_Lajnat_Alnashat_Alfnyi.class);
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
                intent.putExtra("SSN",SSN);
                startActivity(intent);
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}