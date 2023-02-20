package org.example.service.database.entity.order;

public enum OrderStatus {
	ORDERED,
	RESERVED,
	RETURNED,
	REJECTED;

	public String getName() {
		return name();
	}
}
