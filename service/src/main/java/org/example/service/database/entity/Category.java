package org.example.service.database.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@EqualsAndHashCode(of = "name")
@ToString(exclude = "books")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class Category implements BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true, nullable = false, length = 50)
	private String name;

	@Builder.Default
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Book> books = new ArrayList<>();
}
