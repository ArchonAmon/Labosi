package hr.fer.oop.lab4.second;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeSet;

public class MySecondByteReader extends SimpleFileVisitor<Path>{
	
	TreeSet<Artikl> article;
	private String[] imena = new String[10];
	private int brojac = 0;
	private ArrayList<String> sadrzaj;
	private int brojac_datoteka = 0;
	private int ukupno_novi = 0;
	private int ukupno_stari = 0;
	
	public MySecondByteReader() {
		sadrzaj = new ArrayList<>();
	}
	
	public ArrayList<String> getSadrzaj() {
		return sadrzaj;
	}
	
	public String[] getImena() {
		return imena;
	}
	
	public int getStari() {
		return ukupno_stari;
	}
	
	public int getNovi() {
		return ukupno_novi;
	}
	
	
	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		return FileVisitResult.CONTINUE;
	}
	
	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		return FileVisitResult.CONTINUE;
	}
	
	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		return null;
	}
	
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//		System.out.println("reading file :"+file.toString());
		if(file.toString().endsWith("txt")){
			String ime = null;
			BufferedReader br = new BufferedReader(new InputStreamReader(Files.newInputStream(file, StandardOpenOption.READ)));
			while(true){
				String line = br.readLine();
				line.trim();
				line+="\r\n";
				
				if (line.startsWith("Račun br.") && (brojac == 5 || brojac == 50 || brojac == 90 || brojac == 91 || brojac == 100)) {
					ime = "Racun temp"+file.toString().substring(30, 35)+".txt";	//file.toString().substring(30, 35)
					imena[brojac_datoteka] = ime;
					brojac_datoteka++;
					Writer bw1 = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream("C:\\Users\\Robert Pavliš\\eclipse-workspace\\4.labos\\2"+ ime)),"UTF-8"));
					bw1.write(line);
					bw1.close();
				}
				
				if (brojac == 5 || brojac == 50 || brojac == 90 || brojac == 91 || brojac == 100) {
					sadrzaj.add(line);
				}
				
				
				  if(line.startsWith("UKUPNO")) {
                      String[] parts = line.split("\\s+");
      				if (!(brojac == 5 || brojac == 50 || brojac == 90 || brojac == 91 || brojac == 100)) ukupno_novi += Double.parseDouble(parts[1]);
      				ukupno_stari += Double.parseDouble(parts[1]);
                      break;
                  }

			}
            brojac++;
		}
    	return FileVisitResult.CONTINUE;
    	
    	
    }

}
