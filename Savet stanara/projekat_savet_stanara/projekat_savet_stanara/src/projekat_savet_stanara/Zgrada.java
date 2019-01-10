package projekat_savet_stanara;

public class Zgrada 
{
    private String adresa;
    private int broj;
    private String opstina;
    private String grad;
    private Stan[] stanovi;
    private int broj_stanova;
    
    public Zgrada()
    {
        adresa = "nepoznato";
        broj = 0;
        opstina = "nepoznato";
        grad = "nepoznato";
    }
    public Zgrada(String adresa, int broj, String opstina, String grad, int broj_stanova)
    {
        this.adresa = adresa;
        this.broj = broj;
        this.opstina = opstina;
        this.grad = grad;
        this.broj_stanova = broj_stanova;
    }

    public String getAdresa() {
        return adresa;
    }

    public int getBroj() {
        return broj;
    }

    public String getOpstina() {
        return opstina;
    }

    public String getGrad() {
        return grad;
    }

    public Stan[] getStanovi() {
        return stanovi;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }

    public void setOpstina(String opstina) {
        this.opstina = opstina;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public void setStanovi(int broj_stanova) {
        stanovi = new Stan[broj_stanova];
    }
    
    public int getBrojStanova()
    {
        return this.broj_stanova;
    }
    
    public void setStan(int i, Stan s)
    {
        stanovi[i] = s;
    }
    public Stan getStan(int i)
    {
        return stanovi[i];
    }

    @Override
    public String toString() {
        return "Zgrada{" + "adresa=" + adresa + ", broj=" + broj + ", opstina=" + opstina + ", grad=" + grad + ", stanovi=" + stanovi + '}';
    }
    
    
}
