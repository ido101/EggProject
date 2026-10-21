package com.example.eggproject;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Leaderboard extends AppCompatActivity implements View.OnClickListener {
    TextView txt_name;
    TextView txt_score;
    private RecyclerView rv_players;
    Button btn_delete;
    Button btn_specific;
    EditText edit_player;
    ImageButton btn_back;
    private DataBaseHelper dbHelper;
    private ArrayList<Player> players;
    private EggAdapter ea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_leaderboard);
        txt_name = findViewById(R.id.txt_name);
        txt_score = findViewById(R.id.txt_score);
        btn_delete = findViewById(R.id.btn_delete);
        btn_specific = findViewById(R.id.btn_specific);
        edit_player = findViewById(R.id.edit_player);
        txt_name.setPaintFlags(txt_name.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txt_score.setPaintFlags(txt_score.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        Intent intent = getIntent();
        rv_players = (RecyclerView) findViewById(R.id.rv_players);
        btn_back = findViewById(R.id.btn_back2);
        dbHelper = new DataBaseHelper(this);
        players = dbHelper.getPlayersInfo();
        Collections.sort(players);
        ea = new EggAdapter(players);
        btn_back.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_specific.setOnClickListener(this);
        rv_players.setAdapter(ea);
        rv_players.setLayoutManager(new LinearLayoutManager(this));
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.lb_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View view) {
        if(view == btn_back){
            finish();
        }
        if(view == btn_delete){
            dbHelper.clearPlayers();
        }
        if(view == btn_specific){
            dbHelper.removePlayer(String.valueOf(edit_player.getText()));
            edit_player.setText("");
        }
    }
}