package de.zeroco.util;

public class Ticket {
	public String getTicket(String data, String start, String end) {
		return data.substring(data.indexOf(start) + 2, data.indexOf(end));
	}
}
