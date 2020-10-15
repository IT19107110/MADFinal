package com.example.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.SaveInfo;
import android.view.View;
import android.widget.Button;

public class AdminPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
    }

    public void bus(View view) {

    }

    public void schView(View view) {
        Intent i = new Intent(this, ViewS.class);
        startActivity(i);
    }

    public void schAdd(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void schUpdate(View view) {
        Intent i = new Intent(this, Update.class);
        startActivity(i);
    }

    public void schDelete(View view) {
        Intent i = new Intent(this, Delete.class);
        startActivity(i);
    }
}