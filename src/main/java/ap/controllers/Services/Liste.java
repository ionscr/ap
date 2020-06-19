package ap.controllers.Services;
import java.util.ArrayList;
public class Liste {
    static ArrayList<Articol> articoleList = new ArrayList<>();
    static ArrayList<Intrebare> intrebareList = new ArrayList<>();
    static ArrayList<Raspuns> raspunsuriList = new ArrayList<>();


    public static void push(Articol articol) {
        articoleList.add(articol);
    }

    public static void push(Intrebare intrebare) {
        intrebareList.add(intrebare);
    }

    public static void push(Raspuns raspuns) {
        raspunsuriList.add(raspuns);
    }

    public static int getArticlesAmount() {
        return articoleList.size();
    }

    public static Articol getArticol(int i) {
        return articoleList.get(i);
    }

    public static int getintrebareAmount() {
        return intrebareList.size();
    }

    public static Intrebare getIntrebare(int i) {
        return intrebareList.get(i);
    }

    public static int getRaspunsuriAmount() {
        return raspunsuriList.size();
    }
    
    public static Raspuns getRaspuns(int i) {
        return raspunsuriList.get(i);
    }

    public static int deleteArticol(int id) {
        for (Articol articol : articoleList) {
            if (articol.id == id) {
                articoleList.remove(articol);
                return 1;
            }
        }
        return 0;
    }

    public static int updateArticol(Articol updatedArticol) {
        int i=0;
        for (Articol articol : articoleList) {
            if (articol.id == updatedArticol.id) {
                articoleList.set(i, updatedArticol);
                return 1;
            }
            i++;
        }
        return 0;
    }

    public static Articol getArticolById(int id) {
        for (Articol articol : articoleList) {
            if (articol.id == id) {
                return articol;
            }
        }
        return null;
    }
}