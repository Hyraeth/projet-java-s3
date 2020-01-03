package View;

import Model.Jeu;
import Model.Joueur;
import Model.Tuile;
import Model.ZoneCommune;

public class Controleur {

    public Jeu model;
    public Vue vue;
    public LastPicked mem;

    public Controleur(Jeu j) {
        model = j;
    }

    public void sauvegarder(Tuile t, ZoneCommune zc) {
        if(t != null)
        mem = new LastPicked(t, zc);
    }

    public void ajouterLigne(int ligne, int id) {
        model.getJoueur(id).ajouterTuile(mem.origine, mem.tuile, ligne);
        model.foutre_au_centre();
        vue.GROSSE_MAJ();
    }

    public void ajouterPlancher(int id) {
        if(mem != null) {
            model.getJoueur(id).ajouterTuilePlancher(mem.origine, mem.tuile);
            mem = null;
            model.foutre_au_centre();
            vue.GROSSE_MAJ();
        }
        
    }
}