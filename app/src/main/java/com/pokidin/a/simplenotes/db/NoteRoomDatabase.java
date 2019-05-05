package com.pokidin.a.simplenotes.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.pokidin.a.simplenotes.dao.NoteDao;
import com.pokidin.a.simplenotes.entity.Note;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteRoomDatabase extends RoomDatabase {

    public abstract NoteDao getNoteDao();

    private static NoteRoomDatabase INSTANCE;

    public static NoteRoomDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            // Create database
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    NoteRoomDatabase.class, "note_database")
                    .build();
        }
        return INSTANCE;
    }
}
