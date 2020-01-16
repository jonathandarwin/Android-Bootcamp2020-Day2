package com.example.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sharedpreference.model.Note;
import com.example.sharedpreference.model.User;
import com.example.sharedpreference.repository.NoteRepository;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private NoteAdapter adapter;
    private List<Note> noteList;
    private RecyclerView recyclerView;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recycler_view);
        btnAdd = findViewById(R.id.btn_add);

        setAdapter();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, InsertUpdateActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setAdapter(){
        noteList = new ArrayList<>();
        // second parameter is the callback function, and it is called when we clicked the list in adapter
        adapter = new NoteAdapter(noteList, new NoteAdapter.OnNoteItemClick() {
            @Override
            public void onClick(Note note) {
                Intent intent = new Intent(HomeActivity.this, InsertUpdateActivity.class);
                intent.putExtra("note", note);
                startActivity(intent);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void getData(){
        // this function will get the data from repository,
        // set it to noteList (but we should clear it first to refresh the data)
        // and don't forget to call adapter.notifyDataSetChanged() to update your recyclerview
        NoteRepository noteRepository = new NoteRepository(HomeActivity.this);
        noteList.clear();
        noteList.addAll(noteRepository.getAll());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }
}
