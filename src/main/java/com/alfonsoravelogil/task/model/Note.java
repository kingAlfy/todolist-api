package com.alfonsoravelogil.task.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// import java.sql.Date;


// TODO: Falta añadir la relación ManyToMany
@Entity
@Table(name = "notes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {


    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String title;
    String content;
    // Date created_at;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
