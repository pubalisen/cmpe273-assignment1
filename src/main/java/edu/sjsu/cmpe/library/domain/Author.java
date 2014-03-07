package edu.sjsu.cmpe.library.domain;

import java.util.List;

public class Author {
private long id;
private String name;
private List<Book> book;

public long getId(){
	return id;
}

public void setId(int authorIdKey){
	this.id = authorIdKey;
}

public String getName(){
	return name;
}

public void setName(String name){
	this.name = name;
}

}
