package hr.fer.oop.lab2.demo;

import hr.fer.oop.lab2.FootballPlayer;
import hr.fer.oop.lab2.SimpleFootballPlayerCollectionImpl;
import hr.fer.oop.lab2.welcomepack.PlayingPosition;
import hr.fer.oop.lab2.welcomepack.SimpleFootballPlayerCollection;

public class SimpleFootbalPlayerCollectionTest {

	public static void main(String[] args) {

		final int TEST_MAX_SIZE = 10;
		SimpleFootballPlayerCollection collection = new SimpleFootballPlayerCollectionImpl(TEST_MAX_SIZE);

		if (collection.size() != 0) {
			System.err.println("TEST FAILED: Kolekcija bi trebala biti prazna!");
		}

		for (int i = 0; i < 2 * TEST_MAX_SIZE; i++) {
			FootballPlayer sequentialPlayer = new FootballPlayer("NAME" + i, "", 1, 1, PlayingPosition.GK);
				collection.add(sequentialPlayer);
		}
		if(collection.size() > TEST_MAX_SIZE){
			System.err.println("TEST FAILED: Velicina je bitna, broj elemenata u kolekciji ne bi smio prelaziti "+ TEST_MAX_SIZE+ "!");
		}

		collection.clear();
		if(collection.size() != 0){
			System.err.println("TEST FAILED: Kolekcija bi trebala biti prazna !");
		}


		System.out.println("Ako nemate 'TEST FAILED' poruke onda je ovaj osnovni test prosao.\nSada napisite neke svoje testove kako biste isprobali druge funkcionalnosti.");

	}

}
