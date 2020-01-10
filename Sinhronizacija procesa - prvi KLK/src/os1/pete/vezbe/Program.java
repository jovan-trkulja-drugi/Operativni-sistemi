package os1.pete.vezbe;

import java.io.IOException;

public class Program {

	public static void main(String[] args) throws IOException {
		
		Pas p = new Pas();
		Thread pas = new Thread(p);
		pas.start();
		
		Ovca[] ovce = new Ovca[10];
		Thread[] ovceT = new Thread[10];
		
		for(int i = 0; i<10; i++) {
			
			Ovca o = new Ovca(p);
			ovce[i] = o;
			
			Thread ovcaT = new Thread(o);
			ovceT[i] = ovcaT;
			
			ovcaT.start();
			
		}
		
		Imanje imanje = new Imanje(p,ovce);
		
		PozadinskiProces po = new PozadinskiProces(imanje);
		po.start();
		
		System.in.read();
		
		pas.interrupt();
		
		for(Thread t: ovceT) {
			t.interrupt();
		}
		
		po.interrupt();
		
		
	}

}
