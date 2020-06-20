package ap.controllers.Services;

public class Articol {
    public int id;
    public String nume;
    public String poza;
    public String descriere;
    public Boolean tip;

    public Articol(int id, String nume, String poza, String descriere, Boolean tip) {
        this.id = id;
        this.nume = nume;
        this.poza = poza;
        this.descriere = descriere;
        this.tip = tip;
    }
    public Articol(Articol articol) {
        this.id = articol.id;
        this.nume = articol.nume;
        this.poza = articol.poza;
        this.descriere = articol.descriere;
        this.tip = articol.tip;
    }
}
