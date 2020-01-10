package os1.pas.ovce;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Pas p = new Pas();
		
		Ovca[] ovce = new Ovca[10];
		
		for(int i = 0; i < 10; i++) {
			Ovca o = new Ovca(p);
			ovce[i] = o;
		}
		
		Thread[] ovceNiti = new Thread[10];
		
		Thread pasT = new Thread(p);
		pasT.start();
		
		Imanje imanje1 = new Imanje(p, ovce);
		
		for(int i = 0; i < 10; i++) {
			
			Thread r = new Thread(ovce[i]);
			ovceNiti[i] = r;
			r.start();
		}
		
		
		
		PozadinskiProces pp = new PozadinskiProces(imanje1);
		
		Thread t = new Thread(pp);
		
		t.start();
		
		System.in.read();
		
		pasT.interrupt();
		for (int i = 0; i < ovceNiti.length; i++) {
			ovceNiti[i].interrupt();
		}
		
		t.interrupt();
		
		
	}

}
