//COURS: INF 2050 groupe 20
//TITRE: VerificationTest
//COMMENTAIRE: TP3
//Date de remise: 09/05/21
//Auteur: Bogdan Sonnenwirth  SONB01029707

package test;
import main.Dollar;
import main.Verification;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class VerificationTest {

    @Test
    void calcul() {
        int numeroDeSoin; String contrat; String valide; int montant;
        numeroDeSoin = 100;
        contrat = "A";
        montant = 23400;
        Dollar dollar = new Dollar();
        Verification obj = new Verification();
        valide = dollar.calcul(obj, numeroDeSoin, contrat, montant);
        assertEquals("81.90$", valide);
    }

    @Test
    void determinerPrix(){
        int numeroDeSoin; String contrat; int taux; int max;
        numeroDeSoin = 344;
        contrat = "A";
        taux = 0;
        max = 0;
        Verification verif = new Verification();
        int[] valide = verif.determinerPrixPart1(numeroDeSoin, contrat);
        assertArrayEquals(new int[]{taux, max}, valide);
    }

    @Test
    void detPrix0(){
        String contrat; int taux; int max;
        taux = 50;
        max = 4000;
        contrat = "B";
        Verification verif = new Verification();
        int[] valide = verif.detPrix0(contrat);
        assertArrayEquals(new int[]{taux, max}, valide);
    }

    @Test
    void detPrix100() {
        String contrat; int taux; int max;
        taux = 35;
        max = 0;
        contrat = "A";
        Verification verif = new Verification();
        int[] valide = verif.detPrix100(contrat);
        assertArrayEquals(new int[]{taux, max}, valide);
    }

    @Test
    void detPrix150() {
        String contrat; int taux; int max;
        taux = 85;
        max = 0;
        contrat = "C";
        Verification verif = new Verification();
        int[] valide = verif.detPrix150(contrat);
        assertArrayEquals(new int[]{taux, max}, valide);
    }

    @Test
    void detPrix175() {
        String contrat; int taux; int max;
        taux = 90;
        max = 0;
        contrat = "C";
        Verification verif = new Verification();
        int[] valide = verif.detPrix175(contrat);
        assertArrayEquals(new int[]{taux, max}, valide);
    }

    @Test
    void detPrix200() {
        String contrat; int taux; int max;
        taux = 90;
        max = 0;
        contrat = "C";
        Verification verif = new Verification();
        int[] valide = verif.detPrix200(contrat);
        assertArrayEquals(new int[]{taux, max}, valide);
    }

    @Test
    void detPrix300() {
        String contrat; int taux; int max;
        taux = 100;
        max = 0;
        contrat = "D";
        Verification verif = new Verification();
        int[] valide = verif.detPrix300(contrat);
        assertArrayEquals(new int[]{taux, max}, valide);
    }

    @Test
    void detPrix400() {
        String contrat; int taux; int max;
        taux = 0;
        max = 0;
        contrat = "A";
        Verification verif = new Verification();
        int[] valide = verif.detPrix400(contrat);
        assertArrayEquals(new int[]{taux, max}, valide);
    }

    @Test
    void detPrix500() {
        String contrat; int taux; int max;
        taux = 100;
        max = 0;
        contrat = "D";
        Verification verif = new Verification();
        int[] valide = verif.detPrix500(contrat);
        assertArrayEquals(new int[]{taux, max}, valide);
    }

    @Test
    void detPrix600() {
        String contrat; int taux; int max;
        taux = 100;
        max = 10000;
        contrat = "D";
        Verification verif = new Verification();
        int[] valide = verif.detPrix600(contrat);
        assertArrayEquals(new int[]{taux, max}, valide);
    }

    @Test
    void detPrix700() {
        String contrat; int taux; int max;
        taux = 90;
        max = 0;
        contrat = "C";
        Verification verif = new Verification();
        int[] valide = verif.detPrix700(contrat);
        assertArrayEquals(new int[]{taux, max}, valide);
    }

    @Test
    void valDossier() {
        String client;
        client = "19gh9999";
        Verification verif = new Verification();
        boolean valide = verif.valDossier(client);
        assertEquals(false, valide);
    }

    @Test
    void valContrat() {
        String contrat;
        contrat = "Z";
        Verification verif = new Verification();
        boolean valide = verif.valContrat(contrat);
        assertEquals(false, valide);
    }

    @Test
    void valMois() {
        String mois;
        mois = "2017-14";
        Verification verif = new Verification();
        boolean valide = verif.valMois(mois);
        assertEquals(false, valide);
    }

    @Test
    void valSoin() {
        int soin;
        soin = 1100;
        Verification verif = new Verification();
        boolean valide = verif.valSoin(soin);
        assertEquals(false, valide);
    }

    @Test
    void comparaisonDates() {
        String date; String mois;
        date = "2017-01-01";
        mois = "2017-01";
        Verification verif = new Verification();
        boolean valide = verif.comparaisonDates(date, mois);
        assertEquals(true, valide);
    }

    @Test
    void formatMois(){
        String mois; int intMois; int intAnnee;
        mois = "2021-01";
        intMois = 01;
        intAnnee = 2021;
        Verification verif = new Verification();
        boolean valide = verif.formatMois(mois,intMois,intAnnee);
        assertEquals(true,valide);
    }

    @Test
    void detMaxMensuel(){
        int soin;
        soin =100;
        Verification verif = new Verification();
        int max = verif.detMaxMensuel(soin);
        assertEquals(25000,max);
    }
}
