package org.example.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.example.model.Note;
import org.example.service.impl.NoteServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteServiceInterface noteServiceInterface;

    @GetMapping
    public ResponseEntity<List<Note>> getNotes(){
        return new ResponseEntity<>(noteServiceInterface.getAllNotes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id){
        return new ResponseEntity<>(noteServiceInterface.getNoteById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note){
        return new ResponseEntity<>(noteServiceInterface.createNote(note), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> editNote(@PathVariable Long id, @RequestBody ObjectNode objectNode){
        String title = objectNode.get("title").asText();
        String content = objectNode.get("content").asText();
        return new ResponseEntity<>(noteServiceInterface.editNoteById(id, title, content), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Long id){
        noteServiceInterface.deleteNote(id);
    }
}
