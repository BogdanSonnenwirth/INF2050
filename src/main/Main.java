//COURS: INF 2050 groupe 20
//TITRE: Main
//COMMENTAIRE: TP3
//Date de remise: 09/05/21
//Auteur: Bogdan Sonnenwirth  SONB01029707

package main;
import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static String erreurDossier = "Le champ dossier";
    public static String erreurMois = "Le champ mois";
    public static String erreurSoin = "Le champ soin de la ";
    public static String erreurMontant = "Le champ montant de la ";
    public static String erreurDate = "Le champ date associe au ";
    public static boolean estValide = true;
    public static boolean montantMaxEstValide = true;
    public static boolean dossierEstValide = true;
    public static boolean moisEstValide = true;
    public static boolean soinEstValide = true;
    public static boolean montantEstValide = true;
    public static boolean dateEstValide = true;
    public static boolean nbrSoinsParJourEstValide = true;
    public static int positionReclamation = 1;
    public static String dateErreur = "";
    public static String message = "eme reclamation est invalide";
    public static String messageMontant = "";
    public static String messageDate = "eme reclamation ne correspond pas au champ mois de la demande";
    public static String messageDossierMois = " est invalide";
    public static List<String> listeDates = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        if(args.length == 2){ condition2Args(args[0], args[1]); }
        else if(args.length == 3){ condition3Args(args[0], args[1], args[2]); }
        else{
            System.out.println("Le programme accepte seulement 2 ou 3 arguments. Veuillez recommencer");
            System.exit(0);
        }
    }

    private static void condition2Args(String entree, String sortie) throws IOException {
        if(entree.equals("-S") || sortie.equals("-S")) { Statistiques.imprimerStats(); System.exit(0); }
        else if(entree.equals("-SR") || sortie.equals("-SR")){ Statistiques.reinitialiser(); System.exit(0);}
        else{ try { infosDeBase(entree, sortie, "2arguments"); } catch (Exception ex) { ex.printStackTrace(); } }
    }

    private static void condition3Args(String prediction, String entree, String sortie) {
        if(prediction.equals("-p") && entree.equals("entree.json") && sortie.equals("sortie.json")){
            try { infosDeBase(prediction, entree, sortie); } catch (Exception ex) { ex.printStackTrace(); }
        }else{
            System.out.println("Le programme n'accepte que l'argument '-p' comme le premier argument, 'entree.json' comme le deuxieme argument et 'sortie.json' pour le troisieme argument lorsqu'il y a 3 arguments. Veuillez recommencer");
            System.exit(0);
        }
    }

    private static void infosDeBase(String prediction, String entree, String sortie) throws JsonException, IOException {
        if(sortie.equals("2arguments")){ sortie = entree; entree = prediction; prediction = "2arguments"; }
        Reader reader = Files.newBufferedReader(Paths.get(entree));
        JsonObject parser = (JsonObject) Jsoner.deserialize(reader);
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(sortie));
        Map<String, Serializable> fichierFinal = new LinkedHashMap<>();
        String dossier = (String) parser.get("dossier"); String mois = (String) parser.get("mois");
        verifier(dossier, mois, parser, fichierFinal, prediction);
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); gson.toJson(fichierFinal, writer);
        reader.close(); writer.close();
        if(!prediction.equals("-p")){ Statistiques.stats(); }
    }

    private static void verifier(String dossier, String mois, JsonObject parser, Map<String, Serializable> fichierFinal, String prediction) {
        Verification obj = new Verification(); Dollar dollar = new Dollar();
        if(!detValiditeDossier(obj, dossier) || !detValiditeMois(obj, mois)){ nonValide(fichierFinal); }else{
            String contrat = dossier.substring(0, 1);
            JsonArray remboursements = (JsonArray) parser.get("reclamations");
            JsonArray reclamation = new JsonArray();
            remboursements.stream().map(entry -> (JsonObject) entry).forEach(projet ->
            { if(estValide){ creerRemboursements(projet, obj, dollar, mois, contrat, reclamation, prediction); } });
            if(!estValide){ nonValide(fichierFinal); }else {ajoutFichierFinal(fichierFinal, dossier, mois, reclamation);}
        }
    }

    private static void creerRemboursements(JsonObject projet, Verification obj, Dollar dollar, String mois, String contrat, JsonArray reclamation, String prediction){
        try{
            int soin = Integer.parseInt(String.valueOf(projet.get("soin")));
            String date = String.valueOf(projet.get("date"));
            String montant = String.valueOf(projet.get("montant"));
            if(detValidite(obj, dollar, soin, date, mois, montant)) { creerObjetRemboursement(obj, soin, date, montant, dollar, contrat, reclamation, prediction); positionReclamation++; }
        }catch (NullPointerException | NumberFormatException e){
            if(!projet.containsKey("soin")){ erreurSoin = "Le champ soin de la "; message="eme reclamation est null/inexistant"; soinEstValide = false; }
            estValide = false;
        }
    }

    private static void creerObjetRemboursement(Verification obj, int soin, String date, String montant, Dollar dollar, String contrat, JsonArray reclamation, String prediction) {
        int montantFinal = Integer.parseInt(Dollar.formatMontant(montant));
        String deduction = dollar.calcul(obj, soin, contrat, montantFinal);
        Map<String, Serializable> element = new LinkedHashMap<>();
        element.put("soin", soin);
        element.put("date", date);
        element.put("montant", deduction);
        conditionStats(prediction, soin, montantFinal);
        estValide = detValidite500Max(dollar);
        reclamation.addAll(Collections.singletonList(element));
    }

    private static void conditionStats(String prediction, int soin, int montantFinal) {
        if(!prediction.equals("-p")){
            Statistiques.ajoutListe(soin);
            Statistiques.ajoutListeSoinsMontant(soin, montantFinal);
        }
    }

    private static boolean detValidite(Verification obj, Dollar dollar, int soin, String date, String mois, String montant){
        estValide = detValiditeSoin(obj, soin) && detValiditeCompDates(obj, date, mois) && detValiditeNbrSoinsParJour(date) && detValiditeMontant(dollar, montant);
        return estValide;
    }

    private static boolean detValiditeSoin(Verification obj, int soin){
        soinEstValide = obj.valSoin(soin);
        return soinEstValide;
    }

    private static boolean detValiditeCompDates(Verification obj, String date, String mois){
        dateEstValide = obj.comparaisonDates(date, mois);
        return dateEstValide;
    }

    private static boolean detValiditeMontant(Dollar dollar, String montant){
        montantEstValide = dollar.valMontant(montant);
        return montantEstValide;
    }

    private static boolean detValiditeDossier(Verification obj, String dossier){
        dossierEstValide = obj.valDossier(dossier);
        return dossierEstValide;
    }

    private static boolean detValiditeMois(Verification obj, String mois){
        moisEstValide = obj.valMois(mois);
        return moisEstValide;
    }

    private static boolean detValiditeNbrSoinsParJour(String date) {
        listeDates.add(date);
        for(String element : listeDates){
            if (Collections.frequency(listeDates, element) > 4) {
                nbrSoinsParJourEstValide = false;
                dateErreur = element;
                break;
            }
        }
        return nbrSoinsParJourEstValide;
    }

    private static boolean detValidite500Max(Dollar dollar) {
        montantMaxEstValide = dollar.condition500Max();
        return montantMaxEstValide;
    }

    private static void ajoutFichierFinal(Map<String, Serializable> fichierFinal, String dossier, String mois, JsonArray reclamation) {
        fichierFinal.put("dossier", dossier);
        fichierFinal.put("mois", mois);
        fichierFinal.put("remboursements", reclamation);
        fichierFinal.put("total", Dollar.formatDecimale(Dollar.prixTotal) + "$");
    }

    private static void nonValide(Map<String, Serializable> fichierFinal){
        fichierFinal.clear();
        Statistiques.recInvalides();
        if(!dossierEstValide){ fichierFinal.put("message", erreurDossier + messageDossierMois); }
        if(!moisEstValide){ fichierFinal.put("message", erreurMois + messageDossierMois); }
        if(!soinEstValide){ fichierFinal.put("message", erreurSoin + positionReclamation + message); }
        if(!dateEstValide){ fichierFinal.put("message", erreurDate + positionReclamation + messageDate); }
        if(!nbrSoinsParJourEstValide){ fichierFinal.put("message", "Erreur! Il y a plus de 4 soins pour la date de " + dateErreur); }
        if(!montantEstValide){ fichierFinal.put("message", erreurMontant + positionReclamation + message + messageMontant); }
        if(!montantMaxEstValide){ fichierFinal.put("message", "Le montant mensuel reclame pour la categorie de soin " + Dollar.soin + " est superieur de 500.00$"); }
    }
}