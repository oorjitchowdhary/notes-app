package com.example.notes_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.card.MaterialCardView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes_app.adapters.NotesRecyclerAdapter;
import com.example.notes_app.models.NoteDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Random;

import io.opencensus.tags.Tag;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

public class ViewNotesActivity extends AppCompatActivity implements NotesRecyclerAdapter.OnNotesListener {
    Button add_note;
    private FirebaseAuth mAuth;
    private RecyclerView mRecyclerView;
    private ArrayList<NoteDetails> mNotes = new ArrayList<>();
    private NotesRecyclerAdapter mNotesRecyclerAdapter;
    private MaterialCardView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);
        mAuth = FirebaseAuth.getInstance();

        mRecyclerView = findViewById(R.id.recyclerView);

        mRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNoteClick(1);
            }
        });
        initRecyclerView();

        add_note = (Button) findViewById(R.id.create_note);
        add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewNotesActivity.this, CreateNoteActivity.class);
                startActivity(intent);
            }
        });

        getNotes();
    }

    private void initRecyclerView() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mNotesRecyclerAdapter = new NotesRecyclerAdapter(mNotes, this);
        mRecyclerView.setAdapter(mNotesRecyclerAdapter);
    }

    public void logOut(View view){
            mAuth.getInstance().signOut();
            Toast.makeText(this, "Log-out successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,MainActivity.class));

    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(ViewNotesActivity.this, IndividualNoteActivity.class);
        intent.putExtra("selected_note", mNotes.get(position));
        startActivity(intent);
    }

    private void getNotes(){
        FirebaseUser user = mAuth.getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Notes")
                .whereEqualTo("UID", user.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                System.out.println(document.getId() + " => " + document.getData());
                                NoteDetails note = new NoteDetails();
                                note.setTitle(document.getString("title"));
                                note.setContent(document.getString("content"));
                                mNotes.add(note);
                            }
                            mNotesRecyclerAdapter.notifyDataSetChanged();
                        } else {
                            System.out.println("Error retrieving notes: " + task.getException().getMessage());
                        }
                    }
                });
    }
}
