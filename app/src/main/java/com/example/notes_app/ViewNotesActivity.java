package com.example.notes_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ViewNotesActivity extends AppCompatActivity {
    Button add_note;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);

        add_note = (Button) findViewById(R.id.create_note);
        add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewNotesActivity.this, CreateNoteActivity.class);
                startActivity(intent);
            }
        });
    }

        public void logOut(View view){
            mAuth.signOut();
            startActivity(new Intent(ViewNotesActivity.this,MainActivity.class));

        }
}
