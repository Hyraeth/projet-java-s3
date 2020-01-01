public class Joueur {
    private int score;
    public int getScore() {return this.score;}
    private String nom;
    public String getNom() {return this.nom;}
    private Plateau plateau;
    public Plateau getPlateau() { return this.plateau; }

    public Joueur (String n) {
        nom = n;
        score = 0;
        plateau = new Plateau();
    }
// Il faut prendre en compte la tuile premier joueur
    public boolean ajouterTuile(ZoneCommune zc, Tuile t, int ligne) {
        int n = zc.count(t);
        if(zc instanceof Centre && !((Centre) zc).firstMoveDone()) {
            plateau.getPlancher().add(1, new PremierJoueur());
            zc.remove(new PremierJoueur());
        }
        if(plateau.addTuile(ligne, n, t)) {
            zc.remove(t);
            return true;
        }
        else{
            System.out.println("Ajout impossible");
            return false;
        }
    }

    public void ajouterTuilePlancher(ZoneCommune zc, Tuile t) {
        int n = zc.count(t);
        plateau.addTuilePlancher(n, t);
    }

    public static void main(String[] args) {
        Centre c = new Centre();
        c.ajouterTuile(new Tuile("rouge"));
        Joueur j = new Joueur("truc");
        System.out.println(j.ajouterTuile(c, new Tuile("rouge"), 4));
        System.out.println(j.plateau);
    }

	public void updateWall() {
        score = plateau.updateWall();
	}

	public void moveToDefausse() {
        plateau.moveToDefausse();
    }
    
}