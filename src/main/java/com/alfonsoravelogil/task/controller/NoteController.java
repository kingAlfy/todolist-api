package com.alfonsoravelogil.task.controller;


import com.alfonsoravelogil.task.config.markers.UpdateNotesValidationGroup;
import com.alfonsoravelogil.task.dto.NoteDTO;
import com.alfonsoravelogil.task.exception.NoteNotFoundException;
import com.alfonsoravelogil.task.repository.NoteRepository;
import com.alfonsoravelogil.task.service.INoteService;
import com.alfonsoravelogil.task.service.NoteServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
        return noteService.getNoteById(id);
    }

    @PostMapping
    public ResponseEntity<NoteDTO> createNote(@Valid @RequestBody NoteDTO noteDTOClient){
        return ResponseEntity.status(HttpStatus.CREATED).body(noteService.createNote(noteDTOClient));
    }

    @PutMapping("/{id}")
    public NoteDTO putNote(@PathVariable Long id, @Valid @RequestBody NoteDTO noteDTOClient){
        return noteService.updateNoteUsingPut(id, noteDTOClient);
    }

    @PatchMapping("/{id}")
    public NoteDTO updateNote(@PathVariable Long id, @Validated({UpdateNotesValidationGroup.class}) @RequestBody NoteDTO noteDTOClient){
        return noteService.updateNoteUsingPatch(id, noteDTOClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NoteDTO> deleteNote(@PathVariable Long id){
        noteService.deleteNoteById(id);

        return ResponseEntity.noContent().build();
    }

}
