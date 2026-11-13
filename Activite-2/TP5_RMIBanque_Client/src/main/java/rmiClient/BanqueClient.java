package rmiClient;

import metier.Compte;
import rmiService.IBanque;

import javax.naming.Context;
import javax.naming.InitialContext;

public class BanqueClient {

    public static void main(String[] args) {
        try {
            // Recherche de l'objet distant via JNDI (configuration dans jndi.properties)
            Context ctx = new InitialContext();
            IBanque stub = (IBanque) ctx.lookup("BanqueRMI");

            System.out.println(stub.creerCompte(new Compte(1, 1000)));
            System.out.println(stub.creerCompte(new Compte(2, 2500.5)));
            System.out.println(stub.creerCompte(new Compte(1, 300)));

            System.out.println(stub.getInfoCompte(1));
            System.out.println(stub.getInfoCompte(2));
            System.out.println(stub.getInfoCompte(99));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
