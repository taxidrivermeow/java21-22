package telran.books.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Embeddable
@SuppressWarnings("serial")
public class FullName implements Serializable
{
	@Column(name = "first_name")
	String firstName;
	
	@Column(name = "last_name")
	String lastName;
}
