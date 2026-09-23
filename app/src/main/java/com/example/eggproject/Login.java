package com.example.eggproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity implements View.OnClickListener {
    String name;
    Button btn_login;
    EditText edit_txt;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        btn_login = findViewById(R.id.btn_login);
        edit_txt = findViewById(R.id.edit_txt);
        btn_login.setOnClickListener(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View view) {
        if(view == btn_login)
        {
            name = edit_txt.getText().toString().trim();
            if(name.isEmpty())
                Toast.makeText(this, "please enter your name", Toast.LENGTH_LONG).show();
            else
            {
                edit_txt.setText("");
                Intent intent = new Intent(Login.this, MainActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        }
    }
}