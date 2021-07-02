package com.example.e_voting.CandidateResult;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.e_voting.Home_Student;
import com.example.e_voting.R;

public class ElectionResults extends AppCompatActivity {

    String SSN, name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.election_results);

        getSupportActionBar().setTitle("نتائج الانتخابات");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle extra = getIntent().getExtras();
        if (extra!=null){
            SSN = extra.getString("SSN");
            name = extra.getString("name");
        }
    }
    public void RaeesAt7ad(View view) {
        Intent intent = new Intent(ElectionResults.this, CandidateResults_RaeesAt7ad.class);
        startActivity(intent);
    }

    public void NaeebRaeesAt7ad(View view) {
        Intent intent = new Intent(ElectionResults.this, CandidateResult_Nayib_Rayiys_Aitihad_Altalaba.class);
        startActivity(intent);
    }

    public void AmeenLgntAsur(View view) {
        Intent intent = new Intent(ElectionResults.this, CandidateResults_Amin_Lajnat_Alusar.class);
        startActivity(intent);
    }

    public void NaeebAmeenLgntAsur(View view) {
        Intent intent = new Intent(ElectionResults.this, CandidateResults_Nayib_Amin_Lajnat_Alusar.class);
        startActivity(intent);
    }


    public void AmeenElgoala(View view) {
        Intent intent = new Intent(ElectionResults.this, CandidateResults_Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih.class);
        startActivity(intent);
    }

    public void NaeebAmeenElgoala(View view) {
        Intent intent = new Intent(ElectionResults.this, CandidateResults_Nayib_Amin_Lajnat_Aljawalih_Walkhidmat_Al3amih.class);
        startActivity(intent);
    }

    public void AmenElr7lat(View view) {
        Intent intent = new Intent(ElectionResults.this, CandidateResults_Amin_Lajnat_Alnashat_Alaijtimaeii_Walrihlat.class);
        startActivity(intent);
    }

    public void NaeebAmenElr7lat(View view) {
        Intent intent = new Intent(ElectionResults.this, CandidateResults_Nayib_Amin_Lajnat_Alnashat_Alaijtimaeii_Walrihlat.class);
        startActivity(intent);
    }

    public void AmeenElthlafee(View view) {
        Intent intent = new Intent(ElectionResults.this, CandidateResults_Amin_Lajnat_Alnashat_Althaqafii_Waliielamii.class);
        startActivity(intent);
    }

    public void NaeebAmeenElthlafee(View view) {
        Intent intent = new Intent(ElectionResults.this, CandidateResults_Nayib_Amin_Lajnat_Alnashat_Althaqafii_Waliielamii.class);
        startActivity(intent);
    }

    public void AmeenAlriadee(View view) {
        Intent intent = new Intent(ElectionResults.this, CandidateResults_Amin_Lajnat_Alnashat_Alriyadii.class);
        startActivity(intent);
    }

    public void NaeebAmeenAlriadee(View view) {
        Intent intent = new Intent(ElectionResults.this, CandidateResults_Nayib_Amin_Lajnat_Alnashat_Alriyadii.class);
        startActivity(intent);
    }

    public void AmeenEltknologia(View view) {
        Intent intent = new Intent(ElectionResults.this, CandidateResults_Amin_Lajnat_Alnashat.class);
        startActivity(intent);
    }

    public void NaeebAmeenEltknologia(View view) {
        Intent intent = new Intent(ElectionResults.this, CandidateResults_Nayib_Amin_Lajnat_Alnashat.class);
        startActivity(intent);
    }

    public void AmeenElfnie(View view) {
        Intent intent = new Intent(ElectionResults.this, CandidateResults_Amin_Lajnat_Alnashat_Alfnyi.class);
        startActivity(intent);
    }

    public void NaeebAmeenElfnie(View view) {
        Intent intent = new Intent(ElectionResults.this, CandidateResults_Nayib_Amin_Lajnat_Alnashat_Alfnyi.class);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ElectionResults.this, Home_Student.class);
                intent.putExtra("SSN",SSN);
                intent.putExtra("name",name);

                startActivity(intent);
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}