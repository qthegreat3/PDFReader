package com.harquinTechnologies.PdfReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PdfReader {

	public static Logger logger = LoggerFactory.getLogger(PdfReader.class);
	
	public static void main (String[] args)
	{
		//Find all pdfs in the same directory
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		
		File currentPath = new File(s);
		
		System.out.println("Current Path: " + currentPath.getAbsolutePath());
		
		if(currentPath.isDirectory())
		{
			File[] listOfFileInDirectory = currentPath.listFiles();
			
			for (File file : listOfFileInDirectory)
			{
				if(FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("pdf"))
				{
					System.out.println(file.getName() + " is a pdf file.");
					printOutPdf(file.getName());
				}
			}
		}
	}
	
	public static boolean printOutPdf(String fileName)
	{
		//Open Pdf
		try (PDDocument document = PDDocument.load(new File(fileName));)
		{
			PDFTextStripper textStripper = new PDFTextStripper();
			textStripper.setSortByPosition(true);
			
			//Read Contents
			String text = textStripper.getText(document);
			//Print contents to screen'
			System.out.println("Text: " + text);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			 return false;
		}
		
		return true;
	}
}
