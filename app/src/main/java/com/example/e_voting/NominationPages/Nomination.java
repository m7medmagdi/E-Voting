package com.example.e_voting.NominationPages;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_voting.Home_Student;
import com.example.e_voting.Model.Condidate;
import com.example.e_voting.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Nomination extends AppCompatActivity {

    private String state = null;
    Button btnSendData;
    Spinner spinner;
    public Uri imgUri;
    private String imageFilePath = "";
    public static final int REQUEST_IMAGE = 100;

    TextView tv_feesh;
    StorageReference tlab_trsh7_imageReference;
    DatabaseReference candidate_table, users;
    ImageView feesh_image,tlab_trsh7_image;
    ProgressDialog progressDialog;
    String SSN, email, name,issues;
    String [] positions = {"اضغط لاختيار المنصب","رئيس اتحاد الطلبة","نائب رئيس اتحاد الطلبة","امين لجنة الاسر","نائب امين لجنة الاسر",
                           "امين لجنة النشاط الرياضي","نائب امين لجنة النشاط الرياضي","امين لجنة النشاط الثقافي والاعلامي","نائب امين لجنة النشاط الثقافي والاعلامي","امين لجنة النشاط الفني",
                           "نائب امين لجنة النشاط الفني","امين لجنة الجواله والخدمة العامه","نائب امين لجنة الجواله والخدمة العامه","امين لجنة النشاط الاجتماعي والرحلات","نائب امين لجنة النشاط الاجتماعي والرحلات",
                           "امين لجنة النشاط العلمي والتكنولوجي","نائب امين لجنة النشاط العلمي والتكنولوجي"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nomination);


        getSupportActionBar().setTitle("الترشح");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle extra = getIntent().getExtras();
        if (extra!=null){
            name = extra.getString("name");
            SSN = extra.getString("SSN");
            issues = extra.getString("لا يوجد احكام وقضايا");

        }


        btnSendData = findViewById(R.id.send);

        candidate_table = FirebaseDatabase.getInstance().getReference("المرشحون");
        tlab_trsh7_imageReference= FirebaseStorage.getInstance().getReference("صور طلبات الترشح");
        users =FirebaseDatabase.getInstance().getReference("Users");

        progressDialog = new ProgressDialog(Nomination.this);

        spinner = findViewById(R.id.spiner);

//        feesh_image = findViewById(R.id.feesh_image);
        tlab_trsh7_image = findViewById(R.id.tlab_trsh7_image);

        // image talb trash7
        tlab_trsh7_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileChooser();
            }
        });


        //spinner's adapter
        ArrayAdapter  arrayAdapter = new ArrayAdapter <>(this, R.layout.spinner, positions);
        arrayAdapter.setDropDownViewResource(R.layout.spinner2);
        spinner.setAdapter(arrayAdapter);



        btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               if (spinner.getSelectedItemPosition() == 0) {
                    TextView errorText = (TextView) spinner.getSelectedView();
                    errorText.setText("اختر المنصب");
                    errorText.setError("اختر المنصب");
               } else {
                   new AlertDialog.Builder(Nomination.this)
                           .setTitle("تاكيد")
                           .setMessage("هل تريد الترشح؟")
                           .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialogInterface, int i) {

                                   imageUploader();
                                   Intent intent = new Intent(Nomination.this, Home_Student.class);
                                   intent.putExtra("SSN",SSN);
                                   intent.putExtra("name",name);
                                   startActivity(intent);
                                   Toast.makeText(Nomination.this, "لقد تم الترشح", Toast.LENGTH_LONG).show();
                                   finish();
                               }
                           })
                           .setNegativeButton("لا", null)
                           .setIcon(android.R.drawable.ic_dialog_alert)
                           .show();
               }
            }
        });
    }

    private String getExtension(Uri uri){
        if (uri!=null) {
            ContentResolver contentResolver = getContentResolver();
            MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
            return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
        }else {
            return "";
        }
    }

    private void imageUploader() {
        if (imgUri==null){

            new AlertDialog.Builder(Nomination.this)
                    .setTitle("عذرا")
                    .setMessage("قم بادخال صورة طلب الترشح")
                    .setPositiveButton("حسنا",null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }else {
            final StorageReference reference = tlab_trsh7_imageReference.child(System.currentTimeMillis() + "." + getExtension(imgUri));
            reference.putFile(imgUri).addOnSuccessListener(new OnSuccessListener <UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                    //Get a URl to the upload content
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener <Uri>() {
                        @Override
                        public void onSuccess(final Uri uri) {
                            candidate_table.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    Condidate condidate = new Condidate(name, 0, SSN, uri.toString());
                                    candidate_table.child(spinner.getSelectedItem().toString()).child(SSN).setValue(condidate);
                                    users.child(SSN).child("userConditionNomination").setValue("0");

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                }
            });
        }
    }
    private void fileChooser() {
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode== RESULT_OK && data!=null && data.getData()!=null){
            imgUri = data.getData();
            tlab_trsh7_image.setImageURI(imgUri);
        }
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {

                SharedPreferences sharedPref = getSharedPreferences("kk", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("ipath", imageFilePath);
                editor.commit();

                Intent intent = new Intent(this, OCR.class);

                startActivityForResult(intent, 11);

            }
            else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "You cancelled the operation", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == 11){
            state = data.getStringExtra("state");
        }
    }

    // هاااااااااااااااام: ناقص الفيش الجنائي وصورة طلب الترشح
    public void add_feesh(View view) {

        //Toast.makeText(this, "Fesh", Toast.LENGTH_SHORT).show();


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(Nomination.this, Home_Student.class);
                intent.putExtra("SSN",SSN);
                intent.putExtra("name",name);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
