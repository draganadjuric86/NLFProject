package com.project.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 * Entity implementation class for Entity: Student
 *
 */
@Entity
@NamedQuery(name = "Book.findByStudent", query = "SELECT b FROM Book b where b.student = :student")
@NamedQuery(name = "Book.findIssued", query = "SELECT b FROM Book b where b.isIssued = 0")
public class Book implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
   
	@Column
	private String title;
	
	@Column
	private String author;
	
	//1 issued
	@Column
	private int isIssued;
	
	@ManyToOne
	@JoinColumn(name = "id_student")
    private Student student;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}	
	

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}	
	

	public int getIsIssued() {
		return isIssued;
	}

	public void setIsIssued(int isIssued) {
		this.isIssued = isIssued;
	}

	@Override
	public String toString() {
		return title + " - " + author;
	}
	
	
}
