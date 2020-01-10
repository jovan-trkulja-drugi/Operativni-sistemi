package os1.prve.vezbe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Spil {
	
	private List<Karta> karte = new ArrayList<>();
	
	public Spil() {
		for (int b = 0; b < 4; b++) {
			for (int r = 0; r < 13; r++) {
				karte.add(new Karta(Karta.BOJE[b], Karta.RANGOVI[r]));
			}
		}
		karte.add(new Karta(Karta.BOJE[4], Karta.RANGOVI[13]));
		karte.add(new Karta(Karta.BOJE[5], Karta.RANGOVI[13]));
	}
	
	public int velicina() {
		return karte.size();
	}
	
	public Karta uzmiOdGore() {
		return karte.remove(karte.size() - 1);
	}
	
	public Karta uzmiOdDole() {
		return karte.remove(0);
	}
	
	public Karta uzmiIzSredine() {
		int indeks = (int) (Math.random() * karte.size());
		return karte.remove(indeks);
	}
	
	public void staviGore(Karta karta) {
		karte.add(karta);
	}
	
	public void staviDole(Karta karta) {
		karte.add(0, karta);
	}
	
	public void staviUSredinu(Karta karta) {
		int indeks = (int) (Math.random() * (karte.size() + 1));
		karte.add(indeks, karta);
	}
	
	public void promesaj() {
		Collections.shuffle(karte);
	}
}