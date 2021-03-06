package com.example.notes_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null){
            Toast.makeText(this, "Welcome back!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, ViewNotesActivity.class));
        }
    }

        public void signUp(View view) {
            EditText loginet = findViewById(R.id.email);
            EditText passwordet = findViewById(R.id.password);
            String email = loginet.getText().toString();
            String password = passwordet.getText().toString();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(getApplicationContext(),"Sign up successful.",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(MainActivity.this,ViewNotesActivity.class));
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(getApplicationContext(),task.getException().getMessage().toString(),Toast.LENGTH_LONG).show();
                            }

                            // ...
                        }
                    });
        }

        public void logIn(View view){
            EditText loginet = findViewById(R.id.email);
            EditText passwordet = findViewById(R.id.password);
            String email = loginet.getText().toString();
            String password = passwordet.getText().toString();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(MainActivity.this, "Sign-in successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, ViewNotesActivity.class));
                            }
                            else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(MainActivity.this, task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }


