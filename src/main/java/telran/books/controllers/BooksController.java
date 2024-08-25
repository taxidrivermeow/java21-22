package telran.books.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.books.dto.AuthorDto;
import telran.books.dto.BookDto;
import telran.books.dto.PublisherDto;
import telran.books.service.IBook;

@RestController
public class BooksController
{
	@Autowired
	IBook service;
	@PostMapping("/book/add")
	public boolean addBook(@RequestBody BookDto bookDto)
	{
		return service.addBook(bookDto);
	}
	@PostMapping("/author/add")
	public boolean addAuthor(@RequestBody AuthorDto authorDto)
	{
		return service.addAuthor(authorDto);
	}
	@PostMapping("/publisher/add")
	public boolean addPublisher(@RequestBody PublisherDto publisherDto)
	{
		return service.addPublisher(publisherDto);
	}
	@GetMapping("/book/{isbn}")
	public BookDto getBook(@PathVariable String isbn)
	{
		return service.getBook(isbn);
	}
	@GetMapping("/author/{firstName}/{lastName}")
	public AuthorDto getAuthor(@PathVariable String firstName, @PathVariable String lastName)
	{
		return service.getAuthor(firstName, lastName);
	}
	@GetMapping("/publisher/{name}")
	public PublisherDto getPublisher(@PathVariable String name)
	{
		return service.getPublisher(name);
	}
	@GetMapping("/publisher/book/{isbn}")
	public PublisherDto getBookPublisher(@PathVariable String isbn)
	{
		return service.getBookPublisher(isbn);
	}
	@GetMapping("/books/publisher/{name}")
	public Set<BookDto> getBooksByPublisher(@PathVariable String name)
	{
		return service.getBooksByPublisher(name);
	}
	@GetMapping("books/author/{firstName}/{lastName}")
	public Iterable<BookDto> getBooksByAuthor(@PathVariable String firstName, @PathVariable String lastName)
	{
		return service.getBooksByAuthor(firstName, lastName);
	}
	@GetMapping("/publishers/author/{firstName}/{lastName}")
	public Iterable<String> getPublishersByAuthor(@PathVariable String firstName, @PathVariable String lastName)
	{
		return service.getPublishersByAuthor(firstName, lastName);
	}
	@DeleteMapping("book/{isbn}")
	public BookDto removeBook(@PathVariable String isbn)
	{
		return service.removeBook(isbn);
	}
	@DeleteMapping("/author/{firstName}/{lastName}")
	public AuthorDto removeAuthor(@PathVariable String firstName, @PathVariable String lastName)
	{
		return service.removeAuthor(firstName, lastName);
	}
}
