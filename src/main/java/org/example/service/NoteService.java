package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Note;
import org.example.repository.NoteRepository;
import org.example.service.impl.NoteServiceInterface;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService implements NoteServiceInterface {
    private final NoteRepository noteRepository;


    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElse(null);
    }

    @Override
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public Note editNoteById(Long id, String title, String content) {
        Optional<Note> noteToChange = noteRepository.findById(id);
        if(noteToChange.isPresent()){
            Note newNote = noteToChange.get();
            newNote.setTitle(title);
            newNote.setContent(content);
            noteRepository.save(newNote);
            return newNote;
        }else {
            Note note = new Note();
            note.setId(id);
            note.setTitle(title);
            note.setContent(content);
            noteRepository.save(note);
            return note;
        }
    }

    @Override
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}
