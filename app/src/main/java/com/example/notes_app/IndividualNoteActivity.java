package com.example.notes_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.notes_app.models.NoteDetails;

public class IndividualNoteActivity extends AppCompatActivity {
    private TextView mViewTitle, mViewContent;
    private NoteDetails mInitialNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_note);
        mViewTitle = findViewById(R.id.title_tv);
        mViewContent = findViewById(R.id.content_tv);

        if(getIntent().hasExtra("selected_note")){
            mInitialNote = getIntent().getParcelableExtra("selected_note");
            setNoteProperties();
        }
    }

    private void setNoteProperties(){
        mViewTitle.setText(mInitialNote.getTitle());
        mViewContent.setText(mInitialNote.getContent());
    }

    public void goToAddNote(View view){
        startActivity(new Intent(IndividualNoteActivity.this, CreateNoteActivity.class));
    }
    public void goToViewNotes(View view){
        startActivity(new Intent(IndividualNoteActivity.this, ViewNotesActivity.class));
    }
}
