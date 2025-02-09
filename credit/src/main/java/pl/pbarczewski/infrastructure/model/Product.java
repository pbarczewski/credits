package pl.pbarczewski.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Product", schema="ProductDB")
public class Product {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int creditId;

	@Column(name = "name")
	private String name;

	@Column(name = "value")
	private Integer value;
}
