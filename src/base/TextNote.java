package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileWriter;



public class TextNote extends Note {

	private String content;
	private static final long serialVersionUID = 1L;
	
	public TextNote(String title,String content)
	{
		super(title);
		this.content=content;
	}
	
	@Override
	public String getContent()
	{
		return content;
	}
	
	@Override
	public void setContent(String c)
	{
		content = c;
	}
	
	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	
	private String getTextFromFile(String absolutePath) {
		String result = "";
		FileInputStream fis = null;
		InputStreamReader in = null;
	    try 
	    {
            fis = new FileInputStream(absolutePath);
            in = new InputStreamReader(fis);
            int i;
            while ((i =in.read())!=-1)
            {
            	result += (char)i;
            }
            in.close();
	    } 
	    catch (FileNotFoundException e)
	    {
	    	System.out.println("File ["+ absolutePath +"] not found");
	    }
	    catch (Exception e) 
	    {
	    	System.out.println("Error");
	        e.printStackTrace();
	    }

		return result;
	}
	public void exportTextToFile(String pathFolder) 
	{
		
		FileWriter f1 = null;
		BufferedWriter output = null;
		if (pathFolder =="")
		{
			pathFolder=".";
		}
		File file = new File( pathFolder + File.separator + this.getTitle().replaceAll(" ","_") + ".txt");
		//System.out.println("=============");
		//System.out.println(file.getAbsolutePath());
		//System.out.println("=============");
		try
		{
			f1 = new FileWriter(file);
			output = new BufferedWriter(f1);
			output.write(this.content);
			//System.out.println(this.content);
			output.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
