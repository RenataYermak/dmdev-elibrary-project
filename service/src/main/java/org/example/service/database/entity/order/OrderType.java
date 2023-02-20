package org.example.service.database.entity.order;

public enum OrderType {
	READING_ROOM,
	SEASON_TICKET;

	public String getName() {
		return name();
	}
}
