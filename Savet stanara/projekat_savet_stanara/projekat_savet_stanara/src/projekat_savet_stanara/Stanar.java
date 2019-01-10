package projekat_savet_stanara;

public class Stanar {
    private String ime_prezime;
    private int godina_rodjenja;
    private String e_mail;
    private String kor_ime;
    private String lozinka;
    private int broj_stana;
    private boolean nosilac_stana;
    
    public Stanar()
    {
        ime_prezime = "Nepoznato";
        godina_rodjenja = 0;
        e_mail = "Nepoznato";
        kor_ime = "Nepoznato";
        lozinka = "Nepoznata";
        broj_stana = 0;
        nosilac_stana = false;
    }

    public Stanar(String ime_prezime, int godina_rodjenja, String e_mail, String kor_ime, String lozinka, int broj_stana, boolean nosilac_stana) {
        this.ime_prezime = ime_prezime;
        this.godina_rodjenja = godina_rodjenja;
        this.e_mail = e_mail;
        this.kor_ime = kor_ime;
        this.lozinka = lozinka;
        this.broj_stana = broj_stana;
        this.nosilac_stana = nosilac_stana;
    }

    public String getIme_prezime() {
        return ime_prezime;
    }

    public int getGodina_rodjenja() {
        return godina_rodjenja;
    }

    public String getE_mail() {
        return e_mail;
    }

    public String getKor_ime() {
        return kor_ime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public int getBroj_stana() {
        return broj_stana;
    }

    public boolean isNosilac_stana() {
        return nosilac_stana;
    }

    public void setIme_prezime(String ime_prezime) {
        this.ime_prezime = ime_prezime;
    }

    public void setGodina_rodjenja(int godina_rodjenja) {
        this.godina_rodjenja = godina_rodjenja;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public void setKor_ime(String kor_ime) {
        this.kor_ime = kor_ime;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public void setBroj_stana(int broj_stana) {
        this.broj_stana = broj_stana;
    }

    public void setNosilac_stana(boolean nosilac_stana) {
        this.nosilac_stana = nosilac_stana;
    }

    @Override
    public String toString() {
        String nosilac="";
        if(nosilac_stana)
        {
            nosilac =" je nosilac stana";
        }else{
            nosilac =" nije nosilac stana";
        }
        return ime_prezime + ", rodjen: " + godina_rodjenja + ", zivi u broju " + broj_stana + nosilac + " || e_mail:" + e_mail;
    }
    
    
    
}
