package edu.sjsu.cmpe.library.domain;
import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Review;

import java.util.ArrayList;
import java.util.List;


public class Book {
    private long isbn;
    private String title;
    private String date;
	private String status = "Available";
	private  String NoPages;
	private  String language;
	List<Author> author;
	List<Review> review = new ArrayList<Review>();

    // add more fields here

    /**
     * @return the isbn
     */
    public long getIsbn() {
	return isbn;
    }

    /**
     * @param isbn
     *            the isbn to set
     */
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
    	return date;
        }
    
    public void setDate(String date) {
    	this.date = date;
        }
    
    public String getStatus() {
    	return status;
        }
    
    public void setStatus(String status) {
    	this.status = status;
        }
    
    public String getNoPages() {
    	return NoPages;
        }
    
    public void setNoPages(String NoPages) {
    	this.NoPages = NoPages;
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
