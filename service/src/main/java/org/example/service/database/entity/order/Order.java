package org.example.service.database.entity.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long id;

	@Column(name = "book_id", nullable = false)
	private Integer book;

	@Column(name = "user_id", nullable = false)
	private Integer user;

	@Column(name = "status", nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@Column(name = "type", nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	private OrderType type;

	@Column(name = "ordered_date", nullable = false)
	private LocalDateTime orderedDate;

	@Column(name = "reserved_date")
	private LocalDateTime reservedDate;

	@Column(name = "returned_date")
	private LocalDateTime returnedDate;

	@Column(name = "rejected_date")
	private LocalDateTime rejectedDate;
}
