package com.example.notes_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateNoteActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        mAuth = FirebaseAuth.getInstance();

    }

    public void saveNote(View view) {
        EditText titleet = findViewById(R.id.n_title);
        EditText contentet = findViewById(R.id.n_content);
        String title = titleet.getText().toString();
        String content = contentet.getText().toString();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> notedetails = new HashMap<>();
        notedetails.put("title", title);
        notedetails.put("content", content);
        FirebaseUser user = mAuth.getCurrentUser();
        notedetails.put("UID", user.getUid());
        db.collection("Notes").add(notedetails)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(CreateNoteActivity.this, "Note saved", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(CreateNoteActivity.this, ViewNotesActivity.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CreateNoteActivity.this, "Failed to save note", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
