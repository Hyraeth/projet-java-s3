package Model;

public class Tuile {

    private String color;
    public String getColor() { return this.color; }

    public Tuile (String c) {
        this.color = c;
    }

    public boolean equals(Tuile t) {
        if((t instanceof Tuile) && this.color.equals(t.color)) return true;
        else return false;
    }

    public String toString() {
        return color;
    }

} class PremierJoueur extends Tuile {

    public PremierJoueur() {
        super("Premier joueur");
    }
    
}