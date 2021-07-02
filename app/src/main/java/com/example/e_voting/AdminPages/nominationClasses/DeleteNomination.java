package com.example.e_voting.AdminPages.nominationClasses;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.e_voting.Model.DeleteReasonModel;
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

public class DeleteNomination extends AppCompatActivity {

    ImageView imageView;
    EditText et_deleteReason;
    Button btn_delete;

    public Uri imgUri;
    StorageReference deleteReason_imageReference;
    DatabaseReference dbDeleteReason,db_Candidates;
    String name,SSN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_nomination_admin);

        getSupportActionBar().setTitle("حذف مرشح");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle extra = getIntent().getExtras();
        if (extra!=null){
            name = extra.getString("name");
            SSN = extra.getString("SSN");

        }

        imageView = findViewById(R.id.img_delete);
        et_deleteReason = findViewById(R.id.et_deleteReason);
        btn_delete = findViewById(R.id.btn_delete);

        deleteReason_imageReference= FirebaseStorage.getInstance().getReference("صور حذق المرشحين");
        dbDeleteReason = FirebaseDatabase.getInstance().getReference("بيانات حذف المرشحين");
        db_Candidates = FirebaseDatabase.getInstance().getReference("المرشحون");

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageUploader();

                db_Candidates.child("رئيس اتحاد الطلبة").child(SSN).removeValue();
                Toast.makeText(DeleteNomination.this, "لقد تم حذف المرشح", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileChooser();

            }
        });
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
            imageView.setImageURI(imgUri);
        }
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

            new AlertDialog.Builder(DeleteNomination.this)
                    .setTitle("عذرا")
                    .setMessage("قم بادخال صورة سبب الحذف")
                    .setPositiveButton("حسنا",null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }else {
            final StorageReference reference = deleteReason_imageReference.child(System.currentTimeMillis() + "." + getExtension(imgUri));
            reference.putFile(imgUri).addOnSuccessListener(new OnSuccessListener <UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                    //Get a URl to the upload content
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener <Uri>() {
                        @Override
                        public void onSuccess(final Uri uri) {
                            dbDeleteReason.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    DeleteReasonModel reasonModel = new DeleteReasonModel();

                                    reasonModel.setDeleteImage(uri.toString());
                                    reasonModel.setEtDeleteReason(et_deleteReason.getText().toString());
                                    reasonModel.setName(name);
                                    reasonModel.setSSN(SSN);

                                    dbDeleteReason.child(SSN).setValue(reasonModel);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return true;
    }
}