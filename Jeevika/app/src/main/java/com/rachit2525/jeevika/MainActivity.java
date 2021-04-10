package com.rachit2525.jeevika;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //declarations
    Button deleteBtn;
    Button publishBtn;
    Button registerBtn;
    Button feedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deleteBtn = findViewById(R.id.deleteBtn);
        publishBtn = findViewById(R.id.publishBtn);
        registerBtn = findViewById(R.id.registerBtn);
        feedBtn = findViewById(R.id.feedBtn);

        deleteBtn.setOnClickListener(this);
        publishBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        feedBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.registerBtn:
            {
                Toast.makeText(this, "Register here!", Toast.LENGTH_SHORT).show();
                openRegisterActivity();
                break;
            }
            case R.id.deleteBtn:
            {
                Toast.makeText(this, "Remove the job!", Toast.LENGTH_SHORT).show();
                openDeleteActivity();
                break;
            }
            case R.id.publishBtn:
            {
                Toast.makeText(this, "Publish the job!", Toast.LENGTH_SHORT).show();
                openPublishActivity();
                break;
            }
            case R.id.feedBtn:
            {
                Toast.makeText(this,"All Jobs are here!",Toast.LENGTH_LONG).show();
                openFeedActivity();
            }

        }
    }

    private void openDeleteActivity() {
        Intent intent = new Intent(this, DeleteActivity.class);
        startActivity(intent);
    }


    private void openRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void openFeedActivity() {
        Intent intent = new Intent(this,FeedActivity.class);
        startActivity(intent);
    }

    private void openPublishActivity() {
        Intent intent = new Intent(this, PublishActivity.class);
        startActivity(intent);
    }

}