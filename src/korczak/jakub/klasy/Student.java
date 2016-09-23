package korczak.jakub.klasy;
//imie, nazwisko, wiek, rokStudiow

import java.util.Scanner;

public class Student
{
	private int id;
	private String imie;
	private String nazwisko;
	private int wiek;
	private int rokStudiow;
	
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
	
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public Student() 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Podaj id");
		id = Integer.parseInt(sc.nextLine());
		System.out.println("Podaj imie");
		imie = sc.nextLine();
		System.out.println("Podaj naziwisko");
		nazwisko = sc.nextLine();
		System.out.println("Podaj wiek");
		wiek = sc.nextInt();
		sc.nextLine();
		System.out.println("Podaj rok studiów");
		rokStudiow = Integer.parseInt(sc.nextLine());		
	}
	
	public Student (int id, String imie, String nazwisko, int wiek, int rokStudiow)
	{
		this.id = id;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.wiek = wiek;
		this.rokStudiow = rokStudiow;
	}
	
	
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((imie == null) ? 0 : imie.hashCode());
		result = prime * result + ((nazwisko == null) ? 0 : nazwisko.hashCode());
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
		Student other = (Student) obj;
		if (id != other.id)
			return false;
		if (imie == null)
		{
			if (other.imie != null)
				return false;
		} else if (!imie.equals(other.imie))
			return false;
		if (nazwisko == null)
		{
			if (other.nazwisko != null)
				return false;
		} else if (!nazwisko.equals(other.nazwisko))
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
		String napis = id + " " + imie + " " + nazwisko + " " + wiek + " " + rokStudiow;
		return napis;
	}
}
