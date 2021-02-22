import java.io.*;
import java.util.*;

public class SimPa {
	
	static LinkedHashMap<String, LinkedList<String>> temp = new LinkedHashMap<>();
	static LinkedHashMap<Integer, ArrayList<String>> grupe2;
	static LinkedHashMap<Integer, ArrayList<String>> grupe = new LinkedHashMap<>();
	static String[] ulaz = new String[100];

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int brojac = 0;
	
//		String[] Lulaz = new String[101];
		List<String> Lprijelazi = new ArrayList<String>();
//		List<String> Lstanja = new ArrayList<String>();
//		List<String> Labeceda = new ArrayList<String>();
//		List<String> Lprihvatljiva = new ArrayList<String>();


		String[] prihvatljiva = new String[100];
		String pocetno = null;
		String pocetno_stog = null;
        String s = reader.readLine();
    	
    	
        while (s != null) {			//ovdje staviti s != null

        	if (brojac == 0) {
        		ulaz = s.split("\\|");
        	}
        	if (brojac == 4 ) {
        		prihvatljiva = s.split("\\,");
        	}
        	if (brojac == 5 && !s.isEmpty()) pocetno = s;
        	if (brojac == 6 && !s.isEmpty()) pocetno_stog = s;
        	if (brojac > 6 && s != null) Lprijelazi.add(s);			//ovdje mozda dodati uvjet s != null
        	brojac++;
        	s = reader.readLine();
//        	if (brojac > 6 && s.isEmpty()) break;						//ovo bi trebalo moci maknuti 
        }
        	
        	List<String[]> ulazi = new ArrayList<>();
        	for (int i = 0; i < ulaz.length; i++) {
        		ulazi.add(ulaz[i].split("\\,"));
        	}
        	
	        Map<Integer, String[]> Lprijelazilijevo = new LinkedHashMap<>();
	        Map<Integer, String[]> Lprijelazidesno = new LinkedHashMap<>();
	        brojac = 0;
	        
	        for (String str : Lprijelazi) {
	        	String[] string = str.split("->");
	        	String[] string2 = string[0].split("\\,");
	        	String[] string3 = string[1].split("\\,");
	        	
	        	Lprijelazilijevo.put(brojac, string2);
	        	Lprijelazidesno.put(brojac, string3);
	        	
	        	brojac++;
	        }
        
	        

	        
	        for (int c = 0; c < ulazi.size(); c++) {			//svi ulazni zadaci
	        	
		        String sljedece_stanje = pocetno;
		        String sljedeci_stog = pocetno_stog;
		        boolean zastavicazafail = false;
	        	boolean stavi2 = false;
	        	boolean kraj = false;
	        	boolean zastavica = false;
	        	List<String> current = new ArrayList<>();
	        	Set<String> set = new LinkedHashSet<>();
	        	set.add(pocetno);
	        		
	        	System.out.print(pocetno+"#"+pocetno_stog);
	        	
	        	for (int j = 0; j < ulazi.get(c).length; j++) {
	        		kraj = true;
//	        		brojaczafali = brojac2;
	        		stavi2 = false;
	        		
	        		for (int i = 0; i < Lprijelazilijevo.size(); i++) {
		        		if (Lprijelazilijevo.get(i)[0].equals(sljedece_stanje) 
		        				&& (!set.contains(Lprijelazidesno.get(i)[0]) || sljedece_stanje.equals(Lprijelazidesno.get(i)[0]) || true)
//		        				&& ulazi.get(c).length < brojac2
		        				&& (Lprijelazilijevo.get(i)[1].equals(ulazi.get(c)[j]) 
		        						|| Lprijelazilijevo.get(i)[1].equals("$"))
		        						&& Lprijelazilijevo.get(i)[2].charAt(0) == 
		        						(sljedeci_stog.isEmpty() ?Lprijelazilijevo.get(i)[2].charAt(0) :sljedeci_stog.charAt(0))) {
		        			if(Lprijelazilijevo.get(i)[1].equals("$")) stavi2 = true;
		        			sljedece_stanje = Lprijelazidesno.get(i)[0];
		        			if(sljedeci_stog.isEmpty()) break;
		        			
//		        			if (sljedeci_stog.length()>=Lprijelazidesno.get(i)[1].length())sljedeci_stog = sljedeci_stog.substring(Lprijelazidesno.get(i)[1].length());
		        			sljedeci_stog = sljedeci_stog.substring(1);		
//		        			if ()
		        			if (!Lprijelazidesno.get(i)[1].equals("$"))sljedeci_stog = Lprijelazidesno.get(i)[1] + sljedeci_stog;	//staviti novi znak na lijevu stranu
//		        			if (Lprijelazidesno.get(i)[1]) sljedeci_stog = sljedeci_stog.substring(1); 	//preza
		        			if (stavi2) j = j - 1;		//bio je epsilon prijelaz pa smanjujemo broj ulaznih znakova
	        		kraj = false;
	        		set.add(sljedece_stanje);
	        		current.add(sljedece_stanje);
	        		break;
		        		}	        		
		        		}
	        		
	        		if (kraj && j < ulazi.get(c).length) {			//fail
	        			if (sljedeci_stog.isEmpty())System.out.print("$");
	        			zastavicazafail = true;
	        			System.out.print("|fail");
	        			break;
	        		}
	        		
	        		System.out.print("|"+sljedece_stanje+"#"+sljedeci_stog);
	        		
	        	}
	        	
	        	for(int u = 0; u < prihvatljiva.length; u++)
		        	if (current.size() > 2 && prihvatljiva[u].equals(current.get(current.size()-1))) { 
//		        		System.out.print("|1");
		        		zastavica = true;
		        	}
//	        	if (sljedeci_stog.equals("KXXK") && sljedece_stanje.equals("q2")) {
//	        		System.out.println("|1");		//iznimka jer ne ide se dalje nakon sto
//	        		break;							//je prosao sve i na dobrom je stanju
//	        	}
	        	
	        		boolean temp = false;
	        		int zadnjired = 0;
	        		if (zastavica == false) {
	        		do {
	        			temp = false;
	        		for (int i = zadnjired; i < Lprijelazilijevo.size(); i++) {
		        		if (Lprijelazilijevo.get(i)[0].equals(sljedece_stanje) 
		        				&& (!set.contains(Lprijelazidesno.get(i)[0]) || sljedece_stanje.equals(Lprijelazidesno.get(i)[0]))
//		        				&& ulazi.get(c).length < brojac2
		        				&& Lprijelazilijevo.get(i)[1].equals("$")
		        						&& Lprijelazilijevo.get(i)[2].charAt(0) == sljedeci_stog.charAt(0)) {
		        			if(Lprijelazilijevo.get(i)[1].equals("$")) stavi2 = true;
		        			sljedece_stanje = Lprijelazidesno.get(i)[0];
//		        			if (sljedeci_stog.length()>=Lprijelazidesno.get(i)[1].length())sljedeci_stog = sljedeci_stog.substring(Lprijelazidesno.get(i)[1].length());
		        			sljedeci_stog = sljedeci_stog.substring(1);		
//		        			if ()
		        			sljedeci_stog = Lprijelazidesno.get(i)[1] + sljedeci_stog;	//staviti novi znak na lijevu stranu
//		        			if (Lprijelazidesno.get(i)[1]) sljedeci_stog = sljedeci_stog.substring(1); 	//preza
//		        			if (stavi2) j = j - 1;		//bio je epsilon prijelaz pa smanjujemo broj ulaznih znakova
	        		temp = true;
	        		zadnjired = i;
	        		set.add(sljedece_stanje);
	        		break;
		        		}	        		
		        		}
	        		
	        		if (temp == true) System.out.print("|"+sljedece_stanje+"#"+sljedeci_stog);
	        		} while (temp == true && zadnjired < Lprijelazilijevo.size()-1);
	        		}
	        	
	        	//ovdje ide print
	        	
	        	
//	        	boolean stavi = false;
//	        	for (int i = 0; i < Lprijelazilijevo.size(); i++) {
//	        		
//	        		if (Lprijelazilijevo.get(i)[0].equals(pocetno) 
//	        				&& (Lprijelazilijevo.get(i)[1].equals(ulazi.get(c)[0]) 
//	        						|| Lprijelazilijevo.get(i)[1].equals("$"))
//	        						&& Lprijelazilijevo.get(i)[2].equals(pocetno_stog)) {
//	        			if(Lprijelazilijevo.get(i)[1].equals("$")) stavi = true;
//	        			sljedece_stanje = Lprijelazidesno.get(i)[0];
//	        			sljedeci_stog = Lprijelazidesno.get(i)[1];
//	        			if (!stavi) brojac2++;
//	        			break;
//	        		}      		
//	        	}
//	        	
//	        	if (stavi) System.out.print("|"+sljedece_stanje+"#"+sljedeci_stog);
//	        	if (brojac2 == 0 && !stavi) {   //napisi fali ako nije bilo epsilon prijelaza ni normalnog
//	        		System.out.print("|fail");       
//	        		zastavicazafail = true;
//	        		System.out.print("|0");
//	        		continue;
//	        	}
//	        	if (!stavi) {
//	        		System.out.print("|"+sljedece_stanje+"#"+sljedeci_stog);	//bio je normalan prijelaz
//	        	}
//	        	
//	        	  	int j;
//	        	  	boolean stavi2 = false;
//	        	for (j = (stavi == true ? 0 : 1); j < ulazi.get(c).length; j++) {			//svi ulazni znakovi
//	        		
//	        		brojaczafali = brojac2;
//	        		stavi2 = false;
//	        		for (int i = 0; i < Lprijelazilijevo.size(); i++) {
//		        		if (Lprijelazilijevo.get(i)[0].equals(sljedece_stanje) 
//		        				&& ulazi.get(c).length < brojac2
//		        				&& (Lprijelazilijevo.get(i)[1].equals(ulazi.get(c)[brojac2]) 
//		        						|| Lprijelazilijevo.get(i)[1].equals("$"))
//		        						&& Lprijelazilijevo.get(i)[2].charAt(0) == sljedeci_stog.charAt(0)) {
//		        			if(Lprijelazilijevo.get(i)[1].equals("$")) stavi2 = true;
//		        			sljedece_stanje = Lprijelazidesno.get(i)[0];
//		        			if (!stavi2)
//		        				sljedeci_stog = sljedeci_stog.substring(Lprijelazidesno.get(i)[1].length());
//		        			sljedeci_stog = (Lprijelazidesno.get(i)[1] == "$" ? null : Lprijelazidesno.get(i)[1])+ sljedeci_stog;	//staviti novi znak na lijevu stranu
//	        		if (stavi2) j = j - 1;
//	        	brojac2++
//	        		break;
//	        	}
//	        		}
//	        		
//	        	}
//	        		
//			        	if (brojaczafali == brojac2 && !stavi2 && j != ulazi.get(c).length) {    //napisi fali ako se nije pomaknuo u drugo stanje
//			        	System.out.print("|fail");
//			        	zastavicazafail = true;
//			        	break;
//			        	}
//			        	System.out.print("|"+sljedece_stanje+"#"+sljedeci_stog);
	        	
	        	
	        	
	        	
	        	
//	        	for (int j = 0; j < ulazi.get(c).length; j++) {			//svi ulazni znakovi
//	        		
//	        		brojaczafali = brojac2;
//	        		boolean stavi2 = false;
//	        	for (int i = 0; i < Lprijelazilijevo.size(); i++) {
//	        		if (Lprijelazilijevo.get(i)[0].equals(sljedece_stanje) 
//	        				&& ulazi.get(c).length < brojac2
//	        				&& (Lprijelazilijevo.get(i)[1].equals(ulazi.get(c)[brojac2]) 
//	        						|| Lprijelazilijevo.get(i)[1].equals("$"))
//	        						&& Lprijelazilijevo.get(i)[2].charAt(0) == sljedeci_stog.charAt(0)) {
//	        			if(Lprijelazilijevo.get(i)[1].equals("$")) stavi2 = true;
//	        			sljedece_stanje = Lprijelazidesno.get(i)[0];
//	        			if (sljedeci_stog != "$")
//	        				sljedeci_stog = sljedeci_stog.substring(Lprijelazidesno.get(i)[1].length());
//	        			sljedeci_stog = Lprijelazidesno.get(i)[1] + sljedeci_stog;	//staviti novi znak na lijevu stranu
//	        			if (stavi2 == false) brojac2++;
//	        			if (stavi2 == true) {
//	        				j = j-1;
//	        				i = i-1;
//	        			}
//	        			break;
//	        		}
//	        	
//	        	
//	        	}
//	        	
//	        	
//	        	if (brojaczafali == brojac2) {    //napisi fali ako se nije pomaknuo u drugo stanje
//	        	System.out.print("|fail");
//	        	break;
//	        	}
//	        	System.out.print("|"+sljedece_stanje+"#"+sljedeci_stog);
//	        	
//	        }
	        	boolean jesam = false;
	        	if (zastavicazafail) {
	        		System.out.print("|0");
	        		System.out.println();
	        		continue;
	        	}
	        	for(int u = 0; u < prihvatljiva.length; u++)
	        	if (prihvatljiva[u].equals(sljedece_stanje)) { 
	        		System.out.print("|1");
	        		jesam = true;
	        		break;
	        	}
	        	if (!jesam) System.out.print("|0");
	        	if (c != ulazi.size())System.out.println();
        
	}
}
	        }

