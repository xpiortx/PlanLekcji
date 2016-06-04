
public class SalaDzienGodzina {
	SalaDzienGodzina(String sala, String dzien, int godzina, int numerid)
	{
		Sala=sala;
		Dzien=dzien;
		Godzina=godzina;
		ID=numerid;
	}

		private int ID;
		private String Sala;
		private String Dzien;
		private int Godzina;
		
		
		int getID()
		{
			return ID;
		}
		String getSala()
		{
			return Sala;
		}
		String getDzien()
		{
			return Dzien;
		}
		int getGodzina()
		{
			return Godzina;
		}
		
		//----- Dalej niepotrzebne
	void drukuj()
	{
		System.out.println(ID+" "+Sala+" "+ Dzien+" "+Godzina);
	}
}