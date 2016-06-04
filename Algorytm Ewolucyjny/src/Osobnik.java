import java.util.ArrayList;
import java.util.List;

public class Osobnik {
	public List<Asocjacja> Asocjacje;
	public int dopasowanie;
	Osobnik(List<SalaDzienGodzina> ListaSDG)
	{
		Asocjacje= new ArrayList<Asocjacja>();
		for(int i=0; i<ListaSDG.size(); i++)
		{
		Asocjacja asocjacja = new Asocjacja(ListaSDG.get(i));
		Asocjacje.add(asocjacja);
		}
	}
	Osobnik(Osobnik osobnik)
	{
		Asocjacje= new ArrayList<Asocjacja>();
		for(int i=0; i<osobnik.Asocjacje.size(); i++)
		{
		Asocjacja asocjacja = new Asocjacja(osobnik.Asocjacje.get(i).SDG);
		asocjacja.PPG=osobnik.Asocjacje.get(i).PPG;
		Asocjacje.add(asocjacja);
		}
	}
	
	int FunkcjaDopasowania(List<String> Przedmioty, List<String> Prowadzacy, List<String> Grupy, List<String> Dni)
	{
		
		int rozneP=-100000;//ró¿ne zajêcia dla prowadz¹cego w tym samym czasie
		int rozneG=-100000;//ró¿ne zajêcia dla grup w tym samym czasie
		int okienkaG=-2;//okienka grup
		int okienkaP=-1;//okienka prowadz¹cych
		int wolneDniP=1;//wolne dni prowadz¹cych
		int wolneDniG=100;//wolne dni grup
		//przedmioty w tych samych dniach
		dopasowanie=0;
		
		for(int i=0; i<Asocjacje.size()-1; i++)
		{
			if(Asocjacje.get(i).PPG!=null)
			{
				for(int j=i+1; j<Asocjacje.size(); j++)
				{
					if(Asocjacje.get(j).PPG!=null)
					{
						if(Asocjacje.get(j).SDG.getDzien()==Asocjacje.get(i).SDG.getDzien()&&Asocjacje.get(j).SDG.getGodzina()==Asocjacje.get(i).SDG.getGodzina()&&Asocjacje.get(j).PPG.getProwadzacy()==Asocjacje.get(i).PPG.getProwadzacy())
						{
							dopasowanie+=rozneP;
						}
						if(Asocjacje.get(j).SDG.getDzien()==Asocjacje.get(i).SDG.getDzien()&&Asocjacje.get(j).SDG.getGodzina()==Asocjacje.get(i).SDG.getGodzina()&&Asocjacje.get(j).PPG.getGrupa()==Asocjacje.get(i).PPG.getGrupa())
						{
							dopasowanie+=rozneG;
						}
					}
				}
			}
		}
		
		
		
		int last;
		for(int i=0; i<Grupy.size(); i++)
		{
			last=0;
			while(Asocjacje.get(last).PPG==null)
			{
				last++;
			}
		for(int j=0; j<Asocjacje.size(); j++)
		{
			
			if(Asocjacje.get(j).PPG!=null)
			{
				if(Asocjacje.get(j).PPG.getGrupa()==Grupy.get(i))
						{
				for(int roznicaG=2; roznicaG<14; roznicaG++)
				{
					boolean znaleziono=false;
				for(int k=0; k<Asocjacje.size(); k++)
				{
					if(Asocjacje.get(k).PPG!=null)
					{
					if(Asocjacje.get(j).SDG.getDzien()==Asocjacje.get(k).SDG.getDzien()&&Asocjacje.get(j).SDG.getGodzina()==Asocjacje.get(k).SDG.getGodzina()-roznicaG&&znaleziono==false&&Asocjacje.get(k).PPG.getGrupa()==Grupy.get(i))
					{
						znaleziono=true;
						dopasowanie+=(roznicaG-1)*okienkaG;
				//		System.out.println("if: "+roznicaG+"  j "+j+"  k "+k);
					}
					}
				}
				}
						}
			}
			/*if(Asocjacje.get(j).SDG.getSala()!=lastSala)
			{
				lastSala=Asocjacje.get(j).SDG.getSala();
				iloscO++;
				//System.out.println("sala: "+dopasowanie+"   j "+j+"   ilosc "+iloscO);
			}
			if(Asocjacje.get(j).SDG.getDzien()==lastDzien)
			{
		if(Asocjacje.get(j).PPG!=null)
		{
			if(Asocjacje.get(j).PPG.getGrupa()==Grupy.get(i))
			{

				
			//	dopasowanie+=(iloscO)*okienkaG;
				System.out.println("if: "+dopasowanie+"   j "+j+"   ilosc "+iloscO);
				}
		else
			{
				lastDzien=Asocjacje.get(j).SDG.getDzien();
				iloscO=0;
			}
			//	last=j;
			}
		}*/
	
		}
		//System.out.println("for: "+dopasowanie);
		}
		for(int i=0; i<Grupy.size(); i++)
		{
			for(int d=0; d<Dni.size(); d++)
			{
				boolean spelnione=true;
		for(int j=0; j<Asocjacje.size(); j++)
		{
			if(Asocjacje.get(j).PPG!=null)
			{	
				if(Asocjacje.get(j).PPG.getGrupa()==Grupy.get(i)&&Dni.get(d)==Asocjacje.get(j).SDG.getDzien())
				{
					spelnione=false;
				}
			}
		}
		if(spelnione==true)
		{
		dopasowanie+=wolneDniG;
		}
		}
		}
		return dopasowanie;
	}
}

class Asocjacja {
	
	public SalaDzienGodzina SDG;
	public PrzedmiotProwadzacyGrupa PPG;
	
	Asocjacja(SalaDzienGodzina sdg)
	{
		SDG=sdg;
	}
	
	
	void drukuj()
	{
		SDG.drukuj();
		if(PPG!=null)
			PPG.drukuj();
	}
	
}