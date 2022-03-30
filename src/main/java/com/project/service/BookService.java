package com.project.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.project.model.Book;
import com.project.model.Student;

@Stateless
public class BookService {
	@PersistenceContext
    private EntityManager em;

	public void persistEntity(Object entity) {
        em.persist(entity);
    }
	
	public void merge(Object entity) {
		em.merge(entity);
	}

    public List<Book> list() {    	
        return em.createQuery("FROM Book b where b.isIssued = 1", Book.class)
            .getResultList();
    }
    
    public List<Book> unissuedBooks() {   
    	return em.createNamedQuery("Book.findIssued").getResultList();
    }
    
    public List<Student> listStudents() {    	
        return em.createQuery("FROM Student s", Student.class)
            .getResultList();
    }
    
    public void populate() {
    	Student s1 = new Student();
    	s1.setName("Jon Doe");
    	persistEntity(s1);
    	Student s2 = new Student();
    	s2.setName("Mike Smith");
    	persistEntity(s2);
    	Book newB = new Book();
    	newB.setAuthor("Agatha Cristhie");
    	newB.setTitle("The Mysterious Affair at Styles");
    	newB.setIsIssued(1);
		Book b1 = new Book();
    	b1.setAuthor("Agatha Cristhie");
    	b1.setTitle("Hickory Dickory Dock");
    	b1.setIsIssued(0);
    	persistEntity(b1);
    	Book b2 = new Book();
    	b2.setAuthor("Albert Camus");
    	b2.setTitle("Stranger");
    	b2.setIsIssued(0);
    	persistEntity(b2);
    	newB.setStudent(s2);
    	persistEntity(newB);
    }

	public List<Book> getBooksForStudent(Student selectedStudent) {
		try {
			return em.createNamedQuery("Book.findByStudent").setParameter("student", selectedStudent)
					.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}
    
   

}
