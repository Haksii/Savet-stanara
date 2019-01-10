package projekat_savet_stanara;
import com.google.gson.Gson;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Aplikacija {
    
    static int GlavniMeni()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Unesite redni broj opcije koju zelite");
        System.out.println("1. Prijava na sistem");
        System.out.println("2. Ugasi aplikaciju");  
        try{
            int opcija;
            do{
               opcija = scan.nextInt();
            }while(opcija<1 || opcija >2);
        
            return opcija;
        }catch(Exception e)
        {
            System.out.println(e);
        }
        
        
       return 2;
    }
    /*OVO JE PREDSEDNICKI MENI KOJI SADRZI ADMINISTRATIVNE OPCIJE*/
    static void PredsednickiMeni(ArrayList<Stanar> stanari)
    {
        int stanje = 1;
        while(stanje == 1)
        {
            Scanner scan = new Scanner(System.in);
            System.out.println("Unesite redni broj opcije koju zelite");
            System.out.println("1. Zapisnik sa saveta stanara");
            System.out.println("2. Informacije o stanarima u zgradi");
            System.out.println("3. Registracija novog stanara");
            System.out.println("4. Kreiranje novog zapisnika");
            System.out.println("5. Odjava");
            int opcija;
            do{
                opcija = scan.nextInt();
                if(opcija < 1 || opcija > 5)
                {
                    System.out.println("Uneli ste nevazecu opciju!");
                    System.out.println("Unesite zeljenu opciju ponovo:");
                }
            }while(opcija<1 || opcija >5);

            switch(opcija)
            {
                case 1:
                    ZapisnikSavetaStanara();
                    break;
                case 2:
                    InformacijeStanara(stanari);
                    break;
                case 3:
                    RegistrujStanara(stanari);
                    break;
                case 4:
                    KreirajNoviZapisnik();
                    break;
                case 5:
                    stanje = 0;
                    System.out.println("Sada ste izlogovani.");
                    break;
                default:
                    break;
            } 
        }
        
    }
    
    static void KorisnickiMeni(ArrayList<Stanar> stanari)
    {
        int stanje = 1;
        while(stanje == 1)
        {
            Scanner scan = new Scanner(System.in);
            System.out.println("Unesite redni broj opcije koju zelite");
            System.out.println("1. Zapisnik sa saveta stanara");
            System.out.println("2. Informacije o stanarima u zgradi");
            System.out.println("3. Odjava");
            int opcija;
            do{
                opcija = scan.nextInt();
                if(opcija < 1 || opcija > 3)
                {
                    System.out.println("Uneli ste nevazecu opciju!");
                    System.out.println("Unesite zeljenu opciju ponovo:");
                }
            }while(opcija<1 || opcija >5);

            switch(opcija)
            {
                case 1:
                    ZapisnikSavetaStanara();
                    break;
                case 2:
                    InformacijeStanara(stanari);
                    break;
                case 3:
                    stanje = 0;
                    System.out.println("Sada ste izlogovani.");
                    break;
                default:
                    break;
            } 
        }
    }
    
    /*OVA FUNKCIJA SLUZI ZA PRIJAVU KORISNIKA
    PRVO PROVERAVA DA LI SU UNETA LOZINKA I KORISNICKO IME ISTI KAO
    PREDSEDNIKOVI, U SUPROTNOM PROLAZI KROZ NIZ STANARA*/
    static Stanar Logovanje(ArrayList<Stanar> stanari, Stanar predsednik)
    {
        BufferedReader upisniTok =new BufferedReader(new InputStreamReader(System.in));
        String korime = "";
        String lozinka = "";
        try{
           System.out.println("Unesite korisnicko ime: ");
           korime = (String) upisniTok.readLine(); 
           System.out.println("Unesite lozinku: ");
           lozinka = (String) upisniTok.readLine();           
        }catch(IOException e){
            
        }  
        
        if(predsednik.getKor_ime().equals(korime))
        {
            if(predsednik.getLozinka().equals(lozinka))
            {
                return predsednik;
            }
            System.out.println("Uneli ste pogresnu lozinku!");
        }
        
        for(Stanar s: stanari)
        {
            if(s.getKor_ime().equals(korime))
            {
                if(s.getLozinka().equals(lozinka))
                {
                    return s;
                }
                System.out.println("Uneli ste pogresnu lozinku");
            }
        }
        
        return null;
    }
    
    /*OVA FUNKCIJA SLUZI ZA KREIRANJE ZAHTEVA ZA NOVI NALOG ILI ZA KREIRANJE NOVOG NALOGA
    UKOLIKO IMATE ADMINISTRATIVNE DOZVOLE ( UKOLIKO STE PREDSEDNIK)*/
    //mod se prosledjuje kao parametar da utvrdimo da li funkciju poziva admin ili korisnik (1-admin, 0-korisnik)
    static void KreirajNalog(ArrayList<Stanar> stanari, int mod) 
    {
        Stanar stanar = new Stanar();
        Gson gson = new Gson();
        Scanner scanner = new Scanner(System.in);
        BufferedReader upisniTok =new BufferedReader(new InputStreamReader(System.in));  
        
        System.out.println("Da biste kreirali korisnicki nalog, molimo unesite sledece podatke");
        String ime_prezime = "",e_mail = "",kor_ime = "",lozinka="";
        int godina_rodjenja = 0, broj_stana = 0;
        boolean vlasnik = false;
        try{
                System.out.println("Unesite ime i prezime");
                ime_prezime =(String) upisniTok.readLine();
                stanar.setIme_prezime(ime_prezime);
                System.out.println("Unesite e-mail adresu");
                e_mail =(String) upisniTok.readLine();
                stanar.setE_mail(e_mail);
                System.out.println("Unesite korisnicko ime");
                kor_ime =(String) upisniTok.readLine(); 
                stanar.setKor_ime(kor_ime);
                System.out.println("Unesite lozinku");
                lozinka = (String) upisniTok.readLine();
                stanar.setLozinka(lozinka);
                System.out.println("Unesite broj stana:");
                broj_stana = scanner.nextInt();
                stanar.setBroj_stana(broj_stana);
                System.out.println("Unesite godinu rodjenja: ");
                godina_rodjenja = scanner.nextInt();
                stanar.setGodina_rodjenja(godina_rodjenja);
                System.out.println("Nosilac stana (DA / NE)?");
                String odgovor = upisniTok.readLine();
                if(odgovor.toLowerCase().equals("da"))
                {
                    vlasnik = true;
                    stanar.setNosilac_stana(vlasnik);
                }else{
                    stanar.setNosilac_stana(vlasnik);
                }
             
                stanar.setNosilac_stana(vlasnik);
                if(mod == 1){
                    stanari.add(stanar);
                }else{
                    String zahtev = "nalozi\\"+kor_ime+"_zahtev.json";
                    File fajl = new File(zahtev);
                    fajl.getParentFile().mkdirs();
                    try(FileWriter upis = new FileWriter(fajl))
                    {
                        gson.toJson(stanar,upis);
                    }catch(IOException e){
                         System.out.println("Greska Prilikom upisa!");
                    }
                }
                
        }catch(IOException e)
        {
            
        }
    }
    
    /*FUNKCIJA KOJA PRIKAZUJE MENI U KOME MOZETE DA ODABERETE
      KOJI ZAPISNIK ZELITE DA UCITATE*/
    static void ZapisnikSavetaStanara()
    {
        ArrayList<String> fajlovi = new ArrayList<String>();
        File[] files = new File("zapisnici\\").listFiles();
        for (File file : files) {
            if (file.isFile()) {
                fajlovi.add(file.getName());
            }
        }
        
        System.out.println("Ovo je lista svih zapisnika, unesite redni broj zapisnika koji zelite da otvorite");
        int rbr = 1;
        for(String s: fajlovi)
        {
            System.out.println(rbr +". " + s);
            rbr++;
        }
        
        Scanner scan = new Scanner(System.in);
        
        int redni_broj = 0;
        do{
            try{
            redni_broj = scan.nextInt();
            if(redni_broj < 1 || redni_broj > rbr)
            {
                throw new RedniBrojException();
            }
            }catch(Exception e)
            {
                 System.out.println(e);
            }
        }while(redni_broj < 1 || redni_broj >rbr);
        
        IspisiZapisnik("zapisnici\\" + fajlovi.get(redni_broj-1));
        
    }
    
    /*FUNKCIJA KOJA DOBIJA KAO PARAMETAR PUTANJU ZAPISNIKA KOJI JE KORISNIK ODABRAO
    I ISPISUJE NJEGOV SADRZAJ*/
    static void IspisiZapisnik(String zapisnik)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(zapisnik))) {      
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            
            System.out.println("Pritisnite bilo koje dugme da se vratite u meni.");
            System.in.read();
        }catch(IOException e)
        {
            
        }
    }
    
    /*FUNKCIJA KOJA ISPISUJE PODATKE I INFORMACIJE O SVIM STANARIMA*/
    static void InformacijeStanara(ArrayList<Stanar> stanari)
    {
        for(Stanar s: stanari)
        {
            System.out.println(s);
        }
        System.out.println("Pritisnite bilo koje dugme da se vratite u meni.");
        try {
            System.in.read();
        } catch (IOException ex) {
            
        }
    }
    
    /*MENI ZA REGISTRACIJU NOVOG STANARA*/
    static void RegistrujStanara(ArrayList<Stanar> stanari)
    {
        Scanner scan = new Scanner(System.in);
        int stanje = 1;
        int opcija = 0;
        while(stanje == 1)
        {
            System.out.println("Unesite zeljenu opciju: ");
            System.out.println("1. Kreiraj nalog na osnovu podnetih zahteva");
            System.out.println("2. Rucno napravi nalog");
            System.out.println("3. Izlaz");
            try{
                do{
                   opcija = scan.nextInt();
                }while(opcija<1 || opcija >3);
            }catch(Exception e){}
            
            switch(opcija)
            {
                case 1:
                    KreirajNaOsnovuZahteva(stanari);
                    break;
                case 2:
                    KreirajNalog(stanari, 1);
                    break;
                case 3:
                    stanje = 0;
                    break;
            }
        }
    }
    
    /*FUNKCIJA KOJA IZLISTAVA SVE ZAHTEVE IZ FOLDERA NALOZI I OMOGUCAVA ADMINISTRATORU
    DA ODABERE INDEKS ZAHTEVA KOJI ZELI DA ODOBRI I TIME KREIRA NOVI NALOG*/
    static void KreirajNaOsnovuZahteva(ArrayList<Stanar> stanari)
    {
        ArrayList<String> fajlovi = new ArrayList<String>();
        File[] files = new File("nalozi\\").listFiles();
        for (File file : files) {
            if(file.isFile())
            {
                if(file.getName().contains("_zahtev"))
                {
                    fajlovi.add(file.getName());
                }
            }
                     
        }   
        System.out.println("Ovo je lista svih zahteva, unesite redni broj zahteva koji zelite da odobrite");
        int rbr = 1;
        for(String s: fajlovi)
        {
            System.out.println(rbr +". " + s);
            rbr++;
        }
        System.out.println(rbr++ + ". Izadji iz aplikacije");
        Scanner scan = new Scanner(System.in);
        
        int redni_broj = 0;
        do{
            try{
            redni_broj = scan.nextInt();
            if(redni_broj < 1 || redni_broj > rbr)
            {
                throw new RedniBrojException();
            }
            }catch(Exception e)
            {
                 System.out.println(e);
            }
        }while(redni_broj < 1 || redni_broj >rbr);
        
        if(redni_broj == rbr-1) return;
        
        Stanar novi_stanar = new Stanar();
        String zahtev = "nalozi\\" + fajlovi.get(redni_broj-1);
        Gson gson = new Gson();
        File fajl = new File(zahtev);
        try(FileReader citac = new FileReader(fajl)){  
              novi_stanar = gson.fromJson(citac, Stanar.class);
        }catch(IOException e)
        {
            System.out.println("Greska prilikom citanja!");
        }
        
        stanari.add(novi_stanar);
            
    }
    
    /*FUNKCIJA KOJA OMOGUCAVA DODAVANJE NOVOG ZAPISNIKA*/
    static void KreirajNoviZapisnik()
    {
        Scanner scan = new Scanner(System.in);
        try{
            BufferedReader upisniTok =new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Unesite ime zapisnika: ");
            String ime_zapisnika = (String)upisniTok.readLine();
            File fajl = new File("zapisnici\\"+ime_zapisnika+".txt");
            fajl.getParentFile().mkdirs();
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fajl), "utf-8"));
            
            System.out.println("Unesite tekst zapisnika(za kraj unesite rec STOP)");
            String tekst_zapisnika="";
            while(true)
            {
                String linija = (String)upisniTok.readLine();
                if(linija.equals("STOP")) break;
                tekst_zapisnika+=linija+"\n";
            }
            
            writer.write(tekst_zapisnika);
            writer.close();
        }catch (IOException ex) {}
   
    }
    
    
    /*MALA FUNKCIJA KOJA PITA KORISNIKA DA LI ZELI DA PODNESE ZAHTEV ZA KREIRANJE*/
    static boolean DaLiZelis()
    {
        BufferedReader upisniTok =new BufferedReader(new InputStreamReader(System.in));  
        
        try{
            System.out.println("Ovaj nalog ne postoji. Da li zelite da podnesete zahtev za kreiranje? (DA / NE)");
            String odgovor = upisniTok.readLine();
            
            if(odgovor.toLowerCase().equals("da"))
            {
                return true;
            }
            return false;
        }
        catch(IOException e){
            
        }
        return false;
    }
    
    /*FUNKCIJA KOJA CUVA PROMENE O PODACIMA ZA STANARE(NOVI NALOZI)*/
    static void SacuvajPodatkeStanara(ArrayList<Stanar> stanari)
    {
        Gson gson = new Gson();
        File fajl = new File("nalozi\\stanari.json");
        fajl.getParentFile().mkdirs();
        
         try(FileWriter upis = new FileWriter(fajl))
         {
                gson.toJson(stanari,upis);
         }catch(IOException e){
                System.out.println("Greska Prilikom upisa!");
         }
    }
    
    /*FUNKCIJA KOJA POPUNJAVA INFORMACIJE O STANOVIMA U ZGRADI NA 
    OSNOVU PODATAKA O STANARIMA*/
    static void AzurirajPodatkeStanova(ArrayList<Stanar> stanari, Stanar predsednik, Zgrada zgrada)
    {
       if(predsednik.getBroj_stana() < zgrada.getBrojStanova() && predsednik.getBroj_stana() > 0 )
       {
           zgrada.getStan(predsednik.getBroj_stana()).setIme_vlasnika(predsednik.getIme_prezime());
           zgrada.getStan(predsednik.getBroj_stana()).setBroj_stana(predsednik.getBroj_stana());
           zgrada.getStan(predsednik.getBroj_stana()).setBroj_ukucana(zgrada.getStan(predsednik.getBroj_stana()).getBroj_ukucana()+1);
       }
       
       for(Stanar s: stanari)
       {
           if(s.getBroj_stana() < zgrada.getBrojStanova() && s.getBroj_stana() > 0 )
           {
                zgrada.getStan(s.getBroj_stana()).setIme_vlasnika(s.getIme_prezime());
                zgrada.getStan(s.getBroj_stana()).setBroj_stana(s.getBroj_stana());
                zgrada.getStan(s.getBroj_stana()).setBroj_ukucana(zgrada.getStan(s.getBroj_stana()).getBroj_ukucana()+1);
           }
       }
       
            Gson gson = new Gson();
            File fajl = new File("config\\zgrada.json");
            fajl.getParentFile().mkdirs();
            try(FileWriter upis = new FileWriter(fajl))
            {
                gson.toJson(zgrada,upis);
            }catch(IOException e){
                System.out.println("Greska Prilikom upisa!");
            }
    }
}

/*IZUZETAK UKOLIKO JE REDNI BROJ ZAPISNIKA NEPOSTOJECI*/
class RedniBrojException extends Exception
{
    @Override
    public String toString()
    {
        return "Redni broj zapisnika koji ste uneli je nepostojeci";
    }
}
