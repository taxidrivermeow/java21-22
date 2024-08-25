package telran.books.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import telran.books.dto.PublisherDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "publisherName")
@ToString(exclude = "books")
@Entity
@Table(name = "publishers")
public class Publisher
{
	@Id
	@Column(name = "publisher_name")
	String publisherName;
	
	@OneToMany(mappedBy = "publisher")
	Set<Book> books;
	
	public static Publisher of(PublisherDto dto)
	{
		return Publisher.builder().publisherName(dto.getName()).build();
	}
	
	public PublisherDto build()
	{
		return PublisherDto.builder().name(publisherName).build();
	}
}
