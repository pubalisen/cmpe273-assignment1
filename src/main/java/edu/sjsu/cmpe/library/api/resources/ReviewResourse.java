package edu.sjsu.cmpe.library.api.resources;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.dto.ErrorMessage;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.dto.ReviewDto;
import edu.sjsu.cmpe.library.dto.ReviewsDto;
import edu.sjsu.cmpe.library.repository.BookRepositoryInterface;
import edu.sjsu.cmpe.library.repository.ReviewRepositoryInterface;

@Path("/v1/books/{isbn}/reviews")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ReviewResourse {

    private final BookRepositoryInterface bookRepository;
    private final ReviewRepositoryInterface reviewRepository;

    ErrorMessage em = new ErrorMessage();
	/**
	 *
	 */
	public ReviewResourse(BookRepositoryInterface bookRepository, ReviewRepositoryInterface reviewRepository) {
		this.bookRepository = bookRepository;
		this.reviewRepository = reviewRepository;
		// TODO Auto-generated constructor stub
	}

	@POST
	//@Path("/{isbn}/reviews")
	@Timed(name = "create-review")
	public Response createReview(@Valid @PathParam("isbn") LongParam isbn, Review review) {
		Book tempBook = bookRepository.getBookByISBN(isbn.get());
		if(bookRepository.getBookByISBN(isbn.get()) != null){
			if((review.getRating() !=0) && (review.getRating() >=1) && (review.getRating() <=5) ) {
		List<Review> tempReviews = tempBook.getReviews();
		//for (int i = 0 ; i<tempReviews.size(); i++) {}
		tempReviews.add(review);
		//Review newReview = bookRepository.saveReview(isbn.get(), review);
		int id = reviewRepository.saveReviews(tempBook);
		LinksDto links = new LinksDto();
		links.addLink(new LinkDto("view-review","/books/"+isbn+"/reviews/"+id , "GET"));
		return Response.status(201).entity(links).build();
	}
			else
				
			            		em.setStatusCode(400);
			             		em.setMessage("Please enter review rating between (1-5)");
			             		return Response.ok(em).build();
			    			}
				else
			return Response.status(400).build();
	}

	@GET
	@Timed (name = "view-all-reviews")
	public Response getAllReviews(@PathParam("isbn") LongParam isbn) {
		List<Review> reviews = bookRepository.getBookByISBN(isbn.get()).getReviews();
		ReviewsDto reviewResponse = new ReviewsDto(reviews);
		//reviewResponse.addLink(link);
		return Response.ok().entity(reviewResponse).build();
	}

	@GET
	@Path("/{id}")
	@Timed (name = "view-review")
	public Response getOneReview(@PathParam("isbn") LongParam isbn, @PathParam("id") int id){
		//List<Review> reviews = bookRepository.getBookByISBN(isbn.get()).getReviews();
		Review review = reviewRepository.getReviewByISBNandId(isbn.get(), id);
		ReviewDto reviewResponse = new ReviewDto(review);
		reviewResponse.addLink(new LinkDto("view-review", "/books/"+isbn+"/reviews/"
		+id, "GET"));
		return Response.ok().entity(reviewResponse).build();
	}


}
