package com.alfonsoravelogil.task.service;

import com.alfonsoravelogil.task.dto.NoteDTO;
import com.alfonsoravelogil.task.exception.NoteNotFoundException;
import com.alfonsoravelogil.task.model.Note;
import com.alfonsoravelogil.task.repository.NoteRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class NoteServiceImpl implements INoteService{

    @Autowired
    private NoteRepository noteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<NoteDTO> getAllNotes() {

        // Optional<List<Note>> notesOptional = Optional.ofNullable(noteRepository.findAll());
        // List<Note> notes = notesOptional.orElse(Collections.emptyList());
        // return noteRepository.findAll().stream().map(this::convertToNoteDTO).toList();
        return noteRepository.findAll().stream().map(this::convertToNoteDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public NoteDTO getNoteById(Long id) {
        NoteDTO note = noteRepository.findById(id).map(this::convertToNoteDTO).orElse(null);

        if (note == null){
            throw new NoteNotFoundException("Note not found", HttpStatus.NOT_FOUND);
        }

        return note;
    }

    @Override
    @Transactional
    public NoteDTO createNote(NoteDTO noteDTO) {
        Note note = convertToNote(noteDTO);
        Note savedNote = noteRepository.save(note);
        return convertToNoteDTO(savedNote);
    }

    @Override
    @Transactional
    public NoteDTO updateNote(Long id, NoteDTO note) {
        Note existingNote = noteRepository.findById(id).orElse(null);

        if  (existingNote == null){
            return null;
        }

        existingNote.setName(note.getName());
        existingNote.setDescription(note.getDescription());
        // existingNote.setCreated_at(note.getCreatedAt());

        return convertToNoteDTO(noteRepository.save(existingNote));
    }

    @Override
    @Transactional
    public void deleteNoteById(Long id) {
        Note note = noteRepository.findById(id).orElse(null);

        if (note == null){
            throw new NoteNotFoundException("Note not found", HttpStatus.NOT_FOUND);
        }

        noteRepository.deleteById(id);
    }



    private NoteDTO convertToNoteDTO(Note note){
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setId(note.getId());
        noteDTO.setName(note.getName());
        noteDTO.setDescription(note.getDescription());
        // noteDTO.setCreatedAt(note.getCreated_at());

        return noteDTO;
    }

    private Note convertToNote(NoteDTO noteDTO){
        Note note = new Note();
        note.setName(noteDTO.getName());
        note.setDescription(noteDTO.getDescription());
        // note.setCreated_at(noteDTO.getCreatedAt());
        return note;
    }


}
