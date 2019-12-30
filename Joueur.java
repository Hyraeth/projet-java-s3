package Project;

public class Joueur {
    private int score;
    private String nom;
    private Plateau plateau;

    public Joueur (String n) {
        nom = n;
        score = 0;
        plateau = new Plateau();
    }
}