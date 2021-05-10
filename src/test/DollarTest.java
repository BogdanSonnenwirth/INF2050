//COURS: INF 2050 groupe 20
//TITRE: DollarTest
//COMMENTAIRE: TP3
//Date de remise: 09/05/21
//Auteur: Bogdan Sonnenwirth  SONB01029707

package test;

import main.Dollar;
import main.Verification;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DollarTest {

    @Test
    void valMontant() {
        String montant;
        montant = "";
        Dollar dollar = new Dollar();
        boolean verif = dollar.valMontant(montant);
        assertEquals(false,verif);
    }

    @Test
    void montantSousZero(){
        int montant;
        montant = -120;
        Dollar dollar = new Dollar();
        boolean valide = dollar.montantSousZero(montant);
        assertEquals(false,valide);
    }

    @Test
    void formatMontant() {
        String montant;
        montant = "14000$";
        Dollar dollar = new Dollar();
        String valide = dollar.formatMontant(montant);
        assertEquals("14000",valide);
    }

    @Test
    void formatDecimale(){
        int montant = 12345;
        Dollar dollar = new Dollar();
        String valide = dollar.formatDecimale(montant);
        assertEquals("123.45", valide);
    }

    @Test
    void calcul(){
        Verification obj = new Verification();
        Dollar dollar = new Dollar();
        int soin = 100;
        String contrat = "B";
        int montant = 50000;
        String valide = dollar.calcul(obj, soin, contrat, montant);
        assertEquals("50.00$", valide);
    }

     @Test
     void condition500Max(){
        Dollar.prixMaxTotal0 =  60000;
        Dollar dollar = new Dollar();
        assertEquals(false, dollar.condition500Max());

    }

    @Test
    void detQuelPrixUtiliser() {
        int soin; int deduction; int prixMax;
        soin = 100;
        deduction = 75;
        prixMax = 75;
        Dollar dollar = new Dollar();
        int montantDeduit = dollar.detQuelPrixUtiliserPart1(soin,deduction,prixMax);
        assertEquals(75,montantDeduit);
    }

    @Test
    void prix0(){
        int deduction;
        deduction = 50;
        Dollar dollar = new Dollar();
        int montant = dollar.prix0(deduction);
        assertEquals(50,montant);
    }

    @Test
    void prix100() {
        int deduction; int prixMax;
        deduction = 75;
        prixMax = 250;
        Dollar dollar = new Dollar();
        int montant = dollar.prix100(deduction,prixMax);
        assertEquals(75,montant);
    }

    @Test
    void prix150(){
        int deduction;
        deduction = 80;
        Dollar dollar = new Dollar();
        int montant = dollar.prix150(deduction);
        assertEquals(80,montant);
    }

    @Test
    void prix175() {
        int deduction; int prixMax;
        deduction = 75;
        prixMax = 200;
        Dollar dollar = new Dollar();
        int montant = dollar.prix175(deduction,prixMax);
        assertEquals(75,montant);
    }

    @Test
    void prix200() {
        int deduction; int prixMax;
        deduction = 75;
        prixMax = 250;
        Dollar dollar = new Dollar();
        int montant = dollar.prix200(deduction,prixMax);
        assertEquals(75,montant);
    }

    @Test
    void prix300(){
        int deduction;
        deduction = 30;
        Dollar dollar = new Dollar();
        int montant = dollar.prix300(deduction);
        assertEquals(30,montant);
    }

    @Test
    void prix400(){
        int deduction;
        deduction = 40;
        Dollar dollar = new Dollar();
        int montant = dollar.prix400(deduction);
        assertEquals(40,montant);
    }

    @Test
    void prix500() {
        int deduction; int prixMax;
        deduction = 75;
        prixMax = 150;
        Dollar dollar = new Dollar();
        int montant = dollar.prix500(deduction,prixMax);
        assertEquals(75,montant);
    }

    @Test
    void prix600() {
        int deduction; int prixMax;
        deduction = 75;
        prixMax = 300;
        Dollar dollar = new Dollar();
        int montant = dollar.prix600(deduction,prixMax);
        assertEquals(75,montant);
    }

    @Test
    void prix700(){
        int deduction;
        deduction = 75;
        Dollar dollar = new Dollar();
        int montant = dollar.prix700(deduction);
        assertEquals(75,montant);
    }
}