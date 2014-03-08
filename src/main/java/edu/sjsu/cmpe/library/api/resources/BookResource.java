package edu.sjsu.cmpe.library.api.resources;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.validation.Valid;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Request;



//import org.eclipse.jetty.server.Request;


import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.ErrorMessage;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.repository.AuthorRepositoryInterface;
import edu.sjsu.cmpe.library.repository.BookRepositoryInterface;

@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    /** bookRepository instance */
    private final BookRepositoryInterface bookRepository;
    private final AuthorRepositoryInterface authorRepository;

    /**
    * BookResource constructor
    *
    * @param bookRepository
    *            a BookRepository instance
    */
    public BookResource(BookRepositoryInterface bookRepository, AuthorRepositoryInterface authorRepository) {
	this.bookRepository = bookRepository;
	this.authorRepository = authorRepository;
    }

    @GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
    public BookDto getBookByIsbn(@PathParam("isbn") LongParam isbn) {
	Book book = bookRepository.getBookByISBN(isbn.get());
	BookDto bookResponse = new BookDto(book);
	bookResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(),"GET"));
	bookResponse.addLink(new LinkDto("update-book",	"/books/" + book.getIsbn(), "PUT"));
	bookResponse.addLink(new LinkDto("delete-book", "/books/" + book.getIsbn(), "DELETE"));
	bookResponse.addLink(new LinkDto("create-review", "/books/" +book.getIsbn()+"/reviews/+", "POST"));
	bookResponse.addLink(new LinkDto ("view-all-reviews", "/books/"+book.getIsbn()+"/reviews/", "GET"));
	// add more links

	return bookResponse;
    }
    

    @POST
    @Timed(name = "create-book")
    public Response createBook(@Valid Book request) {
	// Store the new book in the BookRepository so that we can retrieve it.
	Book savedBook = bookRepository.saveBook(request);
	authorRepository.saveAuthors(request);

	String location = "/books/" + savedBook.getIsbn();
	//BookDto bookResponse = new BookDto(savedBook);
	LinksDto links = new LinksDto();
	links.addLink(new LinkDto("view-book", location, "GET"));
	links.addLink(new LinkDto("update-book", location, "PUT"));
	links.addLink(new LinkDto("delete-book", location, "DELETE"));
	links.addLink(new LinkDto("create-book", location, "POST"));

	// Add other links if needed

	return Response.status(201).entity(links).build();
    }
    
    @DELETE
    @Path("/{isbn}")
    @Timed(name = "delete-book")
    public Response deleteBook(@PathParam("isbn") LongParam isbn){
    	//Book book = new Book(); //bookRepository.getBookByISBN(isbn.get());
    	LinksDto links = new LinksDto();
    	//BookDto links = new BookDto(null);
    	if (bookRepository.deleteBookByISBN(isbn.get())){
    		System.out.println("Book Deleted");
        	links.addLink(new LinkDto("create-book", "/books/", "POST"));
        	return Response.status(200).entity(links).build();
    	}
    	else
    		return Response.status(400).build();
        	
    	}
    
    @PUT
    @Path("/{isbn}")
    @Timed(name = "update-book")
    public Response updateBookStatusByISBN(@Valid @PathParam("isbn") LongParam isbn, @QueryParam("status") String status){
    	//Book book = new Book();
    	if(bookRepository.updateBookStatusByISBN(isbn.get(), status)) {
     		LinksDto links = new LinksDto();
    		links.addLink(new LinkDto("view-book", "/books/"+isbn, "GET"));
    		links.addLink(new LinkDto("update-book", "/books/"+isbn, "PUT"));
    		links.addLink(new LinkDto("delete-book", "/books/"+isbn, "DELETE"));
    		links.addLink(new LinkDto("create-review", "/books/"+isbn+"reviews", "POST"));
   			links.addLink(new LinkDto ("view-all-reviews", "/books/"+isbn+"/reviews/", "GET"));
    		return Response.status(200).entity(links).build();    		
    	}
    	else
    		return Response.status(404).build();    	
    	
    } 
    
 /*   @SuppressWarnings("null")
	@GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
    public Response conditionalGetBook(@PathParam("isbn") LongParam isbn, @Context Request request){
    	Book book = bookRepository.getBookByISBN(isbn.get());
    	BookDto links = new BookDto(book);
    	    	EntityTag tag = new EntityTag(Integer.toString(book.hashCode()));
    	  ResponseBuilder Response = request.evaluatePreconditions(tag);
      	  CacheControl cc =new CacheControl();
    	  cc.setMaxAge(1200);
    	  if(Response != null) {
    		  Response.cacheControl(cc);
    		  links.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(),"GET"));
    		  links.addLink(new LinkDto("update-book",	"/books/" + book.getIsbn(), "PUT"));
    		  links.addLink(new LinkDto("delete-book", "/books/" + book.getIsbn(), "DELETE"));
    		  links.addLink(new LinkDto("create-review", "/books/" +book.getIsbn()+"/reviews/+", "POST"));
    		  links.addLink(new LinkDto ("view-all-reviews", "/books/"+book.getIsbn()+"/reviews/", "GET"));
    		  return Response.status(304).build();
    	  }
    	  else{
    		  Response = Response.status(200);
    		  Response.cacheControl(cc);
    		  links.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(),"GET"));
    		  links.addLink(new LinkDto("update-book",	"/books/" + book.getIsbn(), "PUT"));
    		  links.addLink(new LinkDto("delete-book", "/books/" + book.getIsbn(), "DELETE"));
    		  links.addLink(new LinkDto("create-review", "/books/" +book.getIsbn()+"/reviews/+", "POST"));
    		  links.addLink(new LinkDto ("view-all-reviews", "/books/"+book.getIsbn()+"/reviews/", "GET"));
  		 return  Response.build();
    		  
    	  }
    	  
    }
    */

    }