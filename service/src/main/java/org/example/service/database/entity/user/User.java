package org.example.service.database.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id ")
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
}
