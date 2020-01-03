package View;

import Model.Jeu;
import Model.Tuile;

public class Controleur {

    public Jeu model;
    public Vue vue;

    public Controleur(Jeu j) {
        model = j;
    }

    public void eleverTuileCentre(Tuile t) {
        model.enleverTuileCentre(t);
        vue.MAJ_Centre();
    }

    public void enleverTuileFabrique(Tuile t, int i) {
        model.enleverTuileFabrique(t, i);
        vue.MAJ_Fabrique();
        vue.MAJ_Centre();
    }
}