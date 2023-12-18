package com.alfonsoravelogil.task.controller;


import com.alfonsoravelogil.task.dto.NoteDTO;
import com.alfonsoravelogil.task.exception.NoteNotFoundException;
import com.alfonsoravelogil.task.repository.NoteRepository;
import com.alfonsoravelogil.task.service.INoteService;
import com.alfonsoravelogil.task.service.NoteServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/notes")
@RestController
public class NoteController {

    INoteService noteService;
    public NoteController(INoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<NoteDTO> getAllNotesDTO(){
        return noteService.getAllNotes();
    }


    @GetMapping("/{id}")
    public NoteDTO getNoteById(@PathVariable Long id){
        NoteDTO noteReturned = noteService.getNoteById(id);

        if (noteReturned != null){
            return noteReturned;
        }

        throw new NoteNotFoundException("Note not exist", HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public NoteDTO createNote(@RequestBody NoteDTO noteDTO){
        return noteService.createNote(noteDTO);
    }

    @PutMapping("/{id}")
    public NoteDTO updateNote(@PathVariable Long id, @RequestBody NoteDTO noteDTO){
        NoteDTO noteReturned = noteService.getNoteById(id);

        if (noteReturned == null){
            throw new NoteNotFoundException("Note not exist", HttpStatus.NOT_FOUND);
        }


        return noteService.updateNote(id, noteDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNote(@PathVariable Long id){
        noteService.deleteNoteById(id);
        return new ResponseEntity<>("EXAMPLE", HttpStatus.OK);
    }

}
