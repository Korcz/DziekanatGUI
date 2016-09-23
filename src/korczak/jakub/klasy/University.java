package korczak.jakub.klasy;
//nazwa, miejscowosc, rokZalozenia(int), nazwaDziekana

import java.util.Scanner;

public class University
{
	private int id;
	private String name;
	private String place;
	private int establishYear;
	private String deansName;

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

	public int getEstablshYear()
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

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public University()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Podaj id");
		id = Integer.parseInt(sc.nextLine());
		System.out.println("Podaj nazwe");
		name = sc.nextLine();
		System.out.println("Podaj miejsowosc");
		place = sc.nextLine();
		System.out.println("Podaj rok zalozenia");
		establishYear = sc.nextInt();
		sc.nextLine();
		System.out.println("Podaj nazwisko dziekana");
		deansName = sc.nextLine();
	}

	public University(int id, String nazwa, String miejscowosc, int rokZalozenia, String nazwiskoDziekana)
	{
		this.id = id;
		this.name = nazwa;
		this.place = miejscowosc;
		this.establishYear = rokZalozenia;
		this.deansName = nazwiskoDziekana;
	}

	

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((place == null) ? 0 : place.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((deansName == null) ? 0 : deansName.hashCode());
		result = prime * result + establishYear;
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
		University other = (University) obj;
		if (id != other.id)
			return false;
		if (place == null)
		{
			if (other.place != null)
				return false;
		} else if (!place.equals(other.place))
			return false;
		if (name == null)
		{
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (deansName == null)
		{
			if (other.deansName != null)
				return false;
		} else if (!deansName.equals(other.deansName))
			return false;
		if (establishYear != other.establishYear)
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		String napis = id + " " + name + " " + place + " " + establishYear + " " + deansName;
		return napis;
	}
	
	

}
