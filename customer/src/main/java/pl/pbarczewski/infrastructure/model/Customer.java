package pl.pbarczewski.infrastructure.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;




@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customer", schema="customerDb")
public class Customer  {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;

	@NotNull
	@Column(name = "creditNumber")
	private String creditNumber;

	@NotNull
	@Column(name = "firstName")
	private String firstName;

	@NotNull
	@Column(name = "surname")
	private String surname;

	@NotNull
	@Column(unique = true)
	@Pattern(regexp="[0-9]{11}")
	private String pesel;

}
