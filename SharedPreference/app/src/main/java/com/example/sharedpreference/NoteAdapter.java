package com.example.sharedpreference;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharedpreference.model.Note;

import java.util.List;


public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder>{

    private List<Note> noteList;
    private OnNoteItemClick listener;

    public NoteAdapter(List<Note> noteList, OnNoteItemClick listener){
        this.noteList = noteList;
        this.listener = listener;
    }


    class NoteViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvContent;
        LinearLayout llNoteItem;

        public NoteViewHolder(View view){
            super(view);

            tvTitle = view.findViewById(R.id.tv_title);
            tvContent = view.findViewById(R.id.tv_content);
            llNoteItem = view.findViewById(R.id.ll_note_item);
        }

        public void bind(final Note note){
            tvTitle.setText(note.getTitle());
            tvContent.setText(note.getContent());

            llNoteItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(note);
                }
            });
        }
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(noteList.get(position));
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    interface OnNoteItemClick{
        void onClick(Note note);
    }
}
