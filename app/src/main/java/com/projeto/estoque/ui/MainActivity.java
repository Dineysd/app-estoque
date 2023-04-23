package com.projeto.estoque.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.projeto.estoque.R;
import com.projeto.estoque.integration.task.SincronizacaoTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
    
        Button query_button = findViewById(R.id.query_button);
        Button sync_button = findViewById(R.id.sync_button);
        query_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ListaProdutosActivity.class));
            }
        });
        sync_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SincronizacaoTask sincronizacaoTask = new SincronizacaoTask(MainActivity.this, 50);
                sincronizacaoTask.execute();
            }
        });
    }
}
