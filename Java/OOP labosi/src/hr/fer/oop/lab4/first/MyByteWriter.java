package hr.fer.oop.lab4.first;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

public class MyByteWriter {
	
	protected InputStream inputStream;
	protected Path newFile;
	
	public MyByteWriter(InputStream inputStream, Path newFile){
		this.inputStream = inputStream;
		this.newFile = newFile;
	}
	
	protected void createFile(Path path){
		try {
			Files.createFile(newFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void run(){
		
		if(Files.notExists(this.newFile, LinkOption.NOFOLLOW_LINKS)){
			this.createFile(this.newFile);
		}
		
		try(OutputStream os= Files.newOutputStream(this.newFile, StandardOpenOption.WRITE)){
			byte [] buffer = new byte[1024];
			BufferedInputStream bis = new BufferedInputStream(this.inputStream);
			while(true){
				int numOfReadBytes = bis.read(buffer);
				if(numOfReadBytes<1) break;
				os.write(buffer, 0, numOfReadBytes);
			}
			bis.close();
			this.inputStream.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
//bolje rijesenje:	
	
	public void run2() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(inputStream), "UTF-8"));
		OutputStream f = Files.newOutputStream(newFile);
		Writer bw = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(f),"UTF-8"));
		
		
		while(true) {		
			String line = br.readLine();
			if (line == null)break;
			line +="\r\n";
			System.out.print(line);
	 		bw.write(line);
	 		}
					
			
			bw.close();
	}
	
//jos bolje rijesenje
	
	public void run3() throws IOException{
	BufferedReader br = new BufferedReader(
			 new InputStreamReader(
			 new BufferedInputStream(
		     new FileInputStream("C:\\Users\\Robert Pavliš\\eclipse-workspace\\4.labos\\racuni3\\2015\\1\\Racun_0.txt")),"UTF-8"));
	Writer bw = new BufferedWriter(
			 new OutputStreamWriter(
			 new BufferedOutputStream(
		   	 new FileOutputStream("C:\\Users\\Robert Pavliš\\eclipse-workspace\\4.labos\\singleout.txt")),"UTF-8"));
	
	while(true) {
			 String line = br.readLine();
			 if (line == null)break;
			 line += "\r\n";
			 bw.write(line);
	}
	bw.close();
	br.close();
	}
}
