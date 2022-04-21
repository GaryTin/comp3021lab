package base;
import java.util.Date;
import java.util.Objects;
import java.io.Serializable;

public class Note implements Comparable<Note>,Serializable{
	
	private Date date;
	private String title;
	private static final long serialVersionUID = 1L;
	
	public Note(String title)
	{
		this.title = title;
		this.date = new Date(System.currentTimeMillis());
	}
	
	public String getTitle()
	{
		return this.title;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Note other = (Note) obj;
		return Objects.equals(title, other.title);
	}
	
	@Override
	public int compareTo(Note o)
	{
		if (this.date.compareTo(o.date) >0  )
		{
			return 1;
		}
		else if(this.date.compareTo(o.date) < 0)
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}
	
	
	public String toString()
	{
		return date.toString()+"\t"+title;
	}
	
	public String getContent()
	{
		return null;
	}
	public void setContent(String c)
	{
		
	}





}
