package com.example.e_voting.NominationPages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_voting.R;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;


public class OCR extends AppCompatActivity {

    private final static String TAG = "ImageOCR";
    private final static String test = "لا توجد احكام جنائية مسجلة";
    private String mAPiKey = "e252f3bca388957"; //Keyapi
    private boolean isOverlayRequired;
    private String mImageUrl;
    private String mLanguage;
    private TextView mTxtResult;
    private String state = null;
    //private static final int REQUEST_IMAGE_CAPTURE = 101;

    private Button b_check;
    private TextView Rresult, output;
    private ImageView picFrame;
    private String selectedImagePath;

    String name,SSN,response,x,y;

    Button btn_sendResult;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_c_r);


        getSupportActionBar().setTitle("الترشح");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle extra = getIntent().getExtras();
        if (extra!=null){
            SSN = extra.getString("SSN");
            name = extra.getString("name");

        }

        picFrame = (ImageView) findViewById(R.id.picFrame);

        mTxtResult = (TextView) findViewById(R.id.test);

        btn_sendResult =findViewById(R.id.btn_check);

        SharedPreferences sharedPref = getSharedPreferences("kk", Context.MODE_PRIVATE);
        selectedImagePath = sharedPref.getString("ipath", null);
        x = encodeImage(selectedImagePath);
        y = "data:image/jpeg;base64,"+x;
        Drawable image = Drawable.createFromPath(selectedImagePath);
        picFrame.setImageDrawable(image);
        //b_check = (Button) findViewById(R.id.btn_check);

        Toast.makeText(OCR.this, "Test Check " , Toast.LENGTH_SHORT).show();
        mImageUrl = y; // imageurl
        mLanguage = "ara"; //Language
        isOverlayRequired = true;

        checkPhoto();

        btn_sendResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mTxtResult.getText().toString().equals("Accept")) {
                    Intent intent = new Intent(OCR.this, Nomination.class);
                    intent.putExtra("SSN",SSN);
                    intent.putExtra("name",name);
                    Toast.makeText(OCR.this, "لا يوجد احكام جنائيا", Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(intent);
                }else {
                    Toast.makeText(OCR.this, "هناك احكام جنائيا", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkPhoto() {
        new OCRAsyncTask(OCR.this, mAPiKey, isOverlayRequired, mLanguage)
                .setCallback(new OCRAsyncTask.OCRCallback() {
                    @Override
                    public void onOCRCallbackResults(JSONObject response) {
                        try {
                            handleJSONResponse(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).execute(mImageUrl);
    }

    private void handleJSONResponse(JSONObject response) throws JSONException {
        if (response.has("ParsedText")) {
            JSONArray overlay = response.getJSONObject("TextOverlay")
                    .getJSONArray("Lines");
            Log.d(TAG, "TextOverlay returned " + overlay.length() + " Lines");
            for (int i = 0; i < overlay.length(); i++) {
                JSONArray words = overlay.getJSONObject(i).getJSONArray("Words");
                Log.d(TAG, "Line " + i + " returned " + words.length() + " Words");
                for (int x = 0; x < words.length(); x++) {
                    Log.d(TAG, "Line " + i + ", Word " + x + ": " +
                            words.getJSONObject(x).getString("WordText"));
                }
            }
            String text = response.getString("ParsedText");
            Log.d("ParsedText", text);

            if (text.contains(test)) {
                mTxtResult.setText("Accept");
                state = "لا يوجد احكام او قضايا";
//                Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "no", Toast.LENGTH_SHORT).show();
                mTxtResult.setText("Reject");
                state = "عذرا يوجد احكام وقضايا";
            }
        } else if (response.has("ErrorMessage")) {
            String error = response.getString("ErrorMessage");
            Log.d("ErrorMessage", error);
            mTxtResult.setText(error);

        }
    }

    private String encodeImage(String path)
    {
        File imagefile = new File(path);
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(imagefile);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        Bitmap bm = BitmapFactory.decodeStream(fis);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,25,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);
        //Base64.de
        return encImage;

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                Intent intent = new Intent(OCR.this,Feesh.class);
                intent.putExtra("SSN", SSN);
                intent.putExtra("name", name);
                startActivity(intent);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

