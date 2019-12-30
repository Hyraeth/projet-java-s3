package Project;

public class Fabrique {

    private Case[] fabrique;

    public Fabrique() {
        fabrique = new Case[4];
        for (int i = 0; i < fabrique.length; i++) {
            fabrique[i] = new Case();
        }
    }

    public void remplir(Sac s) {
        if(this.isEmpty()) {
            for (Case c : fabrique) {
                c.addTuile(s.prendreTuile());
            }
        }
    }

    public boolean isEmpty() {
        for (Case c : fabrique) {
            if(!c.isEmpty()) return false;
        }
        return true;
    }

    public void mettreCentre(Centre centre) {
        for (Case c : fabrique) {
            if(!c.isEmpty()) centre.ajouterTuile(c.getTuile());
        }
    }

    public void clear() {
        for (Case c : fabrique) {
            c.clear();
        }
    }

}