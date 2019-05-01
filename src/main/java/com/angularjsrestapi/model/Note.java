package com.angularjsrestapi.model;


import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="Notes")
@EntityListeners(AuditingEntityListener.class)
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer noteID;

	private String title;

	private String text;

	private Integer userID;

	public Note() {
	}


	public Integer getId() {
		return noteID;
	}


	public void setId(Integer id) {
		this.noteID = id;
	}

	public Integer getUserId() {
		return userID;
	}


	public void setUserId(Integer id) {
		this.userID = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}

	
}