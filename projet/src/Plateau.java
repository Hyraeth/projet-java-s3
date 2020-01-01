public class Plateau {
    private Plancher plancher;
    public Plancher getPlancher() { return plancher; }
    private LigneMotif ligne_motif;
    private Mur mur;

    public Plateau() {
        plancher = new Plancher();

        ligne_motif = new LigneMotif();

        mur = new Mur();
    }

    public boolean addTuile(int ligne, int nombre, Tuile t) {
        int size_prev = ligne_motif.size(ligne);
        if(ligne_motif.add(ligne, nombre, t)) {
            int size_after = ligne_motif.size(ligne);
            plancher.add(nombre-(size_after-size_prev), t);
            return true;
        }
        return false;
    }

}