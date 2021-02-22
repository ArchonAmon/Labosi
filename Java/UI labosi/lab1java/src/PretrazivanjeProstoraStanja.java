import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PretrazivanjeProstoraStanja {
	
	static String polaziste;
	static ArrayList<String> odrediste = new ArrayList<>();
	static ArrayList<String> pretrazeni = new ArrayList<>();
	static ArrayList<String> skokovi = new ArrayList<>();
	static ArrayList<String> rješenje = new ArrayList<>();
	static HashMap<String, Double> heuristic = new HashMap<>();
	static HashMap<String, ArrayList<String>> imena = new HashMap<>();
	static int statesVisited = 0;
	static double statesPrice = 0;
	static ArrayList<String> otvori = new ArrayList<>();
	static boolean trazi = true;
	static String parent;
	static int redniBr = -1;
	static int trazeniBroj;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		  File file = new File("C:\\Users\\Robert Pavliš\\Desktop\\UI labos\\istra.txt"); 			//ovdje staviti gdje su
		  File file2 = new File("C:\\Users\\Robert Pavliš\\Desktop\\UI labos\\istra_pessimistic_heuristic.txt"); //datoteke za zadatak

		  
		  BufferedReader br = new BufferedReader(new FileReader(file)); 
		  BufferedReader br2 = new BufferedReader(new FileReader(file2)); 

		  
		  String st; 
		  int linija = 0;
		  
		  while ((st = br.readLine()) != null) {
			if (st.startsWith("#")) continue;
			if (linija == 0) {
				polaziste = st;
				linija++;
				continue;
			}
			if (linija == 1) {
				for (int i = 0; i < st.split(" ").length; i++)
				odrediste.add(st.split(" ")[i]);
				linija++;
				continue;
			}
			
			skokovi = new ArrayList<>();
			for (int i = 0; i < st.split(" ").length; i++) {
				if (i != 0) skokovi.add(st.split(" ")[i]);
			}
			imena.put(st.split(":")[0], skokovi);
		  }
		  
		  while ((st = br2.readLine()) != null) {
			if (st.startsWith("#")) continue;
			heuristic.put(st.split(": ")[0], Double.parseDouble(st.split(": ")[1]));
		  }
		  
		  
		  //pretrazivanje u sirinu
		  System.out.println("Pretrazivanje u sirinu:");
		  otvori.add(polaziste + ",0");
		  ArrayList<String> prijelazi = new ArrayList<>();
		  
		  while(!otvori.isEmpty()) {
			  
			  String tren = otvori.get(0);
			  otvori.remove(0);				//izbacivanje prvog iz liste
			  if (pretrazeni.contains(tren.split(",")[0])) continue;			//ako smo već bili, preskačemo, ovdje staviti uvjet za cijenu ako treba
			  pretrazeni.add(tren.split(",")[0]);
			  redniBr++;
			  if (odrediste.contains(tren.split(",")[0])) {		//gleda se je li se doslo do nekog od odredisnih stanja
				  rješenje.add(tren.split(",")[0]);
				  statesPrice += Double.parseDouble(tren.split(",")[1]);
				  for (int i = redniBr-1; i > -1; i--) {
					  if (prijelazi.get(i).contains(tren.split(",")[0])) {
						  rješenje.add(prijelazi.get(i).split(",")[0]);
						  statesPrice += Double.parseDouble(prijelazi.get(i).split(",")[1]);
						  tren = prijelazi.get(i).split(",")[0] + "," + prijelazi.get(i).split(",")[1];
					  }
				  }
				  break;  
			  }
			  String str = tren;    //da se može pratiti koji je čvor bio prethodni
			  for (int i = 0; i < imena.get(tren.split(",")[0]).size(); i++) {
				  String s = imena.get(tren.split(",")[0]).get(i);
				  otvori.add(s); 		
				  str += "," + s;
			  }
			  prijelazi.add(str);
			  
		  }
		  
		  System.out.println("States visited = " + pretrazeni.size());  
		  System.out.println("Found parth of length " + rješenje.size() + " " + "with total cost " + statesPrice + ";");
		  
		  
		  for (int i = rješenje.size()-1; i > -1; i--)
			  if (i != 0) 
				  System.out.println(rješenje.get(i) + " =>");
			  else
				  System.out.println(rješenje.get(i));
		  
		  
		  System.out.println("\nPretrazivanje s jednolikom cijenom:");			//sve isto kao prije samo se sortira po cijeni
		  																		//pa se onda stavlja u otvori listu
		  otvori.clear();
		  otvori.add(polaziste + ",0");
		  prijelazi.clear();
		  pretrazeni.clear();
		  redniBr = -1;
		  statesPrice = 0;
		  rješenje.clear();
		  
		  while(!otvori.isEmpty()) {
			  double najmanji = 9999999;
			  int index;
			  int aux_index = 0;
			  boolean imaManji = false;
			  for (index = 0; index < otvori.size(); index++) {   //gleda se tko ima najmanju cijenu i onda se njega otvori
				  if (otvori.size() == 1) index = 0;
				  else if (index == 0) najmanji = Double.parseDouble(otvori.get(index).split(",")[1]);
				  if (Double.parseDouble(otvori.get(index).split(",")[1]) < najmanji) {
					  //otvori član na tom indeksu
					  najmanji = Double.parseDouble(otvori.get(index).split(",")[1]);
					  imaManji = true;
					  aux_index = index;
				  }
			  }
			  if (imaManji == false) aux_index = 0;
			  String tren = otvori.get(aux_index);				
			  otvori.remove(aux_index);
			  if (pretrazeni.contains(tren.split(",")[0])) continue;			//ako smo već bili, preskačemo, ovdje staviti uvjet za cijenu (možda)
			  pretrazeni.add(tren.split(",")[0]);
			  redniBr++;
			  if (odrediste.contains(tren.split(",")[0])) {		//gleda se je li se doslo do nekog od odredisnih stanja
				  rješenje.add(tren.split(",")[0]);
				  statesPrice = Double.parseDouble(tren.split(",")[1]);
				  while(!tren.split(",")[0].equals(polaziste)) {
					  najmanji = 999999;
					  int ii = 0;
					  for (int i = 0; i < prijelazi.size(); i++) {     //gledati je li to prijelaz s najmanjom vrijednosti (dio algoritma opisan u prezentaciji)
						  for (int j = 1; j < prijelazi.get(i).split(",").length; j=j+2) {
						  if (prijelazi.get(i).contains(tren.split(",")[0]) && !prijelazi.get(i).startsWith(tren.split(",")[0])) {
						  if (prijelazi.size() == 1) break;
						  if (prijelazi.get(i).split(",")[j-1].equals(tren.split(",")[0]) && Double.parseDouble(prijelazi.get(i).split(",")[j]) < najmanji) {
							  najmanji = Double.parseDouble(prijelazi.get(i).split(",")[j]);
							  ii = i;
						  }
						  }
					    }
					  }
					  rješenje.add(prijelazi.get(ii).split(",")[0]);
					  tren = prijelazi.get(ii).split(",")[0] + "," + prijelazi.get(ii).split(",")[1];
					  prijelazi.remove(ii);
				  
		  }
				  break;  
			  }
			  String str = tren;    //da se može pratiti koji je čvor bio prethodni
			  for (int i = 0; i < imena.get(tren.split(",")[0]).size(); i++) {			//treba zbrajati cijene za sljedeći čvor
				  String s = imena.get(tren.split(",")[0]).get(i);
				  double novaCijena = Double.parseDouble(tren.split(",")[1]) + Double.parseDouble(s.split(",")[1]);
				  s = s.split(",")[0] + "," + novaCijena;				//povećanje cijene za sljedeći čvor
				  otvori.add(s);
				  str += "," + s;
			  }
			  prijelazi.add(str);
			  
			  
		  }
		  
		  
		  System.out.println("States visited = " + pretrazeni.size());  
		  System.out.println("Found parth of length " + rješenje.size() + " " + "with total cost " + statesPrice + ";");
		  
		  
		  for (int i = rješenje.size()-1; i > -1; i--)
			  if (i != 0) 
				  System.out.println(rješenje.get(i) + " =>");
			  else
				  System.out.println(rješenje.get(i));
		  
		  
		  
		  
		  
		  System.out.println("\nPretrazivanje pomocu A*: ");
		  
		  
		  otvori.clear();
		  otvori.add(polaziste + ",0");
		  prijelazi.clear();
		  pretrazeni.clear();
		  redniBr = -1;
		  statesPrice = 0;
		  rješenje.clear();
		  
		  while(!otvori.isEmpty()) {
			  double najmanji = 9999999;
			  int index;
			  int aux_index = 0;
			  boolean imaManji = false;
			  for (index = 0; index < otvori.size(); index++) {   //gleda se tko ima najmanju cijenu i onda se njega otvori
				  if (otvori.size() == 1) index = 0;
				  else if (index == 0) najmanji = Double.parseDouble(otvori.get(index).split(",")[1]) + heuristic.get(otvori.get(index).split(",")[0]);
				  if (Double.parseDouble(otvori.get(index).split(",")[1]) + heuristic.get(otvori.get(index).split(",")[0]) < najmanji) {
					  //otvori član na tom indeksu
					  najmanji = Double.parseDouble(otvori.get(index).split(",")[1]) + heuristic.get(otvori.get(index).split(",")[0]);
					  imaManji = true;
					  aux_index = index;
				  }
			  }
			  if (imaManji == false) aux_index = 0;
			  String tren = otvori.get(aux_index);				
			  otvori.remove(aux_index);
			  if (pretrazeni.contains(tren.split(",")[0])) continue;			//ako smo već bili, preskačemo, ovdje staviti uvjet za cijenu (možda)
			  pretrazeni.add(tren.split(",")[0]);
			  redniBr++;
			  if (odrediste.contains(tren.split(",")[0])) {		//gleda se je li se doslo do nekog od odredisnih stanja
				  rješenje.add(tren.split(",")[0]);
				  statesPrice = Double.parseDouble(tren.split(",")[1]);
				  while(!tren.split(",")[0].equals(polaziste)) {
					  najmanji = 999999;
					  int ii = 0;
					  for (int i = 0; i < prijelazi.size(); i++) {     //gledati je li to prijelaz s najmanjom vrijednosti (dio algoritma opisan u prezentaciji)
						  for (int j = 1; j < prijelazi.get(i).split(",").length; j=j+2) {
						  if (prijelazi.get(i).contains(tren.split(",")[0]) && !prijelazi.get(i).startsWith(tren.split(",")[0])) {
						  if (prijelazi.size() == 1) break;
						  if (prijelazi.get(i).split(",")[j-1].equals(tren.split(",")[0]) && Double.parseDouble(prijelazi.get(i).split(",")[j]) < najmanji) {
							  najmanji = Double.parseDouble(prijelazi.get(i).split(",")[j]);
							  ii = i;
						  }
						  }
					    }
					  }
					  rješenje.add(prijelazi.get(ii).split(",")[0]);
					  tren = prijelazi.get(ii).split(",")[0] + "," + prijelazi.get(ii).split(",")[1];
					  prijelazi.remove(ii);
				  
		  }
				  break;  
			  }
			  String str = tren;    //da se može pratiti koji je čvor bio prethodni
			  for (int i = 0; i < imena.get(tren.split(",")[0]).size(); i++) {			//treba zbrajati cijene za sljedeći čvor
				  String s = imena.get(tren.split(",")[0]).get(i);
				  double novaCijena = Double.parseDouble(tren.split(",")[1]) + Double.parseDouble(s.split(",")[1]);
				  s = s.split(",")[0] + "," + novaCijena; //+ "," + heuristic.get(tren.split(",")[0]);				//povećanje cijene za sljedeći čvor
				  otvori.add(s);
				  str += "," + s;
			  }
			  prijelazi.add(str);
			  
			  
		  }
		  		  
		  System.out.println("States visited = " + pretrazeni.size());  
		  System.out.println("Found parth of length " + rješenje.size() + " " + "with total cost " + statesPrice + ";");
		  
		  
		  for (int i = rješenje.size()-1; i > -1; i--)
			  if (i != 0) 
				  System.out.println(rješenje.get(i) + " =>");
			  else
				  System.out.println(rješenje.get(i));
		  
		  
		  
		  
		  System.out.println("\nChecking if heuristic is consistent:");	 	//prvo gledati konzistentnost jer ako je konzistentna onda je i optimistična
		  
		  boolean jeKonzistentan = true;
		  
		  for (String key : imena.keySet()) {
			  for (int i = 0; i < imena.get(key).size(); i++) {
				  if (heuristic.get(key) > heuristic.get(imena.get(key).get(i).split(",")[0]) + Double.parseDouble(imena.get(key).get(i).split(",")[1])) {
					  jeKonzistentan = false;
					  System.out.println("[ERR] h(" + key + ") > h(" + imena.get(key).get(i).split(",")[0] + ") + c: " + heuristic.get(key) + " > " + heuristic.get(imena.get(key).get(i).split(",")[0]) + " + " + Double.parseDouble(imena.get(key).get(i).split(",")[1]));
				  }
			  }
		  }
		  
		  if (jeKonzistentan == false) System.out.println("Heuristic is not consistant.");
		  else System.out.println("Heuristic is consistant.");
		  
		  
		  System.out.println("Checking if heuristic is optimistic: ");
		  boolean jeOptimistican = true;
		  if (jeKonzistentan == true) System.out.println("Heuristic is consistant -> heuristic is optimistic, no checking needed");
		  else {
			  
			  for (String key : imena.keySet()) {				//koristimo algoritam pretraživanja s jednolikom cijenom
				  polaziste = key;
				  otvori.clear();
				  otvori.add(polaziste + ",0");
				  prijelazi.clear();
				  pretrazeni.clear();
				  redniBr = -1;
				  statesPrice = 0;
				  rješenje.clear();
				  
				  
				  while(!otvori.isEmpty()) {
					  double najmanji = 9999999;
					  int index;
					  int aux_index = 0;
					  boolean imaManji = false;
					  for (index = 0; index < otvori.size(); index++) {   //gleda se tko ima najmanju cijenu i onda se njega otvori
						  if (otvori.size() == 1) index = 0;
						  else if (index == 0) najmanji = Double.parseDouble(otvori.get(index).split(",")[1]);
						  if (Double.parseDouble(otvori.get(index).split(",")[1]) < najmanji) {
							  //otvori član na tom indeksu
							  imaManji = true;
							  aux_index = index;
						  }
					  }
					  if (imaManji == false) aux_index = 0;
					  String tren = otvori.get(aux_index);				
					  otvori.remove(aux_index);
					  if (pretrazeni.contains(tren.split(",")[0])) continue;			//ako smo već bili, preskačemo, ovdje staviti uvjet za cijenu (možda)
					  pretrazeni.add(tren.split(",")[0]);
					  redniBr++;
					  if (odrediste.contains(tren.split(",")[0])) {		//gleda se je li se doslo do nekog od odredisnih stanja
						  rješenje.add(tren.split(",")[0]);
						  statesPrice = Double.parseDouble(tren.split(",")[1]);
						  while(!tren.split(",")[0].equals(polaziste)) {
							  najmanji = 999999;
							  int ii = 0;
							  for (int i = 0; i < prijelazi.size(); i++) {     //gledati je li to prijelaz s najmanjom vrijednosti (dio algoritma opisan u prezentaciji)
								  for (int j = 1; j < prijelazi.get(i).split(",").length; j=j+2) {
								  if (prijelazi.get(i).contains(tren.split(",")[0]) && !prijelazi.get(i).startsWith(tren.split(",")[0])) {
								  if (prijelazi.size() == 1) break;
								  if (prijelazi.get(i).split(",")[j-1].equals(tren.split(",")[0]) && Double.parseDouble(prijelazi.get(i).split(",")[j]) < najmanji) {
									  najmanji = Double.parseDouble(prijelazi.get(i).split(",")[j]);
									  ii = i;
								  }
								  }
							    }
							  }
							  rješenje.add(prijelazi.get(ii).split(",")[0]);
							  tren = prijelazi.get(ii).split(",")[0] + "," + prijelazi.get(ii).split(",")[1];
							  prijelazi.remove(ii);
						  
				  }
						  break;  
					  }
					  String str = tren;    //da se može pratiti koji je čvor bio prethodni
					  for (int i = 0; i < imena.get(tren.split(",")[0]).size(); i++) {			//treba zbrajati cijene za sljedeći čvor
						  String s = imena.get(tren.split(",")[0]).get(i);
						  double novaCijena = Double.parseDouble(tren.split(",")[1]) + Double.parseDouble(s.split(",")[1]);
						  s = s.split(",")[0] + "," + novaCijena;				//povećanje cijene za sljedeći čvor
						  otvori.add(s);
						  str += "," + s;
					  }
					  prijelazi.add(str);
					  
					  
				  }
				  
				  if (heuristic.get(polaziste) > statesPrice && jeKonzistentan == false) {
					  jeOptimistican = false;
					  System.out.println("[ERR] h(" + polaziste + ") > h*: " + heuristic.get(polaziste) + " > " + statesPrice);
				  }
				  
				  
			  }
			 
		  }
		  
		  if (jeKonzistentan == false && jeOptimistican == false) System.out.println("Heuristic is not optimistic.");
		  else System.out.println("Heuristic is optimistic.");
		  
		  
		  }
	

	}
