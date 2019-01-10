package projekat_savet_stanara;


public class Stan {
    private int broj_stana;
    private int broj_ukucana;
    private String ime_vlasnika;
    
    public Stan()
    {
        broj_stana = 0;
        broj_ukucana = 0;
        ime_vlasnika = "Nepoznato";
    }
    public Stan(int broj_stana, int broj_ukucana, String ime_vlasnika)
    {
        this.broj_stana = broj_stana;
        this.broj_ukucana = broj_ukucana;
        this.ime_vlasnika = ime_vlasnika;
    }

    public int getBroj_stana() {
        return broj_stana;
    }

    public int getBroj_ukucana() {
        return broj_ukucana;
    }

    public String getIme_vlasnika() {
        return ime_vlasnika;
    }

    public void setBroj_stana(int broj_stana) {
        this.broj_stana = broj_stana;
    }

    public void setBroj_ukucana(int broj_ukucana) {
        this.broj_ukucana = broj_ukucana;
    }

    public void setIme_vlasnika(String ime_vlasnika) {
        this.ime_vlasnika = ime_vlasnika;
    }
    
    
}
