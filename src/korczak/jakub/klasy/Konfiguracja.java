package korczak.jakub.klasy;

public class Konfiguracja
{
	private static boolean isAdmin;

	public static boolean isAdmin()
	{
		return isAdmin;
	}

	public static void setAdmin(boolean isAdmin)
	{
		Konfiguracja.isAdmin = isAdmin;
	}
	
	
}
