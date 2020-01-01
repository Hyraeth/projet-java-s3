import java.util.*;

public class Defausse {

    private static ArrayList<Tuile> contenu;

    public ArrayList<Tuile> getContenu() {
        return Defausse.contenu;
    }

    public Defausse() {
        Defausse.contenu = new ArrayList<>();
    }

    public void enleverTuile() {
        Collections.shuffle(Defausse.contenu);
        Defausse.contenu.remove(0);
    }

    public int nbTuiles() {
        return Defausse.contenu.size();
    }

    public static void ajouterTuile(Tuile t) {
        Defausse.contenu.add(t);
        Collections.shuffle(Defausse.contenu);
    }

    public static void ajouterTuile(int n, Tuile t) {
        for (int i = 0; i < n; i++) {
            Defausse.contenu.add(t);
            Collections.shuffle(Defausse.contenu);
        }
    }

    public String toString() {
        String s = "";
        for (Tuile tuile : contenu) {
            s += tuile.toString() +"; ";
        }
        return s;
    }

}