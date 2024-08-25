package telran.books.entities;

import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import telran.books.dto.BookDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "isbn")
@ToString
@Entity
@Table(name = "books")
public class Book
{
	@Id
	String isbn;

	String title;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "books_authors", joinColumns = @JoinColumn(name = "isbn"), inverseJoinColumns = {
			@JoinColumn(name = "first_name"), @JoinColumn(name = "last_name") })
	Set<Author> authors;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "publisher_name")
	Publisher publisher;

	public static Book of(BookDto dto)
	{
		return Book.builder().isbn(dto.getIsbn()).title(dto.getTitle())
				.publisher(Publisher.builder()
						.publisherName(dto.getPublisher()).build()).build();
	}

	public BookDto build()
	{
		return BookDto.builder().isbn(isbn).title(title)
				.publisher(publisher.getPublisherName())
				.authors(authors.stream().map(Author::build)
						.collect(Collectors.toSet())).build();
	}
}
