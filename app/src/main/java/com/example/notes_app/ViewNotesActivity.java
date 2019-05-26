package com.example.notes_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.notes_app.adapters.NotesRecyclerAdapter;
import com.example.notes_app.models.NoteDetails;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ViewNotesActivity extends AppCompatActivity {
    Button add_note;
    private FirebaseAuth mAuth;
    private RecyclerView mRecyclerView;
    private ArrayList<NoteDetails> mNotes = new ArrayList<>();
    private NotesRecyclerAdapter mNotesRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);
        mRecyclerView = findViewById(R.id.recyclerView);
        initRecyclerView();
        randomNotes();

        add_note = (Button) findViewById(R.id.create_note);
        add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewNotesActivity.this, CreateNoteActivity.class);
                startActivity(intent);
            }
        });
    }

    private void randomNotes() {
        for(int i = 1; i < 100; i++){
            NoteDetails note = new NoteDetails();
            note.setTitle("Title #" + i);
            note.setContent("Content #" + i);
            mNotes.add(note);
        }
        mNotesRecyclerAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mNotesRecyclerAdapter = new NotesRecyclerAdapter(mNotes);
        mRecyclerView.setAdapter(mNotesRecyclerAdapter);
    }

    public void logOut(View view){
            mAuth.getInstance().signOut();
            Toast.makeText(this, "Log-out successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,MainActivity.class));

    }

    public void viewNotes(View view){

    }
}
