package edu.sjsu.cmpe.library.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;


import edu.sjsu.cmpe.library.domain.Review;


public class ReviewDto extends LinksDto {
	private Review review;
	/**
	 *
	 */
	public ReviewDto(Review review) {
		// TODO Auto-generated constructor stub
		super();
		this.review = review;
	}


	public Review getReview() {
		return review;		
	}

	public void setReview(Review review) {
		this.review = review;
	}

}
