package korczak.jakub.klasy;

public class Wpis
{
	private int id;
	private int idStudent;
	private int idUczelnia;
	
	
	public Wpis(int id, int idStudent, int idUczelnia)
	{
		this.id = id;
		this.idStudent = idStudent;
		this.idUczelnia = idUczelnia;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getIdStudent()
	{
		return idStudent;
	}
	public void setIdStudent(int idStudent)
	{
		this.idStudent = idStudent;
	}
	public int getIdUczelnia()
	{
		return idUczelnia;
	}
	public void setIdUczelnia(int idUczelnia)
	{
		this.idUczelnia = idUczelnia;
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + idStudent;
		result = prime * result + idUczelnia;
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wpis other = (Wpis) obj;
		if (id != other.id)
			return false;
		if (idStudent != other.idStudent)
			return false;
		if (idUczelnia != other.idUczelnia)
			return false;
		return true;
	}
	@Override
	public String toString()
	{
		return "Wpis [id=" + id + ", idStudent=" + idStudent + ", idUczelnia=" + idUczelnia + "]";
	}
	
	
}
