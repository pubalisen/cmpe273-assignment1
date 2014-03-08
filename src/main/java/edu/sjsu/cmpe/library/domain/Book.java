package edu.sjsu.cmpe.library.domain;
import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Review;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {
private long isbn;
private String title;
private String publicationDate;
private String Language;
private int numPages;
private String status = "available";
List<Author> authors;
List<Review> reviews = new ArrayList<Review>();

public void setIsbn(long isbn){
	this.isbn = isbn;
}

public long getIsbn(){
	return isbn;
}

public void setTitle(String title){
	this.title = title;
}

public String getTitle(){
	return title;
}

@JsonProperty("publication-date")
public String getPublicationDate() {
return publicationDate;
}

public void setPublicationDate(String publicationDate) {
this.publicationDate = publicationDate;
}

public String getLanguage() {
return Language;
}

public void setLanguage(String language) {
Language = language;
}


@JsonProperty("num-pages")
public int getNumPages() {
return numPages;
}

public void setNumPages(int numPages) {
this.numPages = numPages;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}



public List<Author> getAuthors() {
return authors;
}

public void setAuthors(List<Author> authors) {
this.authors = authors;
}

public List<Review> getReviews() {
return reviews;
}

public void setReviews(List<Review> reviews) {
this.reviews = reviews;
}

}










