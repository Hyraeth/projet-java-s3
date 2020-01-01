public class Fabrique implements ZoneCommune{

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

    public void remove(Tuile t) {
        for (Case case1 : fabrique) {
            if(case1.getTuile().equals(t)) case1.clear();
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

    public int count(Tuile t) {
        int c = 0;
        for (Case case1 : fabrique) {
            if(case1.getTuile().equals(t)) c++;
        }
        return c;
    }

}