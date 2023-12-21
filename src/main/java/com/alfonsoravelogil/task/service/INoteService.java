package com.alfonsoravelogil.task.service;

import com.alfonsoravelogil.task.dto.NoteDTO;
import com.alfonsoravelogil.task.model.Note;

import java.util.List;

public interface INoteService {

    List<NoteDTO> getAllNotes();

    NoteDTO getNoteById(Long id);

    NoteDTO createNote(NoteDTO note);


    NoteDTO updateNoteUsingPut(Long id, NoteDTO note);

    NoteDTO updateNoteUsingPatch(Long id, NoteDTO note);

    void deleteNoteById(Long id);
}
