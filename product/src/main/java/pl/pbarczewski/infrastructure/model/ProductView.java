package pl.pbarczewski.infrastructure.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="product_view", schema="productDb")
public class ProductView {
	
	@Id
	@Column(name = "id")
	private Long id;
	@Column(name = "credit_number")
	private String creditNumber;
	@Column(name = "name")
	private String name;
	@Column(name = "value")
	private BigDecimal value;

}
