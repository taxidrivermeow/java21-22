package telran.books.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class BookDto
{
	String isbn;
	String title;
	Set<AuthorDto> authors;
	String publisher;
}
