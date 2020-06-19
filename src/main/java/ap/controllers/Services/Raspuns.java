package ap.controllers.Services;

public class Raspuns {
    public int id;
    public String text;
    public int id_intrebare;
    public int id_user;

    public Raspuns(int id, String text, int id_intrebare, int id_user) {
        this.id = id;
        this.text = text;
        this.id_intrebare = id_intrebare;
        this.id_user = id_user;
    }
}
