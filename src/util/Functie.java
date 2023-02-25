package util;

public enum Functie
{
	ADMIN,
	MAGAZIJNIER;
	
    public static Functie fromString(String functieString) {
        for (Functie functie : Functie.values()) {
            if (functie.name().equalsIgnoreCase(functieString)) {
                return functie;
            }
        }
        throw new IllegalArgumentException("Ongeldige functie: " + functieString);
    }
}