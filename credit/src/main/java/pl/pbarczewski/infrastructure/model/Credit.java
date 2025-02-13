package pl.pbarczewski.infrastructure.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="credit", schema="creditdb")
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
