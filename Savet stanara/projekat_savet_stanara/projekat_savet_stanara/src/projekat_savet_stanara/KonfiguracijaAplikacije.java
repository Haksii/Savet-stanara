package projekat_savet_stanara;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class KonfiguracijaAplikacije {
    
    static boolean KonfigurisanaAplikacija()
    {
        int kontrola = 0;
        File fajl = new File("config\\zgrada.json");
        try(FileReader citac = new FileReader(fajl)){
            if(citac != null)
            {
                kontrola++;
            }
                
        }catch(IOException e)
        {
            //System.out.println("Greska prilikom citanja!");
        }
        
        fajl = new File("nalozi\\predsednik.json");
        try(FileReader citac = new FileReader(fajl)){     
            if(citac != null)
            {
                kontrola++;
            }      
        }catch(IOException e)
        {
            //System.out.println("Greska prilikom citanja!");
        }
        
        return kontrola==2;
    }
    
    static Zgrada UnesiPodatkeZgrade()
    {
        Zgrada zgrada = new Zgrada();
        Gson gson = new Gson();
        Scanner scanner = new Scanner(System.in);
        BufferedReader upisniTok =new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Unesite podatke o zgradi");
        String adresa,opstina,grad;
        int broj, broj_stanova;
        try{
            System.out.println("Unesite adresu zgrade(ulica)");
            adresa =(String) upisniTok.readLine();
            System.out.println("Unesite broj zgrade");
            broj = scanner.nextInt();
            System.out.println("Unesite opstinu");
            opstina =(String) upisniTok.readLine();
            System.out.println("Unesite grad");
            grad =(String) upisniTok.readLine(); 
            System.out.println("Unesite broj stanova u zgradi");
            broj_stanova = scanner.nextInt();
            
            zgrada.setAdresa(adresa);
            zgrada.setBroj(broj);
            zgrada.setOpstina(opstina);
            zgrada.setGrad(grad);
            zgrada.setStanovi(broj_stanova);
            File fajl = new File("config\\zgrada.json");
            fajl.getParentFile().mkdirs();
            try(FileWriter upis = new FileWriter(fajl))
            {
                gson.toJson(zgrada,upis);
            }catch(IOException e){
                System.out.println("Greska Prilikom upisa!");
            }
           
           
        } catch (IOException e){
            System.out.println ("Greška");
        }
        
        return zgrada;
    }
    
    static Zgrada ProcitajPodatkeZgrade()
    {
        Zgrada zgrada = new Zgrada();
        Gson gson = new Gson();
        File fajl = new File("config\\zgrada.json");
        try(FileReader citac = new FileReader(fajl)){  
              zgrada = gson.fromJson(citac, Zgrada.class);
        }catch(IOException e)
        {
            System.out.println("Greska prilikom citanja!");
        }
        
        return zgrada;
        
    }
    
    static Stanar UnesiPodatkePredsednika()
    {
        Stanar stanar = new Stanar();
        Gson gson = new Gson();
        Scanner scanner = new Scanner(System.in);
        BufferedReader upisniTok =new BufferedReader(new InputStreamReader(System.in));  
        
        System.out.println("Unesite podatke predsednika zgrade");
        String ime_prezime = "",e_mail = "",kor_ime = "",lozinka="";
        int godina_rodjenja = 0, broj_stana = 0;
        boolean vlasnik = false;
        try{
                System.out.println("Unesite ime i prezime predsednika");
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
                do{
                    try{
                        System.out.println("Unesite godinu rodjenja: ");
                        godina_rodjenja = scanner.nextInt();
                        if(2018-godina_rodjenja < 18)
                        {
                            throw new Maloletnik(2018-godina_rodjenja);
                        }else{
                            stanar.setGodina_rodjenja(godina_rodjenja);
                        }
                    }catch(Maloletnik m){
                        System.out.println(m);
                    }
                }while(2018-godina_rodjenja < 18);
                
                do{
                   try{
                        System.out.println("Da li je nosilac stana (DA / NE)?");
                        String odgovor = upisniTok.readLine();
                        if(odgovor.toLowerCase().equals("da")){
                            vlasnik = true;
                            stanar.setNosilac_stana(vlasnik);
                        }else{
                            throw new Nosilac();
                        }  
                    }catch(Nosilac n){
                    System.out.println(n);
                    } 
                }while(!vlasnik);
                
            
            File fajl = new File("nalozi\\predsednik.json");
            fajl.getParentFile().mkdirs();
            try(FileWriter upis = new FileWriter(fajl))
            {
                gson.toJson(stanar,upis);
            }catch(IOException e){
                System.out.println("Greska Prilikom upisa!");
            }
           
           
        } catch (IOException e){
            System.out.println ("Greška");
        }
        
        return stanar;
    }
    
    
    
    static Stanar ProcitajPodatkePredsednika()
    {
        Stanar predsednik = new Stanar();
        Gson gson = new Gson();
        File fajl = new File("nalozi\\predsednik.json");
        try(FileReader citac = new FileReader(fajl)){  
              predsednik = gson.fromJson(citac, Stanar.class);
        }catch(IOException e)
        {
            System.out.println("Greska prilikom citanja!");
        }
        
        return predsednik;
    }
    
    
    static ArrayList<Stanar> UcitajStanare()
    {
        ArrayList<Stanar> stanari = new ArrayList<Stanar>();
        Gson gson = new Gson();
        File fajl = new File("nalozi\\stanari.json");
        
        //TypeToken<ArrayList<Stanar>> zaJSON = new TypeToken<ArrayList<Stanar>>(){};
        try(FileReader citac = new FileReader(fajl)){  
             stanari = gson.fromJson(citac, new TypeToken<ArrayList<Stanar>>(){}.getType());
        }catch(IOException e)
        {
            //System.out.println("Greska prilikom citanja!");
        }
        return stanari;
    }

}


class Maloletnik extends Exception
{
    private int godine;
    Maloletnik(int a)
    {
        godine=a;
    }
    
    @Override
    public String toString()
    {
        return "Nosilac stana ime " +godine +" godina - Predsednik mora biti punoletan";
    }
}

class Nosilac extends Exception
{
    @Override
    public String toString()
    {
        return "Predsednik mora biti nosilac stana!";
    }
}
