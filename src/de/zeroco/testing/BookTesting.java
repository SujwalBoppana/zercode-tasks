package de.zeroco.testing;

import java.util.HashMap;
import java.util.Map;

import de.zeroco.util.Book;

public class BookTesting {
	public static void main(String[] args) {
		Map<Integer, Book> map = new HashMap<Integer, Book>();
		map.put(1,
				new Book(1, "Introduction to Programming Using Java", "David J. Eck", "University Press of Florida"));
		map.put(2, new Book(2, "Introduction to Algorithms", "Ronald Rivest", "cs"));
		map.put(3, new Book(3, "Introduction to Algorithms", " Thomas H. Cormen, ", "Mg"));
		// System.out.println(map);
		for (Map.Entry<Integer, Book> book : map.entrySet()) {
			System.out.println(book.getKey() + "------" + book.getValue());
		}
		Map<Integer, Book> mapOne = new HashMap<>();
		mapOne.putAll(map);
		mapOne.put(4, new Book(4, "Introduction to  Java", "David J. Eck", "Press of Florida"));
		for (Map.Entry<Integer, Book> book : mapOne.entrySet()) {
			if (book.getKey().equals(1)) {
				book.setValue(new Book(1, "Introduction to  Java", "David J. Eck", "Press of Florida"));
			}
			System.out.println(book.getKey() + "------" + book.getValue());
		}
		mapOne.remove(1);
		System.out.println(mapOne);
	}

}
