import java.util.*;

public class Defausse {

    private static ArrayList<Tuile> contenu;

    public ArrayList<Tuile> getContenu() {
        return this.contenu;
    }

    public Defausse() {
        this.contenu = new ArrayList<>();
    }

    public void enleverTuile() {
        Collections.shuffle(this.contenu);
        this.contenu.remove(0);
    }

    public int nbTuiles() {
        return this.contenu.size();
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

}