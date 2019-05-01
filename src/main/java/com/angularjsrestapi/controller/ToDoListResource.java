package com.angularjsrestapi.controller;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.angularjsrestapi.model.Note;
import com.angularjsrestapi.service.ToDoListService;

@RestController
@RequestMapping("/api")
public class ToDoListResource {

	private ToDoListService toDoListService;

	public ToDoListResource(ToDoListService toDoListService) {
		this.toDoListService = toDoListService;
	}

	@RequestMapping(value = "note", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Note> getAllNotes(@RequestParam(value = "userId") int userID) {
		return toDoListService.findByUserID(userID);
	}

	@RequestMapping(value = "note", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Note> updateNote(@RequestBody Note note) throws URISyntaxException {
		
		if (note.getId() == null) {
			return new ResponseEntity<Note>(HttpStatus.NOT_FOUND);
		}
		
		try {			
			Note result = toDoListService.update(note);
			return ResponseEntity.created(new URI("/api/note/" + result.getId())).body(result);
		} catch (EntityExistsException e) {
			return new ResponseEntity<Note>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "note", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Note> createNote(@RequestBody Note note) throws URISyntaxException {

		try {
			Note result = toDoListService.save(note);

			return ResponseEntity.created(new URI("/api/note/" + result.getId())).body(result);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<Note>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/note/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteNote(@PathVariable Integer id) {
		toDoListService.delete(id);

		return ResponseEntity.ok().build();
	}
}
