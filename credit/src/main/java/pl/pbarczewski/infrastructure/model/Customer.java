package pl.pbarczewski.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="Customer", schema="CustomerDB")
public class Customer {

	@Id
	@Column(name = "creditId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long creditId;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "surname")
	private String surname;

	@Column(name = "pesel")
	private String pesel;

}
