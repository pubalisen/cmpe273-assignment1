package edu.sjsu.cmpe.library.repository;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Author;

public interface AuthorRepositoryInterface {

	/**
	 * @param request
	 * @return
	 */
	Book saveAuthors(Book request);

	/**
	 * @param long1
	 * @param id
	 * @return
	 */
	Author getAuthorByISBNandID(Long long1, int id);

	/**
	 * @param authors
	 * @return
	 */
	//List<Author> generateAuthorIdKey(List<Author> authors);



}
