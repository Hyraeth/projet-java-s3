package Project;

public class Tuile {

    private String color;
    public String getColor() { return this.color; }

    public Tuile (String c) {
        this.color = c;
    }

} class PremierJoueur extends Tuile {

    public PremierJoueur() {
        super("Premier joueur");
    }
    
}