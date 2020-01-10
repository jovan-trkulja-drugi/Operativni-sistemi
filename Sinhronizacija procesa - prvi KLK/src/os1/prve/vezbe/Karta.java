package os1.prve.vezbe;

public class Karta implements Comparable<Karta> {
	
	public static final String[] BOJE = new String[] {
			"Pik", "Karo", "Herc", "Tref",
			"U boji", "Bez boje"
	};
	
	public static final String[] RANGOVI = new String[] {
			"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A",
			"*"
	};
	
	public final String boja;
	public final String rang;
	
	public Karta(String boja, String rang) {
		int indeksB = indeksZaBoju(boja);
		int indeksR = indeksZaRang(rang);
		if (indeksB == -1) { // Nepoznata boja
			throw new IllegalArgumentException("boja");
		}
		if (indeksR == -1) { // Nepoznat rang
			throw new IllegalArgumentException("rang");
		}
		if (indeksB < 4 != indeksR < 13) { // Poslednje dve boje su za dzokere
			throw new IllegalArgumentException("boja + rang");
		}
		this.boja = boja;
		this.rang = rang;
	}
	
	public String getBoja() {
		return boja;
	}
	
	public String getRang() {
		return rang;
	}
	
	private int indeksZaBoju(String boja) {
		for (int i = 0; i < BOJE.length; i++) {
			if (BOJE[i].equals(boja)) {
				return i;
			}
		}
		return -1;
	}
	
	private int indeksZaRang(String rang) {
		for (int i = 0; i < RANGOVI.length; i++) {
			if (RANGOVI[i].equals(rang)) {
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public String toString() {
		return String.format("%2s %s", rang, boja);
	}
	
	@Override
	public int compareTo(Karta that) {
		if (that == null) { // Sve karte su bolje od null
			return 1;
		}
		return indeksZaRang(this.rang) - indeksZaRang(that.rang);
	}
}
