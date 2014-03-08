package edu.sjsu.cmpe.library.repository;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import edu.sjsu.cmpe.library.domain.Book;

import edu.sjsu.cmpe.library.domain.Author;

@SuppressWarnings("unused")
public class AuthorRepository implements AuthorRepositoryInterface {

	/**
	 *
	 */
	private int authorIdKey;   
    private Author tempAuthor = new Author();
    private final BookRepositoryInterface bookRepository;
    
	public AuthorRepository(BookRepositoryInterface bookRepository) {
		// TODO Auto-generated constructor stub
		this.bookRepository = bookRepository;
	}

	public List<Author> generateAuthorIdKey(List<Author> authors) {
		List<Author> tempAuthors = authors;
		for (int i=0; i<authors.size(); i++) {
			tempAuthor = authors.get(i);
			//id = generateAuthorIdKey();
			authorIdKey++;
			tempAuthor.setId(authorIdKey);
		}
		return tempAuthors;
	}

	public Book saveAuthors(Book newBook){
		List<Author> authors = newBook.getAuthors();
		newBook.setAuthors(generateAuthorIdKey(authors));
		return newBook;
	}

	public Author getAuthorByISBNandID(Long isbn, int id) {
		Book newBook = bookRepository.getBookByISBN(isbn);
		List<Author> authors = newBook.getAuthors();
		for (int i = 0; i<authors.size(); i++) {
			Author author = authors.get(i);
			if (author.getId() == id)
				return author;
		}
		return null;
	}
}

