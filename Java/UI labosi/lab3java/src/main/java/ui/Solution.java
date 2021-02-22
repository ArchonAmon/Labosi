package main.java.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class Solution {
	
	static ArrayList<String[]> learning = new ArrayList<>();
	static ArrayList<String[]> testing = new ArrayList<>();
	static ArrayList<String> header = new ArrayList<>();

	public static void main(String ... args) throws IOException {
		int argCounter = 0;
		String mode = "";
		String model = "";
		int max_depth = -1;
		int num_trees = -1;
		double feature_ratio = 0;
		double example_ratio = 0;
		ArrayList<String> rješenje = new ArrayList<>();
		
		for(String arg : args) {
			argCounter++;
			File file = new File(arg); 			//ovdje staviti gdje su
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			String st;
			int lineNumber = 0;
			int lineNumber2 = 0;
			
			if (argCounter == 1) {
				  while ((st = br.readLine()) != null) {
					if (lineNumber == 0) {
						lineNumber++;
						for (String c : st.split(",")) header.add(c);
					}
					else learning.add(st.split(","));
				  }
			}
			
			else if (argCounter == 2) {
				  while ((st = br.readLine()) != null) {
					if (lineNumber2 == 0) {
						lineNumber2++;
						continue;
					}
					testing.add(st.split(","));
				  }
			}
			
			else if (argCounter == 3) {				  
				  while ((st = br.readLine()) != null) {
					if (st.startsWith("#")) continue;
					else if (st.split("=")[0].equals("mode"))	mode = st.split("=")[1];
					else if (st.startsWith("model")) model = st.split("=")[1];
					else if (st.startsWith("max_depth")) max_depth = Integer.parseInt(st.split("=")[1]);
					else if (st.startsWith("num_trees")) num_trees = Integer.parseInt(st.split("=")[1]);
					else if (st.startsWith("feature_ratio")) feature_ratio = Double.parseDouble(st.split("=")[1]);
					else if (st.startsWith("example_ratio")) example_ratio = Double.parseDouble(st.split("=")[1]);
				  }
			}
		}
		
		
		//			  znacajka	   	vrijednost,zavrsetci
		LinkedHashMap<String, HashMap<String, String>> zapisi = new LinkedHashMap<>();	//mape se insertaju po red kojim su zadani u headeru
		for (int i = 0; i < header.size() - 1; i++)					//- 1 jer ne gledamo ime zadnjeg stupca
			for (int j = 0; j < learning.size(); j++) {
				for (int k = 0; k < learning.get(j).length - 1; k++) {
					if (i == k) {					//na istom smo stupcu kao i znacajka
							//pogledaj postoji li kljuc u zapisima
							if (!zapisi.containsKey(header.get(i))) {	//ako vrijedi, zapis ne postoji
								HashMap<String, String> mapa = new HashMap<>();
								mapa.put(learning.get(j)[k], learning.get(j)[learning.get(j).length - 1] + "," + 1);
								zapisi.put(header.get(i), mapa);
							}
							else { //zapis postoji
								HashMap<String, String> mapa = new HashMap<>();
								mapa = zapisi.get(header.get(i));
								if (!mapa.containsKey(learning.get(j)[k]))
								mapa.put(learning.get(j)[k], learning.get(j)[learning.get(j).length - 1] + "," + 1);
								else {
									String s = mapa.get(learning.get(j)[k]);
									if (!s.contains(learning.get(j)[learning.get(j).length - 1])) {
										s = s + ";" + learning.get(j)[learning.get(j).length - 1] + "," + 1;
										mapa.put(learning.get(j)[k], s);
									}
									else {
										String[] st = s.split(";");
										for (String str : st) {
											if (str.contains(learning.get(j)[learning.get(j).length - 1])) {
												String string = str.split(",")[1];
												int brojPojava = Integer.parseInt(string);
												brojPojava++;
												string = str.split(",")[0] + "," + brojPojava;
												String[] pom = st;
												String pom2 = "";
												for (int m = 0; m < pom.length; m++) {
													if (!pom[m].contains(learning.get(j)[learning.get(j).length - 1]) && m < pom.length - 1) {
														pom2 += pom[m] + ";";
													}
													else if (!pom[m].contains(learning.get(j)[learning.get(j).length - 1])) {
														pom2 += pom[m];
													}
													else if (pom[m].contains(learning.get(j)[learning.get(j).length - 1]) && m < pom.length - 1) {
														pom2 += string + ";";
													}
													else pom2 += string;
												}
												mapa.put(learning.get(j)[k], pom2);
												break;
											}
										}
									}
								}
								zapisi.put(header.get(i), mapa);
							}
						break;
					}
				}
			}
		
		if(!mode.equals("test")) System.out.println(zapisi);
		
		ArrayList<String> lista = new ArrayList<>();
		int indexTrenutnog = 0;
		LinkedHashMap<String, String> čvorovi = new LinkedHashMap<>();
		LinkedHashMap<String, String> izbrisaniČvorovi = new LinkedHashMap<>();
		TreeMap<Integer, String> dubina = new TreeMap<>();

		
		
		
		while (indexTrenutnog < zapisi.size()) {
			
			//računanje velike entropije
			double velEntropija = 1;
			double ukClanova = 0;
			HashMap<String, Integer> ent2 = new HashMap<>();
			for (String s : new ArrayList<HashMap<String, String>>(zapisi.values()).get(indexTrenutnog).values()) {
				for (String st: s.split(";"))
					if (!ent2.containsKey(st.split(",")[0])) ent2.put(st.split(",")[0], Integer.parseInt(st.split(",")[1]));
					else {
						int b = ent2.get(st.split(",")[0]);
						b = b + Integer.parseInt(st.split(",")[1]);
						ent2.put(st.split(",")[0], b);
					}
			}
				
				velEntropija = entropija(ent2);
			
				//broj članova
			for (String s : new ArrayList<HashMap<String, String>>(zapisi.values()).get(indexTrenutnog).values()) {
				for (String st: s.split(";"))
					ukClanova += Integer.parseInt(st.split(",")[1]);
			}
					    
				//računanje informacijske dobiti
			double IG = 0;
			HashMap<String, Integer> ent = new HashMap<>();
			for (String s : new ArrayList<HashMap<String, String>>(zapisi.values()).get(indexTrenutnog).values()) {		
				int clanovi = 0;
				for (String st : s.split(";")) {
					clanovi += Integer.parseInt(st.split(",")[1]);
					ent.put(st.split(",")[0], Integer.parseInt(st.split(",")[1]));
				}
				IG += -clanovi/ukClanova*entropija(ent);
				ent.clear();
			}
			IG = velEntropija + IG;
			if(!mode.equals("test")) System.out.println(zapisi.keySet().toArray()[indexTrenutnog] + " " + IG);			
			lista.add(zapisi.keySet().toArray()[indexTrenutnog] + " " + IG);
			
			
			
			
			
			
			
			String ključZaBrisanje = null;
			indexTrenutnog++;
			if (indexTrenutnog >= zapisi.size()) {				//prosli smo sve zapise
				indexTrenutnog = 0;
				
				double najveciIG = 0;
				String imeNajvecegIG = "";
				for (String s : lista)
					if (Double.parseDouble(s.split(" ")[1]) > najveciIG) {			//trazi najveci IG
						najveciIG = Double.parseDouble(s.split(" ")[1]);
						imeNajvecegIG = s.split(" ")[0];
					}

				lista.clear();
				ArrayList<String> zaBrisanje = new ArrayList<>();
				for (String s : zapisi.get(imeNajvecegIG).keySet()) {
					if (!zapisi.get(imeNajvecegIG).get(s).contains(";")) {
						rješenje.add(imeNajvecegIG + "," + s + "->" + zapisi.get(imeNajvecegIG).get(s).split(",")[0]);
						zaBrisanje.add(s);
					}
				}
				for (String s : zaBrisanje) zapisi.get(imeNajvecegIG).remove(s);		//izbrisi sve gdje je entropija = 0

				for (String s : zapisi.get(imeNajvecegIG).keySet()) {
					čvorovi.put(imeNajvecegIG + "," + s, zapisi.get(imeNajvecegIG).get(s));
				}
				
				
				//određivanje dubine
				/*for (String s : čvorovi.keySet()) {
					int dub = 0;
					for (String st : s.split(";")) {
						if (!dubina.containsKey(dub))
						dubina.put(dub, st.split(",")[0]);
						else {
							String sve = dubina.get(dub);
							dubina.put(dub, sve + "," + st.split(",")[0]);
						}
						dub++;
					}
				}*/
				
				
				
				
				int dub = 0;
				for (String st : imeNajvecegIG.split(";")) {
					if (!dubina.containsKey(dub))
					dubina.put(dub, st.split(",")[0]);
					else {
						String sve = dubina.get(dub);
						if (!sve.contains(st.split(",")[0]))
						dubina.put(dub, sve + "," + st.split(",")[0]);
					}
					dub++;
				}
				
				
				
				
				if (!čvorovi.isEmpty())
				ključZaBrisanje = čvorovi.keySet().toArray()[0].toString();
				
				//provjera dubine!!!!
				
				ArrayList<String> briši = new ArrayList<>();
				
				for (String s : čvorovi.keySet()) {
					long count = s.chars().filter(ch -> ch == ';').count();
					if (max_depth != -1 && count + 1 >= max_depth) {
						briši.add(s);
						izbrisaniČvorovi.put(s, čvorovi.get(s));
					}
				}
				
				for (String s : briši) čvorovi.remove(s);
				
				
				
				if (čvorovi.isEmpty()) break;			//zadatak gotov
				
				if(!mode.equals("test")) System.out.println(čvorovi);
				
				zapisi.clear();			//čišćenje zapisa
				
				for (String čvor : čvorovi.keySet()) {					//obrada samo prvog čvora
					for (int i = 0; i < header.size() - 1; i++) {
						boolean cont = false;
						for (String usporedba : čvor.split(";")) {				//ne provjeravati one koji su na razinama prije
							if (usporedba.split(",")[0].equals(header.get(i))) cont = true;
						}
						if (cont == true) continue;
							for (int j = 0; j < learning.size(); j++) {
								for (int k = 0; k < learning.get(j).length - 1; k++) {
									if (i == k) { 			//na istom smo stupcu
										String novoImeZapisa = čvor + ";";
										/*for (String s : čvor.split(";")) {
											novoImeZapisa += s.split(",")[0] + ";";
										}*/
										novoImeZapisa += header.get(i);
										
										
										boolean goNext = true;
											for (String st : čvor.split(";")) {
												//traženje stupca u kojem je prvi član
												int stupac = 0;
												for (String tmp : header) {
													if (tmp.equals(st.split(",")[0])) break;
													stupac++;
												}
												boolean foundIt = false;
												int counter = 0;
												for (String str : learning.get(j)) {				//matchanje prijašnjih podataka u stablu i sljedećeg čvora sa redom learning
													if (str.contains(st.split(",")[1]) && counter == stupac) {			//bread 'n' butter
														foundIt = true;
														break;
													}
													counter++;
												}
												if (foundIt == true) goNext = false;
											}
										if(goNext == true) continue;
										
										if (!zapisi.containsKey(novoImeZapisa)) {	//ako vrijedi, zapis ne postoji
											HashMap<String, String> mapa = new HashMap<>();
											mapa.put(learning.get(j)[k], learning.get(j)[learning.get(j).length - 1] + "," + 1);
											zapisi.put(novoImeZapisa, mapa);
										}
										else { //zapis postoji
											HashMap<String, String> mapa = new HashMap<>();
											mapa = zapisi.get(novoImeZapisa);
											if (!mapa.containsKey(learning.get(j)[k]))
											mapa.put(learning.get(j)[k], learning.get(j)[learning.get(j).length - 1] + "," + 1);
											else {
												String s = mapa.get(learning.get(j)[k]);
												if (!s.contains(learning.get(j)[learning.get(j).length - 1])) {
													s = s + ";" + learning.get(j)[learning.get(j).length - 1] + "," + 1;
													mapa.put(learning.get(j)[k], s);
												}
												else {
													String[] st = s.split(";");
													for (String str : st) {
														if (str.contains(learning.get(j)[learning.get(j).length - 1])) {
															String string = str.split(",")[1];
															int brojPojava = Integer.parseInt(string);
															brojPojava++;
															string = str.split(",")[0] + "," + brojPojava;
															String[] pom = st;
															String pom2 = "";
															for (int m = 0; m < pom.length; m++) {
																if (!pom[m].contains(learning.get(j)[learning.get(j).length - 1]) && m < pom.length - 1) {
																	pom2 += pom[m] + ";";
																}
																else if (!pom[m].contains(learning.get(j)[learning.get(j).length - 1])) {
																	pom2 += pom[m];
																}
																else if (pom[m].contains(learning.get(j)[learning.get(j).length - 1]) && m < pom.length - 1) {
																	pom2 += string + ";";
																}
																else pom2 += string;
															}
															mapa.put(learning.get(j)[k], pom2);
															break;
														}
													}
												}
											}
											zapisi.put(novoImeZapisa, mapa);
										}
										
									}
								}
							}
						}
					break;			//samo se prvi čvor traži
				}
					
								
				if(!mode.equals("test")) System.out.println(zapisi);
				
				čvorovi.remove(ključZaBrisanje);			//briše se prvi čvor jer je odrađen gore
				
				
				//break;
			}
		
			
			
			
		}
		
		if(!mode.equals("test")) System.out.println(rješenje);
		
		ArrayList<String> isp = new ArrayList<>();
		int cnt = 0;
		for (Integer p : dubina.keySet()) {
			int cnt2 = 0;
			for (String s : dubina.get(p).split(",")) {
				/*if (cnt + 1 < dubina.size() && cnt2 + 1 < dubina.get(p).split(",").length) {
					System.out.print(p + ":" + s + " ");
				}
				else System.out.print(p + ":" + s);
				cnt2++;*/
				isp.add(p + ":" + s);
			}
			cnt++;
		}
		
		System.out.println(String.join(", ", isp));
		
		/*LinkedHashMap<String, Long> pom = new LinkedHashMap<>();

		for (String s : rješenje) {					//ispis s dubinama (dubina je koliko ima ; u stringu)
			long count = s.chars().filter(ch -> ch == ';').count();
			String ključ = s.split(";")[s.split(";").length - 1].split(",")[0];
			if (!pom.containsKey(ključ)) pom.put(ključ, count);
		}
		
		String ispis = "";
		for (String s : pom.keySet()) {
			if (ispis.equals("")) ispis += pom.get(s) + ":" + s;
			else ispis += ", " + pom.get(s) + ":" + s;
		}
		
		System.out.println(ispis);*/
		
		
		ArrayList<String> ispisivanje = new ArrayList<>();
		
		if (max_depth == -1) {
		for (String[] s : testing) {				//redovi
			for (String str : rješenje) {
				int counter = 0;
				int counterEven = 0;
				str = String.join(",", str.split(";"));
				int indexČlana = 0;
				for (String string : str.split(",")) {
					counterEven++;							//gledaju se samo parni zapisi
					
					for (int i = 0; i < header.size(); i++) {
						if (counterEven % 2 == 1 && header.get(i).equals(string)) {
							indexČlana = i;
							break;
						}
					}
					
					if (counterEven % 2 == 0 && !string.contains("->") && s[indexČlana].equals(string))  {     //String.join(" ", s).contains(string)) {
						counter++;
					}
					else if (counterEven % 2 == 0 && string.contains("->") && s[indexČlana].equals(string.split("->")[0])) {   //String.join(" ", s).contains(string.split("->")[0])) {
						counter++;
					}
				}
				if (counter * 2 == str.split(",").length) {
					ispisivanje.add(str.split("->")[1]);
					break;
				}
			}
		}
		}
		
		
		
		else {
			for (String[] s : testing) {				//redovi
				boolean nemaPogađanja = false;
				for (String str : rješenje) {
					int counter = 0;
					int counterEven = 0;
					str = String.join(",", str.split(";"));
					int indexČlana = 0;
					for (String string : str.split(",")) {
						counterEven++;							//gledaju se samo parni zapisi
						
						for (int i = 0; i < header.size(); i++) {
							if (counterEven % 2 == 1 && header.get(i).equals(string)) {
								indexČlana = i;
								break;
							}
						}
						
						if (counterEven % 2 == 0 && !string.contains("->") && s[indexČlana].equals(string))  {     //String.join(" ", s).contains(string)) {
							counter++;
						}
						else if (counterEven % 2 == 0 && string.contains("->") && s[indexČlana].equals(string.split("->")[0])) {   //String.join(" ", s).contains(string.split("->")[0])) {
							counter++;
						}
					}
					if (counter * 2 == str.split(",").length) {
						ispisivanje.add(str.split("->")[1]);
						nemaPogađanja = true;
						break;
					}
				}
				if (nemaPogađanja == false) {
					TreeMap<String, Integer> mapa = new TreeMap<>();
					for (String str : izbrisaniČvorovi.keySet()) {
						
						int counter = 0;
						int counterEven = 0;
						str = String.join(",", str.split(";"));
						int indexČlana = 0;
						for (String string : str.split(",")) {
							counterEven++;							//gledaju se samo parni zapisi
							
							for (int i = 0; i < header.size(); i++) {
								if (counterEven % 2 == 1 && header.get(i).equals(string)) {
									indexČlana = i;
									break;
								}
							}
							
							if (counterEven % 2 == 0 && !string.contains("->") && s[indexČlana].equals(string))  {     //String.join(" ", s).contains(string)) {
								counter++;
							}
							else if (counterEven % 2 == 0 && string.contains("->") && s[indexČlana].equals(string.split("->")[0])) {   //String.join(" ", s).contains(string.split("->")[0])) {
								counter++;
							}
						}
						TreeSet<String> sett = new TreeSet<>();
						if (counter * 2 == str.split(",").length) {
							int najvećiBroj = -1;
							String imeNajvećegBroja = "";
							for (String string : izbrisaniČvorovi.get(str).split(";")) {
								if (Integer.parseInt(string.split(",")[1]) >= najvećiBroj) {
									najvećiBroj = Integer.parseInt(string.split(",")[1]);
									sett.add(string.split(",")[0]);
								}
							}
							
							ispisivanje.add((String) sett.toArray()[0]);
							break;
						}
						
					}
					
				}
				
			}
		}
		
		
		
		
		
		System.out.println(String.join(" ", ispisivanje));
				
		TreeMap<String, Integer> mapa = new TreeMap<>();
		TreeSet<String> set = new TreeSet<>();
		
		int točnih = 0;
		double ukupnih = testing.size();
		for (int i = 0; i < ispisivanje.size(); i++) {
			if (ispisivanje.get(i).equals(testing.get(i)[testing.get(i).length - 1])) točnih++;
			set.add(testing.get(i)[testing.get(i).length - 1]);
			
			if (!mapa.containsKey(ispisivanje.get(i) + "," + testing.get(i)[testing.get(i).length - 1])) {
				mapa.put(ispisivanje.get(i) + "," + testing.get(i)[testing.get(i).length - 1], 1);
			}
			else {
				int broj = mapa.get(ispisivanje.get(i) + "," + testing.get(i)[testing.get(i).length - 1]);
				broj++;
				mapa.put(ispisivanje.get(i) + "," + testing.get(i)[testing.get(i).length - 1], broj);
			}
		}
		System.out.println(String.format("%.5f", točnih/ukupnih));				//accuracy (možda zaokružiti na 5 decimala)

		
				
		TreeMap<String, Integer> temp = new TreeMap<>();
		
		for (String s : mapa.keySet()) {
			String[] tempStr = s.split(",");
			temp.put(tempStr[1] + "," + tempStr[0], mapa.get(s));
		}
		
		
		TreeSet<String> ispisi = new TreeSet<>();
		for (String[] s : learning) {
			ispisi.add(s[header.size() - 1]);	//sve moguće vrijednosti ispisa
		}
		
		int counter2 = 0;
		for (String s : ispisi) {
			int counter3 = 0;
			for (String st : ispisi) {
				if (temp.size() >= counter2 && ((String) temp.keySet().toArray()[counter2]).split(",")[0].equals(s) && ((String) temp.keySet().toArray()[counter2]).split(",")[1].equals(st)) {
					if (counter3 + 1 >= ispisi.size())
					System.out.print(temp.get(temp.keySet().toArray()[counter2]));
					else 
					System.out.print(temp.get(temp.keySet().toArray()[counter2]) + " ");
				}
				else {
					if (counter3 + 1 >= ispisi.size())
					System.out.print("0");
					else System.out.print("0 ");
				}
				
				counter2++;
				if (counter2 >= temp.size()) temp.put(counter2 + "", counter2);
				counter3++;
			}
			System.out.print("\n");
		}
		
		
		
	
	}
	
	
	
	
	public static double entropija(HashMap<String, Integer> zadnjiStupac) {
		double ukupnoVrijednosti = 0;
		for (String s : zadnjiStupac.keySet()) {
			ukupnoVrijednosti += zadnjiStupac.get(s);
		}
		
		double ukupnoEntropija = 0;
		for (String s : zadnjiStupac.keySet()) {
			ukupnoEntropija += (-zadnjiStupac.get(s)/ukupnoVrijednosti*Math.log(zadnjiStupac.get(s)/ukupnoVrijednosti)/Math.log(2));
		}
		
		return ukupnoEntropija;
	}

}
