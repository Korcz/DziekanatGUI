package korczak.jakub.bazadanych;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sqlite.SQLiteConfig;

import korczak.jakub.klasy.Laczona;
import korczak.jakub.klasy.Login;
import korczak.jakub.klasy.Student;
import korczak.jakub.klasy.University;
import korczak.jakub.klasy.Wpis;

public class BazaDanych
{
	//stale przechowujace parametry polaczenia z baza danych
	private static String DRV = "org.sqlite.JDBC"; //nazwa klasy do obslugi sqlite
	private static String DB = "jdbc:sqlite:Dziekanat.db"; //nazwa bazy danych czyli Dziekanat.db
	
	private static Connection conn; //bedziemy uzywac tej zmiennej donawiazania polaczenia z baza danych
	private static Statement stat; //bedzie sluzyl do realizacji zapytan do bazy danych
	
	public static void connect()
	{
		try
		{
			Class.forName(DRV); //zaladowanie odpowiedniej klasy do zarzadzania sqlite
			//sqlite ma domyslnie wylaczona obsluge kluczy obcych
			//trzeba ustawic tak zeby to bylo zalaczone
			SQLiteConfig conf = new SQLiteConfig();
			conf.enforceForeignKeys(true);
			conn = DriverManager.getConnection(DB, conf.toProperties()); //metoda ustanawia polaczenie z baza danych o nazwie podanej jako argument, jezeli bazy nie bylo to tworzy nowa
			//polaczenie z baza danych w naszej aplikacji bedzie reprezentowane przez conn
			stat = conn.createStatement(); //metoda zwraca referencje do stat, za pomoca ktorego bedziemy wysylac zapytania do bazy danych
		} catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		} 
	}
	
	
	public static void createTables()
	{
		String createStudent = "CREATE TABLE IF NOT EXISTS Student (id INTEGER PRIMARY KEY AUTOINCREMENT, imie VARCHAR(50) NOT NULL, "
				             + "nazwisko VARCHAR(50)NOT NULL, wiek INTEGER NOT NULL, rokStudiow INTEGER NOT NULL);";
		
		String createUczelnia = "CREATE TABLE IF NOT EXISTS Uczelnia (id INTEGER PRIMARY KEY AUTOINCREMENT, nazwa VARCHAR(50) NOT NULL, "
	             + "miejscowosc VARCHAR(50)NOT NULL, rokZalozenia INTEGER NOT NULL, nazwiskoDziekana VARCHAR(50)NOT NULL);";
		
		String createWpis = "CREATE TABLE IF NOT EXISTS Wpis (id INTEGER PRIMARY KEY AUTOINCREMENT, idStudent INTEGER NOT NULL , "
				+ "idUczelnia INTEGER NOT NULL, FOREIGN KEY (idStudent) REFERENCES Student(id) ON DELETE CASCADE ON UPDATE CASCADE,"
				+ "FOREIGN KEY (idUczelnia) REFERENCES Uczelnia(id) ON DELETE CASCADE ON UPDATE CASCADE);";
		String createLogin = "CREATE TABLE IF NOT EXISTS Login (id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "username VARCHAR(50) NOT NULL, password VARCHAR(50) NOT NULL, role VARCHAR(50) NOT NULL); ";
		
		
		//tabela Logowanie - pol id, username, passsword, role
		
		try
		{
			stat.execute(createStudent);
			stat.execute(createUczelnia);
			stat.execute(createWpis);
			stat.execute(createLogin);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void insertStudent(String imie, String nazwisko, int wiek, int rokStudiow)
	{
		String insertStudent = "INSERT INTO Student (imie, nazwisko, wiek, rokStudiow) VALUES (?, ?, ?, ?);";
		PreparedStatement ps;
		try
		{
			ps = conn.prepareStatement(insertStudent);
			ps.setString(1, imie); //najpierw piszesz numer znaku zapytania - indeksowanie znakow zapytania zaczyna sie od 1
			ps.setString(2,  nazwisko);
			ps.setInt(3, wiek);
			ps.setInt(4, rokStudiow);
			ps.execute();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void updateStudent(int id, String imie, String nazwisko, int wiek, int rokStudiow)
	{
		String updateS = "UPDATE Student SET imie = ?, nazwisko = ?, wiek = ?, rokStudiow = ? WHERE id = ?;";
		PreparedStatement ps;
		try
		{
			ps = conn.prepareStatement(updateS);
			ps.setString(1, imie);
			ps.setString(2, nazwisko);
			ps.setInt(3, wiek);
			ps.setInt(4, rokStudiow);
			ps.setInt(5, id);
			ps.execute();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void updateStudentImie(int id, String imie)
	{
		String updateSI = "UPDATE Student SET imie = ? WHERE id = ?;";
		PreparedStatement ps;
		
		try
		{
			ps = conn.prepareStatement(updateSI);
			ps.setString(1, imie);
			ps.setInt(2, id);
			ps.execute();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateStudentNawisko(int id, String nazwisko)
	{
		String updateSN = "UPDATE Student SET nazwisko = ? WHERE id = ?;";
		PreparedStatement ps;
		
		try
		{
			ps = conn.prepareStatement(updateSN);
			ps.setString(1, nazwisko);
			ps.setInt(2, id);
			ps.execute();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateStudentWiek(int id, int wiek)
	{
		String updateSW = "UPDATE Student SET wiek = ? WHERE id = ?;";
		PreparedStatement ps;
		
		try
		{
			ps = conn.prepareStatement(updateSW);
			ps.setInt(1, wiek);
			ps.setInt(2, id);
			ps.execute();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateStudentRokStudiow(int id, int rokStudiow)
	{
		String updateSR = "UPDATE Student SET rokStudiow = ? WHERE id = ?;";
		PreparedStatement ps;
		
		try
		{
			ps = conn.prepareStatement(updateSR);
			ps.setInt(1, rokStudiow);
			ps.setInt(2, id);
			ps.execute();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deleteStudent(int id)
	{
		String deleteS = "DELETE FROM Student WHERE id = ?;";
		PreparedStatement ps;
		
		try
		{
			ps = conn.prepareStatement(deleteS);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void deleteStudentImie(String imie)
	{
		String deleteSI = "DELETE FROM Student WHERE imie = ?;";
		PreparedStatement ps;
		
		try
		{
			ps = conn.prepareStatement(deleteSI);
			ps.setString(1, imie);
			ps.execute();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static List<Student> selectStudent()
	{
		List<Student> lista = new ArrayList<>();
		String selectS = "SELECT * FROM Student";
		try
		{
			int id, rokStudiow, wiek;
			String imie, nazwisko;
			ResultSet rs = stat.executeQuery(selectS); //do obiektu klasy ResultSet pobieram wynik zapytania w nawiasie
			while(rs.next())
			{
				id = rs.getInt("id");
				imie = rs.getString("imie");
				nazwisko = rs.getString("nazwisko");
				wiek = rs.getInt("wiek");
				rokStudiow = rs.getInt("rokStudiow");
				lista.add(new Student(id, imie, nazwisko, wiek, rokStudiow));
			}
			return lista;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	
	}
	
	public static Student selectOneStudentById(Integer id1)
	{
		try
		{
			String selectS = "SELECT * FROM Student WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(selectS);
			ps.setInt(1, id1);
		
			int id = 0, rokStudiow = 0, wiek = 0;
			String imie = "", nazwisko = "";
			ResultSet rs = ps.executeQuery();
			
			id = rs.getInt("id");
			imie = rs.getString("imie");
			nazwisko = rs.getString("nazwisko");
			wiek = rs.getInt("wiek");
			rokStudiow = rs.getInt("rokStudiow");	
			
			return new Student(id, imie, nazwisko, wiek, rokStudiow);
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	
	}
	
	public static List<Integer> selectStudentIds()
	{
		List<Integer> lista = new ArrayList<>();
		String selectS = "SELECT id FROM Student";
		try
		{
			ResultSet rs = stat.executeQuery(selectS); //do obiektu klasy ResultSet pobieram wynik zapytania w nawiasie
			while(rs.next())
			{
				lista.add(rs.getInt("id"));
			}
			return lista;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	
	}
	
	public static void insertUczelnia(String nazwa, String miejscowosc, int rokZalozenia, String nazwiskoDziekana)
	{
		String insertUczelnia = "INSERT INTO Uczelnia (nazwa, miejscowosc, rokZalozenia, nazwiskoDziekana) VALUES (?, ?, ?, ?);";
		PreparedStatement ps;
		
		try
		{
			ps = conn.prepareStatement(insertUczelnia);
			ps.setString(1, nazwa);
			ps.setString(2, miejscowosc);
			ps.setInt(3, rokZalozenia);
			ps.setString(4, nazwiskoDziekana);
			ps.execute();
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void updateUczelnia(int id, String nazwa, String miejscowosc, int rokZalozenia, String nazwiskoDziekana)
	{
		String updateU = "UPDATE Uczelnia SET nazwa = ?, miejscowosc = ?, rokZalozenia = ?, nazwiskoDziekana = ? WHERE id = ?;";
		PreparedStatement ps;
		
		try
		{
			ps = conn.prepareStatement(updateU);
			ps.setString(1, nazwa);
			ps.setString(2, miejscowosc);
			ps.setInt(3, rokZalozenia);
			ps.setString(4, nazwiskoDziekana);
			ps.setInt(5, id);
			ps.execute();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateUczelniaNazwa(int id, String nazwa)
	{
		String updateUN = "UPDATE Uczelnia SET nazwa = ? WHERE id = ?;";
		PreparedStatement ps;
		
		try
		{
			ps = conn.prepareStatement(updateUN);
			ps.setString(1, nazwa);
			ps.setInt(2, id);
			ps.execute();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateUczelniaMiejscowosc(int id, String miejscowosc)
	{
		String updateUM = "UPDATE Uczelnia SET miejscowosc = ? WHERE id = ?;";
		PreparedStatement ps;
		
		try
		{
			ps = conn.prepareStatement(updateUM);
			ps.setString(1, miejscowosc);
			ps.setInt(2, id);
			ps.execute();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateUczelniaRokZalozenia(int id, int rokZalozenia)
	{
		String updateUR = "UPDATE Uczelnia SET nazwa = ? WHERE id = ?;";
		PreparedStatement ps;
		
		try
		{
			ps = conn.prepareStatement(updateUR);
			ps.setInt(1, rokZalozenia);
			ps.setInt(2, id);
			ps.execute();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateUczelniaNazwiskoDziekana(int id, String nazwiskoDziekana)
	{
		String updateUNd = "UPDATE Uczelnia SET nazwiskoDziekana = ? WHERE id = ?;";
		PreparedStatement ps;
		
		try
		{
			ps = conn.prepareStatement(updateUNd);
			ps.setString(1, nazwiskoDziekana);
			ps.setInt(2, id);
			ps.execute();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deleteUczelnia(int id)
	{
		String deleteU = "DELETE FROM Uczelnia WHERE id = ?;";
		PreparedStatement ps;
		
		try
		{
			ps = conn.prepareStatement(deleteU);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void deleteUczelniaNazwa(String nazwa)
	{
		String deleteUN = "DELETE FROM Uczelnia WHERE nazwa = ?;";
		PreparedStatement ps;
		
		try
		{
			ps = conn.prepareStatement(deleteUN);
			ps.setString(1, nazwa);
			ps.execute();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static List<University> selectUczelnia()
	{
		List<University> lista = new ArrayList<>();
		String selectU = "SELECT * FROM Uczelnia";
		try
		{
			int id, rokZalozenia;
			String nazwa, miejscowosc, nazwiskoDziekana;
			ResultSet rs = stat.executeQuery(selectU);
			while(rs.next())
			{
				id = rs.getInt("id");
				nazwa = rs.getString("nazwa");
				miejscowosc = rs.getString("miejscowosc");
				rokZalozenia = rs.getInt("rokZalozenia");
				nazwiskoDziekana = rs.getString("nazwiskoDziekana");
				lista.add(new University(id, nazwa, miejscowosc, rokZalozenia, nazwiskoDziekana));
			}
			return lista;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	
	}
	
	public static University selectOneUniversityById(Integer id1)
	{
		try
		{
			String selectU = "SELECT *FROM Uczelnia WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(selectU);
			ps.setInt(1, id1);
			
			int id, rokZalozenia;
			String nazwa, miejscowosc, nazwiskoDziekana;
			ResultSet rs = ps.executeQuery();
			
			
			id = rs.getInt("id");
			nazwa = rs.getString("nazwa");
			miejscowosc = rs.getString("miejscowosc");
			nazwiskoDziekana = rs.getString("nazwiskoDziekana");
			rokZalozenia = rs.getInt("rokZalozenia");
			return new University(id, nazwa, miejscowosc, rokZalozenia,nazwiskoDziekana);
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	
	}

	public static List<Integer> selectUniversityIds()
	{
		List<Integer> lista = new ArrayList<>();
		String selectU = "SELECT id FROM Uczelnia";
		try
		{
			ResultSet rs = stat.executeQuery(selectU); //do obiektu klasy ResultSet pobieram wynik zapytania w nawiasie
			while(rs.next())
			{
				lista.add(rs.getInt("id"));
			}
			return lista;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	
	}
	
	public static double avgWiekStudent()
	{
		String avgWiek = "Select AVG(wiek) FROM Student;"; //SELECT AVG(Price) AS PriceAverage FROM Products;
		try
		{
			ResultSet rs = stat.executeQuery(avgWiek);
			//zwracam tylko jedna liczbe, to w tym momencie nie potrzebuje while, nie potrzebuje zadnej petli
			//tylko moge sprawdzic czy mam dobrze obliczona dana za pomoca ifa
			if (rs.next())
			{
				return rs.getDouble(1);
			}
			return -1;
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return -1;
		}
		
		
	}
	
	public static Student najstarszStudent()
	{
		String najstarszStudent = "Select * FROM Student ORDER BY wiek DESC LIMIT 1";;
		 //SELECT * FROM Customers WHERE ((SELECT Max(CustomerID) From Customers)=CustomerId);
 
 		try
		{
			ResultSet rs = stat.executeQuery(najstarszStudent);
			
			int id = 0, rokStudiow = 0, wiek = 0;
			String imie = "", nazwisko = "";
			
			id = rs.getInt("id");
			imie = rs.getString("imie");
			nazwisko = rs.getString("nazwisko");
			wiek = rs.getInt("wiek");
			rokStudiow = rs.getInt("rokStudiow");	
			
			return new Student(id, imie, nazwisko, wiek, rokStudiow);
			
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static Student najmlodszyStudent()
	{
		String najmlodszyStudent = "Select * FROM Student ORDER BY wiek ASC LIMIT 1";
		
		try
		{
			ResultSet rs = stat.executeQuery(najmlodszyStudent);
			
			int id = 0, rokStudiow = 0, wiek = 0;
			String imie = "", nazwisko = "";
			
			id = rs.getInt("id");
			imie = rs.getString("imie");
			nazwisko = rs.getString("nazwisko");
			wiek = rs.getInt("wiek");
			rokStudiow = rs.getInt("rokStudiow");	
			
			return new Student(id, imie, nazwisko, wiek, rokStudiow);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static University najstarszaUczelnia()
	{
		//String najstarszUczelnia = "Select * FROM Uczelnia WHERE ((SELECT MIN(rokZalozenia) FROM Uczelnia) = rokZalozenia);";
		String najstarszaUczelnia = "Select * FROM Uczelnia ORDER BY rokZalozenia ASC LIMIT 1";
		
		try
		{
			ResultSet rs = stat.executeQuery(najstarszaUczelnia);
			
			int id = 0, rokZalozenia = 0;
			String nazwa = "", miejscowosc = "", nazwiskoDziekana;
			
			id = rs.getInt("id");
			nazwa = rs.getString("nazwa");
			miejscowosc = rs.getString("miejscowosc");
			rokZalozenia = rs.getInt("rokZalozenia");
			nazwiskoDziekana = rs.getString("nazwiskoDziekana");	
			
			return new University(id, nazwa, miejscowosc, rokZalozenia, nazwiskoDziekana);
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static University najmlodszaUczelnia()
	{
		//String najmlodszaUczelnia = "Select * FROM Uczelnia WHERE ((SELECT MIN(rokZalozenia) FROM Uczelnia) = rokZalozenia);";
		String najmlodszaUczelnia = "Select * FROM Uczelnia ORDER BY rokZalozenia DESC LIMIT 1";
		
		try
		{
			ResultSet rs = stat.executeQuery(najmlodszaUczelnia);
			
			int id = 0, rokZalozenia = 0;
			String nazwa = "", miejscowosc = "", nazwiskoDziekana;
			
			id = rs.getInt("id");
			nazwa = rs.getString("nazwa");
			miejscowosc = rs.getString("miejscowosc");
			rokZalozenia = rs.getInt("rokZalozenia");
			nazwiskoDziekana = rs.getString("nazwiskoDziekana");	
			
			return new University(id, nazwa, miejscowosc, rokZalozenia, nazwiskoDziekana);

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static Map<String, Integer> miastoIleStudentow()
	{
		Map<String, Integer> miastaMap = new HashMap<>();
		//List<String> listaMiast = new ArrayList<>();
		String innerJoinSQL = 		
				"select "
				+ "count(Student.id), miejscowosc "
				+ "from "
				+ "Student inner join Wpis "
				+ "on Student.id = Wpis.idStudent "
				+ "inner join "
				+ "Uczelnia on Wpis.idUczelnia = Uczelnia.id "
				+ "group by miejscowosc  ;";
		
		
		try
		{
			ResultSet rs1 = stat.executeQuery(innerJoinSQL);
			while (rs1.next())
			{
				miastaMap.put(rs1.getString(2), rs1.getInt(1)); //key musi byc unikatowy!
				//listaMiast.add(rs1.getString(2) + " - " + rs1.getInt(1));
			}
			return miastaMap;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static int najczsciejUczeszczanyRocznik()
	{
		List<Integer> roczniki = new ArrayList<>();
		Map<Integer, Integer> rocznikiMap = new HashMap<>();
		
		String innerJoinSQL1 = 		
				"select "
				+ "count(Student.id), rokStudiow "
				+ "from "
				+ "Student inner join Wpis "
				+ "on Student.id = Wpis.idStudent "
				+ "inner join "
				+ "Uczelnia on Wpis.idUczelnia = Uczelnia.id  "
				+ "where Student.rokStudiow = 1 ";
		String innerJoinSQL2 = 
				"select "
				+ "count(Student.id), rokStudiow "
				+ "from "
				+ "Student inner join Wpis "
				+ "on Student.id = Wpis.idStudent "
				+ "inner join "
				+ "Uczelnia on Wpis.idUczelnia = Uczelnia.id  "
				+ "where Student.rokStudiow = 2 ";
		String innerJoinSQL3 = 
				"select "
				+ "count(Student.id), rokStudiow "
				+ "from "
				+ "Student inner join Wpis "
				+ "on Student.id = Wpis.idStudent "
				+ "inner join "
				+ "Uczelnia on Wpis.idUczelnia = Uczelnia.id  "
				+ "where Student.rokStudiow = 3 ";
		String innerJoinSQL4 = 
				"select "
				+ "count(Student.id), rokStudiow "
				+ "from "
				+ "Student inner join Wpis "
				+ "on Student.id = Wpis.idStudent "
				+ "inner join "
				+ "Uczelnia on Wpis.idUczelnia = Uczelnia.id  "
				+ "where Student.rokStudiow = 4 ";
		String innerJoinSQL5 = 
				"select "
				+ "count(Student.id), rokStudiow "
				+ "from "
				+ "Student inner join Wpis "
				+ "on Student.id = Wpis.idStudent "
				+ "inner join "
				+ "Uczelnia on Wpis.idUczelnia = Uczelnia.id  "
				+ "where Student.rokStudiow = 5 ";



		//robisz mape -> ilosc studentow jako klucz a jako wartosc rocznik 
		//i robisz mape posortowana, mapa sortuje po kluczach
		//i automatycznie dostajesz posortowane ilosci studentow bierzesz max
		//i jego wartosc to rocznik
		
		try
		{
			ResultSet rs1 = stat.executeQuery(innerJoinSQL1);
			if (rs1.next())
			{
				roczniki.add(rs1.getInt(1));
				rocznikiMap.put(rs1.getInt(1), rs1.getInt(2));
			}
			ResultSet rs2 = stat.executeQuery(innerJoinSQL2);
			if (rs2.next())
			{
				roczniki.add(rs2.getInt(1));
				rocznikiMap.put(rs2.getInt(1), rs2.getInt(2));
			}
			ResultSet rs3 = stat.executeQuery(innerJoinSQL3);
			if (rs3.next())
			{
				roczniki.add(rs3.getInt(1));
				rocznikiMap.put(rs3.getInt(1), rs3.getInt(2));
			}
			ResultSet rs4 = stat.executeQuery(innerJoinSQL4);
			if (rs4.next())
			{
				roczniki.add(rs4.getInt(1));
				rocznikiMap.put(rs4.getInt(1), rs4.getInt(2));
			}
			ResultSet rs5 = stat.executeQuery(innerJoinSQL5);
			if (rs5.next())
			{
				roczniki.add(rs5.getInt(1));
				rocznikiMap.put(rs5.getInt(1), rs5.getInt(2));
			}
			
			//pozniej znajdujesz w liscie max - na podstawie znalezionego max
			//podaj rocznik
			
			
			List keys = new ArrayList(rocznikiMap.keySet());
			Collections.sort(keys);
			
			/*Set<Integer> keys2 = rocznikiMap.keySet();
			for (Integer k : keys2)
			{
				System.out.println("KEY = " + k  + " VALUE = " + rocznikiMap.get(k));
			}*/
			
			int max2 = Collections.max(roczniki);	
			int max = rocznikiMap.get(max2);			
			return max;
			
			
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public static void insertWpis(int idStudent, int idUczelnia)
	{
		String insertWpis = "INSERT INTO Wpis (idStudent, idUczelnia) VALUES (?, ?);";
		PreparedStatement ps;
		try
		{
			ps = conn.prepareStatement(insertWpis);
			ps.setInt(1, idStudent);
			ps.setInt(2, idUczelnia);
			ps.execute();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void updateWpis(int id, int idStudent, int idUczelnia)
	{
		String updateW = "UPDATE Wpis SET idStudent = ?, idUczelnia = ? WHERE id = ?;";
		PreparedStatement ps;
		
		try
		{
			ps = conn.prepareStatement(updateW);
			ps.setInt(1, idStudent);
			ps.setInt(2, idUczelnia);
			ps.setInt(3, id);
			ps.execute();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deleteWpis(int id)
	{
		String deleteW = "DELETE FROM Wpis WHERE id = ?;";
		PreparedStatement ps;
		
		try
		{
			ps = conn.prepareStatement(deleteW);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	public static List<Wpis> selectWpis()
	{
		List<Wpis> lista = new ArrayList<>();
		String selectW = "SELECT * FROM Wpis";
		try
		{
			int id, idStudent, idUczelnia;
			ResultSet rs = stat.executeQuery(selectW);
			while(rs.next())
			{
				id = rs.getInt("id");
				idStudent = rs.getInt("idStudent");
				idUczelnia = rs.getInt("idUczelnia");
				lista.add(new Wpis(id, idStudent, idUczelnia));
			}
			return lista;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	
	}
	

	//tabela Logowanie - pol id, username, passsword, role
	public static void insertLogin(String username, String password, String role)
	{
		String insertLogin = "INSERT INTO Login (username, password, role) VALUES (?, ?, ?);";
		PreparedStatement ps;
		try
		{
			ps = conn.prepareStatement(insertLogin);
			ps.setString(1, username); //najpierw piszesz numer znaku zapytania - indeksowanie znakow zapytania zaczyna sie od 1
			ps.setString(2,  password);
			ps.setString(3, role);
			ps.execute();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static List<Login> selectLogin()
	{
		List<Login> lista = new ArrayList<>();
		String selectL = "SELECT * FROM Login";
		try
		{
			int id;
			String username ,password, role;
			ResultSet rs = stat.executeQuery(selectL);
			while(rs.next())
			{
				id = rs.getInt("id");
				username = rs.getString("username");
				password = rs.getString("password");
				role = rs.getString("role");
				lista.add(new Login(id, username, password, role));
			}
			return lista;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	
	}
	
	
	public static void updateLogin(int id, String username, String password, String role)
	{
		String updateL = "UPDATE Login SET username = ?, password = ?, role = ?, WHERE id = ?;";
		PreparedStatement ps;
		try
		{
			ps = conn.prepareStatement(updateL);
			ps.setString(1, username); //najpierw piszesz numer znaku zapytania - indeksowanie znakow zapytania zaczyna sie od 1
			ps.setString(2,  password);
			ps.setString(3, role);
			ps.setInt(4, id);
			ps.execute();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void deleteLogin(int id)
	{
		String deleteL = "DELETE FROM Login WHERE id = ?;";
		PreparedStatement ps;
		
		try
		{
			ps = conn.prepareStatement(deleteL);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


	public static List<Laczona> innerJoin()
	{
		
		/*
		select *
		from
		    tableA a
		        inner join
		    tableB b
		        on a.common = b.common
		        inner join 
		    TableC c
		        on b.common = c.common
		*/
		
		String innerJoinSQL = 
		"select "
		+ "Wpis.id, Student.imie, Student.nazwisko, Student.wiek, Student.rokStudiow, Uczelnia.nazwa, "
		+ "Uczelnia.miejscowosc, Uczelnia.rokZalozenia, Uczelnia.nazwiskoDziekana "
		+ "from "
		+ "Student inner join Wpis "
		+ "on Student.id = Wpis.idStudent "
		+ "inner join "
		+ "Uczelnia on Wpis.idUczelnia = Uczelnia.id;";
		
		int id, wiek,rokStudiow, rokZalozenia;
		String imie, nazwisko, nazwa, miejscowosc, nazwiskoDziekana;
		
		List<Laczona> lista = new ArrayList<>();
		
		try
		{
			ResultSet rs = stat.executeQuery(innerJoinSQL);
			while(rs.next())
			{
				id = rs.getInt(1);
				imie = rs.getString(2);
				nazwisko = rs.getString(3);
				wiek = rs.getInt(4);
				rokStudiow = rs.getInt(5);
				nazwa = rs.getString(6);
				miejscowosc = rs.getString(7);
				rokZalozenia = rs.getInt(8);
				nazwiskoDziekana = rs.getString(9);
				
				lista.add(new Laczona(id, imie, nazwisko, wiek, rokStudiow, nazwa, miejscowosc, rokZalozenia, nazwiskoDziekana));			
			}
			return lista;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	public static List<String> selectUczelniaNazwa()
	{
		List<String> listaNazw = new ArrayList<>();
		String selectU = "SELECT DISTINCT nazwa FROM Uczelnia";
		try
		{
			String nazwa;
			ResultSet rs = stat.executeQuery(selectU);
			while(rs.next())
			{	
				nazwa = rs.getString("nazwa");
				listaNazw.add(nazwa);
			}
			return listaNazw;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}
	
	
	public static List<String> selectNazwislko()
	{
		List<String> listaNazw = new ArrayList<>();
		String selectS = "SELECT DISTINCT nazwisko FROM Student";
		try
		{
			String nazwa;
			ResultSet rs = stat.executeQuery(selectS);
			while(rs.next())
			{	
				nazwa = rs.getString("nazwisko");
				listaNazw.add(nazwa);
			}
			return listaNazw;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}
	
	public static List<String> selectImie()
	{
		List<String> listaNazw = new ArrayList<>();
		String selectS = "SELECT DISTINCT imie FROM Student";
		try
		{
			String nazwa;
			ResultSet rs = stat.executeQuery(selectS);
			while(rs.next())
			{	
				nazwa = rs.getString("imie");
				listaNazw.add(nazwa);
			}
			return listaNazw;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}
	
	public static List<Integer> selectRokStudiow()
	{
		List<Integer> listaRok = new ArrayList<>();
		String selectU = "SELECT DISTINCT rokStudiow FROM Student";
		try
		{
			int rokStudiow;
			ResultSet rs = stat.executeQuery(selectU);
			while(rs.next())
			{	
				rokStudiow = rs.getInt("rokStudiow");
				listaRok.add(rokStudiow);
			}
			return listaRok;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}
	
	public static List<Integer> selectRokStudiowGreaterThan(int rok)
	{
		List<Integer> listaRok = new ArrayList<>();
		String selectU = "SELECT DISTINCT rokStudiow FROM Student WHERE rokStudiow >= ?";
		try
		{
			int rokStudiow;
			
			PreparedStatement prep = conn.prepareStatement(selectU);
			prep.setInt(1, rok);
			
			ResultSet rs = prep.executeQuery(selectU);
			while(rs.next())
			{	
				rokStudiow = rs.getInt("rokStudiow");
				listaRok.add(rokStudiow);
			}
			return listaRok;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}

	public static List<Integer> selectWiek()
	{
		List<Integer> listaRok = new ArrayList<>();
		String selectU = "SELECT DISTINCT wiek FROM Student";
		try
		{
			int wiek;
			ResultSet rs = stat.executeQuery(selectU);
			while(rs.next())
			{	
				wiek = rs.getInt("wiek");
				listaRok.add(wiek);
			}
			return listaRok;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}
	
	public static List<Integer> selectWiekGreaterThan(int rok)
	{
		List<Integer> listaRok = new ArrayList<>();
		String selectW = "SELECT DISTINCT wiek FROM Student WHERE wiek >= ?";
		try
		{
			int wiek;
			
			PreparedStatement prep = conn.prepareStatement(selectW);
			prep.setInt(1, rok);
			
			ResultSet rs = prep.executeQuery(selectW);
			while(rs.next())
			{	
				wiek = rs.getInt("wiek");
				listaRok.add(wiek);
			}
			return listaRok;
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}
	
	public static  void pobierzDaneFiltrowanie(List<String> imiona, List<String> nazwiska, 
			List<String> nazwy, int[] rokStudiowTab, int[] wiekTab, boolean imionaState, 
			boolean nazwiskaState, boolean nazwyState, boolean rokStudiowState, boolean wiekState)
	{
		String nazwiskaStr = "";
		if (nazwy != null && !nazwy.isEmpty())
		{
			nazwiskaStr = "Student.nazwisko in ( " + String.join(",", nazwy) + " )";
		}
		
		
		String imionaStr = "";
		if (imiona != null && !imiona.isEmpty())
		{
			imionaStr = "Student.imie in ( " + String.join(",", imiona) + " )";
		}
		
		String nazwyStr = "";
		if (nazwy != null && !nazwy.isEmpty())
		{
			nazwyStr = "Uczelnia.nazwa in ( " +  String.join(",", nazwy) + " )";
		}
		
		String rokStudiowStr = "";
		if (rokStudiowTab != null)
		{
			rokStudiowStr = "Student.rokStudiow BETWEEN " + rokStudiowTab[0] + " AND " + rokStudiowTab[1];
		}
		
		String wiekStr = "";
		if (wiekTab != null)
		{
			wiekStr = "Student.wiek BETWEEN " + wiekTab[0] + " AND " + wiekTab[1];
		}
		
		String sql = "select "
				+ "Wpis.id, Student.imie, Student.nazwisko, Student.wiek, Student.rokStudiow, Uczelnia.nazwa, "
				+ "Uczelnia.miejscowosc, Uczelnia.rokZalozenia, Uczelnia.nazwiskoDziekana "
				+ "from "
				+ "Student inner join Wpis "
				+ "on Student.id = Wpis.idStudent "
				+ "inner join "
				+ "Uczelnia on Wpis.idUczelnia = Uczelnia.id where ";
		
		if (imionaState == true)
		{
			sql += imionaStr;
			sql += " ";	
		}
		
		
		if (nazwiskaState)
		{
			if (imionaState == true)
			{
				sql += " AND ";
			}
			
			sql += nazwiskaStr;
			sql += " ";
		}
		
		if (nazwyState == true)
		{
			if (imionaState || nazwiskaState)
			{
				sql += " AND ";
			}
			
			sql += nazwyStr;
			sql += " ";
		}
		
		sql += ";";
		
		System.out.println(sql);
	}


}


