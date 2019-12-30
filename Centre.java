package Project;

import java.util.ArrayList;

public class Centre {
    private ArrayList<Tuile> contenu;

    public Centre() {
        contenu = new ArrayList<>();
        contenu.add(new PremierJoueur());
    }

    public void ajouterTuile(Tuile t) {
        contenu.add(t);
    }
}