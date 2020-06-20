package ap.controllers.Services;

public class Intrebare {
        public int id;
        public int id_user;
        public String text;
        public String raspuns;
        public String nume;

    public String getRaspuns() {
        return raspuns;
    }

    public Intrebare(int id, int id_user, String text, String raspuns,String nume) {
        this.id = id;
        this.id_user = id_user;
        this.text = text;
        this.raspuns = raspuns;
        this.nume = nume;

    }
    public Intrebare(Intrebare intrebare) {
        this.id = intrebare.id;
        this.id_user = intrebare.id_user;
        this.text = intrebare.text;
        this.raspuns = intrebare.raspuns;
        this.nume = intrebare.nume;
    }
}
