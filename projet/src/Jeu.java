import java.util.*;

public class Jeu {

    private Sac sac;
    private Defausse defausse;
    private Fabrique[] fabriques;
    private Centre centre;
    private Joueur[] joueurs;
    
    public Jeu(ArrayList<String> noms) {
        this.sac = new Sac();

        this.defausse = new Defausse();

        this.fabriques = new Fabrique[(noms.size())*2+1];
        for (int i = 0; i < fabriques.length; i++) {
            this.fabriques[i] = new Fabrique();
        }

        this.centre = new Centre();
        
        this.joueurs = new Joueur[noms.size()];
        for (int i = 0; i<joueurs.length; i++) {
            this.joueurs[i] = (new Joueur(noms.get(i)));
        }
    }

    public boolean partieFinie() {
        for (Joueur joueur : joueurs) {
            if(joueur.getPlateau().getMur().ligneRemplie()) return true;
        }
        return false;
    }

    //tant que il n'y a pas de lignes de faite (partie pas terminer)
    public void jouer () {
        int i =(int) (Math.random())*(this.joueurs.length-1);
        while (!partieFinie()) {
            this.manche(i);
            //FIXED ME : i= premier joueur
        }
        System.out.println("PARTIE TERMINEE");
        LinkedList<Joueur> gagnants = new LinkedList<>();  //liste de(s) gagnant(s)
        gagnants.add(joueurs[0]);
        for (Joueur j : joueurs) {
            if (j.getScore() == gagnants.get(0).getScore()) {
                gagnants.add(j);
            } else if (j.getScore() > gagnants.get(0).getScore()) {
                gagnants = new LinkedList<>();
                gagnants.add(j);
            }
        }
        //affichage des/du gagnant
        if (gagnants.size()==1) {
            System.out.println("le gagnant est " + gagnants.get(0));
        } else {
            System.out.print("les gagnants sont ");
            for (Joueur j : gagnants) {
                System.out.print(j.getNom() + " ");
            }
            System.out.println();
        }
    }

    //tant que il reste des tuiles en jeu (manche pas terminer)
    public void manche(int i) {
        boolean fabriques_vides = false;
        tour(i);
        i = ((i+1)%this.joueurs.length);

        if (!(fabriques_vides() && centre.isEmpty())){  //tant que fabriques et centre pas vides
            this.manche(i);
        }
    }

    public boolean fabriques_vides() {
        for (Fabrique fabrique : fabriques) {
            if(!fabrique.isEmpty()) return false;
        }
        return true;
    }

    public void play(int nbplayer, int option) {
        while(!partieFinie()) {
            if(sac.size() < fabriques.length * 4) sac.addAll();
            for (Fabrique fabrique : fabriques) {
                fabrique.remplir(sac);
            }
            while (centre.isEmpty() && fabriques_vides()) {
                //tour joueurs
            }
            //fin manche
            for (Joueur j : joueurs) {
                j.updateWall(); //Deplacer les tuiles des lignes motifs remplie dans le mur et mettre a jour le score
                j.moveToDefausse(); //Deplacer les tuiles des lignes motifs dans la défausse
            }
        }
    }

    //tour du joueur i
    public void tour(int i) {

    }
}