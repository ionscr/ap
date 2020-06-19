package ap.controllers.Services;

public class Intrebare {
        public int id;
        public int id_user;
        public String text;
        public String raspuns;

    public String getRaspuns() {
        return raspuns;
    }

    public Intrebare(int id, int id_user, String text, String raspuns) {
        this.id = id;
        this.id_user = id_user;
        this.text = text;
        this.raspuns = raspuns;
    }
}
