public class Joueur {
    private int score;
    private String nom;
    private Plateau plateau;

    public Joueur (String n) {
        nom = n;
        score = 0;
        plateau = new Plateau();
    }

    public void ajouterTuile(ZoneCommune zc, Tuile t, int ligne) {
        int n = zc.count(t);
        if(plateau.addTuile(ligne, n, t))
            zc.remove(t);
        else
            System.out.println("Ajout impossible");
    }
}