package com.drinf.app;

public class Condamnation {

    private int amende;
    private int retraitDePoint;
    private int peinePrison;

    private boolean suspensionPermis;
    private boolean immobilisationVehicule;
    private boolean stageSensibilisation;

    private Formulaire formulaire;

    public Condamnation(Formulaire formulaire) {
        this(formulaire.getTaux(), formulaire.isProbatoire(), formulaire.isRecidive());
    }

    public Condamnation(double taux, boolean permisProbatoire, boolean recidive) {
        formulaire = new Formulaire(taux, permisProbatoire, recidive);
    }

    public void genererCondamnation() {
        amende = 0;
        retraitDePoint = 0;
        peinePrison = 0;
        suspensionPermis = false;
        immobilisationVehicule = false;
        stageSensibilisation = false;
        if (formulaire.getTaux() >= 0.2) {
            if (formulaire.isProbatoire()) {
                suspensionPermis = true;
                amende = 750;
                immobilisationVehicule = true;
            } else if (formulaire.getTaux() >= 0.5) {
                immobilisationVehicule = true;
                suspensionPermis = true;
                retraitDePoint = 6;
                amende = 750;
                if (formulaire.getTaux() >= 0.8) {
                    retraitDePoint = 6;
                    suspensionPermis = true;
                    immobilisationVehicule = true;
                    stageSensibilisation = true;
                    if (formulaire.isRecidive()) {
                        amende = 9000;
                        peinePrison = 4;
                    } else {
                        amende = 4500;
                        peinePrison = 2;
                    }
                }
            }
        }
    }

    public int getAmende() {
        return amende;
    }

    public int getRetraitDePoint() {
        return retraitDePoint;
    }

    public int getPeinePrison() {
        return peinePrison;
    }

    public boolean isSuspensionPermis() {
        return suspensionPermis;
    }

    public boolean isImmobilisationVehicule() {
        return immobilisationVehicule;
    }

    public boolean isStageSensibilisation() {
        return stageSensibilisation;
    }
}
