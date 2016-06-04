
public class PrzedmiotProwadzacyGrupa {
	PrzedmiotProwadzacyGrupa(String przedmiot, String prowadzacy, String grupa, int numerid)
	{
		Prowadzacy=prowadzacy;
		Przedmiot=przedmiot;
		Grupa=grupa;
		ID=numerid;
	}

		private int ID;
		private String Prowadzacy;
		private String Przedmiot;
		private String Grupa;
	
		int getID()
		{
			return ID;
		}
		String getProwadzacy()
		{
			return Prowadzacy;
		}
		String getPrzedmiot()
		{
			return Przedmiot;
		}
		String getGrupa()
		{
			return Grupa;
		}
		
		//----- Dalej niepotrzebne
	void drukuj()
	{
		System.out.println(ID+" "+Przedmiot+" "+ Prowadzacy+" "+Grupa);
	}
}
