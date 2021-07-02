package com.example.e_voting;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_voting.AdminPages.Home_Admin;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    Button btnLogIn;
    EditText etEmail, etPassword;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        handler = new Handler();
        btnLogIn = findViewById(R.id.logIn);
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);

        //initialize firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference().child("Users");
        ;
        final DatabaseReference student_pages = database.getReference("Student Current Page");

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog dialog = new ProgressDialog(SignIn.this);
                dialog.setMessage("انتظر!");
                handler.post(dialog::show);
                table_user.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Boolean booleanValue = false;
                        for (DataSnapshot child : snapshot.getChildren()) {
                            for (int i = 0; i < child.getChildrenCount(); i++) {
                                if (child.child("email").getValue().toString().equals(etEmail.getText().toString())
                                        && child.child("password").getValue().toString().equals(etPassword.getText().toString())) {

                                    booleanValue = true;
                                    // check if user is admin or user
                                    try {
                                        handler.post(dialog::hide);
                                        if (child.child("user").getValue().toString().equals("admin")) { // when user is admin
                                            table_user.removeEventListener(this);
                                            Intent intent = new Intent(SignIn.this, Home_Admin.class);
                                            startActivity(intent);
                                            finish();

                                        } else if (child.child("user").getValue().toString().equals("student")) { // when user is student
                                            table_user.removeEventListener(this);
                                            Intent intent = new Intent(SignIn.this, Home_Student.class);
                                            intent.putExtra("SSN", child.child("الرقم القومي").getValue().toString());
                                            intent.putExtra("name", child.child("name").getValue().toString());
                                            startActivity(intent);
                                            finish();
                                        }
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                        }
                        if (booleanValue != true) {
                            dialog.dismiss();
                            new AlertDialog.Builder(SignIn.this)
                                    .setTitle("عذرا")
                                    .setMessage("الايميل او كلمة المرور خطاء")
                                    .setPositiveButton("حسنا", null)
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                        }
//                        for (DataSnapshot child : snapshot.getChildren()) {
//                            // check if email and password exist or not
//                            if (child.child("email").getValue().toString().equals(etEmail.getText().toString())
//                                    && child.child("password").getValue().toString().equals(etPassword.getText().toString())) {
//
//                                // check if user is admin or user
//                                if (child.child("user").getValue().toString().equals("admin")) { // when user is admin
//
//                                    Intent intent = new Intent(SignIn.this, Home_Admin.class);
//                                    startActivity(intent);
//
//                                } else if (child.child("user").getValue().toString().equals("student")) { // when user is student
//
//                                    Intent intent = new Intent(SignIn.this, Home_student2.class);
//                                    intent.putExtra("SSN", child.child("الرقم القومي").getValue().toString());
//                                    intent.putExtra("name", child.child("name").getValue().toString());
//                                    startActivity(intent);
//
//                                }
//                                break;
//                            }else if (child.child("email").getValue().toString().equals(etEmail.getText().toString())
//                                    || child.child("password").getValue().toString().equals(etPassword.getText().toString())){
//                                dialog.dismiss();
//                                Toast.makeText(SignIn.this, "الايميل او كلمة المرور خطاء", Toast.LENGTH_SHORT).show();
//                            }
//                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}