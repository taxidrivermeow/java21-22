package telran.books.entities;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import telran.books.dto.AuthorDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "name")
@ToString(exclude = "books")
@Entity
@Table(name = "authors")
public class Author
{
	@EmbeddedId
	FullName name;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date")
	LocalDate birthDate;
	
	@ManyToMany(mappedBy = "authors", cascade = CascadeType.REMOVE)
	Set<Book> books;
	
	public static Author of(AuthorDto dto)
	{
		return Author.builder().name(new FullName(dto.getFirstName(), 
				dto.getLastName())).birthDate(dto.getBirthDate()).build();
	}
	
	public AuthorDto build()
	{
		return AuthorDto.builder().birthDate(birthDate).firstName(name.firstName)
				.lastName(name.lastName).build();
	}
}



















