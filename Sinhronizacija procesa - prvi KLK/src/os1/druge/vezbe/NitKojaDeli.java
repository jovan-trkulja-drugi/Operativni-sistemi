package os1.druge.vezbe;

import os1.prve.vezbe.Karta;
import os1.prve.vezbe.Spil;

public class NitKojaDeli extends Thread {
	
	private Spil spil;
	
	public NitKojaDeli() {
		
		spil = new Spil();
		spil.promesaj();
	}
	
	@Override
	public void run() {
		
		Test t = new Test();
		 
		int i = 0;
		
		while(i < 12) {
			
			Karta k = spil.uzmiOdGore();
			t.getNiti()[i].setKarta(k);
		}
	}

}
