package com.drinf.app;

public class Formulaire {

    private double taux;
    private boolean probatoire;
    private boolean recidive;

    public Formulaire() {
    }

    public Formulaire(double taux, boolean probatoire, boolean recidive) {
        this.taux = taux;
        this.probatoire = probatoire;
        this.recidive = recidive;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public void setProbatoire(boolean probatoire) {
        this.probatoire = probatoire;
    }

    public void setRecidive(boolean recidive) {
        this.recidive = recidive;
    }

    public double getTaux() {
        return taux;
    }

    public boolean isProbatoire() {
        return probatoire;
    }

    public boolean isRecidive() {
        return recidive;
    }
}
