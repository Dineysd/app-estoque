package com.projeto.estoque.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.projeto.estoque.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
    }
}
