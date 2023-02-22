package org.example.service.database.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@ToString(exclude = "orders")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Book implements BaseEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	private String title;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Author author;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Category category;

	@Column(name = "publish_year")
	private Integer publishYear;

	@Column(length = 3000)
	private String description;

	@Column(nullable = false)
	private Integer number;

	@Column
	private String picture;

	@Builder.Default
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
	private List<Order> orders = new ArrayList<>();
}
