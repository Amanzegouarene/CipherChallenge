package fr.amanzegouarene.ali.cipher.challenge.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {
	
	private final static String END_OF_LINE = "\n";

	/**
	 * parseFile: parse the given inputFile buy its path name and return the text into a StringBuilder object.
	 * @param inputFileName
	 * @return StringBuilder which contains the inputFile text.
	 * @throws IOException 
	 */
	public static StringBuilder parseFile(String inputFileName) throws IOException {
		String readed="";
		StringBuilder readedLine = new StringBuilder(); 
		try {
			if(inputFileName == null || inputFileName.equalsIgnoreCase("")){
				System.out.println("The input file name is null or empty: "+inputFileName);
				return null; 
			}
			File inputFile = new File(inputFileName);
			if(!inputFile.exists() || !inputFile.isFile()){
				System.out.println("The input file is not exit or is corempted: "+inputFileName);
				return null;
			}
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
}
