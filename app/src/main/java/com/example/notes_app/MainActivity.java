package com.example.notes_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button login;
    EditText userName;
    EditText passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText)findViewById(R.id.username);
        passWord = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(userName.getText().toString(), passWord.getText().toString());
            }
        });
    }

    private void validate (String userName, String userPassword) {
        if (userName.equals("oorjit") && userPassword.equals("gpw2018")) {
            Intent intent = new Intent(MainActivity.this, CreateNoteActivity.class);
            startActivity(intent);
        }
    }
}
