package projekat_savet_stanara;
import java.util.ArrayList;
import static projekat_savet_stanara.Aplikacija.*;
import static projekat_savet_stanara.KonfiguracijaAplikacije.*;


public class Projekat_savet_stanara {

    
    public static void main(String[] args) {
        
        Zgrada zgrada = new Zgrada();
        ArrayList<Stanar> stanari = new ArrayList<Stanar>();
        Stanar predsednik = new Stanar();
        
        if(KonfigurisanaAplikacija())
        {
            zgrada = ProcitajPodatkeZgrade();
            predsednik = ProcitajPodatkePredsednika();
            stanari = UcitajStanare();
            stanari.add(predsednik);
        }else{
           zgrada = UnesiPodatkeZgrade(); 
           predsednik = UnesiPodatkePredsednika();
        }
        
        petlja1:
        while(true)
        {
            int meni = GlavniMeni();
            petlja2:
            while(meni == 1)
            {
                Stanar ulogovan = Logovanje(stanari,predsednik);
                if(ulogovan == null)
                {
                    if(DaLiZelis())
                    {
                        KreirajNalog(stanari, 0);
                    }
                    continue petlja1;
                    
                }else if(ulogovan == predsednik){
                    System.out.println("Dobrodosli, " + ulogovan.getIme_prezime() + " ,ulogovani ste kao administrator");
                    PredsednickiMeni(stanari);
                    continue petlja1;
                }else{
                    System.out.println("Dobrodosli," + ulogovan.getIme_prezime() + " ,ulogovani ste kao korisnik");
                    KorisnickiMeni(stanari);
                    continue petlja1;
                }   
            }
            
           break;
           
        }
        
        SacuvajPodatkeStanara(stanari);
        AzurirajPodatkeStanova(stanari,predsednik,zgrada);
    }
    
}
