package edu.sjsu.cmpe.library.domain;

import javax.validation.constraints.*;

import javax.validation.ValidationException;
import org.hibernate.validator.constraints.*;
public class Review {
	private int id;
	@NotNull
	private int rating;
	@Size(min = 1, max =5)
	private String comment;
	@NotEmpty(message = "Review comment cannot be empty")
	
	public long getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public long getRating(){
		return rating;
	}
	
	public void setRating(int rating){
		this.rating = rating;
	}
	
	public String getComment(){
		return comment;
	}
	
	public void setComment(String comment){
		this.comment = comment;
	}

}
