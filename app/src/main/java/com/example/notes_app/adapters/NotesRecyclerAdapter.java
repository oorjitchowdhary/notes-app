package com.example.notes_app.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.notes_app.R;
import com.example.notes_app.models.NoteDetails;
import java.util.ArrayList;

public class NotesRecyclerAdapter extends RecyclerView.Adapter<NotesRecyclerAdapter.ViewHolder> {

    private ArrayList<NoteDetails> mNotes = new ArrayList<>();
    private OnNotesListener mOnNotesListener;

    public NotesRecyclerAdapter(ArrayList<NoteDetails> notes, OnNotesListener onNotesListener) {
        this.mNotes = notes;
        this.mOnNotesListener = onNotesListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view, mOnNotesListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.title.setText(mNotes.get(i).getTitle());
        viewHolder.content.setText(mNotes.get(i).getContent());
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, content;
        OnNotesListener onNotesListener;

        public ViewHolder(@NonNull View itemView, OnNotesListener onNotesListener) {
            super(itemView);
            title = itemView.findViewById(R.id.listtitle);
            content = itemView.findViewById(R.id.listcontent);
            this.onNotesListener = onNotesListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNotesListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNotesListener {
        void onNoteClick(int position);
    }
}
