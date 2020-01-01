import java.util.*;

public class Sac {

    private ArrayList<Tuile> contenu;
    public ArrayList<Tuile> getContenu() { return this.contenu; }

    public Sac () {
        for(int i = 0; i < 100; i++) {
            if(i<20) this.contenu.add(new Tuile("bleu"));
            if(i>=20 && i<40) this.contenu.add(new Tuile("jaune"));
            if(i>=40 && i<60) this.contenu.add(new Tuile("rouge"));
            if(i>=60 && i<80) this.contenu.add(new Tuile("noir"));
            if(i>80) this.contenu.add(new Tuile("blanc"));
        }
        Collections.shuffle(this.contenu);
    }

    public void enleverTuile() {
        Collections.shuffle(this.contenu);
        this.contenu.remove(0);
    }

    public Tuile prendreTuile() {
        Collections.shuffle(this.contenu);
        Tuile t = this.contenu.get(0);
        this.contenu.remove(0);
        return t;
    }

    public Tuile[] prendreTuile(int n) {
        Collections.shuffle(this.contenu);
        Tuile[] t = new Tuile[n];
        for (int i = 0; i < t.length; i++) {
            t[i] = this.prendreTuile();
        }
        return t;
    }

    public int size() {
        return this.contenu.size();
    }

    public void ajouterTuile(Tuile t) {
        if(!(t instanceof PremierJoueur)) {
            this.contenu.add(t);
            Collections.shuffle(this.contenu);
        }
    }

    public void addAll() {
        this.contenu.addAll(Defausse.getContenu());
    }

}