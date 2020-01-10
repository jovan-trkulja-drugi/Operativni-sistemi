package os1.trambolina;

import java.util.ArrayList;

import os1.trambolina.DecaITrambolina.Dete;

public class Trambolina {
	
//	BROJACE ZA TEZINU I BROJ DECE NA TRAMBOLINI
	private int ukupna_tezina;
	private int ukupno_dece;
	
//	POLJA ZA MAKSIMALNU TEZINU I MAX BROJ DECE NA TRAMBOLINI
	private final int max_tezina = 300;
	private final int max_deca = 5;
	
//	LISTA DECE NA TRAMBOLINI (KORISTIMO JE ZA PROVERU POLA)
	private ArrayList<Dete> lista = new ArrayList<>(1);
	
	public Trambolina() {
		
		this.ukupna_tezina = 0;
		this.ukupno_dece = 0;
	
	}
	
//	OBICNA POSTAVKA: SINHRONIZOVANA METODA, SAMO JEDNO DETE SE MOZE POPETI NA TRAMBOLINU U JEDNOM TRENUTKU
	public synchronized void popniSe(int tezina) {
		
//		AKO BI SE DETE POPELO I UKUPNA TEZINA PRESLA DOZVOLJENE GRANICE
//		NE DOZVOLI DETETU DA SE POPNE
		while(ukupna_tezina + tezina > max_tezina || ukupno_dece + 1 > max_deca) {
			try {
				wait();
			} catch (InterruptedException e) {
			
			}
		}
		
//		AKO IMA MESTA POPNI DETE NA TRAMBOLINU
		ukupna_tezina += tezina;
		ukupno_dece += 1;
		
//		PROBUDI SVU DECU KOJA CEKAJU DA SE POPNU (NEKA POKUSAJU PONOVO)
		notifyAll();
	}
	
//	OBICNA POSTAVKA: SINHRONIZOVANA METODA, SAMO JEDNO DETE U JEDNOM TRENUTKU MOZE DA SIDJE
	public synchronized void sidji(int tezina) {
		
//		SPUSTI DETE SA TRAMBOLINE
		ukupna_tezina -= tezina;
		ukupno_dece -= 1;
		
//		OBAVESTI NITI DA JE DETE SPUSTENO SA TRAMBOLINE I NEKA POKUSAJU DRUGA DECA DA SE SPUSTE
		notifyAll();
	}
	
//	DODATNI ZADATAK METODA ZA PENJANJE:
	/*
	 * KAO PARAMETAR PROSLEDJUJEMO DETE. U KLASI DETE SMO DODALI METODE getTezina() i getPol()
	 * 
	 */
	public synchronized void popniNekoDete(Dete d) {
		
//		UBACI DETE U LISTU
		lista.add(d);
		
//		DOKLE GOD JE POL DETETA KOJE POKUSAVA DA SE POPNE RAZLICITO OD POLA PRVOG DETETA KOJE SE POPELO CEKAJ
//		(OSTALE PROVERE ZA TEZINU I BROJ DECE OSTAJU ISTE)
//		ODNOSNO, SKAKACE ONAJ POL KOJI SE PRVI POPNE. AKO SE PRVO POPNE MUSKO DETE SKAKACE SAMO MUSKARCI I OBRNUTO
		while((d.getPol() != lista.get(0).getPol())
				|| (ukupna_tezina + d.getTezina() > max_tezina) 
				|| (ukupno_dece + 1 > max_deca )) {
			
			try {
				wait();
			} catch(InterruptedException e) {
				
			}
		}
		
//		DODAJ DETE NA TRAMBOLINU
		ukupna_tezina += d.getTezina();
		ukupno_dece += 1;
		
//		OBAVESTI OSTALE DA POKUSAJU PONOVO DA SE POPNU
		notifyAll();
	}
	
	public synchronized void spustiNekoDete(Dete d) {
		
//		SPUSTI DETE SA TRAMBOLINE I OBAVESTI OSTALE
		ukupna_tezina -= d.getTezina();
		
		ukupno_dece -= 1;
		
		notifyAll();
	}

}
