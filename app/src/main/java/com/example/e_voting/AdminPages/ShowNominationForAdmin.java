package com.example.e_voting.AdminPages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.e_voting.AdminPages.nominationClasses.AmeenAlriadee;
import com.example.e_voting.AdminPages.nominationClasses.AmeenElfnie;
import com.example.e_voting.AdminPages.nominationClasses.AmeenElgoala;
import com.example.e_voting.AdminPages.nominationClasses.AmeenElthlafee;
import com.example.e_voting.AdminPages.nominationClasses.AmeenEltknologia;
import com.example.e_voting.AdminPages.nominationClasses.AmeenLgntAsur;
import com.example.e_voting.AdminPages.nominationClasses.AmenElr7lat;
import com.example.e_voting.AdminPages.nominationClasses.NaeebAmeenAlriadee;
import com.example.e_voting.AdminPages.nominationClasses.NaeebAmeenElfnie;
import com.example.e_voting.AdminPages.nominationClasses.NaeebAmeenElgoala;
import com.example.e_voting.AdminPages.nominationClasses.NaeebAmeenElthlafee;
import com.example.e_voting.AdminPages.nominationClasses.NaeebAmeenEltknologia;
import com.example.e_voting.AdminPages.nominationClasses.NaeebAmeenLgntAsur;
import com.example.e_voting.AdminPages.nominationClasses.NaeebAmenElr7lat;
import com.example.e_voting.AdminPages.nominationClasses.NaeebRaeesAt7ad;
import com.example.e_voting.AdminPages.nominationClasses.RaeesAt7ad;
import com.example.e_voting.R;

public class ShowNominationForAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_nomination_for_admin);
    }

    public void RaeesAt7ad(View view) {
        Intent intent = new Intent(ShowNominationForAdmin.this, RaeesAt7ad.class);
        startActivity(intent);
    }

    public void NaeebRaeesAt7ad(View view) {
        Intent intent = new Intent(ShowNominationForAdmin.this, NaeebRaeesAt7ad.class);
        startActivity(intent);
    }

    public void AmeenLgntAsur(View view) {
        Intent intent = new Intent(ShowNominationForAdmin.this, AmeenLgntAsur.class);
        startActivity(intent);
    }

    public void NaeebAmeenLgntAsur(View view) {
        Intent intent = new Intent(ShowNominationForAdmin.this, NaeebAmeenLgntAsur.class);
        startActivity(intent);
    }


    public void AmeenElgoala(View view) {
        Intent intent = new Intent(ShowNominationForAdmin.this, AmeenElgoala.class);
        startActivity(intent);
    }

    public void NaeebAmeenElgoala(View view) {
        Intent intent = new Intent(ShowNominationForAdmin.this, NaeebAmeenElgoala.class);
        startActivity(intent);
    }

    public void AmenElr7lat(View view) {
        Intent intent = new Intent(ShowNominationForAdmin.this, AmenElr7lat.class);
        startActivity(intent);
    }

    public void NaeebAmenElr7lat(View view) {
        Intent intent = new Intent(ShowNominationForAdmin.this, NaeebAmenElr7lat.class);
        startActivity(intent);
    }

    public void AmeenElthlafee(View view) {
        Intent intent = new Intent(ShowNominationForAdmin.this, AmeenElthlafee.class);
        startActivity(intent);
    }

    public void NaeebAmeenElthlafee(View view) {
        Intent intent = new Intent(ShowNominationForAdmin.this, NaeebAmeenElthlafee.class);
        startActivity(intent);
    }

    public void AmeenAlriadee(View view) {
        Intent intent = new Intent(ShowNominationForAdmin.this, AmeenAlriadee.class);
        startActivity(intent);
    }

    public void NaeebAmeenAlriadee(View view) {
        Intent intent = new Intent(ShowNominationForAdmin.this, NaeebAmeenAlriadee.class);
        startActivity(intent);
    }

    public void AmeenEltknologia(View view) {
        Intent intent = new Intent(ShowNominationForAdmin.this, AmeenEltknologia.class);
        startActivity(intent);
    }

    public void NaeebAmeenEltknologia(View view) {
        Intent intent = new Intent(ShowNominationForAdmin.this, NaeebAmeenEltknologia.class);
        startActivity(intent);
    }

    public void AmeenElfnie(View view) {
        Intent intent = new Intent(ShowNominationForAdmin.this, AmeenElfnie.class);
        startActivity(intent);
    }

    public void NaeebAmeenElfnie(View view) {
        Intent intent = new Intent(ShowNominationForAdmin.this, NaeebAmeenElfnie.class);
        startActivity(intent);
    }
}