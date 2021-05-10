//COURS: INF 2050 groupe 20
//TITRE: Verification
//COMMENTAIRE: TP3
//Date de remise: 09/05/21
//Auteur: Bogdan Sonnenwirth  SONB01029707

package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Verification {

    public int[] determinerPrixPart1(int soin, String contrat){
        int taux; int max;
        if(soin < 400){
            taux = determinerPrixPart2(soin, contrat)[0];
            max = determinerPrixPart2(soin, contrat)[1];
        }else{
            taux = determinerPrixPart3(soin, contrat)[0];
            max = determinerPrixPart3(soin, contrat)[1];
        }
        return new int[]{taux, max};
    }

    public int[] determinerPrixPart2(int soin, String contrat){
        int taux; int max;
        switch (soin) {
            case 0   -> { taux = detPrix0(contrat)[0]; max = detPrix0(contrat)[1]; }
            case 100 -> { taux = detPrix100(contrat)[0]; max = detPrix100(contrat)[1]; }
            case 150 -> { taux = detPrix150(contrat)[0]; max = detPrix150(contrat)[1]; }
            case 175 -> { taux = detPrix175(contrat)[0]; max = detPrix175(contrat)[1]; }
            case 200 -> { taux = detPrix200(contrat)[0]; max = detPrix200(contrat)[1]; }
            default  -> { taux = detPrix300(contrat)[0]; max = detPrix300(contrat)[1]; }
        }
        return new int[]{taux, max};
    }

    public int[] determinerPrixPart3(int soin, String contrat){
        int taux; int max;
        switch (soin) {
            case 400 -> { taux = detPrix400(contrat)[0]; max = detPrix400(contrat)[1]; }
            case 500 -> { taux = detPrix500(contrat)[0]; max = detPrix500(contrat)[1]; }
            case 600 -> { taux = detPrix600(contrat)[0]; max = detPrix600(contrat)[1]; }
            default  -> { taux = detPrix700(contrat)[0]; max = detPrix700(contrat)[1]; }
        }
        return new int[]{taux, max};
    }

    public int[] detPrix0(String contrat){
        int taux; int max;
        switch (contrat) {
            case "A" -> { taux = 25; max = 0; }
            case "B" -> { taux = 50; max = 4000; }
            case "C" -> { taux = 90; max = 0; }
            case "D" -> { taux = 100; max = 8500; }
            default  -> { taux = 15; max = 0; }
        }
        return new int[]{taux, max};
    }

    public int[] detPrix100(String contrat){
        int taux; int max;
        switch (contrat) {
            case "A" -> { taux = 35; max = 0; }
            case "B" -> { taux = 50; max = 5000; }
            case "C" -> { taux = 95; max = 0; }
            case "D" -> { taux = 100; max = 7500; }
            default  -> { taux = 25; max = 0; }
        }
        return new int[]{taux, max};
    }

    public int[] detPrix150(String contrat){
        int taux; int max;
        switch (contrat) {
            case "A", "B" -> { taux = 0; max = 0; }
            case "C"      -> { taux = 85; max = 0; }
            case "D"      -> { taux = 100; max = 15000; }
            default       -> { taux = 15; max = 0; }
        }
        return new int[]{taux, max};
    }

    public int[] detPrix175(String contrat){
        int taux; int max;
        switch (contrat) {
            case "A" -> { taux = 50; max = 0; }
            case "B" -> { taux = 75; max = 0; }
            case "C" -> { taux = 90; max = 0; }
            case "D" -> { taux = 95; max = 0; }
            default  -> { taux = 25; max = 2000; }
        }
        return new int[]{taux, max};
    }

    public int[] detPrix200(String contrat) {
        int taux; int max;
        switch (contrat) {
            case "A" -> { taux = 25; max = 0; }
            case "B" -> { taux = 100; max = 0; }
            case "C" -> { taux = 90; max = 0; }
            case "D" -> { taux = 100; max = 10000; }
            default  -> { taux = 12; max = 0; }
        }
        return new int[]{taux, max};
    }

    public int[] detPrix300(String contrat) {
        int taux; int max;
        switch (contrat) {
            case "A" -> { taux = 0; max = 0; }
            case "B" -> { taux = 50; max = 0; }
            case "C" -> { taux = 90; max = 0; }
            case "D" -> { taux = 100; max = 0; }
            default  -> { taux = 60; max = 0; }
        }
        return new int[]{taux, max};
    }

    public int[] detPrix400(String contrat) {
        int taux; int max;
        switch (contrat) {
            case "A", "B" -> { taux = 0; max = 0; }
            case "C"      -> { taux = 90; max = 0; }
            case "D"      -> { taux = 100; max = 6500; }
            default       -> { taux = 25; max = 1500; }
        }
        return new int[]{taux, max};
    }

    public int[] detPrix500(String contrat){
        int taux; int max;
        switch (contrat) {
            case "A" -> { taux = 25; max = 0; }
            case "B" -> { taux = 50; max = 5000; }
            case "C" -> { taux = 90; max = 0; }
            case "D" -> { taux = 100; max = 0; }
            default  -> { taux = 30; max = 2000; }
        }
        return new int[]{taux, max};
    }

    public int[] detPrix600(String contrat) {
        int taux; int max;
        switch (contrat) {
            case "A" -> { taux = 40; max = 0; }
            case "B" -> { taux = 100; max = 0; }
            case "C" -> { taux = 75; max = 0; }
            case "D" -> { taux = 100; max = 10000; }
            default  -> { taux = 15; max = 0; }
        }
        return new int[]{taux, max};
    }

    public int[] detPrix700(String contrat) {
        int taux; int max;
        switch (contrat) {
            case "A" -> { taux = 0; max = 0; }
            case "B" -> { taux = 70; max = 0; }
            case "C" -> { taux = 90; max = 0; }
            case "D" -> { taux = 100; max = 9000; }
            default  -> { taux = 22; max = 0; }
        }
        return new int[]{taux, max};
    }

    public boolean valDossier(String dossier){
        boolean estValide = false;
        if(dossier != null && !dossier.isEmpty()){
            String contrat = dossier.substring(0, 1);
            if(valContrat(contrat)){ estValide = (dossier.length() == 7) && dossier.substring(1, 7).matches("[0-9]+") ; }
        }else{ Main.messageDossierMois = " est null/inexistant"; }
        return estValide;
    }

    public boolean valContrat(String contrat){
        return contrat != null && (contrat.equals("A") || contrat.equals("B") || contrat.equals("C") || contrat.equals("D") || contrat.equals("E"));
    }

    public boolean valMois(String mois){
        boolean estValide = false;
        if(mois != null && !mois.isEmpty()){
            if( mois.charAt(4) == '-'){
                int intMois = Integer.parseInt(mois.substring(5, 7));
                int intAnnee = Integer.parseInt(mois.substring(0, 4));
                estValide = formatMois(mois, intMois, intAnnee);
            }
        }else{ Main.messageDossierMois = " est null/inexistant"; }
        return estValide;
    }

    public boolean valSoin(int soin){
        return (soin == 0 || soin == 100 || soin == 150 || soin == 175 || soin == 200 || soin == 500 || soin == 600 || soin == 700 || (soin >= 300 && soin <= 400));
    }

    public boolean comparaisonDates(String date, String mois){
        boolean estValide = true;
        try{
            LocalDate.parse(date, DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT));
            date = date.substring(0,7);
        }catch(DateTimeParseException e){
            if(date.equals("null") || date.equals("")){ Main.messageDate = "eme reclamation est null/inexistant"; } else{ Main.messageDate = Main.message; }
            Main.dateEstValide = false; estValide = false;
        }
        if(!date.equals(mois)){ estValide = false; }
        return estValide;
    }

    public static boolean formatMois(String mois, int intMois, int intAnnee){
        return mois.length() == 7 && !mois.matches("[a-zA-Z]+") && intMois <= 12 && intMois > 0 && intAnnee <= 2021 && intAnnee >= 0 && mois.contains("-");
    }

    public int detMaxMensuel(int soin){
        //les max sont multiplies par 100, car les multiplications se font en int
        int max;
        switch (soin) {
            case 100, 200 -> max = 25000;
            case 175      -> max = 20000;
            case 500      -> max = 15000;
            case 600      -> max = 30000;
            default       -> max = 0;
        }
        return max;
    }
}