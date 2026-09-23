package com.example.eggproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton btn_egg;
    Button btn_reset;
    Button btn_scores;
    ImageButton btn_back;
    TextView txt_num;
    TextView txt_help;
    String name;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        txt_help = findViewById(R.id.txt_help);
        txt_help.setText(name + " help me!");
        count = 10;
        btn_egg = findViewById(R.id.btn_egg);
        btn_reset = findViewById(R.id.btn_reset);
        btn_scores = findViewById(R.id.btn_scores);
        btn_back = findViewById(R.id.btn_back);
        txt_num = findViewById(R.id.txt_num);
        btn_egg.setOnClickListener(this);
        btn_reset.setOnClickListener(this);
        btn_scores.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v)
    {
        if(v == btn_egg)
        {
            if(count > 1)
            {
                count--;
                txt_num.setText(Integer.toString(count));
                if (count == 5)
                {
                    btn_egg.setImageResource(R.drawable.cracked);
                }
            }
            else
            {
                txt_num.setVisibility(View.INVISIBLE);
                btn_egg.setImageResource(R.drawable.bird);
            }
        }
        if(v == btn_reset)
        {
            btn_egg.setImageResource(R.drawable.egg);
            count = 10;
            txt_num.setText(Integer.toString(count));
            txt_num.setVisibility(View.VISIBLE);
        }
        if(v == btn_back)
        {
            finish();
        }
    }
}