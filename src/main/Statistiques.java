//COURS: INF 2050 groupe 20
//TITRE: Statistiques
//COMMENTAIRE: TP3
//Date de remise: 09/05/21
//Auteur: Bogdan Sonnenwirth  SONB01029707

package main;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Statistiques {

    public static int reclamationsValides = 0;
    public static int reclamationsInvalides = 0;
    public static int montantMaxReclame = 0;
    public static int montantMoyenReclame = 0;
    public static int compteur = 0;
    public static ArrayList<String> listeSoinsMontant = new ArrayList<>();
    public static ArrayList<Integer> listeSoins = new ArrayList<>();
    public static ArrayList<Integer> liste = new ArrayList<>();

    public static void ajoutListeSoinsMontant(int soin, int montantReclame){
        String entree = soin + ":" + montantReclame;
        listeSoinsMontant.add(entree);
    }

    public static void ajoutListe(int soin){
        listeSoins.add(soin);
        reclamationsValides++;
    }

    public static void recInvalides(){
        reclamationsInvalides++;
    }

    public static void stats() {
        File f = new File("statistiques.txt");
        if(!Main.estValide){ reclamationsValides = 0; }
        if(f.exists()){ modifier(f); }
        else{
            try {
                FileWriter myWriter = new FileWriter("statistiques.txt");
                ecrit(myWriter);
                myWriter.close();
            } catch (IOException e) { e.printStackTrace(); }
        }
    }

    public static void ecrit(FileWriter myWriter) throws IOException {
        myWriter.write("Le nombre de reclamations valides traitees est " + reclamationsValides + '\n');
        myWriter.write("Le nombre de demandes rejetees est " + reclamationsInvalides + '\n');
        if(Main.estValide)
            listeSoins.forEach((n) -> { if (!liste.contains(n)) {
                liste.add(n); int freq = Collections.frequency(listeSoins, n);
                String max = Dollar.formatDecimale(montantMaxReclame(n.toString())) + "$";
                String moyenne = Dollar.formatDecimale(moyenneMontantReclame(n.toString())) + "$";
                montantMaxReclame = 0; montantMoyenReclame = 0; compteur = 0;
                try { myWriter.write("- soin " + n + " : " + freq + "    Montant maximal reclame: " + max  + "    Moyenne des montants reclames: " + moyenne +  '\n'); } catch (IOException e) { e.printStackTrace(); }
            }});
    }

    private static int moyenneMontantReclame(String soin) {
        listeSoinsMontant.forEach((n) ->{
            int i = Integer.parseInt(n.substring(n.indexOf(":") + 1));
            if(n.substring(0, n.indexOf(":")).equals(soin)){
                compteur++;
                montantMoyenReclame += i;
            }
        });
        montantMoyenReclame = montantMoyenReclame/compteur;
        return montantMoyenReclame;
    }

    private static int montantMaxReclame(String soin) {
        listeSoinsMontant.forEach((n) ->{
            int i = Integer.parseInt(n.substring(n.indexOf(":") + 1));
            if(n.substring(0, n.indexOf(":")).equals(soin) && i > montantMaxReclame){
                montantMaxReclame = i;
            }
        });
        return montantMaxReclame;
    }

    public static void modifier(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String ligne = reader.readLine();
            lireFichier(reader, ligne);
            reader.close();
            FileWriter myWriter = new FileWriter("statistiques.txt");
            ecrit(myWriter);
            myWriter.close();
        } catch (IOException e) { e.printStackTrace(); }
    }

    private static void lireFichier(BufferedReader reader, String ligne) throws IOException {
        int ligneNbr = 0;
        while (ligne != null) {
            if(ligneNbr == 0 && ligne.split("est ",2)[1].matches("^\\d*[1-9]\\d*$")) {
                reclamationsValides = Integer.parseInt(ligne.split("est ",2)[1]) + reclamationsValides;
            }else if(ligneNbr == 1 && ligne.split("est ",2)[1].matches("^\\d*[1-9]\\d*$")){
                reclamationsInvalides = Integer.parseInt(ligne.split("est ",2)[1]) + reclamationsInvalides;
            }
            ligne = reader.readLine();
            ligneNbr++;
        }
    }

    public static void imprimerStats(){
        try (BufferedReader br = new BufferedReader(new FileReader("statistiques.txt"))) {
            String ligne;
            while ((ligne = br.readLine()) != null) { System.out.println(ligne); }
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static void reinitialiser() throws IOException {
        listeSoins.clear();
        liste.clear();
        reclamationsValides = 0;
        reclamationsInvalides = 0;
        FileWriter myWriter = new FileWriter("statistiques.txt");
        ecrit(myWriter);
        myWriter.close();
        System.out.println("Les statistiques sont reinitialisees!");
    }
}