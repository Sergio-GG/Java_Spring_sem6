package org.example.service.impl;

import org.example.model.Note;

import java.util.List;

public interface NoteServiceInterface {
    public List<Note> getAllNotes();
    public Note getNoteById(Long id);
    public Note createNote(Note note);
    public Note editNoteById(Long id, String title, String content);
    public void deleteNote(Long id);
}
