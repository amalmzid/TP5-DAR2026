package rmiService;

import metier.Compte;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BanqueImpl extends UnicastRemoteObject implements IBanque {

    private final Map<Integer, Compte> comptes = new ConcurrentHashMap<>();

    public BanqueImpl() throws RemoteException {
        super();
    }

    @Override
    public String creerCompte(Compte c) throws RemoteException {
        if (comptes.putIfAbsent(c.getCode(), c) != null)
            return "Erreur : le compte " + c.getCode() + " existe déjà.";
        return "Compte " + c.getCode() + " créé avec succès (solde initial : " + c.getSolde() + ").";
    }

    @Override
    public String getInfoCompte(int code) throws RemoteException {
        Compte c = comptes.get(code);
        return c == null ? "Erreur : le compte " + code + " n'existe pas." : c.toString();
    }
}
