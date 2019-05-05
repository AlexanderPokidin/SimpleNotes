package com.pokidin.a.simplenotes.repo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.pokidin.a.simplenotes.dao.NoteDao;
import com.pokidin.a.simplenotes.db.NoteRoomDatabase;
import com.pokidin.a.simplenotes.entity.Note;

import java.util.List;

public class NoteRepository {
    private NoteDao mNoteDao;
    private LiveData<List<Note>> mAllNotes;

    public NoteRepository(Application application) {
        NoteRoomDatabase database = NoteRoomDatabase.getInstance(application);
        mNoteDao = database.getNoteDao();
        mAllNotes = mNoteDao.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }

    public void insert(Note note) {
        new InsertAsyncTask(mNoteDao).execute(note);
    }

    private static class InsertAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao mAsyncNoteDao;

        public InsertAsyncTask(NoteDao dao) {
            mAsyncNoteDao = dao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mAsyncNoteDao.insert(notes[0]);
            return null;
        }
    }
}
