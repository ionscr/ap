package ap.controllers.Services;

public class Intrebare {
        public int id;
        public int id_user;
        public String text;
        public Boolean raspuns;

    public Intrebare(int id, int id_user, String text, Boolean raspuns) {
        this.id = id;
        this.id_user = id_user;
        this.text = text;
        this.raspuns = raspuns;
    }
}
