import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class SemantickiAnalizator {
	
	static HashMap<String, String> normalnaLista = new HashMap<>();
	static HashMap<String, String> novaLista = new HashMap<>();
//	static ArrayList<ArrayList<String>> listaLista = new ArrayList<ArrayList<String>>();
	static ArrayList<String> zadatak = new ArrayList<>();
	static ArrayList<String> temp = new ArrayList<>();
	static ArrayList<String> ispis = new ArrayList<>();
	static boolean uPetljiSam = false;
	static boolean imamJednako = false;
	static boolean nijeprvi = false;
	static boolean pocinjePetlja = false;
	static boolean naOd = false;
	static boolean naDo = false;
	static boolean zaustaviPetlju = false;
	static String redJednako = null;
	static String redPocetkaPetlje = null;
	static String znakPocetkaPetlje = null;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String s = reader.readLine();
		
		 while (s != null) {				//promijeni
			 temp.add(s);
			 
			 
			 s = reader.readLine();
//			 if (s.isEmpty()) break;
		 }
		 
		 for (int i = 0; i < temp.size(); i++)
		 if (!temp.get(i).trim().startsWith("<") && !temp.get(i).trim().startsWith("$")) zadatak.add(temp.get(i)); 			 						//maknuti nepotrebne
		 
		 for (int i = 0; i < zadatak.size(); i++) {

				 
			 if (zadatak.get(i).trim().split(" ")[0].startsWith("IDN") || zadatak.get(i).trim().split(" ")[0].startsWith("OP_PRIDRUZI")
				|| zadatak.get(i).trim().split(" ")[0].startsWith("KR_ZA") || zadatak.get(i).trim().split(" ")[0].startsWith("KR_OD")
				|| zadatak.get(i).trim().split(" ")[0].startsWith("KR_DO") || zadatak.get(i).trim().split(" ")[0].startsWith("BROJ")
				|| zadatak.get(i).trim().split(" ")[0].startsWith("KR_AZ")) {
				 
				 if (!zadatak.get(i).trim().split(" ")[1].equals(redJednako)) { 		//mozda greska ako dobro ne usporeduje
					 imamJednako = false;
				 }
				 
				 if (uPetljiSam == false && imamJednako == false && !normalnaLista.containsKey(zadatak.get(i).trim().split(" ")[2])) {
					 if (zadatak.get(i).trim().split(" ")[0].startsWith("IDN"))
						 normalnaLista.put(zadatak.get(i).trim().split(" ")[2], zadatak.get(i).trim().split(" ")[1]);  //kad dode idn na lijevoj strani onda ga ne ispisujem, ali stavljam u mapu
				 }

				 if (zadatak.get(i).trim().split(" ")[0].startsWith("OP_PRIDRUZI")) {
					 imamJednako = true;							//jesam li desno od jednako
					 redJednako = zadatak.get(i).trim().split(" ")[1];
				 }
				 if (uPetljiSam == false && imamJednako == true) {		//desna strana
					 if (zadatak.get(i).trim().split(" ")[0].startsWith("IDN")) {
						if (normalnaLista.containsKey(zadatak.get(i).trim().split(" ")[2]) && !zadatak.get(i).trim().split(" ")[1].equals(normalnaLista.get(zadatak.get(i).trim().split(" ")[2]))) {	//provjeri ima li ga u listi   (ovdje je mijenjano)
							ispis.add(zadatak.get(i).trim().split(" ")[1] + " " + normalnaLista.get(zadatak.get(i).trim().split(" ")[2]) + " " + zadatak.get(i).trim().split(" ")[2]);													
						}
						else {
							ispis.add("err" + " " + zadatak.get(i).trim().split(" ")[1] + " " + zadatak.get(i).trim().split(" ")[2]);
							break;
						}
					 }
				 }
				 
				 if (zadatak.get(i).trim().split(" ")[0].startsWith("KR_ZA")) {
					 uPetljiSam = true;
					 pocinjePetlja = true;
					 continue;
				 }
				 
				 if (zadatak.get(i).trim().split(" ")[0].startsWith("KR_AZ")) {
					 uPetljiSam = false;
					 novaLista.clear();
					 continue;
				 }
				 
				 if (zadatak.get(i).trim().split(" ")[0].startsWith("IDN") && pocinjePetlja == true) {			//samo id pocetka petlje
//					if (!normalnaLista.containsKey(zadatak.get(i).trim().split(" ")[2])) 
					 novaLista.put(zadatak.get(i).trim().split(" ")[2], zadatak.get(i).trim().split(" ")[1]);		//VJEROJATNO KRIVO i trebao sam staviti novaLista
					 znakPocetkaPetlje = zadatak.get(i).trim().split(" ")[2];
					 pocinjePetlja = false;
					 continue;
				 }
				 
				 if (zadatak.get(i).trim().split(" ")[0].startsWith("KR_OD")) {
					naOd = true;
					continue;
				 }
				 
				 if (naOd == true && (zadatak.get(i).trim().split(" ")[0].startsWith("IDN") || zadatak.get(i).trim().split(" ")[0].startsWith("BROJ"))) {					//iskemijati nesto sa brojem reda ako ne bude dobro
					 redPocetkaPetlje = zadatak.get(i).trim().split(" ")[1];
					 while (true) {
					 if (zadatak.get(i).trim().split(" ")[0].startsWith("IDN") && zadatak.get(i).trim().split(" ")[1].equals(redPocetkaPetlje)) {
						 if	(zadatak.get(i).trim().split(" ")[2].equals(znakPocetkaPetlje)) {
								ispis.add("err" + " " + zadatak.get(i).trim().split(" ")[1] + " " + zadatak.get(i).trim().split(" ")[2]);
								zaustaviPetlju = true;
								break;							//sumnjivo
						 }
						 else if (normalnaLista.containsKey(zadatak.get(i).trim().split(" ")[2])) {	//provjeri ima li ga u listi
								ispis.add(zadatak.get(i).trim().split(" ")[1] + " " + normalnaLista.get(zadatak.get(i).trim().split(" ")[2]) + " " + zadatak.get(i).trim().split(" ")[2]);													
							}
							else {
								ispis.add("err" + " " + zadatak.get(i).trim().split(" ")[1] + " " + zadatak.get(i).trim().split(" ")[2]);
								zaustaviPetlju = true;
								break;
							}
					 }
					 if (zadatak.get(i).trim().split(" ")[1].equals(redPocetkaPetlje)) {
							i++;
					 }
					 else break;
					 }
					 if (zaustaviPetlju) break;
					 i--;
					 naOd = false;
					 continue;
				 }
				 
				 if (zadatak.get(i).trim().split(" ")[0].startsWith("KR_DO") ) {
					naDo = true;
					continue;
				 }
				 
				 if (naDo == true && (zadatak.get(i).trim().split(" ")[0].startsWith("IDN") || zadatak.get(i).trim().split(" ")[0].startsWith("BROJ"))) {					//iskemijati nesto sa brojem reda ako ne bude dobro
					 redPocetkaPetlje = zadatak.get(i).trim().split(" ")[1];
					 while (true) {
						 if	(zadatak.get(i).trim().split(" ")[2].equals(znakPocetkaPetlje)) {
								ispis.add("err" + " " + zadatak.get(i).trim().split(" ")[1] + " " + zadatak.get(i).trim().split(" ")[2]);
								zaustaviPetlju = true;
								break;							//sumnjivo
						 }
						 else if (zadatak.get(i).trim().split(" ")[0].startsWith("IDN") && zadatak.get(i).trim().split(" ")[1].equals(redPocetkaPetlje)) {
							if (normalnaLista.containsKey(zadatak.get(i).trim().split(" ")[2])) {	//provjeri ima li ga u listi
								ispis.add(zadatak.get(i).trim().split(" ")[1] + " " + normalnaLista.get(zadatak.get(i).trim().split(" ")[2]) + " " + zadatak.get(i).trim().split(" ")[2]);													
							}
							else {
								ispis.add("err" + " " + zadatak.get(i).trim().split(" ")[1] + " " + zadatak.get(i).trim().split(" ")[2]);
								zaustaviPetlju = true;
								break;
							}
					 }
					 if (zadatak.get(i).trim().split(" ")[1].equals(redPocetkaPetlje)) {
						 i++;
					 }
					 else break;
					 }
					 if (zaustaviPetlju) break;
					 i--;
					 naDo = false;
					 continue;
				 }

				 if (uPetljiSam == true && imamJednako == false) {	 
					 if (zadatak.get(i).trim().split(" ")[0].startsWith("IDN") && !normalnaLista.containsKey(zadatak.get(i).trim().split(" ")[2]) && !novaLista.containsKey(zadatak.get(i).trim().split(" ")[2])) { //3. uvjet dodan
						 novaLista.put(zadatak.get(i).trim().split(" ")[2], zadatak.get(i).trim().split(" ")[1]);  //stavit u NovuMapu
					 }
				 }
				 
				 if (uPetljiSam == true && imamJednako == true) {			//provjerit sve s desne strane
					 
					 if (zadatak.get(i).trim().split(" ")[0].startsWith("IDN")) {

							if (novaLista.containsKey(zadatak.get(i).trim().split(" ")[2])) {		//ako samo postoji na lijevoj strani od jednako onda ne ovo raditi
								ispis.add(zadatak.get(i).trim().split(" ")[1] + " " + novaLista.get(zadatak.get(i).trim().split(" ")[2]) + " " + zadatak.get(i).trim().split(" ")[2]);
							}  //zamijeni prva 2
							else if (normalnaLista.containsKey(zadatak.get(i).trim().split(" ")[2])) {	//provjeri ima li ga u listi
								ispis.add(zadatak.get(i).trim().split(" ")[1] + " " + normalnaLista.get(zadatak.get(i).trim().split(" ")[2]) + " " + zadatak.get(i).trim().split(" ")[2]);													
							}
							else {
								ispis.add("err" + " " + zadatak.get(i).trim().split(" ")[1] + " " + zadatak.get(i).trim().split(" ")[2]);
								break;
							}
					 }
				 }
				 
				 
			 }
			 
		 }
		 
		 for (int i = 0; i < ispis.size(); i++) {
			 if (i < ispis.size() && nijeprvi != false) System.out.print("\n");
			 System.out.print(ispis.get(i));									//ispis
			 nijeprvi = true;
		 }


	}
	//ako red pocinje sa za onda se cijeli red ignorira i ide se dalje, ali se provjerava postoje li te varijable u listi
	//svaka nova iteracija stvara svoju listu varijabla
	//pocisti red i ako nije $ i ako ne pocinje s < onda gledas 3. clan
}
