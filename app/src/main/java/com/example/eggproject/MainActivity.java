package com.example.eggproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private EggManager em;
    ImageButton btn_egg;
    Button btn_reset;
    Button btn_scores;
    ImageButton btn_back;
    TextView txt_num;
    TextView txt_help;
    Switch swt;
    String name;
    String password;
    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        password = intent.getStringExtra("password");
        txt_help = findViewById(R.id.txt_help);
        txt_help.setText(name + " help me!");
        em = new EggManager(this);
        btn_egg = findViewById(R.id.btn_egg);
        btn_reset = findViewById(R.id.btn_reset);
        btn_scores = findViewById(R.id.btn_scores);
        btn_back = findViewById(R.id.btn_back);
        txt_num = findViewById(R.id.txt_num);
        swt = findViewById(R.id.swt);
        btn_egg.setOnClickListener(this);
        btn_reset.setOnClickListener(this);
        btn_scores.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        swt.setOnCheckedChangeListener(this);
        dbHelper = new DataBaseHelper(this);
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
            if(!em.isItTime())
            {
                em.setCount(em.getCount()-1);
                txt_num.setText(Integer.toString(em.getCount()));
                if (em.getCount() == 5)
                {
                    btn_egg.setImageResource(R.drawable.cracked);
                }
            }
            else
            {
                txt_num.setVisibility(View.INVISIBLE);
                txt_help.setVisibility(View.INVISIBLE);
                btn_egg.setImageResource(R.drawable.bird);
                dbHelper.updateGames(name, password);
            }
        }
        if(v == btn_reset)
        {
            btn_egg.setImageResource(R.drawable.egg);
            em.setCount(10);
            txt_num.setText(Integer.toString(em.getCount()));
            txt_num.setVisibility(View.VISIBLE);
        }
        if(v == btn_back)
        {
            finish();
        }
    }

    @Override
    public void onCheckedChanged(@NonNull CompoundButton compoundButton, boolean b)
    {
        if(b)
        {
            btn_egg.setImageAlpha(128);
        }
        else
        {
            btn_egg.setImageAlpha(255);
        }
    }
}