package edu.sjsu.cmpe.library.repository;
import java.util.List;


import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;
public interface ReviewRepositoryInterface {

	/**
	 * @param reviews
	 * @return
	 */
	List<Review> generateReviewIdKey(List<Review> reviews);

	/**
	 * @param tempBook
	 */
	//void saveReviews(Book tempBook);

	/**
	 * @param tempBook
	 * @return
	 */
	int saveReviews(Book tempBook);

	/**
	 * @param long1
	 * @param id
	 * @return
	 */
	Review getReviewByISBNandId(Long long1, int id);

	/**
	 * @param long1
	 * @param id
	 * @return
	 */
	//Review getReviewByISBNandId(Long long1, int id);

	/**
	 * @param review
	 * @return
	 */
	//int generateReviewIdKey(Review review);

	/**
	 * @param long1
	 * @return
	 */
	//Book getBookByISBN(Long long1);

}

