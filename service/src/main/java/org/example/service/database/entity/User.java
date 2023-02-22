package org.example.service.database.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@EqualsAndHashCode(of = "login")
@ToString(exclude = "orders")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User implements BaseEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 25)
	private String login;

	@Column(nullable = false, length = 25)
	private String firstname;

	@Column(nullable = false, length = 25)
	private String lastname;

	@Column(nullable = false, unique = true, length = 50)
	private String email;

	@Column(nullable = false, length = 49)
	private String password;

	@Column(nullable = false, length = 10)
	@Enumerated(EnumType.STRING)
	private Role role;

	@Builder.Default
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Order> orders = new ArrayList<>();
}
