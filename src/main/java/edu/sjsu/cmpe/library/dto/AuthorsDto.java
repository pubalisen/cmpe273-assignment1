package edu.sjsu.cmpe.library.dto;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Author;

@JsonPropertyOrder(alphabetic = true)

public class AuthorsDto extends LinksDto {
	private List<Author> authors;
	/**
	 *
	 */
	public AuthorsDto(List<Author> authors) {
		// TODO Auto-generated constructor stub
		this.setAuthors(authors);
	}
	/**
	 * @return the authors
	 */
	public List<Author> getAuthors() {
		return authors;
	}
	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

}
