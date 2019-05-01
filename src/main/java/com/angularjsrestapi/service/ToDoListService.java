package com.angularjsrestapi.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.angularjsrestapi.model.Note;
import com.angularjsrestapi.repository.ToDoListRepository;

@Service
public class ToDoListService {
	private ToDoListRepository toDoListRepository;

	@Autowired
	public ToDoListService(ToDoListRepository toDoListRepository) {
		this.toDoListRepository = toDoListRepository;
	}

	public Note save(Note note) {
		if (note.getId() != null && toDoListRepository.exists(note.getId())) {
			throw new EntityExistsException("There is already existing entity with such ID in the database.");
		}

		return toDoListRepository.save(note);
	}

	public Note update(Note note) {
		if (note.getId() != null && !toDoListRepository.exists(note.getId())) {
			throw new EntityNotFoundException("There is no entity with such ID in the database.");
		}

		return toDoListRepository.save(note);
	}

	public List<Note> findAll() {
		return toDoListRepository.findAll();
	}

	public List<Note> findByUserID(int userID) {
		return toDoListRepository.findByUserID(userID);
	}
	
	
	public Note findOne(Integer id) {
		return toDoListRepository.findOne(id);
	}

	public void delete(Integer id) {
		toDoListRepository.delete(id);
	}
}