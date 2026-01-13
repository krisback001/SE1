package org.hbrs.se1.ws25.exercises.uebung6;

public class ReiseAnbieterAdapter implements IReiseAnbieter{

    private Reiseanbieter legacyAnbieter;

    public ReiseAnbieterAdapter(){
        this.legacyAnbieter = legacyAnbieter;
    }

    @Override
    public SuchErgebnis suche(SuchAuftrag auftrag) {

        // Transformation: internes SuchAuftrag-Objekt → QueryObject
        QueryObject query = mapToQueryObject(auftrag);

        // Aufruf der Legacy-Schnittstelle
        QueryResult result = legacyAnbieter.executeQuery(query);

        // Transformation: QueryResult → internes SuchErgebnis
        SuchErgebnis suchErgebnis = mapToSuchErgebnis(result);

        return suchErgebnis;
    }

    /**
     * Transformiert einen internen SuchAuftrag in ein QueryObject
     * für den Legacy-Reiseanbieter.
     */
    private QueryObject mapToQueryObject(SuchAuftrag auftrag) {
        // konkrete Abbildung der Attribute bleibt hier bewusst offen
        return new QueryObject();
    }

    /**
     * Transformiert das Ergebnis des Legacy-Reiseanbieters
     * in ein internes SuchErgebnis.
     */
    private SuchErgebnis mapToSuchErgebnis(QueryResult result) {
        // konkrete Abbildung der Attribute bleibt hier bewusst offen
        return new SuchErgebnis();
    }
}