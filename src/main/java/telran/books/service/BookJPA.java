package telran.books.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import telran.books.dto.AuthorDto;
import telran.books.dto.BookDto;
import telran.books.dto.PublisherDto;
import telran.books.entities.Author;
import telran.books.entities.Book;
import telran.books.entities.FullName;
import telran.books.entities.Publisher;
import telran.books.repo.AuthorRepository;
import telran.books.repo.BookRepository;
import telran.books.repo.PublisherRepository;

@Service
@RequiredArgsConstructor
public class BookJPA implements IBook
{
	final BookRepository bookRepo;
	final AuthorRepository authorRepo;
	final PublisherRepository publisherRepo;

	@Override
	@Transactional
	public boolean addBook(BookDto bookDto)
	{
		if(bookRepo.existsById(bookDto.getIsbn()))
			throw new ResponseStatusException(HttpStatus.CONFLICT, //409
					"Book already exsists");
		
		if(!publisherRepo.existsById(bookDto.getPublisher()))
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, //404
					"No publisher with such name");
		
		Set<Author> set = bookDto.getAuthors().stream()
				.map(adto -> authorRepo.findById(new FullName(adto.getFirstName(), 
						adto.getLastName()))
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, //404
								"No autor with such name")))
				.collect(Collectors.toSet());
		
		Book book = Book.of(bookDto);
		book.setAuthors(set);
		bookRepo.save(book);
		return true;
	}

	@Override
	@Transactional
	public boolean addAuthor(AuthorDto authorDto)
	{
		if(authorRepo.existsById(new FullName(authorDto.getFirstName(), 
				authorDto.getLastName())))
			throw new ResponseStatusException(HttpStatus.CONFLICT, //409
					"Author already exsists");
		
		authorRepo.save(Author.of(authorDto));
		return true;
	}

	@Override
	@Transactional
	public boolean addPublisher(PublisherDto publisherDto)
	{
		if(publisherRepo.existsById(publisherDto.getName()))
			throw new ResponseStatusException(HttpStatus.CONFLICT, //409
					"Publisher already exsists");
		
		publisherRepo.save(Publisher.of(publisherDto));
		return true;
	}

	@Override
	public BookDto getBook(String isbn)
	{
		Book book = bookRepo.findById(isbn).orElseThrow(() -> 
		new ResponseStatusException(HttpStatus.NOT_FOUND, //404
				"No book with such isbn"));
		return book.build();
	}

	@Override
	public AuthorDto getAuthor(String firstName, String lastName)
	{
		Author author = authorRepo.findById(new FullName(firstName, lastName)).orElseThrow(() -> 
		new ResponseStatusException(HttpStatus.NOT_FOUND, //404
				"No author with such name"));
		return author.build();
	}

	@Override
	public PublisherDto getPublisher(String name)
	{
		Publisher publisher = publisherRepo.findById(name).orElseThrow(() -> 
		new ResponseStatusException(HttpStatus.NOT_FOUND, //404
				"No publisher with such name"));
		return publisher.build();
	}

	@Override
	public PublisherDto getBookPublisher(String isbn)
	{
		
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Set<BookDto> getBooksByPublisher(String name)
	{
		Publisher p = publisherRepo.findById(name)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, //404
					"No publisher with such name"));
		return p.getBooks().stream().map(Book::build).collect(Collectors.toSet());
	}

	@Override
	public Iterable<BookDto> getBooksByAuthor(String firstName, String lastName)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<String> getPublishersByAuthor(String firstName, String lastName)
	{
		return publisherRepo.findDistinctByBooksAuthorsName(new FullName(firstName, 
				lastName)).map(p -> p.getPublisherName())
				.collect(Collectors.toList());
	}

	@Override
	public BookDto removeBook(String isbn)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public AuthorDto removeAuthor(String firstName, String lastName)
	{
		Author autor = authorRepo.findById(new FullName(firstName, lastName))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, //404
						"No author with such name"));
		authorRepo.delete(autor);
		return autor.build();
	}

}
