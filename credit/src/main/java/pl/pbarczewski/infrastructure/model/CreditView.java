package pl.pbarczewski.infrastructure.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="credit_view", schema="creditdb")
public class CreditView {
	
	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "credit_number")
	private String creditNumber;

	@Column(name = "credit_number")
	private String creditName;

	@Column(name = "name")
	private String name;

	@Column(name = "customer_name")
	private String customerName;

	@Column(name = "customer_surname")
	private String customerSurname;

	@Column(name = "pesel")
	private String pesel;

	@Column(name = "productName")
	private BigDecimal productValue;

}
