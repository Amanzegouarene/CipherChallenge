package fr.amanzegouarene.ali.cipher.challenge.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class EnglishAlphabeticUtils {
	
	private final static String SEPARATOR = "_";
	private final static String LETTERS_FREQUENCY_FILE_NAME = "C:/Users/Ali/Documents/CipherChallenge/englishAlphabeticLettersFrequency.txt";
	private final static Map<Character, Double> LETTERS_FREQUENCY_MAP = new HashMap<Character, Double>(); 

	public EnglishAlphabeticUtils() {
		// TODO Auto-generated constructor stub
	}

	public static Map<Character, Double> calculateDefaultLettersFrequencies(){
		try {
			File sourceFile = new File(LETTERS_FREQUENCY_FILE_NAME);
			if(sourceFile == null || !sourceFile.exists() || !sourceFile.isFile()){
				System.out.println(" EnglishAlphabeticUtils.calculateDefaultLettersFrequencies(): "
			+LETTERS_FREQUENCY_FILE_NAME+"does nont exist or is not a file");
				return null; 
			}
			FileReader fileRead = new FileReader(sourceFile);
			BufferedReader bf = new BufferedReader(fileRead); 
			String line = ""; 
			while ((line=bf.readLine())!=null) {
				String[] attributs = line.split(SEPARATOR);
				char key = attributs[0].charAt(0); 
				Double value = Double.valueOf(attributs[1]);
				LETTERS_FREQUENCY_MAP.put(key, value); 
			}
			bf.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return LETTERS_FREQUENCY_MAP; 
	}
}
