package rmiServer;

import rmiService.BanqueImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

public class BanqueServer {

    public static void main(String[] args) {
        try {
            // Activer l'annuaire RMIRegistry (port 1099)
            LocateRegistry.createRegistry(1099);

            // Créer l'objet distant
            BanqueImpl od = new BanqueImpl();

            // Publier la référence via JNDI (configuration dans jndi.properties)
            Context ctx = new InitialContext();
            ctx.rebind("BanqueRMI", od);

            System.out.println("Serveur RMI de la banque démarré (enregistrement JNDI)...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
