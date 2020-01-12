package os1.atomics.trambolina;

import java.util.concurrent.atomic.AtomicInteger;

import os1.atomics.trambolina.DecaITrambolina.Pol;

public class TrambolinaAtomic {
	
	private AtomicInteger brojDece;
	private AtomicInteger tezina;
	private AtomicInteger polBr;
	
	public TrambolinaAtomic() {
		brojDece = new AtomicInteger(5);
		tezina = new AtomicInteger(300);
		polBr = new AtomicInteger(0);
	}
	
	public void popniDete(Integer t, Pol pol) {
		
		boolean ok = true;
		
		do {
			if(polBr.get() == 0) {
				if(pol == Pol.MUSKI)
					polBr.compareAndSet(0, 1);
				else if(pol == Pol.ZENSKI)
					polBr.compareAndSet(0, 2);
			} else if(polBr.get() == 1){
				
				int staraVr = tezina.get();
				int novaVr = staraVr - t;
				int polBrojac = polBr.get();
				
				ok = novaVr >= 0 && polBrojac == 1;
				
				if(ok) {
					ok = tezina.compareAndSet(staraVr, novaVr);
				} else {
					Thread.yield();
				}
			} else if(polBr.get() == 2) {
				
				int staraVr = tezina.get();
				int novaVr = staraVr - t;
				int polBrojac = polBr.get();
				
				ok = novaVr >= 0 && polBrojac == 2;
				
				if(ok) {
					ok = tezina.compareAndSet(staraVr, novaVr);
				} else {
					Thread.yield();
				}
			} else {
				Thread.yield();
			}
			
			
		} while(!ok);
		
		boolean okD;
		
		do {
		
			int broj = brojDece.get();
			int noviBr = broj - 1;
			
			okD = noviBr >= 0;
			
			if(okD) {
				okD = brojDece.compareAndSet(broj, noviBr);
			} else {
				Thread.yield();
			}
			
		} while(!okD);
	}
	
	public void spustiDete(Integer t, Pol pol) {
		brojDece.incrementAndGet();
		boolean ok;
		
		do {
			
			if(brojDece.get() == 0) {
				polBr.compareAndSet(polBr.get(), 0);
			} 
				
			int staraVr = tezina.get();
			int novaVr = staraVr + t;
				
			ok = tezina.compareAndSet(staraVr, novaVr);
			
		} while(!ok);
	
	}

}
