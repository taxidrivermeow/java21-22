package telran.books;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import telran.books.entities.Author;
import telran.books.entities.FullName;
import telran.books.entities.Publisher;
import telran.books.entities.Book;
import telran.books.repo.AuthorRepository;
import telran.books.repo.BookRepository;
import telran.books.repo.PublisherRepository;
import telran.books.service.IBook;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext contex = 
				SpringApplication.run(Application.class, args);
		
		PublisherRepository pr = contex.getBean(PublisherRepository.class);
		AuthorRepository ar = contex.getBean(AuthorRepository.class);
		BookRepository br = contex.getBean(BookRepository.class);
		IBook service = contex.getBean(IBook.class);
		
		Publisher p = new Publisher("p", null);
		Author a = new Author(new FullName("fn", "ln"), LocalDate.now(), null);
		pr.save(p);
		ar.save(a);
		br.save(new Book("isbn", "title", new HashSet<Author>(List.of(a)), p));
		System.out.println(service.getBooksByPublisher("p"));
	}

}
