package com.nitish.paymatrixandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class RentAgreementActivity extends AppCompatActivity {

    Button upload;
    private ImageView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_agreement);

        upload = findViewById(R.id.uploadfile);
        navigation = findViewById(R.id.navigation2);

        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),settings.class));
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),UploadFileActivity.class));
            }
        });
    }

    public void goBack(View v){
        finish();
    }
}
