package telran.books.repo;

import java.util.Set;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.books.entities.FullName;
import telran.books.entities.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, String>
{

	Stream<Publisher> findDistinctByBooksAuthorsName(FullName fullName);

}
