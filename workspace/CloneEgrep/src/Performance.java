
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import regex.RegEx;

public class Performance {
	
	 public static void testKmp(String[] listMotif, String text) {
			HashMap<String,Long> resultKMP = new HashMap<String,Long>();
			System.out.println("Test de l'algorithmes KMP >>>>>>>>>");
			for(String motif : listMotif) {
				String []args = {motif, text};
				long start = System.currentTimeMillis(); 
				KMP.main(args);
				long end = System.currentTimeMillis();
				long temps = (end-start);
				System.out.println("Le temps d'execution pour motif : "+motif+" dans "+text+" est : " + temps);
				resultKMP.put(motif, temps);
			}
			writeToFile( "src/performance/resultKMP"+ text + ".txt", resultKMP);
			
	}
	 
	public static void testAutomate(String[] listMotif, String text) {
		HashMap<String,Long> resAutomate = new HashMap<String,Long>();
		System.out.println("Test d'automate avec une suite de concatenations >>>>>>>>>");
		for(String motif : listMotif) {
			String []args = {motif, text};
			long start = System.currentTimeMillis(); 
			RegEx.main(args);
			long end = System.currentTimeMillis(); 
			long temps = (end-start);
			System.out.println("Le temps d'execution pour motif : "+motif+" dans "+text+" est : " + temps);
			resAutomate.put(motif, temps);
		}
		
		writeToFile( "src/performance/resultConcatRegEx"+ text + ".txt", resAutomate);
	}
	
	public static void testRegEx(String[] listMotif, String text) {
		HashMap<String,Long> resAutomate = new HashMap<String,Long>();
		System.out.println("Test d'automate avec expression reguliere >>>>>>>>>");
		for(String motif : listMotif) {
			String []args = {motif, text};
			long start = System.currentTimeMillis(); 
			RegEx.main(args);
			long end = System.currentTimeMillis(); 
			long temps = (end-start);
			System.out.println("Le temps d'execution pour motif : "+motif+" dans "+text+" est : " + temps);
			resAutomate.put(motif, temps);
		}
		
		writeToFile( "src/performance/resultRegEx"+ text + ".txt", resAutomate);
	}
	
	public static void testRegExJava(String[] listRegex, String text) {
		HashMap<String,Long> res = new HashMap<String,Long>();
		System.out.println("Test with class Pattern of Java >>>>>>>>>");
		for(String motif : listRegex) {
			String []args = {motif, text};
			long start = System.currentTimeMillis(); 
			BufferedReader reader;
	        try {
	            reader = new BufferedReader(new FileReader("src/texts/"+text));
	            String line;
	            while((line = reader.readLine()) != null) {
	                if(Pattern.compile(motif).matcher(line).find()) {
	                	System.out.println(line);
	                }
	            }
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			long end = System.currentTimeMillis(); 
			long temps = (end-start);
			System.out.println("Le temps d'execution pour motif : "+motif+" dans "+text+" est : " + temps);
			res.put(motif, temps);
		}
		
		writeToFile( "src/performance/resultJavaRegEx"+ text + ".txt", res);
	}
	 
	public static void writeToFile(String nomFichier, HashMap<String,Long> result) {
			BufferedWriter writer;
			try {
				writer = new BufferedWriter(new FileWriter(nomFichier));
				for(Entry<String,Long> entry: result.entrySet()) {
					String ligne = entry.getKey()+"\t"+entry.getValue()+"\n";
					writer.write(ligne);
				}
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args) {
		String[] motifConcat1 = {"are", "the", "of", "Babyl"}; 
		String text1 = "text1";
		testKmp(motifConcat1, text1);
		testAutomate(motifConcat1, text1);
		testRegExJava(motifConcat1, text1);
		
		String[] motifConcat2 = {"king", "slave", "Babylon", "civilization"}; 
		String text2 = "text2";
		testKmp(motifConcat2, text2);
		testAutomate(motifConcat2, text2);
		testRegExJava(motifConcat2, text2);
		
	}
}
