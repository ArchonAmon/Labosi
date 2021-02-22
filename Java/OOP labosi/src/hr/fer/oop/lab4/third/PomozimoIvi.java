package hr.fer.oop.lab4.third;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class PomozimoIvi {
	
	public static void main (String[] args) {
		
		int ocjena;
		String osoba;
		ArrayList<Integer> temp;
		Scanner sc = new Scanner (System.in);
		LinkedHashMap<String,ArrayList<Integer>> map = new LinkedHashMap<>();
		do {
			osoba = sc.next();
			if (osoba.equals("kraj")) break;
			ocjena = sc.nextInt();
			
			temp = new ArrayList<Integer>();
			temp.addAll(map.getOrDefault(osoba, temp));
			temp.add(ocjena);
			map.put(osoba, temp);
		} while(true);
		
		
		for (Entry<String, ArrayList<Integer>> s : map.entrySet()) {
			System.out.printf("Ucenik %s\n", s.getKey());
			broj(s);
			ocjene(s);
			razliciteOcjene(s);
			prosjek(s);
			stddev(s);
			System.out.printf("\n");
		}
		
		System.out.println(map);
		sc.close();
	}

	private static void broj(Entry<String, ArrayList<Integer>> s) {
		System.out.println("Broj ocjena:" + s.getValue().size());
	}

	private static void prosjek(Entry<String, ArrayList<Integer>> s) {
		double prosjek = 0;
		for (Integer l : s.getValue()) {
			prosjek += l;
		}
		prosjek=prosjek/s.getValue().size();
		System.out.println("Prosjecna ocjena:" + prosjek);
	}

	private static void razliciteOcjene(Entry<String, ArrayList<Integer>> s) {
		int[] polje = new int[5];
		System.out.print("Razlicite ocjene:");
		for (Integer l : s.getValue()) {
			if (l == 1 && polje[0] != 1) {
				polje[0] = 1;
				System.out.print(" 1");
			}
			if (l == 2 && polje[1] != 2) {
				polje[1] = 2;
				System.out.print(" 2");
			}
			if (l == 3 && polje[2] != 3) {
				polje[2] = 3;
				System.out.print(" 3");
			}
			if (l == 4 && polje[3] != 4) {
				polje[3] = 4;
				System.out.print(" 4");
			}
			if (l == 5 && polje[4] != 5) {
				polje[4] = 5;
				System.out.print(" 5");
			}
		}	
		System.out.printf("\n");
	}

	private static void ocjene(Entry<String, ArrayList<Integer>> s) {
		System.out.printf("Ocjene su:");
		for (Integer l : s.getValue()) {
			System.out.printf(" %d", l);
		}
		System.out.printf("\n");
	}

	private static void stddev(Entry<String, ArrayList<Integer>> s) {
		double prosjek = 0;
		double dev = 0;
		double suma = 0;
		for (Integer l : s.getValue()) {
			prosjek += l;
		}
		prosjek=prosjek/s.getValue().size();
		for (Integer l : s.getValue()) {
			suma += Math.pow(prosjek, 2);
		}
		dev = Math.pow(suma/(s.getValue().size()-1), 0.5);
		System.out.printf("Standardna devijacija je: %f", dev);
	}

}
