package com.example.sharedpreference.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sharedpreference.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteRepository extends DatabaseHelper {

    // NoteRepository Function :
    // CRUD Database business logic
    // contains of method that we need to perform some CRUD operations

    // don't forget to extends DatabaseHelper
    public NoteRepository(Context context){
        super(context);
    }

    public void insert(Note note){
        SQLiteDatabase db = getWritableDatabase();

        // Inserted value should be moved to this model (ContentValues)
        ContentValues value = new ContentValues();
        value.put("Title", note.getTitle());
        value.put("Content", note.getContent());

        // 3 Parameters : table_name, null, value
        db.insert("Note", null, value);
    }

    public void update(Note note){
        SQLiteDatabase db = getWritableDatabase();

        // Updated value should be moved to this model (ContentValues)
        ContentValues value = new ContentValues();
        value.put("Title", note.getTitle());
        value.put("Content", note.getContent());

        // 4 parameters : table_name, value that want to be updated, where condition, '?' from where condition if any
        db.update("Note",
                value,
                "IDNote=" + note.getId(),
                null);
    }

    public void delete(int id){
        SQLiteDatabase db = getWritableDatabase();

        // 3 parameters : table_name, where condition, '?' from where condition if any
        db.delete("Note",
                "IDNote=" + id,
                null);
    }

    public List<Note> getAll(){
        SQLiteDatabase db = getWritableDatabase();

        // noteList is used to hold the data that we get from database
        List<Note> noteList = new ArrayList<>();

        // we execute a raw query from string query. the return type is a 'Cursor'
        String query = "SELECT * FROM Note";
        Cursor cursor = db.rawQuery(query, null);

        // before you iterate the result, you should move the cursor to the first row of result
         cursor.moveToFirst();

         // loop while the cursor is not after the last data
         while(!cursor.isAfterLast()){
             Note note = new Note();

             // get the data, and put it to model
             note.setId(cursor.getInt(cursor.getColumnIndex("IDNote")));
             note.setTitle(cursor.getString(cursor.getColumnIndex("Title")));
             note.setContent(cursor.getString(cursor.getColumnIndex("Content")));

             // don't forget to add the model to noteList
             noteList.add(note);
             // after that, let the cursor move to the next row of the result
             cursor.moveToNext();
         }

         return noteList;
    }

}
