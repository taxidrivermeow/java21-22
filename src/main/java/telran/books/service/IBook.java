package telran.books.service;

import java.util.Set;

import telran.books.dto.AuthorDto;
import telran.books.dto.BookDto;
import telran.books.dto.PublisherDto;

public interface IBook
{
	boolean addBook(BookDto bookDto);
	boolean addAuthor(AuthorDto authorDto);
	boolean addPublisher(PublisherDto publisherDto);
	BookDto getBook(String isbn);
	AuthorDto getAuthor(String firstName, String lastName);
	PublisherDto getPublisher(String name);
	PublisherDto getBookPublisher(String isbn);
	
	Set<BookDto> getBooksByPublisher(String name);
	Iterable<BookDto> getBooksByAuthor(String firstName, String lastName);
	Iterable<String> getPublishersByAuthor(String firstName, String lastName);
	BookDto removeBook(String isbn);
	AuthorDto removeAuthor(String firstName, String lastName);
}
