package korczak.jakub.klasy;

public class Laczona
{
	private int id;
	private String imie;
	private String nazwisko;
	private int wiek;
	private int rokStudiow;
	private String name;
	private String place;
	private int establishYear;
	private String deansName;
	
	public Laczona(int id, String imie, String nazwisko, int wiek, int rokStudiow, String name, String place,
			int establishYear, String deansName)
	{
		this.id = id;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.wiek = wiek;
		this.rokStudiow = rokStudiow;
		this.name = name;
		this.place = place;
		this.establishYear = establishYear;
		this.deansName = deansName;
	}
	
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getImie()
	{
		return imie;
	}
	public void setImie(String imie)
	{
		this.imie = imie;
	}
	public String getNazwisko()
	{
		return nazwisko;
	}
	public void setNazwisko(String nazwisko)
	{
		this.nazwisko = nazwisko;
	}
	public int getWiek()
	{
		return wiek;
	}
	public void setWiek(int wiek)
	{
		this.wiek = wiek;
	}
	public int getRokStudiow()
	{
		return rokStudiow;
	}
	public void setRokStudiow(int rokStudiow)
	{
		this.rokStudiow = rokStudiow;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getPlace()
	{
		return place;
	}
	public void setPlace(String place)
	{
		this.place = place;
	}
	public int getEstablishYear()
	{
		return establishYear;
	}
	public void setEstablishYear(int establishYear)
	{
		this.establishYear = establishYear;
	}
	public String getDeansName()
	{
		return deansName;
	}
	public void setDeansName(String deansName)
	{
		this.deansName = deansName;
	}
	
	
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deansName == null) ? 0 : deansName.hashCode());
		result = prime * result + establishYear;
		result = prime * result + id;
		result = prime * result + ((imie == null) ? 0 : imie.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nazwisko == null) ? 0 : nazwisko.hashCode());
		result = prime * result + ((place == null) ? 0 : place.hashCode());
		result = prime * result + rokStudiow;
		result = prime * result + wiek;
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
		Laczona other = (Laczona) obj;
		if (deansName == null)
		{
			if (other.deansName != null)
				return false;
		} else if (!deansName.equals(other.deansName))
			return false;
		if (establishYear != other.establishYear)
			return false;
		if (id != other.id)
			return false;
		if (imie == null)
		{
			if (other.imie != null)
				return false;
		} else if (!imie.equals(other.imie))
			return false;
		if (name == null)
		{
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nazwisko == null)
		{
			if (other.nazwisko != null)
				return false;
		} else if (!nazwisko.equals(other.nazwisko))
			return false;
		if (place == null)
		{
			if (other.place != null)
				return false;
		} else if (!place.equals(other.place))
			return false;
		if (rokStudiow != other.rokStudiow)
			return false;
		if (wiek != other.wiek)
			return false;
		return true;
	}
	@Override
	public String toString()
	{
		return "Laczona [id=" + id + ", imie=" + imie + ", nazwisko=" + nazwisko + ", wiek=" + wiek + ", rokStudiow="
				+ rokStudiow + ", name=" + name + ", place=" + place + ", establishYear=" + establishYear
				+ ", deansName=" + deansName + "]";
	}
	
	
}
