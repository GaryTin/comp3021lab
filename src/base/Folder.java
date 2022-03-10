package base;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.io.Serializable;

public class Folder implements Comparable<Folder>,Serializable{
	private ArrayList<Note> notes;
	private String name;

	public Folder(String name) {
	
		notes = new ArrayList<Note>();
		this.name = name;

	}

	public void addNote(Note note)
	{
		notes.add(note);
	}

	public String getName()
	{
		
		return this.name;
	}

	public ArrayList<Note> getNotes() 
	{
		
		return notes;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		return Objects.equals(name, other.name);
	}
	
	@Override
	public String toString()
	{
		int nText = 0;
		int nImage =0;
		for (Note note:notes)
		{
			if (note instanceof TextNote)
			{
				nText++;
			}
			else
			{
				nImage++;
			}
				
		}
		
		return name + ":" + nText + ":" + nImage;

	}
	
	public void sortNotes()
	{
		Collections.sort(notes);
	}
	
	@Override
	public int compareTo(Folder o)
	{
		if (this.name.compareTo(o.name) >0  )
		{
			return 1;
		}
		else if(this.name.compareTo(o.name) < 0)
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}
	
	public List<Note> searchNotes(String keywords)
	{
		List<Note> result = new ArrayList<Note>();
		keywords = keywords.toUpperCase();
		String[] temp_keyword_list = keywords.split(" ");
		int index = 0;
		List<String> keyword_list = new ArrayList<String>();
		while (index < temp_keyword_list.length)
		{
			if (index < temp_keyword_list.length-2 && temp_keyword_list[index+1].equals("OR"))
			{
				keyword_list.add("o "+temp_keyword_list[index]+" "+temp_keyword_list[index+2]);
				index = index +3;
			}
			else
			{
				keyword_list.add(temp_keyword_list[index]);
				index = index +1;
			}
		}
		//System.out.println(keyword_list);
		for (Note note:notes)
		{
			//System.out.println(note.getTitle()+"\t"+note.getContent());
			boolean valid = true;
			for (String keyword:keyword_list)
			{
				if (keyword.substring(0,1).equals("o"))
                {
                    String[] sub_keyword_list = keyword.split(" ");
                    //for (String t:sub_keyword_list)
                    //	System.out.println(t);
                    if (!(note.getTitle().toUpperCase().contains(sub_keyword_list[1]) || note.getTitle().toUpperCase().contains(sub_keyword_list[2]) || (note.getContent()!=null && (note.getContent().toUpperCase().contains(sub_keyword_list[1]) || note.getContent().toUpperCase().contains(sub_keyword_list[2])))) )
                    {
                    	valid = false;
                    	break;
                    }
                    
                }
                else
                {
                    if (!(note.getTitle().toUpperCase().contains(keyword)||(note.getContent()!=null && note.getContent().toUpperCase().contains(keyword))) )
                    {
                    	valid = false;
                    	break;
                    }
                }
			}
			//System.out.println(valid);
			if (valid)
			{
				result.add(note);
			}
				
		}
		
		
		return result;
		
	}
}
