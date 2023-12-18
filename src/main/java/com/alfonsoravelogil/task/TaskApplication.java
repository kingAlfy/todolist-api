package com.alfonsoravelogil.task;

import com.alfonsoravelogil.task.model.Note;
import com.alfonsoravelogil.task.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class TaskApplication {
	private static Logger logger = LoggerFactory.getLogger(TaskApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);
	}

	@Bean
	CommandLineRunner init(NoteRepository noteRepository) {
		return args -> {

			logger.info("Start bbdd data");

			ArrayList<Note> notes = new ArrayList<>();
			notes.add(new Note("Example Name Note 1", "Note 1 created"));
			notes.add(new Note("Recordar ir a comprar", "Comprar carne, pescado..."));
			notes.add(new Note("Example Name Note 2", "Note 2 created"));

			notes.forEach(noteRepository::save);

			logger.warn("End bbdd data");

			logger.info("Number of notes: {}", noteRepository.count());
		};

	}
}
