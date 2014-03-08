package edu.sjsu.cmpe.library.api.resources;


import java.util.List;

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

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.dto.AuthorDto;
import edu.sjsu.cmpe.library.dto.AuthorsDto;
import edu.sjsu.cmpe.library.repository.BookRepositoryInterface;
import edu.sjsu.cmpe.library.repository.AuthorRepositoryInterface;

@Path("/v1/books/{isbn}/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {

	private final BookRepositoryInterface bookRepository;
	private final AuthorRepositoryInterface authorRepository;

	/**
	 *
	 */
	public AuthorResource(BookRepositoryInterface bookRepository, AuthorRepositoryInterface authorRepository) {
		// TODO Auto-generated constructor stub
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
	}

	@GET
	@Timed (name = "view-all-authors")
	public Response getAllAuthors(@PathParam("isbn") LongParam isbn) {
		List<Author> authors = bookRepository.getBookByISBN(isbn.get()).getAuthors();
		AuthorsDto authorResponse = new AuthorsDto(authors);
		return Response.ok().entity(authorResponse).build();
	}

	@GET
	@Path("/{id}")
	@Timed (name = "view-authors")


	public Response getOneAuthor(@PathParam("isbn") LongParam isbn, @PathParam("id") int id) {
		Author author = authorRepository.getAuthorByISBNandID(isbn.get(),id);
		AuthorDto authorResponse = new AuthorDto(author);
		authorResponse.addLink(new LinkDto("view-author", "/books/"+isbn+"/authors/"+id, "GET"));
		return Response.ok().entity(authorResponse).build();
	}
}

