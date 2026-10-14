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
    String password;
    Button btn_login;
    EditText edit_name;
    EditText edit_pass;
    DataBaseHelper dbHelper;
    private DataBaseHelper db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        db = new DataBaseHelper(this);
        btn_login = findViewById(R.id.btn_login);
        edit_name = findViewById(R.id.edit_name);
        edit_pass = findViewById(R.id.edit_pass);
        dbHelper = new DataBaseHelper(this);
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
            name = edit_name.getText().toString().trim();
            password = edit_pass.getText().toString().trim();
            if(name.isEmpty() || password.isEmpty())
                Toast.makeText(this, "please enter your name and password!", Toast.LENGTH_LONG).show();
            else
            {
                if(db.playerExists(name)){
                    if(db.checkPass(name, password)){
                        edit_name.setText("");
                        edit_pass.setText("");
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        intent.putExtra("name", name);
                        intent.putExtra("password", password);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(this, "password is incorrect!", Toast.LENGTH_LONG).show();
                        edit_pass.setText("");
                    }
                }
                else{
                    db.addPlayer(name, password, 0);
                    edit_name.setText("");
                    edit_pass.setText("");
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("password", password);
                    startActivity(intent);
                }

            }
        }
    }
}