package telran.books.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.books.entities.Book;

public interface BookRepository extends JpaRepository<Book, String>
{

}
