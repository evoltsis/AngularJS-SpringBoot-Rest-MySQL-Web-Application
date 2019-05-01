package com.angularjsrestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.angularjsrestapi.model.Note;

public interface ToDoListRepository extends JpaRepository<Note, Integer> {
	Note findByTitle(String title);
	//List<Note> findByUserId(int userId);

	List<Note> findByUserID(int userID);
}
