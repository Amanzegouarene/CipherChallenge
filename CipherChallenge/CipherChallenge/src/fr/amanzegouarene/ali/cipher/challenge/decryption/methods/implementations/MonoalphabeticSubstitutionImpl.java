package fr.amanzegouarene.ali.cipher.challenge.decryption.methods.implementations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import fr.amanzegouarene.ali.cipher.challenge.decryption.methods.interfaces.MonoalphabeticSubstitution;
import fr.amanzegouarene.ali.cipher.challenge.utils.EnglishAlphabeticUtils;
import fr.amanzegouarene.ali.cipher.challenge.utils.FileUtils;
import fr.amanzegouarene.ali.cipher.challenge.utils.MapUtils;

public class MonoalphabeticSubstitutionImpl implements MonoalphabeticSubstitution {

	private final static String ESCAPE = "\\"; 
	private final static String DECRYPTED_SUFFIX = "_decrypted.txt";
	private final static String END_OF_LINE = "\n"; 
	
	private HashMap<Character, Double> letters = new HashMap<>(); 
	private int nbLetters = 0;
	private HashMap<Character, Character> decryptingKey; 
	
	@Override
	public boolean decrypteTextFromFile(String inputFileName) {
		if(inputFileName == null || inputFileName.trim().equals("")){
			return false;
		}
		try {
			// 1- Extract the text into the file
			StringBuilder text = FileUtils.parseFile(inputFileName); 
			if(text == null){
				System.out.println("Can't parse the input file: "+inputFileName);
				return false; 
			}
			// 2- Parse file and extract letters
			parseFileLetterFrequency(text);
			
		// TODO: loop over the next 3 steps
			// 2- Build a key according to the letters frequencies in the text
			buildKey(letters);
			
			// 3- Decrypte the text using the key
			StringBuilder decryptedText = decryptText(decryptingKey, text); 
			
			// 4- Verify the end of decryption 
			Boolean isDecryptionValid = validateDecryption(decryptedText);
			
			// 5- Write the result into the output text if 
			
//			
//			String outputFileName = inputFile.getParent()+ESCAPE+inputFile.getName()+DECRYPTED_SUFFIX; 
//			File outputFile = new File(outputFileName);
//			BufferedWriter bfWriter = new BufferedWriter(new FileWriter(outputFile)); 
//			
//			FileReader inFReader = new FileReader(inputFile);
//			BufferedReader inBuffer = new BufferedReader(inFReader);
//			int readed=0;
//			char readedChar=0;
//			char decryptedChar=0;
//			StringBuffer decryptedLine = new StringBuffer(); 
//			while ((readed = inBuffer.read()) != -1) {
//				readedChar = (char) readed; 
//				// Verify the readed char is a letter
//				if (isALetter(readedChar)){
//					// retreive the dycrypted letter 
//					decryptedChar = decrypteLetter(readedChar);
//					decryptedLine.append(decryptedChar);
//					// A new line
//				}else if(readedChar=='\n'){
//					bfWriter.write(decryptedLine.toString());
//					decryptedLine.delete(0, decryptedLine.length()); 
//				}else{
//					decryptedLine.append(readedChar); 
//				}
//				
//			}
//			bfWriter.write(decryptedLine.toString());
//			inBuffer.close();
//			bfWriter.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		return true;
	}

	private Boolean validateDecryption(StringBuilder decryptedText) {
		// TODO Auto-generated method stub
		return null;
	}

	private StringBuilder decryptText(HashMap<Character, Character> decryptingKey, StringBuilder text) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * parseFile: parse the given inputFile and return the text into a StringBuilder object.
	 * @param inputFile
	 * @return StringBuilder which contains the inputFile text.
	 * @throws IOException 
	 */
	private StringBuilder parseFile(File inputFile) throws IOException {
		String readed="";
		StringBuilder readedLine = new StringBuilder(); 
		try {
			FileReader inFReader = new FileReader(inputFile);
			BufferedReader inBuffer = new BufferedReader(inFReader);
			while ((readed = inBuffer.readLine()) != null) {
				readedLine.append(readed); 
				readedLine.append(END_OF_LINE); 
			}
			inBuffer.close();
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		
		return readedLine;
	}

	private HashMap<Character, Character> buildKey(HashMap<Character, Double> letters) {
		// First, sort the map letter on value
		Map<Character, Double> sortedLetters = MapUtils.sortByValue(letters); 
		System.out.println("********* LETTERS :");
		System.out.println(letters.toString());
		System.out.println("********* SORTED LETTERS :");
		System.out.println(sortedLetters.toString());
		
		Map<Character, Double> defaultLettersFrequencyMap = EnglishAlphabeticUtils.calculateDefaultLettersFrequencies();
		defaultLettersFrequencyMap = MapUtils.sortByValue(defaultLettersFrequencyMap);
		System.out.println("********* DEFAULT LETTERS :");
		System.out.println(defaultLettersFrequencyMap.toString());
		Character[] textLetters = sortedLetters.keySet().toArray(new Character[0]);
		int count = 0; 
		for (char actualChar : defaultLettersFrequencyMap.keySet()) {
			if(textLetters.length==count){
				break; 
			}
			decryptingKey.put(textLetters[count], actualChar);
			count++; 
		}
		return null;
	}

	/**
	 * parseFileLetterFrequency: calculate the frequency of apparition of each text letter 
	 * and save it in the map letters.
	 * @param text
	 */
	private void parseFileLetterFrequency(StringBuilder text) {
		if (text == null || text.length()<=0){
			return ;
		}
		IntStream textChars = text.chars();
		textChars.forEach(nbr -> calculteFrequanecy(nbr));
		if(letters != null && !letters.isEmpty() && nbLetters > 0){
			Double frequency = null; 
			for (Character key : letters.keySet()) {
				frequency = (double) letters.get(key) / nbLetters;
				letters.put(key, frequency); 
			}
		}
	}

	/**
	 * calculateFrequency: calculate the number of apparition of each letter of the text
	 * and save the result in le variable lettres. 
	 * @param intChar
	 */
	private void calculteFrequanecy(int intChar){ 
		if (Character.isAlphabetic(intChar)){
			nbLetters ++; 
			char letter  = (char) intChar; 
			if(letters.containsKey(letter)){
				Double value = letters.get(letter) + 1L;
				letters.put(letter, value); 
			}else{
				letters.put(letter, 1.0); 
			}
		}
	}
	
	private char decrypteLetter(char readedChar) {
		// TODO: algorithm of substitution 
		return readedChar; 
	}

	private boolean isALetter(char readedChar) {
		if (readedChar==0){
			return false;  
		}
		return Character.isAlphabetic(readedChar);
	}

}
