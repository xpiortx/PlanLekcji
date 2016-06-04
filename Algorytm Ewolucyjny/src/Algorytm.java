import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

//import SalaDzienGodzina.java;

public class Algorytm {

	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException
{
	Dane dane = new Dane();
	dane.genPopulacja(1000);
	dane.AlgorytmEwolucyjny(100, 10);
	dane.drukuj();
	dane.WriteToCSV();
}

void CreateSDGlist()
{
	
}

}

class Dane
{
	private List<String> Dni;
	private List<Integer> Godziny;
	private List<String> Sale;
	private List<SalaDzienGodzina> ListaSDG;
	
	private List<String> Przedmioty;
	private List<String> Prowadzacy;
	private List<String> Grupy;
	private List<PrzedmiotProwadzacyGrupa> ListaPPG;
	private List<Osobnik> Populacja;
	
	Dane(){
	Dni = new ArrayList<String>();
	Godziny = new ArrayList<Integer>();
	Sale = new ArrayList<String>();
	ListaSDG = new ArrayList<SalaDzienGodzina>();
	
	Przedmioty= new ArrayList<String>();
	Prowadzacy= new ArrayList<String>();
	Grupy= new ArrayList<String>();
	ListaPPG= new ArrayList<PrzedmiotProwadzacyGrupa>();
	Populacja= new ArrayList<Osobnik>();
	
	Dni.add("Poniedzia³ek");
	Dni.add("Wtorek");
	Dni.add("Œroda");
	Dni.add("Czwartek");
	Dni.add("Pi¹tek");
	
	for(int i=8; i<=20; i++)
	{
	Godziny.add(i);
	}
	
	for(int i=1; i<=10; i++)
	{
	Sale.add(Integer.toString(i));
	}
	int licznik=0;
	for(int i=0; i<Sale.size(); i++)
	{
		for(int j=0; j<Dni.size(); j++)
		{
			for(int k=0; k<Godziny.size(); k++)
			{
				SalaDzienGodzina o1 = new SalaDzienGodzina(Sale.get(i), Dni.get(j), Godziny.get(k), ++licznik);
				ListaSDG.add(o1);
			}
		}
	}
	
	genListaPPG();
	
	
	}
	void drukuj()
	{
		/*for(int i=0; i<ListaSDG.size(); i++)
		ListaSDG.get(i).drukuj();
		
		for(int i=0; i<ListaPPG.size(); i++)
		ListaPPG.get(i).drukuj();*/
		
		for(int i=0; i<Populacja.size(); i++)
		{
			for(int j=0; j<Populacja.get(i).Asocjacje.size(); j++)
			{
			//Populacja.get(i).Asocjacje.get(j).drukuj();
			}
		}
	/*	for(int i=0; i<Populacja.size(); i++)
		{
		Populacja.get(i).FunkcjaDopasowania(Przedmioty, Prowadzacy, Grupy);
		}
		Sortowanie();*/
		for(int i=0; i<Populacja.size(); i++)
		{
		System.out.println(Populacja.get(i).FunkcjaDopasowania(Przedmioty, Prowadzacy, Grupy, Dni)+"   "+Populacja.size());
		}
		
		for(int j=0; j<Populacja.get(0).Asocjacje.size(); j++)
		{
		Populacja.get(0).Asocjacje.get(j).drukuj();
		}
		
	}
	
	void AlgorytmEwolucyjny(int iloscPowtorzen, int iloscPozostajacych)
	{
		int rozmiarP;
		int rozmiarPstary=Populacja.size();
		for(int j=0; j<Populacja.size(); j++)
		{
		Populacja.get(j).FunkcjaDopasowania(Przedmioty, Prowadzacy, Grupy, Dni);
		}
		Sortowanie();
		for(int i=0; i<iloscPowtorzen; i++)
		{
			System.out.println(i+"/"+iloscPowtorzen);
		for(int k=iloscPozostajacych; k<Populacja.size();)
		{
			//System.out.println("   "+Populacja.size());		
			Populacja.remove(iloscPozostajacych);
			//Osobnik osobnik = new Osobnik(ListaSDG);
			//Losuj(osobnik);
			//Populacja.add(osobnik);
		}
		rozmiarP=Populacja.size();
		for(int j=0; j<rozmiarP; j++)
		{
			for(int k=0; k<rozmiarPstary/rozmiarP-1; k++)
			{
				Populacja.add(Mutuj(Populacja.get(j)));
				//System.out.println(Populacja.get(Populacja.size()-1).FunkcjaDopasowania(Przedmioty, Prowadzacy, Grupy)+"   "+Populacja.size());		
				}
		}
		for(int j=0; j<Populacja.size(); j++)
		{
		Populacja.get(j).FunkcjaDopasowania(Przedmioty, Prowadzacy, Grupy, Dni);
		}
		Sortowanie();
		}
	}
	
	Osobnik Mutuj(Osobnik osobnik)
	{
		Osobnik nowyosobnik=new Osobnik(osobnik);
		PrzedmiotProwadzacyGrupa zapasowy=nowyosobnik.Asocjacje.get(0).PPG;
		
		Random rand = new Random();
		while((int)rand.nextInt(2)==1){
		int los1=rand.nextInt(nowyosobnik.Asocjacje.size()-1);
		int los2=rand.nextInt(nowyosobnik.Asocjacje.size()-1);
		//System.out.println(nowyosobnik.Asocjacje.get(los1).PPG+" "+nowyosobnik.Asocjacje.get(los2).PPG+" "+osobnik.Asocjacje.get(los1).PPG+" "+osobnik.Asocjacje.get(los2).PPG);
		zapasowy=nowyosobnik.Asocjacje.get(los1).PPG;
		nowyosobnik.Asocjacje.get(los1).PPG=nowyosobnik.Asocjacje.get(los2).PPG;
		nowyosobnik.Asocjacje.get(los2).PPG=zapasowy;
		}
		//System.out.println(zapasowy+" "+nowyosobnik.Asocjacje.get(los1).PPG+" "+nowyosobnik.Asocjacje.get(los2).PPG+" "+osobnik.Asocjacje.get(los1).PPG+" "+osobnik.Asocjacje.get(los2).PPG);
		return nowyosobnik;
	}
	
	void Sortowanie()
	{
		Osobnik zapasowy;
		for(int i=0; i<Populacja.size()-1; i++)
		{
			for(int j=i+1; j<Populacja.size(); j++)
			{
			if(Populacja.get(i).dopasowanie<Populacja.get(j).dopasowanie)
			{
				zapasowy=Populacja.get(i);
				Populacja.set(i, Populacja.get(j));
				Populacja.set(j, zapasowy);
			//	System.out.println(Populacja.get(i).dopasowanie+"   "+Populacja.get(j).dopasowanie);
			}
			}
		}
	}
	
	void WriteToCSV() throws FileNotFoundException, UnsupportedEncodingException
	{
		PrintWriter writer = new PrintWriter("1.csv", "UTF-8");
		//writer.print("\"\"");
		int pop=0;
		for(int g=0; g<Grupy.size(); g++)
		{
			writer.println("Grupa: "+ Grupy.get(g));
		for(int i=0; i<Dni.size(); i++)
		{
			writer.print(";"+Dni.get(i));
		}
		writer.println();
		
		for(int i=0; i<Godziny.size(); i++)
		{
			writer.print(Godziny.get(i));
			for(int j=0; j<Dni.size(); j++)
			{
				boolean wypelniono=false;
				for(int k=0; k<Populacja.get(pop).Asocjacje.size(); k++)
				{
					if(Populacja.get(pop).Asocjacje.get(k).SDG.getDzien()==Dni.get(j)&&Populacja.get(pop).Asocjacje.get(k).SDG.getGodzina()==Godziny.get(i))
					{
						if(Populacja.get(pop).Asocjacje.get(k).PPG!=null)
						{
							if(Populacja.get(pop).Asocjacje.get(k).PPG.getGrupa()==Grupy.get(g))
							{
								writer.print(";\""+Populacja.get(pop).Asocjacje.get(k).PPG.getPrzedmiot()+"\n"+Populacja.get(pop).Asocjacje.get(k).PPG.getProwadzacy()+"\"");
								wypelniono=true;
							}
						}
					}
				}
				if(wypelniono==false)
				{
					writer.print(";");
				}
			}
			writer.println();
		}
		writer.println();
		
		}
		
		
		writer.println("The first line");
		writer.println("The second line");
		writer.close();
	}
	
	void genListaPPG()
	{
		Przedmioty.add("Matematyka");
		Przedmioty.add("Religia");
		
		Prowadzacy.add("ks. Maciej");
		Prowadzacy.add("Joanna Ca³ka");
		Prowadzacy.add("Jan Pasibrzuch");
		
		Grupy.add("1a");
		Grupy.add("1b");
		Grupy.add("1c");
		Grupy.add("1d");
		
		PrzedmiotProwadzacyGrupa o1 = new PrzedmiotProwadzacyGrupa(Przedmioty.get(0), Prowadzacy.get(1), Grupy.get(0), 1);
		ListaPPG.add(o1);
		o1 = new PrzedmiotProwadzacyGrupa(Przedmioty.get(0), Prowadzacy.get(1), Grupy.get(1), 2);
		ListaPPG.add(o1);
		o1 = new PrzedmiotProwadzacyGrupa(Przedmioty.get(0), Prowadzacy.get(2), Grupy.get(2), 3);
		ListaPPG.add(o1);
		o1 = new PrzedmiotProwadzacyGrupa(Przedmioty.get(0), Prowadzacy.get(2), Grupy.get(3), 4);
		ListaPPG.add(o1);
		o1 = new PrzedmiotProwadzacyGrupa(Przedmioty.get(1), Prowadzacy.get(0), Grupy.get(0), 5);
		ListaPPG.add(o1);
		o1 = new PrzedmiotProwadzacyGrupa(Przedmioty.get(1), Prowadzacy.get(0), Grupy.get(1), 6);
		ListaPPG.add(o1);
		o1 = new PrzedmiotProwadzacyGrupa(Przedmioty.get(1), Prowadzacy.get(0), Grupy.get(2), 7);
		ListaPPG.add(o1);
		o1 = new PrzedmiotProwadzacyGrupa(Przedmioty.get(1), Prowadzacy.get(0), Grupy.get(3), 8);
		ListaPPG.add(o1);
		o1 = new PrzedmiotProwadzacyGrupa(Przedmioty.get(1), Prowadzacy.get(0), Grupy.get(3), 9);
		ListaPPG.add(o1);
		o1 = new PrzedmiotProwadzacyGrupa(Przedmioty.get(1), Prowadzacy.get(0), Grupy.get(3), 10);
		ListaPPG.add(o1);
		o1 = new PrzedmiotProwadzacyGrupa(Przedmioty.get(1), Prowadzacy.get(0), Grupy.get(3), 11);
		ListaPPG.add(o1);
		o1 = new PrzedmiotProwadzacyGrupa(Przedmioty.get(1), Prowadzacy.get(0), Grupy.get(3), 12);
		ListaPPG.add(o1);
		o1 = new PrzedmiotProwadzacyGrupa(Przedmioty.get(1), Prowadzacy.get(0), Grupy.get(3), 13);
		ListaPPG.add(o1);
		o1 = new PrzedmiotProwadzacyGrupa(Przedmioty.get(1), Prowadzacy.get(0), Grupy.get(3), 14);
		ListaPPG.add(o1);
		o1 = new PrzedmiotProwadzacyGrupa(Przedmioty.get(1), Prowadzacy.get(0), Grupy.get(3), 15);
		ListaPPG.add(o1);
		o1 = new PrzedmiotProwadzacyGrupa(Przedmioty.get(1), Prowadzacy.get(0), Grupy.get(3), 16);
		ListaPPG.add(o1);
		o1 = new PrzedmiotProwadzacyGrupa(Przedmioty.get(1), Prowadzacy.get(0), Grupy.get(3), 17);
		ListaPPG.add(o1);
		o1 = new PrzedmiotProwadzacyGrupa(Przedmioty.get(1), Prowadzacy.get(0), Grupy.get(3), 18);
		ListaPPG.add(o1);
	}	

	void genPopulacja(int liczba)
	{
		int ppp=0;
		for(int i=0; i<liczba; i++)
		{
			Osobnik osobnik = new Osobnik(ListaSDG);
			Losuj(osobnik);
			//System.out.println("   ppp");
			Populacja.add(osobnik);
			//System.out.println(ppp++);
		}
	}
	
	void Losuj(Osobnik osobnik)
	{
		int los;
		Random rand = new Random();
		
		for(int i=0; i<ListaPPG.size(); i++)
		{
			
			//los=(int)Math.round(Math.random()*(ListaSDG.size()-1));
			los=rand.nextInt(ListaSDG.size()-1);
			while(osobnik.Asocjacje.get(los).PPG!=null)
			{
				los=rand.nextInt(ListaSDG.size()-1);
				}
			
			
			
	

			osobnik.Asocjacje.get(los).PPG=ListaPPG.get(i);

		}
	}
	

}