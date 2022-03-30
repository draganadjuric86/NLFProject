package com.project.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.project.model.Book;
import com.project.model.Student;
import com.project.service.BookService;

@Named
@ApplicationScoped
public class Bean {

	private List<Book> books;
	private Book selectedBook;
	private Student selectedStudent;
	private List<Book> unissuedBooks;
	private List<Student> studentList;
	private List<Book> studentBooks;

	@Inject
	private BookService bookService;

	@PostConstruct
	public void init() {
		bookService.populate();
		books = bookService.list();
		studentList = bookService.listStudents();
	}

	public String submit() {
		selectedBook.setStudent(selectedStudent);
		selectedBook.setIsIssued(1);
		bookService.merge(selectedBook);
		books = bookService.list();
		return "main.xhtml?faces-redirect=true";
	}

	
	public List<Book> getBooks() {
		return books;
	}

	public String goToBookLoan() {
		this.unissuedBooks = bookService.unissuedBooks();
		setSelectedBook(null);
		setSelectedStudent(null);
		return "loanBook.xhtml?faces-redirect=true";
	}

	public String goToBookReturn() {
		setSelectedBook(null);
		setSelectedStudent(null);
		return "returnBook.xhtml?faces-redirect=true";
	}
	
	public String returnBook() {
		selectedBook.setIsIssued(0);
		bookService.merge(selectedBook);
		books = bookService.list();
		return "main.xhtml?faces-redirect=true";
	}

	public void studentSOM_listener() {
		if (selectedStudent != null) {
			this.studentBooks = bookService.getBooksForStudent(selectedStudent);
		} else {
			this.studentBooks = null;
		}
	}

	public Book getSelectedBook() {
		return selectedBook;
	}

	public void setSelectedBook(Book selectedBook) {
		this.selectedBook = selectedBook;
	}

	public List<Book> getUnissuedBooks() {
		return unissuedBooks;
	}

	public void setUnissuedBooks(List<Book> unissuedBooks) {
		this.unissuedBooks = unissuedBooks;
	}

	public Student getSelectedStudent() {
		return selectedStudent;
	}

	public void setSelectedStudent(Student selectedStudent) {
		this.selectedStudent = selectedStudent;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	
	public List<Book> getStudentBooks() {
		return studentBooks;
	}

	public void setStudentBooks(List<Book> studentBooks) {
		this.studentBooks = studentBooks;
	}


}
