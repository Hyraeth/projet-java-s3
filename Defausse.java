package Project;

import java.util.*;

public class Defausse {

    private ArrayList<Tuile> contenu;
    public ArrayList<Tuile> getContenu() { return this.contenu; }

    public Defausse () {
        this.contenu = new ArrayList<>();
    }

    public void enleverTuile() {
        Collections.shuffle(this.contenu);
        this.contenu.remove(0);
    }

    public int nbTuiles() {
        return this.contenu.size();
    }

    public void ajouterTuile(Tuile t) {
        this.contenu.add(t);
        Collections.shuffle(this.contenu);
    }

}