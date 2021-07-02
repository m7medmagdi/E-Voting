package com.example.e_voting.VotingPages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.e_voting.Home_Student;
import com.example.e_voting.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReVote2 extends AppCompatActivity {

    Spinner  Raees_At7ad_Spinner, Naeep_Raees_At7ad_Spinner, Lgnt_elaser_spinner, Naeeb_lgnt_elaser_spinner, lgnt_elgoal_and_khdma_3ama_spinner, Naeeb_lgnt_elgoala_and_khdma_3ama_spinner, lgnt_elnshat, naaeb_lgnt_elnshat_spinner, lgnt_elnshat_elagtma3e_and_r7lat, Naeeb_lgnt_elnshat_elagtma3e_and_r7lat_spinner, lgnt_elnshat_elsakafa_and_e3lame_spinner, naeep_lgnt_elnshat_elsakafa_and_e3lame_spinner, lgnt_elnshat_elreade, naeeb_lgnt_elnshat_elreade, lgnt_elnshat_elfnee, naeeb_lgnt_elnshat_elfnee;

    String item, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11, item12, item13, item14, item15, item16;
    ValueEventListener listener;
    ArrayList <String> list, list2, list3, list4, list5, list6, list7, list8, list9, list10, list11, list12, list13, list14, list15, list16;
    ArrayAdapter <String> adapter, adapter2, adapter3, adapter4, adapter5, adapter6, adapter7, adapter8, adapter9, adapter10, adapter11, adapter12, adapter13, adapter14, adapter15, adapter16;
    Button btnVote;
    DatabaseReference db,db2,users;
    LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4,linearLayout5,linearLayout6,linearLayout7,linearLayout8,
            linearLayout9,linearLayout10,linearLayout11,linearLayout12,linearLayout13,linearLayout14,linearLayout15,linearLayout16;
    String SSN,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.re_vote2);

        getSupportActionBar().setTitle("دورة الاعادة");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle extra = getIntent().getExtras();
        if (extra!=null){
            SSN = extra.getString("SSN");
            name = extra.getString("name");

        }

        db = FirebaseDatabase.getInstance().getReference("الاشخاص المتعادلين");
        db2 = FirebaseDatabase.getInstance().getReference("المرشحون");
        users = FirebaseDatabase.getInstance().getReference("Users");

        btnVote = findViewById(R.id.btnVote);
        linearLayout1 = findViewById(R.id.linear1);
        linearLayout2 = findViewById(R.id.linear2);
        linearLayout3 = findViewById(R.id.linear3);
        linearLayout4 = findViewById(R.id.linear4);
        linearLayout5 = findViewById(R.id.linear5);
        linearLayout6 = findViewById(R.id.linear6);
        linearLayout7 = findViewById(R.id.linear7);
        linearLayout8 = findViewById(R.id.linear8);
        linearLayout9 = findViewById(R.id.linear9);
        linearLayout10 = findViewById(R.id.linear10);
        linearLayout11 = findViewById(R.id.linear11);
        linearLayout12 = findViewById(R.id.linear12);
        linearLayout13 = findViewById(R.id.linear13);
        linearLayout14 = findViewById(R.id.linear14);
        linearLayout15 = findViewById(R.id.linear15);
        linearLayout16 = findViewById(R.id.linear16);


        Raees_At7ad_Spinner = findViewById(R.id.Raees_At7ad);
        Naeep_Raees_At7ad_Spinner = findViewById(R.id.Naeeb_Raees_At7ad);
        Lgnt_elaser_spinner = findViewById(R.id.lgnt_elaser_spinner);
        Naeeb_lgnt_elaser_spinner = findViewById(R.id.Naeeb_lgnt_elaser);
        lgnt_elgoal_and_khdma_3ama_spinner = findViewById(R.id.lgnt_elgoal_and_khdma_3ama_spinner);
        Naeeb_lgnt_elgoala_and_khdma_3ama_spinner = findViewById(R.id.Naeeb_lgnt_elgoala_and_khdma_3ama_spinner);
        lgnt_elnshat = findViewById(R.id.lgnt_elnshat);
        naaeb_lgnt_elnshat_spinner = findViewById(R.id.naaeb_lgnt_elnshat_spinner);
        lgnt_elnshat_elagtma3e_and_r7lat = findViewById(R.id.lgnt_elnshat_elagtma3e_and_r7lat);
        Naeeb_lgnt_elnshat_elagtma3e_and_r7lat_spinner = findViewById(R.id.Naeeb_lgnt_elnshat_elagtma3e_and_r7lat_spinner);
        lgnt_elnshat_elsakafa_and_e3lame_spinner = findViewById(R.id.lgnt_elnshat_elsakafa_and_e3lame_spinner);
        naeep_lgnt_elnshat_elsakafa_and_e3lame_spinner = findViewById(R.id.naeep_lgnt_elnshat_elsakafa_and_e3lame_spinner);
        lgnt_elnshat_elreade = findViewById(R.id.lgnt_elnshat_elreade);
        naeeb_lgnt_elnshat_elreade = findViewById(R.id.naeeb_lgnt_elnshat_elreade);
        lgnt_elnshat_elfnee = findViewById(R.id.lgnt_elnshat_elfnee);
        naeeb_lgnt_elnshat_elfnee = findViewById(R.id.naeeb_lgnt_elnshat_elfnee);

        list = new ArrayList <String>();
        adapter = new ArrayAdapter <>(this, R.layout.spinner, list);
        adapter.setDropDownViewResource(R.layout.spinner2);
        list2 = new ArrayList <String>();
        adapter2 = new ArrayAdapter <>(this, R.layout.spinner, list2);
        adapter2.setDropDownViewResource(R.layout.spinner2);
        list3 = new ArrayList <String>();
        adapter3 = new ArrayAdapter <>(this, R.layout.spinner, list3);
        adapter3.setDropDownViewResource(R.layout.spinner2);
        list4 = new ArrayList <String>();
        adapter4 = new ArrayAdapter <>(this, R.layout.spinner, list4);
        adapter4.setDropDownViewResource(R.layout.spinner2);
        list5 = new ArrayList <String>();
        adapter5 = new ArrayAdapter <>(this, R.layout.spinner, list5);
        adapter5.setDropDownViewResource(R.layout.spinner2);
        list6 = new ArrayList <String>();
        adapter6 = new ArrayAdapter <>(this, R.layout.spinner, list6);
        adapter6.setDropDownViewResource(R.layout.spinner2);
        list7 = new ArrayList <String>();
        adapter7 = new ArrayAdapter <>(this, R.layout.spinner, list7);
        adapter7.setDropDownViewResource(R.layout.spinner2);
        list8 = new ArrayList <String>();
        adapter8 = new ArrayAdapter <>(this, R.layout.spinner, list8);
        adapter8.setDropDownViewResource(R.layout.spinner2);
        list9 = new ArrayList <String>();
        adapter9 = new ArrayAdapter <>(this, R.layout.spinner, list9);
        adapter9.setDropDownViewResource(R.layout.spinner2);
        list10 = new ArrayList <String>();
        adapter10 = new ArrayAdapter <>(this, R.layout.spinner, list10);
        adapter10.setDropDownViewResource(R.layout.spinner2);
        list11 = new ArrayList <String>();
        adapter11 = new ArrayAdapter <>(this, R.layout.spinner, list11);
        adapter10.setDropDownViewResource(R.layout.spinner2);
        list12 = new ArrayList <String>();
        adapter12 = new ArrayAdapter <>(this, R.layout.spinner, list12);
        adapter12.setDropDownViewResource(R.layout.spinner2);
        list13 = new ArrayList <String>();
        adapter13 = new ArrayAdapter <>(this, R.layout.spinner, list13);
        adapter13.setDropDownViewResource(R.layout.spinner2);
        list14 = new ArrayList <String>();
        adapter14 = new ArrayAdapter <>(this, R.layout.spinner, list14);
        adapter14.setDropDownViewResource(R.layout.spinner2);
        list15 = new ArrayList <String>();
        adapter15 = new ArrayAdapter <>(this, R.layout.spinner, list15);
        adapter15.setDropDownViewResource(R.layout.spinner2);
        list16 = new ArrayList <String>();
        adapter16 = new ArrayAdapter <>(this, R.layout.spinner, list16);
        adapter16.setDropDownViewResource(R.layout.spinner2);

        Raees_At7ad_Spinner.setAdapter(adapter);
        Naeep_Raees_At7ad_Spinner.setAdapter(adapter2);
        Lgnt_elaser_spinner.setAdapter(adapter3);
        Naeeb_lgnt_elaser_spinner.setAdapter(adapter4);
        lgnt_elgoal_and_khdma_3ama_spinner.setAdapter(adapter5);
        Naeeb_lgnt_elgoala_and_khdma_3ama_spinner.setAdapter(adapter6);
        lgnt_elnshat.setAdapter(adapter7);
        naaeb_lgnt_elnshat_spinner.setAdapter(adapter8);
        lgnt_elnshat_elagtma3e_and_r7lat.setAdapter(adapter9);
        Naeeb_lgnt_elnshat_elagtma3e_and_r7lat_spinner.setAdapter(adapter10);
        lgnt_elnshat_elsakafa_and_e3lame_spinner.setAdapter(adapter11);
        naeep_lgnt_elnshat_elsakafa_and_e3lame_spinner.setAdapter(adapter12);
        lgnt_elnshat_elreade.setAdapter(adapter13);
        naeeb_lgnt_elnshat_elreade.setAdapter(adapter14);
        lgnt_elnshat_elfnee.setAdapter(adapter15);
        naeeb_lgnt_elnshat_elfnee.setAdapter(adapter16);

        fetchData_Raees_At7ad();
        fetchData_Naeeb_Raees_At7ad();
        fetchData_lgnt_elaser();
        fetchData_Naeeb_lgnt_elaser();
        fetchData_lgnt_elgoal_and_khdma_3ama_spinner();
        fetchData_Naeeb_lgnt_elgoala_and_khdma_3ama_spinner();
        fetchData_lgnt_elnshat();
        fetchData_naaeb_lgnt_elnshat_spinner();
        fetchData_lgnt_elnshat_elagtma3e_and_r7lat();
        fetchData_Naeeb_lgnt_elnshat_elagtma3e_and_r7lat_spinner();
        fetchData_lgnt_elnshat_elsakafa_and_e3lame_spinner();
        fetchData_naeep_lgnt_elnshat_elsakafa_and_e3lame_spinner();
        fetchData_lgnt_elnshat_elreade();
        fetchData_naeeb_lgnt_elnshat_elreade();
        fetchData_lgnt_elnshat_elfnee();
        fetchData_naeeb_lgnt_elnshat_elfnee();

        Raees_At7ad_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView <?> adapterView, View view, int i, long l) {

                item = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView <?> adapterView) {

            }
        });
        Naeep_Raees_At7ad_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView <?> adapterView, View view, int i, long l) {

                item2 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView <?> adapterView) {

            }
        });
        Lgnt_elaser_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView <?> adapterView, View view, int i, long l) {

                item3 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView <?> adapterView) {

            }
        });
        Naeeb_lgnt_elaser_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView <?> adapterView, View view, int i, long l) {

                item4 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView <?> adapterView) {

            }
        });
        lgnt_elgoal_and_khdma_3ama_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView <?> adapterView, View view, int i, long l) {

                item5 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView <?> adapterView) {

            }
        });
        Naeeb_lgnt_elgoala_and_khdma_3ama_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView <?> adapterView, View view, int i, long l) {

                item6 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView <?> adapterView) {

            }
        });
        lgnt_elnshat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView <?> adapterView, View view, int i, long l) {

                item7 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView <?> adapterView) {

            }
        });
        naaeb_lgnt_elnshat_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView <?> adapterView, View view, int i, long l) {

                item8 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView <?> adapterView) {

            }
        });
        lgnt_elnshat_elagtma3e_and_r7lat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView <?> adapterView, View view, int i, long l) {

                item9 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView <?> adapterView) {

            }
        });
        Naeeb_lgnt_elnshat_elagtma3e_and_r7lat_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView <?> adapterView, View view, int i, long l) {

                item10 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView <?> adapterView) {

            }
        });
        lgnt_elnshat_elsakafa_and_e3lame_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView <?> adapterView, View view, int i, long l) {

                item11 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView <?> adapterView) {

            }
        });
        naeep_lgnt_elnshat_elsakafa_and_e3lame_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView <?> adapterView, View view, int i, long l) {

                item12 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView <?> adapterView) {

            }
        });
        lgnt_elnshat_elreade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView <?> adapterView, View view, int i, long l) {

                item13 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView <?> adapterView) {

            }
        });
        naeeb_lgnt_elnshat_elreade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView <?> adapterView, View view, int i, long l) {

                item14 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView <?> adapterView) {

            }
        });
        lgnt_elnshat_elfnee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView <?> adapterView, View view, int i, long l) {

                item15 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView <?> adapterView) {

            }
        });
        naeeb_lgnt_elnshat_elfnee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView <?> adapterView, View view, int i, long l) {

                item16 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView <?> adapterView) {

            }
        });

        btnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (Raees_At7ad_Spinner.getSelectedItemPosition()==0 || Naeep_Raees_At7ad_Spinner.getSelectedItemPosition()==0 ||
//                        Lgnt_elaser_spinner.getSelectedItemPosition()==0 || Naeeb_lgnt_elaser_spinner.getSelectedItemPosition()==0 ||
//                        lgnt_elgoal_and_khdma_3ama_spinner.getSelectedItemPosition()==0 || Naeeb_lgnt_elgoala_and_khdma_3ama_spinner.getSelectedItemPosition()==0 ||
//                        lgnt_elnshat.getSelectedItemPosition()==0 || naaeb_lgnt_elnshat_spinner.getSelectedItemPosition()==0 ||
//                        lgnt_elnshat_elagtma3e_and_r7lat.getSelectedItemPosition()==0 || Naeeb_lgnt_elnshat_elagtma3e_and_r7lat_spinner.getSelectedItemPosition()==0 ||
//                        lgnt_elnshat_elsakafa_and_e3lame_spinner.getSelectedItemPosition()==0  || naeep_lgnt_elnshat_elsakafa_and_e3lame_spinner.getSelectedItemPosition()==0 ||
//                        lgnt_elnshat_elreade.getSelectedItemPosition()==0 || naeeb_lgnt_elnshat_elreade.getSelectedItemPosition()==0 ||
//                        lgnt_elnshat_elfnee.getSelectedItemPosition()==0 || naeeb_lgnt_elnshat_elfnee.getSelectedItemPosition()==0) {
//
//                    new AlertDialog.Builder(VotingPage.this)
//                            .setTitle("عذرا")
//                            .setMessage("لم تقم بالتصويت الي جميع المناصب")
//                            .setPositiveButton("حسنا", null)
//                            .setIcon(android.R.drawable.ic_dialog_alert)
//                            .show();
//
//                } else {
                    new AlertDialog.Builder(ReVote2.this)
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

                                    Intent intent = new Intent(ReVote2.this, Home_Student.class);
                                    intent.putExtra("SSN", SSN);
                                    intent.putExtra("name", name);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .setNegativeButton("لا", null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
//                }
            }
        });
    }


    private void fetchData_Raees_At7ad() {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("رئيس اتحاد الطلبة").exists()){
                    linearLayout1.setVisibility(View.VISIBLE);
                    listener = db.child("رئيس اتحاد الطلبة").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot child : snapshot.getChildren()) {
                                list.add(child.child("name").getValue().toString());
                                adapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void fetchData_Naeeb_Raees_At7ad() {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("نائب رئيس اتحاد الطلبة").exists()){
                    linearLayout2.setVisibility(View.VISIBLE);
                    listener = db.child("نائب رئيس اتحاد الطلبة").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot child : snapshot.getChildren()) {
                                list2.add(child.child("name").getValue().toString());
                                adapter2.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void fetchData_lgnt_elaser() {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("امين لجنة الاسر").exists()){
                    linearLayout3.setVisibility(View.VISIBLE);
                    listener = db.child("امين لجنة الاسر").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot child : snapshot.getChildren()) {
                                list3.add(child.child("name").getValue().toString());
                                adapter3.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void fetchData_Naeeb_lgnt_elaser() {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("نائب امين لجنة الاسر").exists()){
                    linearLayout4.setVisibility(View.VISIBLE);
                    listener = db.child("نائب امين لجنة الاسر").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot child : snapshot.getChildren()) {
                                list4.add(child.child("name").getValue().toString());
                                adapter4.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void fetchData_lgnt_elgoal_and_khdma_3ama_spinner() {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("امين لجنة الجواله والخدمة العامه").exists()){
                    linearLayout5.setVisibility(View.VISIBLE);
                    listener = db.child("امين لجنة الجواله والخدمة العامه").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot child : snapshot.getChildren()) {
                                list5.add(child.child("name").getValue().toString());
                                adapter5.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void fetchData_Naeeb_lgnt_elgoala_and_khdma_3ama_spinner() {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("نائب امين لجنة الجواله والخدمة العامه").exists()){
                    linearLayout6.setVisibility(View.VISIBLE);
                    listener = db.child("نائب امين لجنة الجواله والخدمة العامه").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot child : snapshot.getChildren()) {
                                list6.add(child.child("name").getValue().toString());
                                adapter6.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void fetchData_lgnt_elnshat() {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("امين لجنة النشاط العلمي والتكنولوجي").exists()){
                    linearLayout7.setVisibility(View.VISIBLE);
                    listener = db.child("امين لجنة النشاط العلمي والتكنولوجي").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot child : snapshot.getChildren()) {
                                list7.add(child.child("name").getValue().toString());
                                adapter7.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void fetchData_naaeb_lgnt_elnshat_spinner() {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("نائب امين لجنة النشاط العلمي والتكنولوجي").exists()){
                    linearLayout8.setVisibility(View.VISIBLE);
                    listener = db.child("نائب امين لجنة النشاط العلمي والتكنولوجي").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot child : snapshot.getChildren()) {
                                list8.add(child.child("name").getValue().toString());
                                adapter8.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void fetchData_lgnt_elnshat_elagtma3e_and_r7lat() {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("امين لجنة النشاط الاجتماعي والرحلات").exists()){
                    linearLayout9.setVisibility(View.VISIBLE);
                    listener = db.child("امين لجنة النشاط الاجتماعي والرحلات").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot child : snapshot.getChildren()) {
                                list9.add(child.child("name").getValue().toString());
                                adapter9.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void fetchData_Naeeb_lgnt_elnshat_elagtma3e_and_r7lat_spinner() {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("نائب امين لجنة النشاط الاجتماعي والرحلات").exists()){
                    linearLayout10.setVisibility(View.VISIBLE);
                    listener = db.child("نائب امين لجنة النشاط الاجتماعي والرحلات").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot child : snapshot.getChildren()) {
                                list10.add(child.child("name").getValue().toString());
                                adapter10.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void fetchData_lgnt_elnshat_elsakafa_and_e3lame_spinner() {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("امين لجنة النشاط الثقافي والاعلامي").exists()){
                    linearLayout11.setVisibility(View.VISIBLE);
                    listener = db.child("امين لجنة النشاط الثقافي والاعلامي").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot child : snapshot.getChildren()) {
                                list11.add(child.child("name").getValue().toString());
                                adapter11.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void fetchData_naeep_lgnt_elnshat_elsakafa_and_e3lame_spinner() {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("نائب امين لجنة النشاط الثقافي والاعلامي").exists()){
                    linearLayout12.setVisibility(View.VISIBLE);
                    listener = db.child("نائب امين لجنة النشاط الثقافي والاعلامي").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot child : snapshot.getChildren()) {
                                list12.add(child.child("name").getValue().toString());
                                adapter12.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void fetchData_lgnt_elnshat_elreade() {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("امين لجنة النشاط الرياضي").exists()){
                    linearLayout13.setVisibility(View.VISIBLE);
                    listener = db.child("امين لجنة النشاط الرياضي").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot child : snapshot.getChildren()) {
                                list13.add(child.child("name").getValue().toString());
                                adapter13.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void fetchData_naeeb_lgnt_elnshat_elreade() {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("نائب امين لجنة النشاط الرياضي").exists()){
                    linearLayout14.setVisibility(View.VISIBLE);
                    listener = db.child("نائب امين لجنة النشاط الرياضي").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot child : snapshot.getChildren()) {
                                list14.add(child.child("name").getValue().toString());
                                adapter14.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void fetchData_lgnt_elnshat_elfnee() {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("امين لجنة النشاط الفني").exists()){
                    linearLayout15.setVisibility(View.VISIBLE);
                    listener = db.child("امين لجنة النشاط الفني").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot child : snapshot.getChildren()) {
                                list15.add(child.child("name").getValue().toString());
                                adapter15.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void fetchData_naeeb_lgnt_elnshat_elfnee() {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("نائب امين لجنة النشاط الفني").exists()){
                    linearLayout16.setVisibility(View.VISIBLE);
                    listener = db.child("نائب امين لجنة النشاط الفني").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot child : snapshot.getChildren()) {
                                list16.add(child.child("name").getValue().toString());
                                adapter16.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    private void raees_at7ad_saveValue1() {
        db.child("رئيس اتحاد الطلبة").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(item)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);
//                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        db2.child("رئيس اتحاد الطلبة").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(item)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                        Integer count = child.child("count").getValue(Integer.class);
                        child.child("count").getRef().setValue(count + 1);
//                        }
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
                    if (child.child("name").getValue().toString().equals(item2)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);
//                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        db2.child("نائب رئيس اتحاد الطلبة").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(item2)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                        Integer count = child.child("count").getValue(Integer.class);
                        child.child("count").getRef().setValue(count + 1);
//                        }
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
                    if (child.child("name").getValue().toString().equals(item3)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);
//                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        db2.child("امين لجنة الاسر").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(item3)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                        Integer count = child.child("count").getValue(Integer.class);
                        child.child("count").getRef().setValue(count + 1);
//                        }
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
                    if (child.child("name").getValue().toString().equals(item4)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);
//                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        db2.child("نائب امين لجنة الاسر").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(item4)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                        Integer count = child.child("count").getValue(Integer.class);
                        child.child("count").getRef().setValue(count + 1);
//                        }
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
                    if (child.child("name").getValue().toString().equals(item5)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);
//                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        db2.child("امين لجنة الجواله والخدمة العامه").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(item5)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                        Integer count = child.child("count").getValue(Integer.class);
                        child.child("count").getRef().setValue(count + 1);
//                        }
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
                    if (child.child("name").getValue().toString().equals(item6)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);
//                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        db2.child("نائب امين لجنة الجواله والخدمة العامه").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(item6)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                        Integer count = child.child("count").getValue(Integer.class);
                        child.child("count").getRef().setValue(count + 1);
//                        }
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
                    if (child.child("name").getValue().toString().equals(item7)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);
//                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        db2.child("امين لجنة النشاط العلمي والتكنولوجي").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(item7)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                        Integer count = child.child("count").getValue(Integer.class);
                        child.child("count").getRef().setValue(count + 1);
//                        }
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
                    if (child.child("name").getValue().toString().equals(item8)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);
//                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        db2.child("نائب امين لجنة النشاط العلمي والتكنولوجي").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(item8)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                        Integer count = child.child("count").getValue(Integer.class);
                        child.child("count").getRef().setValue(count + 1);
//                        }
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
                    if (child.child("name").getValue().toString().equals(item9)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);
//                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        db2.child("امين لجنة النشاط الاجتماعي والرحلات").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(item9)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                        Integer count = child.child("count").getValue(Integer.class);
                        child.child("count").getRef().setValue(count + 1);
//                        }
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
                    if (child.child("name").getValue().toString().equals(item10)) {
                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {

                        } else {
                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        db2.child("نائب امين لجنة النشاط الاجتماعي والرحلات").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(item10)) {
                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {

                        } else {
                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);
                        }
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
                    if (child.child("name").getValue().toString().equals(item11)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);
//                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        db2.child("امين لجنة النشاط الثقافي والاعلامي").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(item11)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                        Integer count = child.child("count").getValue(Integer.class);
                        child.child("count").getRef().setValue(count + 1);
//                        }
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
                    if (child.child("name").getValue().toString().equals(item12)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);
//                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        db2.child("نائب امين لجنة النشاط الثقافي والاعلامي").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(item12)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                        Integer count = child.child("count").getValue(Integer.class);
                        child.child("count").getRef().setValue(count + 1);
//                        }
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
                    if (child.child("name").getValue().toString().equals(item13)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);
//                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        db2.child("امين لجنة النشاط الرياضي").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(item13)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                        Integer count = child.child("count").getValue(Integer.class);
                        child.child("count").getRef().setValue(count + 1);
//                        }
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
                    if (child.child("name").getValue().toString().equals(item14)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);
//                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        db2.child("نائب امين لجنة النشاط الرياضي").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(item14)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                        Integer count = child.child("count").getValue(Integer.class);
                        child.child("count").getRef().setValue(count + 1);
//                        }
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
                    if (child.child("name").getValue().toString().equals(item15)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);
//                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        db2.child("امين لجنة النشاط الفني").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(item15)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                        Integer count = child.child("count").getValue(Integer.class);
                        child.child("count").getRef().setValue(count + 1);
//                        }
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
                    if (child.child("name").getValue().toString().equals(item16)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                            Integer count = child.child("count").getValue(Integer.class);
                            child.child("count").getRef().setValue(count + 1);
//                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        db2.child("نائب امين لجنة النشاط الفني").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    if (child.child("name").getValue().toString().equals(item16)) {
//                        if (child.child("name").getValue().toString().equals("اضغط للتصويت")) {
//
//                        } else {
                        Integer count = child.child("count").getValue(Integer.class);
                        child.child("count").getRef().setValue(count + 1);
//                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void btnBack(View view) {
        Intent intent = new Intent(ReVote2.this, Home_Student.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                Intent intent = new Intent(ReVote2.this, Home_Student.class);
                intent.putExtra("SSN", SSN);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}

