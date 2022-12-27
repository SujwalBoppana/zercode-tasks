package de.zeroco.testing;

import de.zeroco.util.Ticket;

public class TicketTesting {
	public static void main(String[] args) {
		Ticket ticket = new Ticket();
		System.out.println(
				ticket.getTicket("Dear customer, Your ticket number is : {{HYDTS123456}}. Happy Journey", null,null));
	}

}
