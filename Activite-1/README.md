# TP5 – Activité 5-1 : Service de conversion de devise (Java RMI)


## Objectif

Un **client** appelle à distance une méthode d'un **serveur** qui convertit un montant en euros vers des dinars. 

On utilise **Java RMI** (Remote Method Invocation) : appeler un objet situé dans une autre JVM comme s'il était local.


## Étape 2 – Développement du serveur

**1. Interface distante `IConversion`** : elle étend `Remote` et déclare la méthode avec `throws RemoteException`.

![IConversion](../capture/Capture%20d'%C3%A9cran%202026-11-13%20075157.png)

**2. Implémentation `ConversionImpl`** : elle étend `UnicastRemoteObject` (l'objet est exporté automatiquement) et retourne `mt * 3`.

![ConversionImpl](../capture/Capture%20d'%C3%A9cran%202026-11-13%20075150.png)

**3. Serveur `ConversionServer`** : il  démarre le registre sur le port 1099, crée l'objet, et le publie sous le nom `BK`.

![ConversionServer](../capture/Capture%20d'%C3%A9cran%202026-11-13%20075136.png)

## Étape 3 – Développement du client

**1. Générer `conversionClient.jar`** (contient seulement `IConversion`, pas `ConversionImpl`).

Dans le `pom.xml` du serveur, `finalName` = `conversionClient` et `maven-jar-plugin` n'inclut que `rmiService/IConversion.class`.

![pom serveur](../capture/Capture%20d'%C3%A9cran%202026-11-13%20075800.png)

![mvn clean install](../capture/Capture%20d'%C3%A9cran%202026-11-11%20220332.png)

**2. Ajouter le JAR au client** : avec Maven, on déclare une dépendance vers le projet serveur dans le `pom.xml` du client (équivalent de « Add External JARs »).

![pom client](../capture/Capture%20d'%C3%A9cran%202026-11-13%20075749.png)

**3. Client `ConversionClient`** : il cherche l'objet distant `BK` dans le registre (`Naming.lookup`) puis appelle `convertirMontant(...)`.

![ConversionClient](../capture/Capture%20d'%C3%A9cran%202026-11-13%20075114.png)

## Étape 4 – Exécution

Toujours lancer le **serveur d'abord**, puis le client (port 1099 libre).


**Serveur démarré**:

![Serveur](../capture/Capture%20d'%C3%A9cran%202026-11-11%20220636.png)

**Client** : `convertirMontant(200)` retourne `600.0` (200 × 3).

![Client](../capture/Capture%20d'%C3%A9cran%202026-11-11%20220650.png)
