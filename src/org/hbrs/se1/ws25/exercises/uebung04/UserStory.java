package org.hbrs.se1.ws25.exercises.uebung04;

import java.io.Serializable;

public class UserStory implements Serializable {

    private final int id;
    private final String titel;
    private final String akzeptanzkriterium;
    private final String projekt;


    //Kennzahlen nach Gloger
    private final int mehrwert;
    private final int strafe;
    private final int risiko;
    private final int aufwand;
    private final double prioritierung;

    public UserStory(int id, String titel, String akzeptanzkriterium, String projekt,
                     int mehrwert, int strafe, int risiko, int aufwand) {
        this.id = id;
        this.titel = titel;
        this.akzeptanzkriterium = akzeptanzkriterium;
        this.projekt = projekt;
        this.mehrwert = mehrwert;
        this.strafe = strafe;
        this.risiko = risiko;
        this.aufwand = aufwand;
        this.prioritierung = berechnePrioritaet();
    }

    private double berechnePrioritaet() {
        if (aufwand <= 0) {
            return 0.0;
        }
        return (mehrwert + strafe + risiko) / (double) aufwand;
    }

    public boolean isValid() {
        return id > 0
                && titel != null && !titel.isBlank()
                && akzeptanzkriterium != null && !akzeptanzkriterium.isBlank()
                && mehrwert >= 0 && strafe >= 0 && risiko >= 0 && aufwand > 0;
    }

    public int getId() { return id; }
    public String getTitel() { return titel; }
    public String getAkzeptanzkriterium() { return akzeptanzkriterium; }
    public String getProjekt() { return projekt; }
    public int getMehrwert() { return mehrwert; }
    public int getStrafe() { return strafe; }
    public int getRisiko() { return risiko; }
    public int getAufwand() { return aufwand; }
    public double getPrioritierung() { return prioritierung; }

    public String toString() {
        return String.format(
                "ID: %d | Titel: %s | Akzeptanzkriterium: %s | Projekt: %s | M:%d S:%d R:%d A:%d | Priorität: %.2f",
                id, titel, akzeptanzkriterium, projekt, mehrwert, strafe, risiko, aufwand, prioritierung);
    }

    /*
    Mal schauen ob dieser Konstruktor relevant wird

    public UserStory(int ID, String Title) {
        this.id = id;
        this.titel = titel;
    }
     */
}
