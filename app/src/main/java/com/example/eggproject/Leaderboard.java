package com.example.eggproject;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Leaderboard extends AppCompatActivity {
    TextView txt_name;
    TextView txt_score;
    private DataBaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_leaderboard);
        ConstraintLayout layout = findViewById(R.id.main);

        txt_name = findViewById(R.id.txt_name);
        txt_score = findViewById(R.id.txt_score);
        dbHelper = new DataBaseHelper(this);
        ArrayList<String> names = dbHelper.getNames();
        ArrayList<Integer> games = dbHelper.getGames();
        for(int i = 0; i < names.size(); i++){
            TextView name_txt = new TextView(this);
            name_txt.setText(names.get(i));
            TextView score_txt = new TextView(this);
            score_txt.setText(games.get(i));
            layout.addView(name_txt);
            layout.addView(score_txt);
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}