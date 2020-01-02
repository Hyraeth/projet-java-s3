package Model;

import java.util.ArrayList;

public class Centre implements ZoneCommune{
    private ArrayList<Tuile> contenu;

    public Centre() {
        contenu = new ArrayList<>();
        contenu.add(new PremierJoueur());
    }

    public void ajouterTuile(Tuile t) {
        contenu.add(t);
    }

    public void remove(Tuile t) {
        contenu.removeIf(item -> item.equals(t));
    }

    public int count(Tuile t) {
        int c = 0;
        for (Tuile tuile : contenu) {
            if(tuile.equals(t)) c++;
        }
        return c;
    }

    public boolean isEmpty() {
        return contenu.isEmpty();
    }

    public boolean firstMoveDone() {
        if(contenu.get(0) instanceof PremierJoueur) return false;
        return true;
    }

    public void clear() {
        contenu.clear();
    }

    public String toString() {
        String s ="";
        for (Tuile tuile : contenu) {
            s += tuile.toString() +";";
        }
        return s;
    }
}