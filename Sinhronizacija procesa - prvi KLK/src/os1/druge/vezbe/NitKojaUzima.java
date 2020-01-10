package os1.druge.vezbe;

import os1.prve.vezbe.Karta;

public class NitKojaUzima extends Thread {
	
	private volatile Karta k;
	
	public NitKojaUzima() {
		
	}
	
	@Override
	public void run() {
		
		while(getKarta() == null) {
			
		}
		
		System.out.println("Nit stampa kartu -> " + k.toString());
	}
	
	public void setKarta(Karta k) {
		this.k = k;
	}
	
	public Karta getKarta() {
		return k;
	}

}
