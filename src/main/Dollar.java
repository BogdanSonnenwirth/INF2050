//COURS: INF 2050 groupe 20
//TITRE: Verification
//COMMENTAIRE: TP3
//Date de remise: 09/05/21
//Auteur: Bogdan Sonnenwirth  SONB01029707

package main;
import java.text.NumberFormat;
import java.util.Locale;

public class Dollar {

    public static int prixTotal = 0;
    public static int prixTotal100 = 0;
    public static int prixTotal175 = 0;
    public static int prixTotal200 = 0;
    public static int prixTotal500 = 0;
    public static int prixTotal600 = 0;
    public static int prixMaxTotal0 = 0;
    public static int prixMaxTotal100 = 0;
    public static int prixMaxTotal150 = 0;
    public static int prixMaxTotal175 = 0;
    public static int prixMaxTotal200 = 0;
    public static int prixMaxTotal300 = 0;
    public static int prixMaxTotal400 = 0;
    public static int prixMaxTotal500 = 0;
    public static int prixMaxTotal600 = 0;
    public static int prixMaxTotal700 = 0;
    public static String soin = "";

    public boolean valMontant(String montant){
        boolean estValide = false;
        if(montant.equals("") || montant.equals("null")){
            Main.message = " est null/inexistant";
            Main.montantEstValide = false;
            Main.estValide = false;
        }else{
            int montantSansConfig = Integer.parseInt(formatMontant(montant));
            if(montant.contains("$")){ estValide = montantSousZero(montantSansConfig); }
        }
        return estValide;
    }

    public boolean montantSousZero(int montantSansConfig) {
        boolean estValide = true;
        if(montantSansConfig <= 0 ) {
            Main.messageMontant = ", car le montant est inferieur a 0.00$";
            estValide = false;
        }
        return estValide;
    }

    public static String formatMontant(String montant){
        if(montant.contains("$")){montant = montant.substring(0, montant.length()- 1); }
        if(montant.contains(",")){ montant = montant.replace(",", ""); }
        if(montant.contains(".")){ montant = montant.replace(".", ""); }
        return montant;
    }

    public static String formatDecimale(int montant){
        String montantDeduitCents = String.valueOf(montant % 100);
        String montantDeduitDollars = String.valueOf(montant/100);
        double montantDeduit = Double.parseDouble(montantDeduitDollars + "." + montantDeduitCents);
        Locale locale = Locale.ENGLISH;
        NumberFormat nf = NumberFormat.getNumberInstance(locale);
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        return nf.format(montantDeduit);
    }

    public String calcul(Verification obj, int soin, String contrat, int montant){
        int taux = obj.determinerPrixPart1(soin, contrat)[0];
        int max = obj.determinerPrixPart1(soin, contrat)[1];
        max500Part1(soin, montant);
        if(taux > 0){ montant = montant * taux/100; } else{ montant = 0; }
        if(max != 0){ if(montant > max){ montant = max; } }
        int prixMax = obj.detMaxMensuel(soin);
        int montantDeduit = detQuelPrixUtiliserPart1(soin, montant, prixMax);
        return formatDecimale(montantDeduit) +"$";
    }

    private void max500Part1(int soin, int montant) {
        if(soin < 400) {
            switch (soin) {
                case 0   -> prixMaxTotal0 += montant;
                case 100 -> prixMaxTotal100 += montant;
                case 150 -> prixMaxTotal150 += montant;
                case 175 -> prixMaxTotal175 += montant;
                case 200 -> prixMaxTotal200 += montant;
                default  -> prixMaxTotal300 += montant;
            }
        }else{ max500Part2(soin, montant); }
    }

    private void max500Part2(int soin, int montant) {
        switch(soin){
            case 400      -> prixMaxTotal400 += montant;
            case 500      -> prixMaxTotal500 += montant;
            case 600      -> prixMaxTotal600 += montant;
            default       -> prixMaxTotal700 += montant;
        }
    }

    public boolean condition500Max() {
        boolean estValide = true;
        if(prixMaxTotal0 > 50000){ estValide = false; soin = "massotherapie (0)"; }
        if(prixMaxTotal100 > 50000){ estValide = false; soin = "osteopathie (100)"; }
        if(prixMaxTotal150 > 50000){ estValide = false; soin = "kinesitherapie (150)"; }
        if(prixMaxTotal175 > 50000){ estValide = false; soin = "medecin generaliste prive (175)"; }
        if(prixMaxTotal200 > 50000){ estValide = false; soin = "psychologie individuelle (200)"; }
        if(prixMaxTotal300 > 50000){ estValide = false; soin = "soins dentaires (300-399)"; }
        estValide = condition500MaxContinue(estValide);
        return estValide;
    }

    public boolean condition500MaxContinue(boolean estValide) {
        if(estValide){
            if(prixMaxTotal400 > 50000){ estValide = false; soin = "naturopathie, acuponture (400)"; }
            if(prixMaxTotal500 > 50000){ estValide = false; soin = "chiropratie (500)"; }
            if(prixMaxTotal600 > 50000){ estValide = false; soin = "physiotherapie (600)"; }
            if(prixMaxTotal700 > 50000){ estValide = false; soin = "orthophonie, ergotherapie (700)"; }
        }
        return estValide;
    }

    public static int detQuelPrixUtiliserPart1(int soin, int deduction, int prixMax) {
        int montantDeduit = 0;
        if(soin < 200){
            switch(soin) {
                case 0   -> montantDeduit = prix0(deduction);
                case 100 -> montantDeduit = prix100(deduction, prixMax);
                case 150 -> montantDeduit = prix150(deduction);
                default  -> montantDeduit = prix175(deduction, prixMax);
            }
        }else{ montantDeduit = detQuelPrixUtiliserPart2(soin, deduction, prixMax); }
        return montantDeduit;
    }

    private static int detQuelPrixUtiliserPart2(int soin, int deduction, int prixMax) {
        int montantDeduit = 0;
        switch(soin) {
            case 200      -> montantDeduit = prix200(deduction, prixMax);
            case 400      -> montantDeduit = prix400(deduction);
            case 500      -> montantDeduit = prix500(deduction, prixMax);
            case 600      -> montantDeduit = prix600(deduction, prixMax);
            case 700      -> montantDeduit = prix700(deduction);
            default       -> montantDeduit = prix300(deduction);
        }
        return montantDeduit;
    }

    public static int prix0(int deduction){
        prixTotal += deduction;
        return deduction;
    }

    public static int prix100(int deduction, int prixMax){
        if(prixTotal100 <= prixMax){
            if(prixTotal100 + deduction >= prixMax){ deduction = prixMax - prixTotal100; }
            prixTotal100 += deduction;
            prixTotal += deduction;
        }
        return deduction;
    }

    public static int prix150(int deduction){
        prixTotal += deduction;
        return deduction;
    }

    public static int prix175(int deduction, int prixMax){
        if(prixTotal175 <= prixMax){
            if(prixTotal175 + deduction >= prixMax){ deduction = prixMax - prixTotal175; }
            prixTotal175 += deduction;
            prixTotal += deduction;
        }
        return deduction;
    }
    public static int prix200(int deduction, int prixMax){
        if(prixTotal200 <= prixMax){
            if(prixTotal200 + deduction >= prixMax){ deduction = prixMax - prixTotal200; }
            prixTotal200 += deduction;
            prixTotal += deduction;
        }
        return deduction;
    }

    public static int prix300(int deduction){
        prixTotal += deduction;
        return deduction;
    }

    public static int prix400(int deduction){
        prixTotal += deduction;
        return deduction;
    }

    public static int prix500(int deduction, int prixMax){
        if(prixTotal500 <= prixMax){
            if(prixTotal500 + deduction >= prixMax){ deduction = prixMax - prixTotal500; }
            prixTotal500 += deduction;
            prixTotal += deduction;
        }
        return deduction;
    }
    public static int prix600(int deduction, int prixMax){
        if(prixTotal600 <= prixMax){
            if(prixTotal600 + deduction >= prixMax){ deduction = prixMax - prixTotal600; }
            prixTotal600 += deduction;
            prixTotal += deduction;
        }
        return deduction;
    }

    public static int prix700(int deduction){
        prixTotal += deduction;
        return deduction;
    }
}