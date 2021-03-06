package Model;

import java.util.*;

public class Jeu {

    private Sac sac;
    private Defausse defausse;
    private Fabrique[] fabriques;
    private Centre centre;
    private Joueur[] joueurs;
    private int option;
    
    public Jeu(ArrayList<String> noms, int o) {
        this.sac = new Sac();

        this.defausse = new Defausse();

        this.fabriques = new Fabrique[(noms.size())*2+1];
        for (int i = 0; i < fabriques.length; i++) {
            this.fabriques[i] = new Fabrique();
            this.fabriques[i].remplir(sac);
        }

        this.centre = new Centre();
        
        this.joueurs = new Joueur[noms.size()];
        for (int i = 0; i<joueurs.length; i++) {
            this.joueurs[i] = (new Joueur(noms.get(i)));
        }
        option = o;
    }

    public Centre getCentre() {
        return centre;
    }

    public int getNombreJoueurs() {
        return joueurs.length;
    }

    public Fabrique getFabrique(int i) {
        return fabriques[i];
    }

    public Joueur getJoueur(int i) {
        return joueurs[i];
    }

    public String getGagnant() {
        Joueur g = joueurs[0];
        for (Joueur j : joueurs) {
            if(j.getScore() > g.getScore()) g = j;
        }
        return g.getNom();
    }

    public void foutre_au_centre() {
        for (Fabrique fabrique : fabriques) {
            if(!fabrique.Remplie()) {
                for (Case c : fabrique.getFabrique()) {
                    if(!c.isEmpty()) centre.ajouterTuile(c.getTuile());
                }
                fabrique.clear();
            }
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
        play();
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
    /*public void manche(int i) {
        boolean fabriques_vides = false;
        tour(i);
        i = ((i+1)%this.joueurs.length);

        if (!(fabriques_vides() && centre.isEmpty())){  //tant que fabriques et centre pas vides
            this.manche(i);
        }
    }*/

    public boolean fabriques_vides() {
        for (Fabrique fabrique : fabriques) {
            if(!fabrique.isEmpty()) return false;
        }
        return true;
    }

    public boolean mancheFinie() {
        return fabriques_vides()&&centre.isEmpty();
    }

    public void nouvelleManche() {
        if(sac.size() < fabriques.length * 4) sac.addAll();
            for (Fabrique fabrique : fabriques) {
                fabrique.remplir(sac);
            }   
        centre.ajouterTuile(new PremierJoueur());
        for (Joueur j : joueurs) {
            j.updateWall(); //Deplacer les tuiles des lignes motifs remplie dans le mur et mettre a jour le score
            j.moveToDefausse(); //Deplacer les tuiles des lignes motifs dans la défausse
        }
    }

    public int getPremierJoueur() {
        for (int k = 0; k < joueurs.length; k++) {
            if(joueurs[k].getPlateau().getPlancher().getPlancher().contains(new PremierJoueur())) return k;
        } 
        return (int) (Math.random())*(this.joueurs.length-1);
    }

    public void play() {
        while(!partieFinie()) {
            if(sac.size() < fabriques.length * 4) sac.addAll();
            for (Fabrique fabrique : fabriques) {
                fabrique.remplir(sac);
            }
            int i = getPremierJoueur();
            while (!centre.isEmpty() || !fabriques_vides()) {
                System.out.println("Tour du joueur "+joueurs[i].getNom());
                for (int j = 0; j < fabriques.length; j++) {
                    if(!fabriques[j].isEmpty()) System.out.println("Fabrique ("+j+") : "+fabriques[j]);
                }
                System.out.println("Centre : "+centre);
                System.out.println(joueurs[i]);
                i=joue(i%joueurs.length);
                i = (i+1)%joueurs.length;
                //System.out.println(joueurs[i]);
            }
            //fin manche
            centre.ajouterTuile(new PremierJoueur());
            for (Joueur j : joueurs) {
                j.updateWall(); //Deplacer les tuiles des lignes motifs remplie dans le mur et mettre a jour le score
                j.moveToDefausse(); //Deplacer les tuiles des lignes motifs dans la défausse
            }
        }
    }

    public int joue(int i) {
        Scanner sc = new Scanner(System.in);

        String zone = "";  //Fab ou centre ?
        while (!(zone.equals("f") || zone.equals("c"))) {
            System.out.println("fabrique(f) ou centre(c) ?");
            zone = sc.nextLine();
        }
        int fab = -1; 
        if (zone.equals("f") && !fabriques_vides()) { //Quelle fabrique ?
            Scanner sc2 = new Scanner(System.in);
            while (fab<0 || fab>=fabriques.length || fabriques[fab].isEmpty()) {
                System.out.println("Quelle fabrique choisissez vous ?");
                for (int j = 0; j < fabriques.length; j++) {
                    if(!fabriques[j].isEmpty()) System.out.println("Fabrique ("+j+") : "+fabriques[j]);
                }
                fab = sc2.nextInt();
            }
        }
        Scanner sc3 = new Scanner(System.in);
        String color = "";  //Quelle couleur de tuile ?
        while (!(color.equals("bleu") || color.equals("jaune") || color.equals("rouge") || color.equals("noir") || color.equals("blanc") || color.equals("Premier joueur"))) {
            System.out.println("Quelle couleur de tuile choisissez vous ?");
            if(zone.equals("f")) System.out.println(fabriques[fab]);
            else System.out.println(centre);
            color = sc3.nextLine();
        }
        Scanner sc4 = new Scanner(System.in);
        String destination = "";  //Vers où ?
        while (!(destination.equals("l") || destination.equals("p"))) {
            System.out.println("vers ligne motif(l) ou vers le plancher(p)");
            destination = sc4.nextLine();
        }
        Scanner sc5 = new Scanner(System.in);
        int ligne = -1;
        if (destination.equals("l")) {  //Quelle ligne ?
            while (ligne<0 || ligne>4) {
                System.out.println("Quelle ligne choisissez vous ?");
                System.out.println("Donnez un nombre entre 0 et 4");
                ligne = sc5.nextInt();
            }
        }
        if(zone.equals("f")) {
            if(destination.equals("l")) {
                if(fabriques[fab].count(new Tuile(color))!=0 && joueurs[i].ajouterTuile(fabriques[fab], new Tuile(color), ligne)) {
                    foutre_au_centre();
                    fabriques[fab].clear();
                } else {
                    System.out.println("AJOUT IMPOSSIBLE");
                    return i-1;
                }
            }
            else {
                if(fabriques[fab].count(new Tuile(color))!=0 ) {
                    joueurs[i].ajouterTuilePlancher(fabriques[fab], new Tuile(color));
                    foutre_au_centre();
                    fabriques[fab].clear();
                }else {
                    System.out.println("AJOUT IMPOSSIBLE");
                    return i-1;
                }
            }
        } else {
            if(destination.equals("l")) {
                if(centre.count(new Tuile(color))!=0) joueurs[i].ajouterTuile(centre, new Tuile(color), ligne);
                else {
                    System.out.println("AJOUT IMPOSSIBLE");
                    return i-1;
                }
            }
            else {
                if(centre.count(new Tuile(color))!=0) joueurs[i].ajouterTuilePlancher(fabriques[fab], new Tuile(color));
                else {
                    System.out.println("AJOUT IMPOSSIBLE");
                    return i-1;
                }
            }
        }
        return i;

    }
    public static void main(String[] args) {
        ArrayList<String> a = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        int nbjoueur = 0;
        while (nbjoueur<2 || nbjoueur>4) {
            System.out.println("nombre de joueur ? (entre 2 et 4)");
            nbjoueur = scan.nextInt();
        }
        Scanner scan2 = new Scanner(System.in);
        String j = "";
        for (int i=0; i<nbjoueur; i++) {
            System.out.println("joueur n°" + (i+1));
            j = scan2.nextLine();
            a.add(j);
        }
        Jeu jeu = new Jeu(a, 0);
        jeu.jouer();
    }
}