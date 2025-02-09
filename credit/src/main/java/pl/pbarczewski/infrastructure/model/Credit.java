package pl.pbarczewski.infrastructure.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="Credit", schema="CreditDB")
public class Credit {
	
	@Id
	@Column(name = "credit_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "credit_number")
	private String creditNumber;

	@Column(name = "name")
	private String name;

	@Column(name = "customer_id")
	private Long customerId;

	@Column(name = "product_id")
	private Long productId;

}
