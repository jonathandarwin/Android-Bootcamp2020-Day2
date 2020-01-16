package com.example.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sharedpreference.model.Note;
import com.example.sharedpreference.repository.NoteRepository;

public class InsertUpdateActivity extends AppCompatActivity {

    EditText etTitle;
    EditText etContent;
    Button btnSave;
    Button btnDelete;

    private int id;
    private int type;
    private static final int INSERT = 1;
    private static final int UPDATE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_update);

        etTitle = findViewById(R.id.et_title);
        etContent = findViewById(R.id.et_content);
        btnSave = findViewById(R.id.btn_save);
        btnDelete = findViewById(R.id.btn_delete);

        // get the note data from intent
        final Note note = (Note) getIntent().getSerializableExtra("note");
        if(note != null){
            // Update
            // if the data is not null, then we know that the user wants to update/delete data
            etTitle.setText(note.getTitle());
            etContent.setText(note.getContent());
            type = UPDATE;
            // don't forget to save the id to do some update/delete operation
            // because we need the id to update/delete data
            id = note.getId();
        }
        else{
            // if the data is null, then the user wants to insert a new data
            type = INSERT;
            btnDelete.setVisibility(View.GONE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString().trim();
                String content = etContent.getText().toString().trim();

                if(title.isEmpty() || content.isEmpty()){
                    Toast.makeText(InsertUpdateActivity.this, "Please input title and content", Toast.LENGTH_SHORT).show();
                }
                else{
                    // Insert Update
                    NoteRepository noteRepository = new NoteRepository(InsertUpdateActivity.this);
                    Note note = new Note();
                    note.setTitle(title);
                    note.setContent(content);

                    if(type == INSERT){
                        noteRepository.insert(note);
                        Toast.makeText(InsertUpdateActivity.this, "Insert Success", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        // Update
                        // don't forget to set the id before we update data
                        note.setId(id);
                        noteRepository.update(note);
                        Toast.makeText(InsertUpdateActivity.this, "Update Success", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoteRepository noteRepository = new NoteRepository(InsertUpdateActivity.this);
                noteRepository.delete(id);
                Toast.makeText(InsertUpdateActivity.this, "Delete Success", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
