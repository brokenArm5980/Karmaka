package Karmaka;

import java.util.*;

public class Jeu {
	Queue<Integer> pile = new LinkedList<>();
	
	public void initialize() {
		pile.add(0);
		pile.add(1);
		pile.add(2);
		pile.add(3);
		System.out.println(pile.element());
	}
}
