package edu.sjsu.cmpe.library.repository;


import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;


import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;

public class ReviewRepository implements ReviewRepositoryInterface {

	/**
	 *
	 */
	private final BookRepositoryInterface bookRepository;
	private static int reviewIdKey;   
    private Review tempReview = new Review();
    
	public ReviewRepository(BookRepositoryInterface bookRepository) {
		// TODO Auto-generated constructor stub
		this.bookRepository = bookRepository;
	}


	public List<Review> generateReviewIdKey(List<Review> reviews) {

		List<Review> tempReviews = reviews;
		for (int i=0; i<reviews.size(); i++) {
			tempReview = reviews.get(i);
			if (i == reviews.size()-1) {

				reviewIdKey++;
				tempReview.setId(reviewIdKey);
			}
		}

		return tempReviews;
	}



    public int saveReviews(Book newBook) {
    	List<Review> reviews = newBook.getReviews();
    	newBook.setReviews(generateReviewIdKey(reviews));
    

	return reviews.size();
    	
    }

	public Review getReviewByISBNandId(Long isbn, int id) {
		Book newBook = bookRepository.getBookByISBN(isbn);
		List<Review> reviews = newBook.getReviews();
		for (int i = 0; i< reviews.size(); i++) {
			Review review = reviews.get(i);
			if (review.getId() == id)
				return review;
		}
		return null;

	}




	/* (non-Javadoc)
	 * @see edu.sjsu.cmpe.library.repository.ReviewRepositoryInterface#getReviewByISBNandId(java.lang.Long, int)
	 */
	/*@Override
	public Review getReviewByISBNandId(Long long1, int id) {
		// TODO Auto-generated method stub
		return null;
	}*/

	/* (non-Javadoc)
	 * @see edu.sjsu.cmpe.library.repository.ReviewRepositoryInterface#generateReviewIdKey(edu.sjsu.cmpe.library.domain.Review)
	 */




}
