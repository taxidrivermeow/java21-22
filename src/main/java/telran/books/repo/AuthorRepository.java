package telran.books.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.books.entities.Author;
import telran.books.entities.FullName;

public interface AuthorRepository extends JpaRepository<Author, FullName>
{

}
