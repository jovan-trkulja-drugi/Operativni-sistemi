package os1.restoran;

import java.io.IOException;

public class Restoran {

	public static void main(String[] args) throws IOException {
		
		Kuhinja k = new Kuhinja();
		
		Potaz p = new Potaz(k);
		GrilovaniTofu gf = new GrilovaniTofu(k);
		GrilovaniTofu gf2 = new GrilovaniTofu(k);
		Sendvic s = new Sendvic(k);
		
		Konobar rada = new Konobar(k);
		Konobar dara = new Konobar(k);
		
		p.start();
		gf.start();
		gf2.start();
		s.start();
		
		rada.start();
		dara.start();
		
		System.in.read();
		
		p.interrupt();
		gf.interrupt();
		gf2.interrupt();
		s.interrupt();
		
		rada.interrupt();
		dara.interrupt();
		
		Integer zarada = k.getCena();
		
		System.out.println("Program je zavrsio sa radom. Ukupna cena je -> " + zarada);
		

	}

}
