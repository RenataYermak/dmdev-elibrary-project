package org.example.service.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	private String title;

	@Column(name = "author_id", nullable = false)
	private Integer author;

	@Column(name = "category_id", nullable = false)
	private Integer category;

	@Column(name = "publish_year")
	private Integer publishYear;

	@Column(length = 3000)
	private String description;

	@Column(nullable = false)
	private Integer number;

	@Column
	private String picture;
}
