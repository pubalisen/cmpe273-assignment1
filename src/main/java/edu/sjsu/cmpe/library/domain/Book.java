package edu.sjsu.cmpe.library.domain;
import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Review;

import java.util.ArrayList;
import java.util.List;


public class Book {
    private long isbn;
    private String title;
    private String publicationDate;
	private String status = "Available";
	private  String numpages;
	private  String language;
	List<Author> author;
	List<Review> review = new ArrayList<Review>();

    // add more fields here

   
    public long getIsbn() {
	return isbn;
    }

    public void setIsbn(long isbn) {
	this.isbn = isbn;
    }

    /**
     * @return the title
     */
    public String getTitle() {
	return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
	this.title = title;
    }
    
    public String getDate() {
    	return publicationDate;
        }
    
    public void setDate(String publicationDate) {
    	this.publicationDate = publicationDate;
        }
    
    public String getStatus() {
    	return status;
        }
    
    public void setStatus(String status) {
    	this.status = status;
        }
    
    public String getNumPages() {
    	return numpages;
        }
    
    public void setNumPages(String numPages) {
    	this.numpages = numPages;
        }
    public List<Author> getAuthors(){
    	return author;
    }
    

	public void setAuthors(List<Author> author) {
		this.author = author;
	}

	public List<Review> getReviews() {
		return review;
	}

	public void setReviews(List<Review> reviews) {
		this.review = reviews;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

    
    }
