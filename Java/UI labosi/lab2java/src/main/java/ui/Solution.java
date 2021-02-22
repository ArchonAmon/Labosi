package main.java.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
	
	static ArrayList<String> klauzule = new ArrayList<>();
	static ArrayList<String> naredbe = new ArrayList<>();
	static ArrayList<String> nove = new ArrayList<>();
	static boolean verboseActive = false;

	public static void main(String ... args) throws IOException {
		int argCounter = 0;
		String zastavicaPodzadatka = null;
		for(String arg : args) {
			if (argCounter == 0) {
				zastavicaPodzadatka = arg;
				argCounter++;
				continue;
			}
			if (argCounter == 1) {
				File file = new File(arg); 			//ovdje staviti gdje su
				BufferedReader br = new BufferedReader(new FileReader(file)); 
				
				  String st; 
				  
				  while ((st = br.readLine()) != null) {
					if (st.startsWith("#")) continue;
					klauzule.add(st.toLowerCase());					//napraviti direktnu obradu dok se čita, ako bude potrebno (pretvarati -> i <=> i tako)
				  }
				argCounter++;
				continue;
			}
			if (argCounter > 1 && arg.equals("verbose")) {
				verboseActive = true;
				continue;
			}
			else if (argCounter > 1) {
				File file2 = new File(arg); 			//ovdje staviti gdje su
				BufferedReader br2 = new BufferedReader(new FileReader(file2));
				
				String st2;
				
				  while ((st2 = br2.readLine()) != null) {
					if (st2.startsWith("#")) continue;
					naredbe.add(st2.toLowerCase());
				  }
			}
		}
		
		if (zastavicaPodzadatka.equals("resolution")) {
		
		ArrayList<String> originalneKlauzule = new ArrayList<>();
		for (String s : klauzule) {
			originalneKlauzule.add(s);
		}
				
		String zadnji[] = klauzule.get(klauzule.size()-1).split(" v ");		//invertiranje zadnjeg
		for (int i = 0; i < zadnji.length; i++) {
			if (zadnji[i].startsWith("~")) zadnji[i].substring(1);
			else zadnji[i] = "~" + zadnji[i];
		}
		klauzule.remove(klauzule.size()-1);
		String noviZadnji = zadnji[0];
		for (int i = 1; i < zadnji.length; i++) {
			noviZadnji = noviZadnji + " v " + zadnji[i].trim();
		}
		klauzule.add(noviZadnji);
		
		
		boolean nasaoNIL = false;
		boolean promjena = true;
		while (promjena == true) {
			promjena = false;
			int trenutni;
			if (klauzule.size() == 0 || klauzule.size() == 1) break;
			for (int i = 0; i < klauzule.size() - 1; i++) {
				for (int j = i + 1; j < klauzule.size(); j++) {
					String[] splitaniPrvi = klauzule.get(i).split(" v ");
					String[] splitaniDrugi = klauzule.get(j).split(" v ");
					boolean promjena2 = false;
					for (int k = 0; k < klauzule.get(i).split(" v ").length; k++)	{
						for (int l = 0; l < klauzule.get(j).split(" v ").length; l++) {
							if (splitaniPrvi[k].trim().equals("~" + splitaniDrugi[l].trim()) || splitaniDrugi[l].trim().equals("~" + splitaniPrvi[k].trim())) {
								splitaniDrugi[l] = "0000000";
								splitaniPrvi[k] = "1111111";
								promjena = true;
								promjena2 = true;
							}
						}
					}
					if (promjena2 == false) {
						continue;
					}
					int counter1 = 0;
					if (splitaniPrvi.length == splitaniDrugi.length) {			//provjera je li NIL (ako su svi 000000 i 111111 onda je NIL)
					for (int c = 0; c < splitaniPrvi.length; c++) {
						if (splitaniPrvi[c].equals("1111111") && splitaniDrugi[c].equals("0000000")) counter1++;
					}
					if (counter1 == splitaniPrvi.length) {
						nasaoNIL = true;
						klauzule.add("NIL");
						nove.add("NIL" + " (" + (i + 1) + ", " + (j + 1) + ")");
						break;
					}
					}
					
					boolean promijenjeno1 = false;
					boolean promijenjeno2 = false;
					ArrayList<String> lista = new ArrayList<>();
					for (int c = 0; c < klauzule.get(i).split(" v ").length; c++) {				//bacanje clanova koji su vec koristeni
						if (!splitaniPrvi[c].equals("1111111")) {
							lista.add(klauzule.get(i).split(" v ")[c]);
							promijenjeno1 = true;
						}
					}
					for (int c = 0; c < klauzule.get(j).split(" v ").length; c++) {				//bacanje clanova koji su vec koristeni
						if (!splitaniDrugi[c].equals("0000000")) {
							lista.add(klauzule.get(j).split(" v ")[c]);
							promijenjeno2 = true;
						}
					}
					
					if (promijenjeno2 == true) {
						klauzule.remove(j);
						klauzule.add(j, "//////");
					}
					if (promijenjeno1 == true) {
						klauzule.remove(i);
						klauzule.add(i, "///");
					}

					
					String noviZapis = lista.isEmpty() ? "" : lista.get(0);
					if (!noviZapis.equals("") || lista.size() > 1)
					for (int c = 1; c < lista.size(); c++) {
						noviZapis = noviZapis + " v " + lista.get(c);
					}
					if (!noviZapis.equals("")) {
					nove.add(noviZapis.trim() + " (" + (i + 1) + ", " + (j + 1) + ")");
					klauzule.add(noviZapis);
					}
					promjena2 = false;
				}
				if (nasaoNIL == true) break;
			}
		if (nasaoNIL == true) break;
	}

		
		
		
		
		
		
		if (verboseActive == true && nasaoNIL == false) {				//napredni ispis
			int counter = 1;
			for (int i = 0; i < originalneKlauzule.size() - 1; i++) {
				System.out.println(counter + ". " + originalneKlauzule.get(i));
				counter++;
			}
			System.out.println("============");
			System.out.println(counter + ". " + noviZadnji);
			System.out.println("============");
			for (String s : nove) {
				counter++;
				System.out.println(counter + ". " + s);
			}
			System.out.println("============");
		}
		
		if (nasaoNIL == true) System.out.println(originalneKlauzule.get(originalneKlauzule.size() - 1) + " is true");
		else System.out.println(originalneKlauzule.get(originalneKlauzule.size() - 1) + " is unknown");
		
		}
		
		
		else if (zastavicaPodzadatka.equals("cooking_test") || zastavicaPodzadatka.equals("cooking_interactive")) {
			String s = "";
			boolean izasao = false;
			boolean prviProlaz = true;
			int naredbeCounter = 0;
			//klauzule.remove(klauzule.size() - 1);				//ovo maknuti ako nema zadnjeg reda u datoteci
			ArrayList<String> originalneKlauzule = new ArrayList<>();
			for (String ss : klauzule) {
				originalneKlauzule.add(ss);
			}
			while (!s.equals("exit") && naredbeCounter < naredbe.size()) {
				
				klauzule.clear();
				int velicina = prviProlaz ? originalneKlauzule.size() : originalneKlauzule.size() - 1;
				for (int i = 0; i < velicina; i++) {
					klauzule.add(originalneKlauzule.get(i));
				}
				prviProlaz = false;

				if(zastavicaPodzadatka.equals("cooking_interactive")) {
					
					while(true) {			//citanje iz konzole
					BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
					s = bufferRead.readLine();
					
					if (s.equals("exit")) {
						izasao = true;
						break;
					}
					else if (s.endsWith("+")) {
						char[] c = new char[1000];
						for (int j = 0; j < s.length() - 2; j++) c[j] = s.charAt(j);
						klauzule.add(new String(c).trim());					}
					else if (s.endsWith("-")) {
						char[] c = new char[1000];
						for (int j = 0; j < s.length() - 2; j++) c[j] = s.charAt(j);
						klauzule.remove(new String(c).trim());					}
					else if (s.endsWith("?")) {
						char[] c = new char[1000];
						for (int j = 0; j < s.length() - 2; j++) c[j] = s.charAt(j);
						klauzule.add(new String(c).trim());						break;
					}
					}
					
					if (izasao == true) break;
					
					nove.clear();
					originalneKlauzule.clear();
					for (int i = 0; i < klauzule.size(); i++) {
						originalneKlauzule.add(klauzule.get(i));
					}
					
					String zadnji[] = klauzule.get(klauzule.size()-1).split(" v ");		//invertiranje zadnjeg
					for (int i = 0; i < zadnji.length; i++) {
						if (zadnji[i].startsWith("~")) zadnji[i].substring(1);
						else zadnji[i] = "~" + zadnji[i];
					}
					klauzule.remove(klauzule.size()-1);
					String noviZadnji = zadnji[0];
					for (int i = 1; i < zadnji.length; i++) {
						noviZadnji = noviZadnji + " v " + zadnji[i].trim();
					}
					klauzule.add(noviZadnji);
					
					
					
					
					
					boolean nasaoNIL = false;
					boolean promjena = true;
					while (promjena == true) {
						promjena = false;
						int trenutni;
						if (klauzule.size() == 0 || klauzule.size() == 1) break;
						for (int i = 0; i < klauzule.size() - 1; i++) {
							for (int j = i + 1; j < klauzule.size(); j++) {
								String[] splitaniPrvi = klauzule.get(i).split(" v ");
								String[] splitaniDrugi = klauzule.get(j).split(" v ");
								boolean promjena2 = false;
								for (int k = 0; k < klauzule.get(i).split(" v ").length; k++)	{
									for (int l = 0; l < klauzule.get(j).split(" v ").length; l++) {
										if (splitaniPrvi[k].trim().equals("~" + splitaniDrugi[l].trim()) || splitaniDrugi[l].trim().equals("~" + splitaniPrvi[k].trim())) {
											splitaniDrugi[l] = "0000000";
											splitaniPrvi[k] = "1111111";
											promjena = true;
											promjena2 = true;
										}
									}
								}
								if (promjena2 == false) {
									continue;
								}
								int counter1 = 0;
								if (splitaniPrvi.length == splitaniDrugi.length) {			//provjera je li NIL (ako su svi 000000 i 111111 onda je NIL)
								for (int c = 0; c < splitaniPrvi.length; c++) {
									if (splitaniPrvi[c].equals("1111111") && splitaniDrugi[c].equals("0000000")) counter1++;
								}
								if (counter1 == splitaniPrvi.length) {
									nasaoNIL = true;
									klauzule.add("NIL");
									nove.add("NIL" + " (" + (i + 1) + ", " + (j + 1) + ")");
									break;
								}
								}
								
								boolean promijenjeno1 = false;
								boolean promijenjeno2 = false;
								ArrayList<String> lista = new ArrayList<>();
								for (int c = 0; c < klauzule.get(i).split(" v ").length; c++) {				//bacanje clanova koji su vec koristeni
									if (!splitaniPrvi[c].equals("1111111")) {
										lista.add(klauzule.get(i).split(" v ")[c]);
										promijenjeno1 = true;
									}
								}
								for (int c = 0; c < klauzule.get(j).split("v").length; c++) {				//bacanje clanova koji su vec koristeni
									if (!splitaniDrugi[c].equals("0000000")) {
										lista.add(klauzule.get(j).split(" v ")[c]);
										promijenjeno2 = true;
									}
								}
								
								if (promijenjeno2 == true) {
									klauzule.remove(j);
									klauzule.add(j, "//////");
								}
								if (promijenjeno1 == true) {
									klauzule.remove(i);
									klauzule.add(i, "///");
								}

								
								String noviZapis = lista.isEmpty() ? "" : lista.get(0);
								if (!noviZapis.equals("") || lista.size() > 1)
								for (int c = 1; c < lista.size(); c++) {
									noviZapis = noviZapis + "v" + lista.get(c);
								}
								if (!noviZapis.equals("")) {
								nove.add(noviZapis.trim() + " (" + (i + 1) + ", " + (j + 1) + ")");
								klauzule.add(noviZapis);
								}
								promjena2 = false;
							}
							if (nasaoNIL == true) break;
						}
					if (nasaoNIL == true) break;
				}
					
					if (verboseActive == true && nasaoNIL == true) {				//napredni ispis
						int counter = 1;
						for (int i = 0; i < originalneKlauzule.size() - 1; i++) {
							System.out.println(counter + ". " + originalneKlauzule.get(i));
							counter++;
						}
						System.out.println("============");
						System.out.println(counter + ". " + noviZadnji);
						System.out.println("============");
						for (String ss : nove) {
							counter++;
							System.out.println(counter + ". " + ss);
						}
						System.out.println("============");
					}
					
					if (nasaoNIL == true) System.out.println(originalneKlauzule.get(originalneKlauzule.size() - 1) + " is true");
					else System.out.println(originalneKlauzule.get(originalneKlauzule.size() - 1) + " is unknown");
					
				}
				
				else {
					
					
					for (int i = naredbeCounter; i < naredbe.size(); i++) {			//citanje iz datoteke	
						String sss = naredbe.get(i);
					if (sss.endsWith(" +")) {
						char[] c = new char[1000];
						for (int j = 0; j < sss.length() - 2; j++) c[j] = sss.charAt(j);
						klauzule.add(new String(c).trim());
					}
					else if (sss.endsWith(" -")) {
						char[] c = new char[1000];
						for (int j = 0; j < sss.length() - 2; j++) c[j] = sss.charAt(j);
						klauzule.remove(new String(c).trim());
						}
					else if (sss.endsWith(" ?")) {
						char[] c = new char[1000];
						for (int j = 0; j < sss.length() - 2; j++) c[j] = sss.charAt(j);
						klauzule.add(new String(c).trim());
						naredbeCounter++;
						break;
					}
					
					naredbeCounter++;
					
					}
					
					nove.clear();
					originalneKlauzule.clear();
					for (int i = 0; i < klauzule.size(); i++) {
						originalneKlauzule.add(klauzule.get(i));
					}
					
					String zadnji[] = klauzule.get(klauzule.size()-1).split("v");		//invertiranje zadnjeg
					for (int i = 0; i < zadnji.length; i++) {
						if (zadnji[i].startsWith("~")) zadnji[i].substring(1);
						else zadnji[i] = "~" + zadnji[i];
					}
					klauzule.remove(klauzule.size()-1);
					String noviZadnji = zadnji[0];
					for (int i = 1; i < zadnji.length; i++) {
						noviZadnji = noviZadnji + " v " + zadnji[i].trim();
					}
					klauzule.add(noviZadnji);
					
					
					
					
					
					boolean nasaoNIL = false;
					boolean promjena = true;
					while (promjena == true) {
						promjena = false;
						int trenutni;
						if (klauzule.size() == 0 || klauzule.size() == 1) break;
						for (int i = 0; i < klauzule.size() - 1; i++) {
							for (int j = i + 1; j < klauzule.size(); j++) {
								String[] splitaniPrvi = klauzule.get(i).split(" v ");
								String[] splitaniDrugi = klauzule.get(j).split(" v ");
								boolean promjena2 = false;
								for (int k = 0; k < klauzule.get(i).split(" v ").length; k++)	{
									for (int l = 0; l < klauzule.get(j).split(" v ").length; l++) {
										if (splitaniPrvi[k].trim().equals("~" + splitaniDrugi[l].trim()) || splitaniDrugi[l].trim().equals("~" + splitaniPrvi[k].trim())) {
											splitaniDrugi[l] = "0000000";
											splitaniPrvi[k] = "1111111";
											promjena = true;
											promjena2 = true;
										}
									}
								}
								if (promjena2 == false) {
									continue;
								}
								int counter1 = 0;
								if (splitaniPrvi.length == splitaniDrugi.length) {			//provjera je li NIL (ako su svi 000000 i 111111 onda je NIL)
								for (int c = 0; c < splitaniPrvi.length; c++) {
									if (splitaniPrvi[c].equals("1111111") && splitaniDrugi[c].equals("0000000")) counter1++;
								}
								if (counter1 == splitaniPrvi.length) {
									nasaoNIL = true;
									klauzule.add("NIL");
									nove.add("NIL" + " (" + (i + 1) + ", " + (j + 1) + ")");
									break;
								}
								}
								
								boolean promijenjeno1 = false;
								boolean promijenjeno2 = false;
								ArrayList<String> lista = new ArrayList<>();
								for (int c = 0; c < klauzule.get(i).split(" v ").length; c++) {				//bacanje clanova koji su vec koristeni
									if (!splitaniPrvi[c].equals("1111111")) {
										lista.add(klauzule.get(i).split("v")[c]);
										promijenjeno1 = true;
									}
								}
								for (int c = 0; c < klauzule.get(j).split(" v ").length; c++) {				//bacanje clanova koji su vec koristeni
									if (!splitaniDrugi[c].equals("0000000")) {
										lista.add(klauzule.get(j).split(" v ")[c]);
										promijenjeno2 = true;
									}
								}
								
								if (promijenjeno2 == true) {
									klauzule.remove(j);
									klauzule.add(j, "//////");
								}
								if (promijenjeno1 == true) {
									klauzule.remove(i);
									klauzule.add(i, "///");
								}

								
								String noviZapis = lista.isEmpty() ? "" : lista.get(0);
								if (!noviZapis.equals("") || lista.size() > 1)
								for (int c = 1; c < lista.size(); c++) {
									noviZapis = noviZapis + " v " + lista.get(c);
								}
								if (!noviZapis.equals("")) {
								nove.add(noviZapis.trim() + " (" + (i + 1) + ", " + (j + 1) + ")");
								klauzule.add(noviZapis);
								}
								promjena2 = false;
							}
							if (nasaoNIL == true) break;
						}
					if (nasaoNIL == true) break;
				}
					
					if (verboseActive == true && nasaoNIL == true) {				//napredni ispis
						int counter = 1;
						for (int i = 0; i < originalneKlauzule.size() - 1; i++) {
							System.out.println(counter + ". " + originalneKlauzule.get(i));
							counter++;
						}
						System.out.println("============");
						System.out.println(counter + ". " + noviZadnji);
						System.out.println("============");
						for (String ss : nove) {
							counter++;
							System.out.println(counter + ". " + ss);
						}
						System.out.println("============");
					}
					
					if (nasaoNIL == true) System.out.println(originalneKlauzule.get(originalneKlauzule.size() - 1) + " is true");
					else System.out.println(originalneKlauzule.get(originalneKlauzule.size() - 1) + " is unknown");
					
					
					
					
					
					
					
					
				}
				
				
				
				
				
				
				
				
				
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
		
		
		
		
		
	}
}
