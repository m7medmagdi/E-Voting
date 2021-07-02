package com.example.e_voting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.e_voting.Model.Winers;
import com.example.e_voting.NominationPages.Nomination;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CurrentMember extends AppCompatActivity {

    private DatabaseReference reference,currentStage,currentMembers,dbEqualsVotes;
    String countwin;

    TextView txt_winner1,txt_winner2,txt_winner3,txt_winner4,txt_winner5,txt_winner6,txt_winner7,txt_winner8,txt_winner9,
            txt_winner10,txt_winner11,txt_winner12,txt_winner13,txt_winner14,txt_winner15,txt_winner16;

    private ValueEventListener transFromWinnerToCurrent =new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            Calendar myCalendar = Calendar.getInstance();
            int day = myCalendar.get(Calendar.DAY_OF_MONTH);
            int month = myCalendar.get(Calendar.MONTH);
            int year = myCalendar.get(Calendar.YEAR);
            int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
            int minute = myCalendar.get(Calendar.MINUTE);

            int databaseYear = Integer.parseInt(snapshot.child("vote").child("date").child("year").getValue().toString());
            int databaseMonth = Integer.parseInt(snapshot.child("vote").child("date").child("month").getValue().toString());
            int databaseDay = Integer.parseInt(snapshot.child("vote").child("date").child("day").getValue().toString());
            int databaseHour = Integer.parseInt(snapshot.child("vote").child("time").child("hour").getValue().toString());
            int databaseMinute = Integer.parseInt(snapshot.child("vote").child("time").child("minute").getValue().toString());

            if (databaseYear < year) {
             sumVoteFunctions();
            } else if (databaseYear > year) {
                afterEndVote();
            } else if (databaseYear == year) {
                if (databaseMonth < month) {
                    sumVoteFunctions();
                } else if (databaseMonth > month) {
                    afterEndVote();
                } else if (databaseMonth == month) {
                    if (databaseDay < day) {
                        sumVoteFunctions();
                    } else if (databaseDay > day) {
                        afterEndVote();
                    } else if (databaseDay == day) {
                        if (databaseHour < hour) {
                            sumVoteFunctions();
                        } else if (databaseHour > hour) {
                            afterEndVote();
                        } else if (databaseHour == hour) {
                            if (databaseMinute < minute) {
                                sumVoteFunctions();
                            } else if (databaseMinute > minute) {
                                afterEndVote();
                            }
                        }
                    }
                }
            }

            int EdatabaseYear = Integer.parseInt(snapshot.child("التعادل").child("date").child("year").getValue().toString());
            int EdatabaseMonth = Integer.parseInt(snapshot.child("التعادل").child("date").child("month").getValue().toString());
            int EdatabaseDay = Integer.parseInt(snapshot.child("التعادل").child("date").child("day").getValue().toString());
            int EdatabaseHour = Integer.parseInt(snapshot.child("التعادل").child("time").child("hour").getValue().toString());
            int EdatabaseMinute = Integer.parseInt(snapshot.child("التعادل").child("time").child("minute").getValue().toString());

            if (EdatabaseYear < year) {
                sumEqualFunctions();
            } else if (EdatabaseYear > year) {
                currentMembers.child("رئيس اتحاد الطلبة").child("name").setValue("في انتظار مرحلة التعادل.. ");
            } else if (EdatabaseYear == year) {
                if (EdatabaseMonth < month) {
                    sumEqualFunctions();
                } else if (EdatabaseMonth > month) {
                    currentMembers.child("رئيس اتحاد الطلبة").child("name").setValue("في انتظار مرحلة التعادل.. ");
                } else if (EdatabaseMonth == month) {
                    if (EdatabaseDay < day) {
                        sumEqualFunctions();
                    } else if (EdatabaseDay > day) {
                        currentMembers.child("رئيس اتحاد الطلبة").child("name").setValue("في انتظار مرحلة التعادل.. ");
                    } else if (EdatabaseDay == day) {
                        if (EdatabaseHour < hour) {
                            sumEqualFunctions();
                        } else if (EdatabaseHour > hour) {
                            currentMembers.child("رئيس اتحاد الطلبة").child("name").setValue("في انتظار مرحلة التعادل.. ");
                        } else if (EdatabaseHour == hour) {
                            if (EdatabaseMinute < minute) {
                                sumEqualFunctions();
                            } else if (EdatabaseMinute > minute) {
                                currentMembers.child("رئيس اتحاد الطلبة").child("name").setValue("في انتظار مرحلة التعادل.. ");
                            }
                        }
                    }
                }
            }
        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) { }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_member);

        getSupportActionBar().setTitle("الاعضاء الحالين");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        txt_winner1 = findViewById(R.id.txt_winner1);
        txt_winner2 = findViewById(R.id.txt_winner2);
        txt_winner3 = findViewById(R.id.txt_winner3);
        txt_winner4 = findViewById(R.id.txt_winner4);
        txt_winner5 = findViewById(R.id.txt_winner5);
        txt_winner6 = findViewById(R.id.txt_winner6);
        txt_winner7 = findViewById(R.id.txt_winner7);
        txt_winner8 = findViewById(R.id.txt_winner8);
        txt_winner9 = findViewById(R.id.txt_winner9);
        txt_winner10 = findViewById(R.id.txt_winner10);
        txt_winner11 = findViewById(R.id.txt_winner11);
        txt_winner12 = findViewById(R.id.txt_winner12);
        txt_winner13 = findViewById(R.id.txt_winner13);
        txt_winner14 = findViewById(R.id.txt_winner14);
        txt_winner15 = findViewById(R.id.txt_winner15);
        txt_winner16 = findViewById(R.id.txt_winner16);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        currentStage = database.getReference("CurrentStage");
        currentMembers = database.getReference("الاعضاء الحاليون");
        dbEqualsVotes = database.getReference("الاشخاص المتعادلين");

        transFromWinnerToCurrent();
        showWinnersFromCurrent();
    }

    private void transFromWinnerToCurrent() { currentStage.addValueEventListener(transFromWinnerToCurrent);}

    // بنجيب الاشخاص الذين يملكون اعلي نسبة تصويت
    private void getWinRaeesAt7ad(){

        reference = FirebaseDatabase.getInstance()
                .getReference().child("المرشحون").child("رئيس اتحاد الطلبة");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists()){
                    for (DataSnapshot snapshot : datasnapshot.getChildren()){
                        countwin = String.valueOf(snapshot.child("count").getValue());
                        sameNumberOfVoteRaeesAt7ad(countwin);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    // لو شخص واحد بنعلنه كفائز لو اكثر بنسجلهم في الداتا علشان يتم عمل انتخابات جديدة ليهم
    private  void sameNumberOfVoteRaeesAt7ad(String countwin){
        final List <Winers> winnerList=new ArrayList <>();
        reference = FirebaseDatabase.getInstance()
                .getReference().child("المرشحون").child("رئيس اتحاد الطلبة");
        Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        String namewin = String.valueOf(snapshot1.child("name").getValue());
                        Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                        String idtwin = String.valueOf(snapshot1.child("id").getValue());
                        winnerList.add(new Winers(namewin,countwin,idtwin));
                    }
                }
                if(winnerList.size()>1){
                    //handle here to start process again
                    for (Winers item: winnerList){
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        dbEqualsVotes.child("رئيس اتحاد الطلبة").child(SSN).setValue(item);
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("رئيس اتحاد الطلبة").child("name");
                        reference.setValue("في انتظار التصوبت..");
                        currentMembers.child("رئيس اتحاد الطلبة").child("name").setValue("في انتظار التصوبت..");
                    }

                }else{
                    //only one winner
                    for (Winers item: winnerList) {
                        item.getName();
                        item.getCount();
                        item.getId();
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("رئيس اتحاد الطلبة");
                        reference.setValue(item);
                        currentMembers.child("رئيس اتحاد الطلبة").setValue(item);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getWinNaeebRaeesAt7ad(){
        reference = FirebaseDatabase.getInstance()
                .getReference().child("المرشحون").child("نائب رئيس اتحاد الطلبة");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists()){
                    for (DataSnapshot snapshot : datasnapshot.getChildren()){
                        countwin = String.valueOf(snapshot.child("count").getValue());
                        sameNumberOfVoteNaeebRaeesAt7ad(countwin);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private  void sameNumberOfVoteNaeebRaeesAt7ad(String countwin){
        final List <Winers> winnerList=new ArrayList <>();
        reference = FirebaseDatabase.getInstance()
                .getReference().child("المرشحون").child("نائب رئيس اتحاد الطلبة");
        Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        String namewin = String.valueOf(snapshot1.child("name").getValue());
                        Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                        String idtwin = String.valueOf(snapshot1.child("id").getValue());
                        winnerList.add(new Winers(namewin,countwin,idtwin));
                    }}
                if(winnerList.size()>1){
                    //handle here to start process again
                    for (Winers item: winnerList){
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        dbEqualsVotes.child("نائب رئيس اتحاد الطلبة").child(SSN).setValue(item);
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("نائب رئيس اتحاد الطلبة").child("name");
                        reference.setValue("في انتظار التصوبت..");
                        currentMembers.child("نائب رئيس اتحاد الطلبة").child("name").setValue("في انتظار التصوبت..");

                    }

                }else{
                    //only one winner
                    for (Winers item: winnerList) {
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("نائب رئيس اتحاد الطلبة");
                        reference.setValue(item);
                        currentMembers.child("نائب رئيس اتحاد الطلبة").setValue(item);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getWin3(){
        reference = FirebaseDatabase.getInstance()
                .getReference().child("المرشحون").child("امين لجنة الاسر");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists()){
                    for (DataSnapshot snapshot : datasnapshot.getChildren()){
                        countwin = String.valueOf(snapshot.child("count").getValue());
                        sameNumberOfVote3(countwin);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private  void sameNumberOfVote3(String countwin){
        final List <Winers> winnerList=new ArrayList <>();
        reference = FirebaseDatabase.getInstance()
                .getReference().child("المرشحون").child("امين لجنة الاسر");
        Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        String namewin = String.valueOf(snapshot1.child("name").getValue());
                        Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                        String idtwin = String.valueOf(snapshot1.child("id").getValue());
                        winnerList.add(new Winers(namewin,countwin,idtwin));
                    }
                }
                if(winnerList.size()>1){
                    //handle here to start process again
                    for (Winers item: winnerList){
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        dbEqualsVotes.child("امين لجنة الاسر").child(SSN).setValue(item);
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("امين لجنة الاسر").child("name");
                        reference.setValue("في انتظار التصوبت..");
                        currentMembers.child("امين لجنة الاسر").child("name").setValue("في انتظار التصوبت..");
                    }

                }else{
                    //only one winner
                    for (Winers item: winnerList) {
                        item.getName();
                        item.getCount();
                        item.getId();
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("امين لجنة الاسر");
                        reference.setValue(item);
                        currentMembers.child("امين لجنة الاسر").setValue(item);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getWin4(){reference = FirebaseDatabase.getInstance()
            .getReference().child("المرشحون").child("نائب امين لجنة الاسر");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists()){
                    for (DataSnapshot snapshot : datasnapshot.getChildren()){
                        countwin = String.valueOf(snapshot.child("count").getValue());
                        sameNumberOfVote4(countwin);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });}
    private  void sameNumberOfVote4(String countwin){ final List <Winers> winnerList=new ArrayList <>();
        reference = FirebaseDatabase.getInstance()
                .getReference().child("المرشحون").child("نائب امين لجنة الاسر");
        Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        String namewin = String.valueOf(snapshot1.child("name").getValue());
                        Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                        String idtwin = String.valueOf(snapshot1.child("id").getValue());
                        winnerList.add(new Winers(namewin,countwin,idtwin));
                    }}
                if(winnerList.size()>1){
                    //handle here to start process again
                    for (Winers item: winnerList){
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        dbEqualsVotes.child("نائب امين لجنة الاسر").child(SSN).setValue(item);
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("نائب امين لجنة الاسر").child("name");
                        reference.setValue("في انتظار التصوبت..");
                        currentMembers.child("نائب امين لجنة الاسر").child("name").setValue("في انتظار التصوبت..");
                    }

                }else{
                    //only one winner
                    for (Winers item: winnerList) {
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("نائب امين لجنة الاسر");
                        reference.setValue(item);
                        currentMembers.child("نائب امين لجنة الاسر").setValue(item);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });}

    private void getWin5(){reference = FirebaseDatabase.getInstance()
            .getReference().child("المرشحون").child("امين لجنة الجواله والخدمة العامه");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists()){
                    for (DataSnapshot snapshot : datasnapshot.getChildren()){
                        countwin = String.valueOf(snapshot.child("count").getValue());
                        sameNumberOfVote5(countwin);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });}
    private  void sameNumberOfVote5(String countwin){ final List <Winers> winnerList=new ArrayList <>();
        reference = FirebaseDatabase.getInstance()
                .getReference().child("المرشحون").child("امين لجنة الجواله والخدمة العامه");
        Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        String namewin = String.valueOf(snapshot1.child("name").getValue());
                        Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                        String idtwin = String.valueOf(snapshot1.child("id").getValue());
                        winnerList.add(new Winers(namewin,countwin,idtwin));
                    }}
                if(winnerList.size()>1){
                    //handle here to start process again
                    for (Winers item: winnerList){
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        dbEqualsVotes.child("امين لجنة الجواله والخدمة العامه").child(SSN).setValue(item);
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("امين لجنة الجواله والخدمة العامه").child("name");
                        reference.setValue("في انتظار التصوبت..");
                        currentMembers.child("امين لجنة الجواله والخدمة العامه").child("name").setValue("في انتظار التصوبت..");

                    }

                }else{
                    //only one winner
                    for (Winers item: winnerList) {
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("امين لجنة الجواله والخدمة العامه");
                        reference.setValue(item);
                        currentMembers.child("امين لجنة الجواله والخدمة العامه").setValue(item);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });}

    private void getWin6(){reference = FirebaseDatabase.getInstance()
            .getReference().child("المرشحون").child("نائب امين لجنة الجواله والخدمة العامه");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists()){
                    for (DataSnapshot snapshot : datasnapshot.getChildren()){
                        countwin = String.valueOf(snapshot.child("count").getValue());
                        sameNumberOfVote6(countwin);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });}
    private  void sameNumberOfVote6(String countwin){ final List <Winers> winnerList=new ArrayList <>();
        reference = FirebaseDatabase.getInstance()
                .getReference().child("المرشحون").child("نائب امين لجنة الجواله والخدمة العامه");
        Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        String namewin = String.valueOf(snapshot1.child("name").getValue());
                        Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                        String idtwin = String.valueOf(snapshot1.child("id").getValue());
                        winnerList.add(new Winers(namewin,countwin,idtwin));
                    }}
                if(winnerList.size()>1){
                    //handle here to start process again
                    for (Winers item: winnerList){
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        dbEqualsVotes.child("نائب امين لجنة الجواله والخدمة العامه").child(SSN).setValue(item);
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("نائب امين لجنة الجواله والخدمة العامه").child("name");
                        reference.setValue("في انتظار التصوبت..");
                        currentMembers.child("نائب امين لجنة الجواله والخدمة العامه").child("name").setValue("في انتظار التصوبت..");

                    }

                }else{
                    //only one winner
                    for (Winers item: winnerList) {
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("نائب امين لجنة الجواله والخدمة العامه");
                        reference.setValue(item);
                        currentMembers.child("نائب امين لجنة الجواله والخدمة العامه").setValue(item);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });}

    private void getWin7(){reference = FirebaseDatabase.getInstance()
            .getReference().child("المرشحون").child("امين لجنة النشاط الاجتماعي والرحلات");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists()){
                    for (DataSnapshot snapshot : datasnapshot.getChildren()){
                        countwin = String.valueOf(snapshot.child("count").getValue());
                        sameNumberOfVote7(countwin);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });}
    private  void sameNumberOfVote7(String countwin){ final List <Winers> winnerList=new ArrayList <>();
        reference = FirebaseDatabase.getInstance()
                .getReference().child("المرشحون").child("امين لجنة النشاط الاجتماعي والرحلات");
        Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        String namewin = String.valueOf(snapshot1.child("name").getValue());
                        Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                        String idtwin = String.valueOf(snapshot1.child("id").getValue());
                        winnerList.add(new Winers(namewin,countwin,idtwin));
                    }}
                if(winnerList.size()>1){
                    //handle here to start process again
                    for (Winers item: winnerList){
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        dbEqualsVotes.child("امين لجنة النشاط الاجتماعي والرحلات").child(SSN).setValue(item);
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("امين لجنة النشاط الاجتماعي والرحلات").child("name");
                        reference.setValue("في انتظار التصوبت..");
                        currentMembers.child("امين لجنة النشاط الاجتماعي والرحلات").child("name").setValue("في انتظار التصوبت..");

                    }

                }else{
                    //only one winner
                    for (Winers item: winnerList) {
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("امين لجنة النشاط الاجتماعي والرحلات");
                        reference.setValue(item);
                        currentMembers.child("امين لجنة النشاط الاجتماعي والرحلات").setValue(item);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getWin8(){reference = FirebaseDatabase.getInstance()
            .getReference().child("المرشحون").child("نائب امين لجنة النشاط الاجتماعي والرحلات");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists()){
                    for (DataSnapshot snapshot : datasnapshot.getChildren()){
                        countwin = String.valueOf(snapshot.child("count").getValue());
                        sameNumberOfVote8(countwin);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });}
    private  void sameNumberOfVote8(String countwin){ final List <Winers> winnerList=new ArrayList <>();
        reference = FirebaseDatabase.getInstance()
                .getReference().child("المرشحون").child("نائب امين لجنة النشاط الاجتماعي والرحلات");
        Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        String namewin = String.valueOf(snapshot1.child("name").getValue());
                        Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                        String idtwin = String.valueOf(snapshot1.child("id").getValue());
                        winnerList.add(new Winers(namewin,countwin,idtwin));
                    }}
                if(winnerList.size()>1){
                    //handle here to start process again
                    for (Winers item: winnerList){
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        dbEqualsVotes.child("نائب امين لجنة النشاط الاجتماعي والرحلات").child(SSN).setValue(item);
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("نائب امين لجنة النشاط الاجتماعي والرحلات").child("name");
                        reference.setValue("في انتظار التصوبت..");
                        currentMembers.child("نائب امين لجنة النشاط الاجتماعي والرحلات").child("name").setValue("في انتظار التصوبت..");

                    }

                }else{
                    //only one winner
                    for (Winers item: winnerList) {
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("نائب امين لجنة النشاط الاجتماعي والرحلات");
                        reference.setValue(item);
                        currentMembers.child("نائب امين لجنة النشاط الاجتماعي والرحلات").setValue(item);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getWin9(){reference = FirebaseDatabase.getInstance()
            .getReference().child("المرشحون").child("امين لجنة النشاط الثقافي والاعلامي");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists()){
                    for (DataSnapshot snapshot : datasnapshot.getChildren()){
                        countwin = String.valueOf(snapshot.child("count").getValue());
                        sameNumberOfVote9(countwin);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });}
    private  void sameNumberOfVote9(String countwin){ final List <Winers> winnerList=new ArrayList <>();
        reference = FirebaseDatabase.getInstance()
                .getReference().child("المرشحون").child("امين لجنة النشاط الثقافي والاعلامي");
        Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        String namewin = String.valueOf(snapshot1.child("name").getValue());
                        Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                        String idtwin = String.valueOf(snapshot1.child("id").getValue());
                        winnerList.add(new Winers(namewin,countwin,idtwin));
                    }}
                if(winnerList.size()>1){
                    //handle here to start process again
                    for (Winers item: winnerList){
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        dbEqualsVotes.child("امين لجنة النشاط الثقافي والاعلامي").child(SSN).setValue(item);
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("امين لجنة النشاط الثقافي والاعلامي").child("name");
                        reference.setValue("في انتظار التصوبت..");
                        currentMembers.child("امين لجنة النشاط الثقافي والاعلامي").child("name").setValue("في انتظار التصوبت..");

                    }

                }else{
                    //only one winner
                    for (Winers item: winnerList) {
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("امين لجنة النشاط الثقافي والاعلامي");
                        reference.setValue(item);
                        currentMembers.child("امين لجنة النشاط الثقافي والاعلامي").setValue(item);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });}

    private void getWin10(){reference = FirebaseDatabase.getInstance()
            .getReference().child("المرشحون").child("نائب امين لجنة النشاط الثقافي والاعلامي");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists()){
                    for (DataSnapshot snapshot : datasnapshot.getChildren()){
                        countwin = String.valueOf(snapshot.child("count").getValue());
                        sameNumberOfVote10(countwin);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });}
    private  void sameNumberOfVote10(String countwin){ final List <Winers> winnerList=new ArrayList <>();
        reference = FirebaseDatabase.getInstance()
                .getReference().child("المرشحون").child("نائب امين لجنة النشاط الثقافي والاعلامي");
        Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        String namewin = String.valueOf(snapshot1.child("name").getValue());
                        Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                        String idtwin = String.valueOf(snapshot1.child("id").getValue());
                        winnerList.add(new Winers(namewin,countwin,idtwin));
                    }}
                if(winnerList.size()>1){
                    //handle here to start process again
                    for (Winers item: winnerList){
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        dbEqualsVotes.child("نائب امين لجنة النشاط الثقافي والاعلامي").child(SSN).setValue(item);
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("نائب امين لجنة النشاط الثقافي والاعلامي").child("name");
                        reference.setValue("في انتظار التصوبت..");
                        currentMembers.child("نائب امين لجنة النشاط الثقافي والاعلامي").child("name").setValue("في انتظار التصوبت..");

                    }

                }else{
                    //only one winner
                    for (Winers item: winnerList) {
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("نائب امين لجنة النشاط الثقافي والاعلامي");
                        reference.setValue(item);
                        currentMembers.child("نائب امين لجنة النشاط الثقافي والاعلامي").setValue(item);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });}

    private void getWin11(){reference = FirebaseDatabase.getInstance()
            .getReference().child("المرشحون").child("امين لجنة النشاط الرياضي");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists()){
                    for (DataSnapshot snapshot : datasnapshot.getChildren()){
                        countwin = String.valueOf(snapshot.child("count").getValue());
                        sameNumberOfVote11(countwin);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });}
    private  void sameNumberOfVote11(String countwin){ final List <Winers> winnerList=new ArrayList <>();
        reference = FirebaseDatabase.getInstance()
                .getReference().child("المرشحون").child("امين لجنة النشاط الرياضي");
        Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        String namewin = String.valueOf(snapshot1.child("name").getValue());
                        Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                        String idtwin = String.valueOf(snapshot1.child("id").getValue());
                        winnerList.add(new Winers(namewin,countwin,idtwin));
                    }}
                if(winnerList.size()>1){
                    //handle here to start process again
                    for (Winers item: winnerList){
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        dbEqualsVotes.child("امين لجنة النشاط الرياضي").child(SSN).setValue(item);
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("امين لجنة النشاط الرياضي").child("name");
                        reference.setValue("في انتظار التصوبت..");
                        currentMembers.child("امين لجنة النشاط الرياضي").child("name").setValue("في انتظار التصوبت..");

                    }

                }else{
                    //only one winner
                    for (Winers item: winnerList) {
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("امين لجنة النشاط الرياضي");
                        reference.setValue(item);
                        currentMembers.child("امين لجنة النشاط الرياضي").setValue(item);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });}

    private void getWin12(){reference = FirebaseDatabase.getInstance()
            .getReference().child("المرشحون").child("نائب امين لجنة النشاط الرياضي");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists()){
                    for (DataSnapshot snapshot : datasnapshot.getChildren()){
                        countwin = String.valueOf(snapshot.child("count").getValue());
                        sameNumberOfVote12(countwin);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });}
    private  void sameNumberOfVote12(String countwin){ final List <Winers> winnerList=new ArrayList <>();
        reference = FirebaseDatabase.getInstance()
                .getReference().child("المرشحون").child("نائب امين لجنة النشاط الرياضي");
        Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        String namewin = String.valueOf(snapshot1.child("name").getValue());
                        Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                        String idtwin = String.valueOf(snapshot1.child("id").getValue());
                        winnerList.add(new Winers(namewin,countwin,idtwin));
                    }}
                if(winnerList.size()>1){
                    //handle here to start process again
                    for (Winers item: winnerList){
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        dbEqualsVotes.child("نائب امين لجنة النشاط الرياضي").child(SSN).setValue(item);
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("نائب امين لجنة النشاط الرياضي").child("name");
                        reference.setValue("في انتظار التصوبت..");
                        currentMembers.child("نائب امين لجنة النشاط الرياضي").child("name").setValue("في انتظار التصوبت..");

                    }

                }else{
                    //only one winner
                    for (Winers item: winnerList) {
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("نائب امين لجنة النشاط الرياضي");
                        reference.setValue(item);
                        currentMembers.child("نائب امين لجنة النشاط الرياضي").setValue(item);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });}

    private void getWin13(){reference = FirebaseDatabase.getInstance()
            .getReference().child("المرشحون").child("امين لجنة النشاط العلمي والتكنولوجي");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists()){
                    for (DataSnapshot snapshot : datasnapshot.getChildren()){
                        countwin = String.valueOf(snapshot.child("count").getValue());
                        sameNumberOfVote13(countwin);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });}
    private  void sameNumberOfVote13(String countwin){ final List <Winers> winnerList=new ArrayList <>();
        reference = FirebaseDatabase.getInstance()
                .getReference().child("المرشحون").child("امين لجنة النشاط العلمي والتكنولوجي");
        Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        String namewin = String.valueOf(snapshot1.child("name").getValue());
                        Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                        String idtwin = String.valueOf(snapshot1.child("id").getValue());
                        winnerList.add(new Winers(namewin,countwin,idtwin));
                    }}
                if(winnerList.size()>1){
                    //handle here to start process again
                    for (Winers item: winnerList){
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        dbEqualsVotes.child("امين لجنة النشاط العلمي والتكنولوجي").child(SSN).setValue(item);
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("امين لجنة النشاط العلمي والتكنولوجي").child("name");
                        reference.setValue("في انتظار التصوبت..");
                        currentMembers.child("امين لجنة النشاط العلمي والتكنولوجي").child("name").setValue("في انتظار التصوبت..");

                    }

                }else{
                    //only one winner
                    for (Winers item: winnerList) {
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("امين لجنة النشاط العلمي والتكنولوجي");
                        reference.setValue(item);
                        currentMembers.child("امين لجنة النشاط العلمي والتكنولوجي").setValue(item);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });}

    private void getWin14(){reference = FirebaseDatabase.getInstance()
            .getReference().child("المرشحون").child("نائب امين لجنة النشاط العلمي والتكنولوجي");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists()){
                    for (DataSnapshot snapshot : datasnapshot.getChildren()){
                        countwin = String.valueOf(snapshot.child("count").getValue());
                        sameNumberOfVote14(countwin);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });}
    private  void sameNumberOfVote14(String countwin){ final List <Winers> winnerList=new ArrayList <>();
        reference = FirebaseDatabase.getInstance()
                .getReference().child("المرشحون").child("نائب امين لجنة النشاط العلمي والتكنولوجي");
        Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        String namewin = String.valueOf(snapshot1.child("name").getValue());
                        Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                        String idtwin = String.valueOf(snapshot1.child("id").getValue());
                        winnerList.add(new Winers(namewin,countwin,idtwin));
                    }}
                if(winnerList.size()>1){
                    //handle here to start process again
                    for (Winers item: winnerList){
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        dbEqualsVotes.child("نائب امين لجنة النشاط العلمي والتكنولوجي").child(SSN).setValue(item);
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("نائب امين لجنة النشاط العلمي والتكنولوجي").child("name");
                        reference.setValue("في انتظار التصوبت..");
                        currentMembers.child("نائب امين لجنة النشاط العلمي والتكنولوجي").child("name").setValue("في انتظار التصوبت..");

                    }

                }else{
                    //only one winner
                    for (Winers item: winnerList) {
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("نائب امين لجنة النشاط العلمي والتكنولوجي");
                        reference.setValue(item);
                        currentMembers.child("نائب امين لجنة النشاط العلمي والتكنولوجي").setValue(item);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });}

    private void getWin15(){reference = FirebaseDatabase.getInstance()
            .getReference().child("المرشحون").child("امين لجنة النشاط الفني");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists()){
                    for (DataSnapshot snapshot : datasnapshot.getChildren()){
                        countwin = String.valueOf(snapshot.child("count").getValue());
                        sameNumberOfVote15(countwin);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });}
    private  void sameNumberOfVote15(String countwin){ final List <Winers> winnerList=new ArrayList <>();
        reference = FirebaseDatabase.getInstance()
                .getReference().child("المرشحون").child("امين لجنة النشاط الفني");
        Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        String namewin = String.valueOf(snapshot1.child("name").getValue());
                        Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                        String idtwin = String.valueOf(snapshot1.child("id").getValue());
                        winnerList.add(new Winers(namewin,countwin,idtwin));
                    }}
                if(winnerList.size()>1){
                    //handle here to start process again
                    for (Winers item: winnerList){
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        dbEqualsVotes.child("امين لجنة النشاط الفني").child(SSN).setValue(item);
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("امين لجنة النشاط الفني").child("name");
                        reference.setValue("في انتظار التصوبت..");
                        currentMembers.child("امين لجنة النشاط الفني").child("name").setValue("في انتظار التصوبت..");

                    }

                }else{
                    //only one winner
                    for (Winers item: winnerList) {
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("امين لجنة النشاط الفني");
                        reference.setValue(item);
                        currentMembers.child("امين لجنة النشاط الفني").setValue(item);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });}

    private void getWin16(){reference = FirebaseDatabase.getInstance()
            .getReference().child("المرشحون").child("نائب امين لجنة النشاط الفني");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists()){
                    for (DataSnapshot snapshot : datasnapshot.getChildren()){
                        countwin = String.valueOf(snapshot.child("count").getValue());
                        sameNumberOfVote16(countwin);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });}
    private  void sameNumberOfVote16(String countwin){ final List <Winers> winnerList=new ArrayList <>();
        reference = FirebaseDatabase.getInstance()
                .getReference().child("المرشحون").child("نائب امين لجنة النشاط الفني");
        Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        String namewin = String.valueOf(snapshot1.child("name").getValue());
                        Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                        String idtwin = String.valueOf(snapshot1.child("id").getValue());
                        winnerList.add(new Winers(namewin,countwin,idtwin));
                    }}
                if(winnerList.size()>1){
                    //handle here to start process again
                    for (Winers item: winnerList){
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        dbEqualsVotes.child("نائب امين لجنة النشاط الفني").child(SSN).setValue(item);
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("نائب امين لجنة النشاط الفني").child("name");
                        reference.setValue("في انتظار التصوبت..");
                        currentMembers.child("نائب امين لجنة النشاط الفني").child("name").setValue("في انتظار التصوبت..");

                    }

                }else{
                    //only one winner
                    for (Winers item: winnerList) {
                        item.getName();
                        item.getCount();
                        item.getId();
                        String SSN = item.getId();
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الفائزون").child("نائب امين لجنة النشاط الفني");
                        reference.setValue(item);
                        currentMembers.child("نائب امين لجنة النشاط الفني").setValue(item);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });}

    private void showWinnersFromCurrent() {
        currentMembers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("رئيس اتحاد الطلبة").child("name").getValue().toString();
                txt_winner1.setText(name);
                String name2 = snapshot.child("نائب رئيس اتحاد الطلبة").child("name").getValue().toString();
                txt_winner2.setText(name2);
                String name3 = snapshot.child("امين لجنة الاسر").child("name").getValue().toString();
                txt_winner3.setText(name3);
                String name4 = snapshot.child("نائب امين لجنة الاسر").child("name").getValue().toString();
                txt_winner4.setText(name4);
                String name5 = snapshot.child("امين لجنة الجواله والخدمة العامه").child("name").getValue().toString();
                txt_winner5.setText(name5);
                String name6 = snapshot.child("نائب امين لجنة الجواله والخدمة العامه").child("name").getValue().toString();
                txt_winner6.setText(name6);
                String name7 = snapshot.child("امين لجنة النشاط العلمي والتكنولوجي").child("name").getValue().toString();
                txt_winner7.setText(name7);
                String name8 = snapshot.child("نائب امين لجنة النشاط العلمي والتكنولوجي").child("name").getValue().toString();
                txt_winner8.setText(name8);
                String name9 = snapshot.child("امين لجنة النشاط الاجتماعي والرحلات").child("name").getValue().toString();
                txt_winner9.setText(name9);
                String name10 = snapshot.child("نائب امين لجنة النشاط الاجتماعي والرحلات").child("name").getValue().toString();
                txt_winner10.setText(name10);
                String name11 = snapshot.child("امين لجنة النشاط الثقافي والاعلامي").child("name").getValue().toString();
                txt_winner11.setText(name11);
                String name12 = snapshot.child("نائب امين لجنة النشاط الثقافي والاعلامي").child("name").getValue().toString();
                txt_winner12.setText(name12);
                String name13 = snapshot.child("امين لجنة النشاط الرياضي").child("name").getValue().toString();
                txt_winner13.setText(name13);
                String name14 = snapshot.child("نائب امين لجنة النشاط الرياضي").child("name").getValue().toString();
                txt_winner14.setText(name14);
                String name15 = snapshot.child("امين لجنة النشاط الفني").child("name").getValue().toString();
                txt_winner15.setText(name15);
                String name16 = snapshot.child("نائب امين لجنة النشاط الفني").child("name").getValue().toString();
                txt_winner16.setText(name16);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    // التعادل
    private void ReVote2getWinRaeesAt7ad() {
        reference = FirebaseDatabase.getInstance()
                .getReference().child("الاشخاص المتعادلين").child("رئيس اتحاد الطلبة");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()) {
                    for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                        countwin = String.valueOf(snapshot.child("count").getValue());

                        final List<Winers> winnerList = new ArrayList<>();
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الاشخاص المتعادلين").child("رئيس اتحاد الطلبة");
                        Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                        String namewin = String.valueOf(snapshot1.child("name").getValue());
                                        Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                                        String idtwin = String.valueOf(snapshot1.child("id").getValue());
                                        winnerList.add(new Winers(namewin, countwin, idtwin));
                                    }
                                }
                                if (winnerList.size() > 1) {
                                    currentMembers.child("رئيس اتحاد الطلبة").child("name").setValue("في حالة تعادل");

                                } else {
                                    //only one winner
                                    for (Winers item : winnerList) {
                                        item.getName();
                                        item.getCount();
                                        item.getId();
                                        String SSN = item.getId();
                                        reference = FirebaseDatabase.getInstance()
                                                .getReference().child("الفائزون").child("رئيس اتحاد الطلبة");
                                        reference.setValue(item);
                                        currentMembers.child("نائب رئيس اتحاد الطلبة").setValue(item);
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void ReVote2getWinNaeebRaeesAt7ad() {
        reference = FirebaseDatabase.getInstance()
                .getReference().child("الاشخاص المتعادلين").child("نائب رئيس اتحاد الطلبة");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()) {
                    if (datasnapshot.exists()) {
                        for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                            String countwin = String.valueOf(snapshot.child("count").getValue().toString());

                            final List<Winers> winnerList = new ArrayList<>();
                            reference = FirebaseDatabase.getInstance()
                                    .getReference().child("الاشخاص المتعادلين").child("نائب رئيس اتحاد الطلبة");
                            Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                            String namewin = String.valueOf(snapshot1.child("name").getValue());
                                            Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                                            String idtwin = String.valueOf(snapshot1.child("id").getValue());
                                            winnerList.add(new Winers(namewin, countwin, idtwin));
                                        }
                                    }
                                    if (winnerList.size() > 1) {
                                        currentMembers.child("نائب رئيس اتحاد الطلبة").child("name").setValue("في حالة تعادل");
                                    } else {
                                        //only one winner
                                        for (Winers item : winnerList) {
                                            item.getName();
                                            item.getCount();
                                            item.getId();
                                            String SSN = item.getId();
                                            reference = FirebaseDatabase.getInstance()
                                                    .getReference().child("الفائزون").child("نائب رئيس اتحاد الطلبة");
                                            currentMembers.child("نائب رئيس اتحاد الطلبة").setValue(item);
                                            reference.setValue(item);
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void ReVote2getWin3() {
        reference = FirebaseDatabase.getInstance()
                .getReference().child("الاشخاص المتعادلين").child("امين لجنة الاسر");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()) {
                    for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                        countwin = String.valueOf(snapshot.child("count").getValue());

                        final List<Winers> winnerList = new ArrayList<>();
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الاشخاص المتعادلين").child("امين لجنة الاسر");
                        Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                        String namewin = String.valueOf(snapshot1.child("name").getValue());
                                        Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                                        String idtwin = String.valueOf(snapshot1.child("id").getValue());
                                        winnerList.add(new Winers(namewin, countwin, idtwin));
                                    }
                                }
                                if (winnerList.size() > 1) {
                                    currentMembers.child("امين لجنة الاسر").child("name").setValue("في حالة تعادل");

                                } else {
                                    //only one winner
                                    for (Winers item : winnerList) {
                                        item.getName();
                                        item.getCount();
                                        item.getId();
                                        String SSN = item.getId();
                                        reference = FirebaseDatabase.getInstance()
                                                .getReference().child("الفائزون").child("امين لجنة الاسر");
                                        reference.setValue(item);
                                        currentMembers.child("امين لجنة الاسر").setValue(item);


                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void ReVote2getWin4() {
        reference = FirebaseDatabase.getInstance()
                .getReference().child("الاشخاص المتعادلين").child("نائب امين لجنة الاسر");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()) {
                    if (datasnapshot.exists()) {
                        for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                            String countwin = String.valueOf(snapshot.child("count").getValue().toString());

                            final List<Winers> winnerList = new ArrayList<>();
                            reference = FirebaseDatabase.getInstance()
                                    .getReference().child("الاشخاص المتعادلين").child("نائب امين لجنة الاسر");
                            Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                            String namewin = String.valueOf(snapshot1.child("name").getValue());
                                            Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                                            String idtwin = String.valueOf(snapshot1.child("id").getValue());
                                            winnerList.add(new Winers(namewin, countwin, idtwin));
                                        }
                                    }
                                    if (winnerList.size() > 1) {
                                        currentMembers.child("نائب امين لجنة الاسر").child("name").setValue("في حالة تعادل");
                                    } else {
                                        //only one winner
                                        for (Winers item : winnerList) {
                                            item.getName();
                                            item.getCount();
                                            item.getId();
                                            String SSN = item.getId();
                                            reference = FirebaseDatabase.getInstance()
                                                    .getReference().child("الفائزون").child("نائب امين لجنة الاسر");
                                            currentMembers.child("نائب امين لجنة الاسر").setValue(item);
                                            reference.setValue(item);
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void ReVote2getWin5() {
        reference = FirebaseDatabase.getInstance()
                .getReference().child("الاشخاص المتعادلين").child("امين لجنة الجواله والخدمة العامه");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()) {
                    for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                        countwin = String.valueOf(snapshot.child("count").getValue());

                        final List<Winers> winnerList = new ArrayList<>();
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الاشخاص المتعادلين").child("امين لجنة الجواله والخدمة العامه");
                        Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                        String namewin = String.valueOf(snapshot1.child("name").getValue());
                                        Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                                        String idtwin = String.valueOf(snapshot1.child("id").getValue());
                                        winnerList.add(new Winers(namewin, countwin, idtwin));
                                    }
                                }
                                if (winnerList.size() > 1) {
                                    currentMembers.child("امين لجنة الجواله والخدمة العامه").child("name").setValue("في حالة تعادل");

                                } else {
                                    //only one winner
                                    for (Winers item : winnerList) {
                                        item.getName();
                                        item.getCount();
                                        item.getId();
                                        String SSN = item.getId();
                                        reference = FirebaseDatabase.getInstance()
                                                .getReference().child("الفائزون").child("امين لجنة الجواله والخدمة العامه");
                                        reference.setValue(item);
                                        currentMembers.child("امين لجنة الجواله والخدمة العامه").setValue(item);

                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void ReVote2getWin6() {
        reference = FirebaseDatabase.getInstance()
                .getReference().child("الاشخاص المتعادلين").child("نائب امين لجنة الجواله والخدمة العامه");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()) {
                    if (datasnapshot.exists()) {
                        for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                            String countwin = String.valueOf(snapshot.child("count").getValue().toString());

                            final List<Winers> winnerList = new ArrayList<>();
                            reference = FirebaseDatabase.getInstance()
                                    .getReference().child("الاشخاص المتعادلين").child("نائب امين لجنة الجواله والخدمة العامه");
                            Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                            String namewin = String.valueOf(snapshot1.child("name").getValue());
                                            Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                                            String idtwin = String.valueOf(snapshot1.child("id").getValue());
                                            winnerList.add(new Winers(namewin, countwin, idtwin));
                                        }
                                    }
                                    if (winnerList.size() > 1) {
                                        currentMembers.child("نائب امين لجنة الجواله والخدمة العامه").child("name").setValue("في حالة تعادل");
                                    } else {
                                        //only one winner
                                        for (Winers item : winnerList) {
                                            item.getName();
                                            item.getCount();
                                            item.getId();
                                            String SSN = item.getId();
                                            reference = FirebaseDatabase.getInstance()
                                                    .getReference().child("الفائزون").child("نائب امين لجنة الجواله والخدمة العامه");
                                            currentMembers.child("نائب امين لجنة الجواله والخدمة العامه").setValue(item);
                                            reference.setValue(item);
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void ReVote2getWin7() {
        reference = FirebaseDatabase.getInstance()
                .getReference().child("الاشخاص المتعادلين").child("امين لجنة النشاط الاجتماعي والرحلات");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()) {
                    for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                        countwin = String.valueOf(snapshot.child("count").getValue());

                        final List<Winers> winnerList = new ArrayList<>();
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الاشخاص المتعادلين").child("امين لجنة النشاط الاجتماعي والرحلات");
                        Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                        String namewin = String.valueOf(snapshot1.child("name").getValue());
                                        Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                                        String idtwin = String.valueOf(snapshot1.child("id").getValue());
                                        winnerList.add(new Winers(namewin, countwin, idtwin));
                                    }
                                }
                                if (winnerList.size() > 1) {
                                    currentMembers.child("امين لجنة النشاط الاجتماعي والرحلات").child("name").setValue("في حالة تعادل");

                                } else {
                                    //only one winner
                                    for (Winers item : winnerList) {
                                        item.getName();
                                        item.getCount();
                                        item.getId();
                                        String SSN = item.getId();
                                        reference = FirebaseDatabase.getInstance()
                                                .getReference().child("الفائزون").child("امين لجنة النشاط الاجتماعي والرحلات");
                                        reference.setValue(item);
                                        currentMembers.child("امين لجنة النشاط الاجتماعي والرحلات").setValue(item);

                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void ReVote2getWin8() {
        reference = FirebaseDatabase.getInstance()
                .getReference().child("الاشخاص المتعادلين").child("نائب امين لجنة النشاط الاجتماعي والرحلات");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()) {
                    if (datasnapshot.exists()) {
                        for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                            String countwin = String.valueOf(snapshot.child("count").getValue().toString());

                            final List<Winers> winnerList = new ArrayList<>();
                            reference = FirebaseDatabase.getInstance()
                                    .getReference().child("الاشخاص المتعادلين").child("نائب امين لجنة النشاط الاجتماعي والرحلات");
                            Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                            String namewin = String.valueOf(snapshot1.child("name").getValue());
                                            Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                                            String idtwin = String.valueOf(snapshot1.child("id").getValue());
                                            winnerList.add(new Winers(namewin, countwin, idtwin));
                                        }
                                    }
                                    if (winnerList.size() > 1) {
                                        currentMembers.child("نائب امين لجنة النشاط الاجتماعي والرحلات").child("name").setValue("في حالة تعادل");
                                    } else {
                                        //only one winner
                                        for (Winers item : winnerList) {
                                            item.getName();
                                            item.getCount();
                                            item.getId();
                                            String SSN = item.getId();
                                            reference = FirebaseDatabase.getInstance()
                                                    .getReference().child("الفائزون").child("نائب امين لجنة النشاط الاجتماعي والرحلات");
                                            currentMembers.child("نائب امين لجنة النشاط الاجتماعي والرحلات").setValue(item);
                                            reference.setValue(item);
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void ReVote2getWin9() {
        reference = FirebaseDatabase.getInstance()
                .getReference().child("الاشخاص المتعادلين").child("امين لجنة النشاط الثقافي والاعلامي");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()) {
                    for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                        countwin = String.valueOf(snapshot.child("count").getValue());

                        final List<Winers> winnerList = new ArrayList<>();
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الاشخاص المتعادلين").child("امين لجنة النشاط الثقافي والاعلامي");
                        Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                        String namewin = String.valueOf(snapshot1.child("name").getValue());
                                        Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                                        String idtwin = String.valueOf(snapshot1.child("id").getValue());
                                        winnerList.add(new Winers(namewin, countwin, idtwin));
                                    }
                                }
                                if (winnerList.size() > 1) {
                                    currentMembers.child("امين لجنة النشاط الثقافي والاعلامي").child("name").setValue("في حالة تعادل");

                                } else {
                                    //only one winner
                                    for (Winers item : winnerList) {
                                        item.getName();
                                        item.getCount();
                                        item.getId();
                                        String SSN = item.getId();
                                        reference = FirebaseDatabase.getInstance()
                                                .getReference().child("الفائزون").child("امين لجنة النشاط الثقافي والاعلامي");
                                        reference.setValue(item);
                                        currentMembers.child("امين لجنة النشاط الثقافي والاعلامي").setValue(item);


                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void ReVote2getWin10() {
        reference = FirebaseDatabase.getInstance()
                .getReference().child("الاشخاص المتعادلين").child("نائب امين لجنة النشاط الثقافي والاعلامي");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()) {
                    if (datasnapshot.exists()) {
                        for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                            String countwin = String.valueOf(snapshot.child("count").getValue().toString());

                            final List<Winers> winnerList = new ArrayList<>();
                            reference = FirebaseDatabase.getInstance()
                                    .getReference().child("الاشخاص المتعادلين").child("نائب امين لجنة النشاط الثقافي والاعلامي");
                            Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                            String namewin = String.valueOf(snapshot1.child("name").getValue());
                                            Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                                            String idtwin = String.valueOf(snapshot1.child("id").getValue());
                                            winnerList.add(new Winers(namewin, countwin, idtwin));
                                        }
                                    }
                                    if (winnerList.size() > 1) {
                                        currentMembers.child("نائب امين لجنة النشاط الثقافي والاعلامي").child("name").setValue("في حالة تعادل");
                                    } else {
                                        //only one winner
                                        for (Winers item : winnerList) {
                                            item.getName();
                                            item.getCount();
                                            item.getId();
                                            String SSN = item.getId();
                                            reference = FirebaseDatabase.getInstance()
                                                    .getReference().child("الفائزون").child("نائب امين لجنة النشاط الثقافي والاعلامي");
                                            currentMembers.child("نائب امين لجنة النشاط الثقافي والاعلامي").setValue(item);
                                            reference.setValue(item);
                                        }
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                }
                            });
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void ReVote2getWin11() {
        reference = FirebaseDatabase.getInstance()
                .getReference().child("الاشخاص المتعادلين").child("امين لجنة النشاط الرياضي");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()) {
                    for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                        countwin = String.valueOf(snapshot.child("count").getValue());

                        final List<Winers> winnerList = new ArrayList<>();
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الاشخاص المتعادلين").child("امين لجنة النشاط الرياضي");
                        Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                        String namewin = String.valueOf(snapshot1.child("name").getValue());
                                        Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                                        String idtwin = String.valueOf(snapshot1.child("id").getValue());
                                        winnerList.add(new Winers(namewin, countwin, idtwin));
                                    }
                                }
                                if (winnerList.size() > 1) {
                                    currentMembers.child("امين لجنة النشاط الرياضي").child("name").setValue("في حالة تعادل");

                                } else {
                                    //only one winner
                                    for (Winers item : winnerList) {
                                        item.getName();
                                        item.getCount();
                                        item.getId();
                                        String SSN = item.getId();
                                        reference = FirebaseDatabase.getInstance()
                                                .getReference().child("الفائزون").child("امين لجنة النشاط الرياضي");
                                        reference.setValue(item);
                                        currentMembers.child("امين لجنة النشاط الرياضي").setValue(item);
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) { }
                        });
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void ReVote2getWin12() {
        reference = FirebaseDatabase.getInstance()
                .getReference().child("الاشخاص المتعادلين").child("نائب امين لجنة النشاط الرياضي");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()) {
                    if (datasnapshot.exists()) {
                        for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                            String countwin = String.valueOf(snapshot.child("count").getValue().toString());

                            final List<Winers> winnerList = new ArrayList<>();
                            reference = FirebaseDatabase.getInstance()
                                    .getReference().child("الاشخاص المتعادلين").child("نائب امين لجنة النشاط الرياضي");
                            Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                            String namewin = String.valueOf(snapshot1.child("name").getValue());
                                            Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                                            String idtwin = String.valueOf(snapshot1.child("id").getValue());
                                            winnerList.add(new Winers(namewin, countwin, idtwin));
                                        }
                                    }
                                    if (winnerList.size() > 1) {
                                        currentMembers.child("نائب امين لجنة النشاط الرياضي").child("name").setValue("في حالة تعادل");
                                    } else {
                                        //only one winner
                                        for (Winers item : winnerList) {
                                            item.getName();
                                            item.getCount();
                                            item.getId();
                                            String SSN = item.getId();
                                            reference = FirebaseDatabase.getInstance()
                                                    .getReference().child("الفائزون").child("نائب امين لجنة النشاط الرياضي");
                                            currentMembers.child("نائب امين لجنة النشاط الرياضي").setValue(item);
                                            reference.setValue(item);
                                        }
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) { }
                            });
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void ReVote2getWin13() {
        reference = FirebaseDatabase.getInstance()
                .getReference().child("الاشخاص المتعادلين").child("امين لجنة النشاط العلمي والتكنولوجي");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()) {
                    for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                        countwin = String.valueOf(snapshot.child("count").getValue());

                        final List<Winers> winnerList = new ArrayList<>();
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الاشخاص المتعادلين").child("امين لجنة النشاط العلمي والتكنولوجي");
                        Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                        String namewin = String.valueOf(snapshot1.child("name").getValue());
                                        Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                                        String idtwin = String.valueOf(snapshot1.child("id").getValue());
                                        winnerList.add(new Winers(namewin, countwin, idtwin));
                                    }
                                }
                                if (winnerList.size() > 1) {
                                    currentMembers.child("امين لجنة النشاط العلمي والتكنولوجي").child("name").setValue("في حالة تعادل");

                                } else {
                                    //only one winner
                                    for (Winers item : winnerList) {
                                        item.getName();
                                        item.getCount();
                                        item.getId();
                                        String SSN = item.getId();
                                        reference = FirebaseDatabase.getInstance()
                                                .getReference().child("الفائزون").child("امين لجنة النشاط العلمي والتكنولوجي");
                                        reference.setValue(item);
                                        currentMembers.child("امين لجنة النشاط العلمي والتكنولوجي").setValue(item);
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) { }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void ReVote2getWin14() {
        reference = FirebaseDatabase.getInstance()
                .getReference().child("الاشخاص المتعادلين").child("نائب امين لجنة النشاط العلمي والتكنولوجي");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()) {
                    if (datasnapshot.exists()) {
                        for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                            String countwin = String.valueOf(snapshot.child("count").getValue().toString());

                            final List<Winers> winnerList = new ArrayList<>();
                            reference = FirebaseDatabase.getInstance()
                                    .getReference().child("الاشخاص المتعادلين").child("نائب امين لجنة النشاط العلمي والتكنولوجي");
                            Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                            String namewin = String.valueOf(snapshot1.child("name").getValue());
                                            Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                                            String idtwin = String.valueOf(snapshot1.child("id").getValue());
                                            winnerList.add(new Winers(namewin, countwin, idtwin));
                                        }
                                    }
                                    if (winnerList.size() > 1) {
                                        currentMembers.child("نائب امين لجنة النشاط العلمي والتكنولوجي").child("name").setValue("في حالة تعادل");
                                    } else {
                                        //only one winner
                                        for (Winers item : winnerList) {
                                            item.getName();
                                            item.getCount();
                                            item.getId();
                                            String SSN = item.getId();
                                            reference = FirebaseDatabase.getInstance()
                                                    .getReference().child("الفائزون").child("نائب امين لجنة النشاط العلمي والتكنولوجي");
                                            currentMembers.child("نائب امين لجنة النشاط العلمي والتكنولوجي").setValue(item);
                                            reference.setValue(item);
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) { }
                            });
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
    private void ReVote2getWin15() {
        reference = FirebaseDatabase.getInstance()
                .getReference().child("الاشخاص المتعادلين").child("امين لجنة النشاط الفني");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()) {
                    for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                        countwin = String.valueOf(snapshot.child("count").getValue());

                        final List<Winers> winnerList = new ArrayList<>();
                        reference = FirebaseDatabase.getInstance()
                                .getReference().child("الاشخاص المتعادلين").child("امين لجنة النشاط الفني");
                        Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                        String namewin = String.valueOf(snapshot1.child("name").getValue());
                                        Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                                        String idtwin = String.valueOf(snapshot1.child("id").getValue());
                                        winnerList.add(new Winers(namewin, countwin, idtwin));
                                    }
                                }
                                if (winnerList.size() > 1) {
                                    currentMembers.child("امين لجنة النشاط الفني").child("name").setValue("في حالة تعادل");

                                } else {
                                    //only one winner
                                    for (Winers item : winnerList) {
                                        item.getName();
                                        item.getCount();
                                        item.getId();
                                        String SSN = item.getId();
                                        reference = FirebaseDatabase.getInstance()
                                                .getReference().child("الفائزون").child("امين لجنة النشاط الفني");
                                        reference.setValue(item);
                                        currentMembers.child("امين لجنة النشاط الفني").setValue(item);
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) { }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
    private void ReVote2getWin16() {
        reference = FirebaseDatabase.getInstance()
                .getReference().child("الاشخاص المتعادلين").child("نائب امين لجنة النشاط الفني");
        Query query = reference.orderByChild("count").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()) {
                    if (datasnapshot.exists()) {
                        for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                            String countwin = String.valueOf(snapshot.child("count").getValue().toString());

                            final List<Winers> winnerList = new ArrayList<>();
                            reference = FirebaseDatabase.getInstance()
                                    .getReference().child("الاشخاص المتعادلين").child("نائب امين لجنة النشاط الفني");
                            Query query = reference.orderByChild("count").equalTo(Integer.parseInt(countwin));
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                            String namewin = String.valueOf(snapshot1.child("name").getValue());
                                            Integer countwin = Integer.parseInt(snapshot1.child("count").getValue().toString());
                                            String idtwin = String.valueOf(snapshot1.child("id").getValue());
                                            winnerList.add(new Winers(namewin, countwin, idtwin));
                                        }
                                    }
                                    if (winnerList.size() > 1) {
                                        currentMembers.child("نائب امين لجنة النشاط الفني").child("name").setValue("في حالة تعادل");
                                    } else {
                                        //only one winner
                                        for (Winers item : winnerList) {
                                            item.getName();
                                            item.getCount();
                                            item.getId();
                                            String SSN = item.getId();
                                            reference = FirebaseDatabase.getInstance()
                                                    .getReference().child("الفائزون").child("نائب امين لجنة النشاط الفني");
                                            currentMembers.child("نائب امين لجنة النشاط الفني").setValue(item);
                                            reference.setValue(item);
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) { }
                            });
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void afterEndVote(){
        reference = FirebaseDatabase.getInstance()
                .getReference().child("الفائزون");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name1 = snapshot.child("رئيس اتحاد الطلبة").child("name").getValue().toString();
                String name2 = snapshot.child("نائب رئيس اتحاد الطلبة").child("name").getValue().toString();
                currentMembers.child("رئيس اتحاد الطلبة").child("name").setValue(name1);
                currentMembers.child("نائب رئيس اتحاد الطلبة").child("name").setValue(name2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void sumVoteFunctions(){
        getWinRaeesAt7ad();
        getWinNaeebRaeesAt7ad();
        getWin3();getWin4();getWin5();getWin6();getWin7();getWin8();getWin9();getWin10();getWin11();getWin12();getWin13();getWin14();getWin15();getWin16();
    }
    private void sumEqualFunctions(){
        ReVote2getWinRaeesAt7ad();
        ReVote2getWinNaeebRaeesAt7ad();
        ReVote2getWin3();ReVote2getWin4();ReVote2getWin5();ReVote2getWin6();ReVote2getWin7();ReVote2getWin8();ReVote2getWin9();ReVote2getWin10();ReVote2getWin11();
        ReVote2getWin12();ReVote2getWin13();ReVote2getWin14();ReVote2getWin15();ReVote2getWin16();  }

}