import java.io.*;
import java.util.*;

public class SimEnka {
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int brojac = 0;
	
		List<List<String>> Lulaz2 = new ArrayList<>();
		List<String> Lprijelazi = new ArrayList<String>();
		List<String> Lstanja = new ArrayList<String>();
		List<String> Labeceda = new ArrayList<String>();
		List<String> Lprihvatljiva = new ArrayList<String>();
		String pocetno = null;
        String s = reader.readLine();
        int brRedova = 0;
        
        while (s != null) {			//ovdje staviti s != null
        	if (brojac == 0) {
                String ulaz = s;
                String[] st = ulaz.split("\\|");
                String[] str;
                brRedova = st.length;
                for (int i = 0; i < st.length; i++) {
            		List<String> set = new ArrayList<>();
                	str = st[i].split("\\,");
                for (int j = 0; j < str.length; j++) {
                	set.add(str[j]);
                }
                Lulaz2.add(set);
        	}
        	}
        	if (brojac == 1) {
        		String[] st1 = s.split("\\,");
        		for (int i = 0; i < st1.length; i++)
        		Lstanja.add(st1[i]);
        	}
        	if (brojac == 2) {
        		String[] st1 = s.split("\\,");
        		for (int i = 0; i < st1.length; i++)
        		Labeceda.add(st1[i]);
        	}
        	if (brojac == 3) {
        		String[] st1 = s.split("\\,");
        		for (int i = 0; i < st1.length; i++)
        		Lprihvatljiva.add(st1[i]);
        	}
        	if (brojac == 4) pocetno = s;
        	if (brojac > 4 && s != null) Lprijelazi.add(s);			//ovdje mozda dodati uvjet s != null
        	brojac++;
        	s = reader.readLine();
//        	if (brojac > 4 && s.isEmpty()) break;						//ovo bi trebalo moci maknuti 
        }
        
        Map<Integer, ArrayList<String>> Lprijelazilijevo = new LinkedHashMap<Integer, ArrayList<String>>();
        Map<Integer, ArrayList<String>> Lprijelazidesno = new LinkedHashMap<Integer, ArrayList<String>>();
        int br = 0;
        
        for (String str : Lprijelazi) {
            ArrayList<String> lista1 = new ArrayList<>();
            ArrayList<String> lista2 = new ArrayList<>();
        	String[] string = str.split("->");
        	String[] string2 = string[0].split("\\,");
        	String[] string3 = string[1].split("\\,");
        	for (int i = 0; i < string2.length; i++)
        		lista1.add(string2[i]);
        	for (int i = 0; i < string3.length; i++)
        		lista2.add(string3[i]);
        	
        	Lprijelazilijevo.put(br, lista1);
        	Lprijelazidesno.put(br, lista2);
        	
        	br++;
        }
        
 


        
        for (int i = 0; i < brRedova; i++) {
        	
        	List<Set<String>> trenutno = new ArrayList<>();
        	Set<String> tren = new TreeSet<>();
        	Set<String> temp3 = new TreeSet<>();
        	temp3.add(pocetno);
        	tren.add(pocetno);
        	trenutno.add(tren);
        	
        	for (int j = 0; j < 101; j++) {			//petlja za vise e-prijelaza u pocetnim stanjima
    		for (String m : trenutno.get(0)) {
    			//pocetni niz + e-prijelazi za ta stanja
    			
    			for (int k = 0; k < Lprijelazilijevo.size(); k++) {
    				int tempoldd = temp3.size();
    				if (Lprijelazilijevo.get(k).get(0).equals(m) && Lprijelazilijevo.get(k).get(1).equals("$")) {
    					temp3.addAll(Lprijelazidesno.get(k));
    					
    				}
    				if (temp3.size() != tempoldd) k = 0;		//trenutno.add(temp3)
    				
    			}
    			
    		}
        	trenutno.get(0).addAll(temp3);
        	}
        	
        	trenutno.get(0).addAll(temp3);
    		int brojac3 = 0;

			for (String st : temp3) {
				
				if (brojac3 < temp3.size() - 1) System.out.print(st + ",");			//ispis prvih stanja
				else System.out.print(st);
				
				brojac3++;
			}

        	for (String sta : Lulaz2.get(i)) {
        		System.out.print("|");
        		Set<String> temp = new TreeSet<>();
        		Set<String> temp2 = new TreeSet<>();
        		
        		
        		for (int j = 0; j < 101; j++) {			//petlja za vise e-prijelaza u pocetnim stanjima
        		for (String m : trenutno.get(0)) {
        			//gledati koje su jos mogucnosti sa e-prijelazima
        			
        			for (int k = 0; k < Lprijelazilijevo.size(); k++) {
        				int tempold = temp2.size();
        				if (Lprijelazilijevo.get(k).get(0).equals(m) && Lprijelazilijevo.get(k).get(1).equals("$")) {
        					temp2.addAll(Lprijelazidesno.get(k));
        				}
        				if (temp2.size() != tempold) k = 0;
        				
        			}
        		}
        		}
        		
        		trenutno.get(0).addAll(temp2);
        			
        			for (String m : trenutno.get(0)) {
        				
        				for (int k = 0; k < Lprijelazilijevo.size(); k++) {
        					
        					if (Lprijelazilijevo.get(k).get(0).equals(m) && Lprijelazilijevo.get(k).get(1).equals(sta))
        						temp.addAll(Lprijelazidesno.get(k));
        					
//        					if (Lprijelazilijevo.get(k).get(0).equals(m) && Lprijelazilijevo.get(k).get(1).equals("$"))
//        						temp.remove(Lprijelazilijevo.get(k).get(0));
        				}
        				
        				
        			}
        		trenutno.clear();
        		trenutno.add(new TreeSet<String>());
        		trenutno.get(0).addAll(temp); 		//trenutno.add(temp)
        			
        		for (int c = 0; c < 101; c++) {
        			for (String m : trenutno.get(0)) {
        				
        				for (int k = 0; k < Lprijelazilijevo.size(); k++) {
        				
        				if (Lprijelazilijevo.get(k).get(0).equals(m) && Lprijelazilijevo.get(k).get(1).equals("$"))
        					temp.addAll(Lprijelazidesno.get(k));
        			}
        				
        			}
        			trenutno.get(0).addAll(temp);
        		}
        		
        		
            		if (temp.isEmpty()) temp.add("#");
            		if (temp2.isEmpty()) temp2.add("#");
            		
            		trenutno.clear();
            		trenutno.add(temp);

            		int brojac2 = 0;

            			for (String st : temp) {
            				if (!st.equals("$") && !st.equals("#") || (temp.size() == 1 && st.equals("#")) ) {
            				if (brojac2 < temp.size() - 1) System.out.print(st + ",");
            				else System.out.print(st);
            				}
            				brojac2++;
            			}
        		
        		

        	}
			



        	System.out.print("\n");
        }
       
	}

}
