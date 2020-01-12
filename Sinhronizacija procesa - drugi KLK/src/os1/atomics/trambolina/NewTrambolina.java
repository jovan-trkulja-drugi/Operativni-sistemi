package os1.atomics.trambolina;

import java.util.concurrent.atomic.AtomicInteger;

import os1.atomics.trambolina.DecaITrambolina.Dete;
import os1.atomics.trambolina.DecaITrambolina.Pol;

public class NewTrambolina {
	
	private AtomicInteger brojacDeca;
	private AtomicInteger tezina;
	
	private Integer brojacD;
	private Integer brojacDev;
	
	private Dete d = null;
	
	public NewTrambolina() {
		brojacDeca = new AtomicInteger(5);
		tezina = new AtomicInteger(300);
		
		brojacD = 0;
		brojacDev = 0;
	}
	
	public void popniDecaka(Integer t) {
		
		boolean ok;
		
		do {
			
			Integer staraVr = tezina.get();
			Integer novaVr = staraVr - t;
			
			ok = novaVr >= 0;
			
			if(ok)
				ok = tezina.compareAndSet(staraVr, novaVr);
			else
				Thread.yield();
			
		} while(!ok);
		
		boolean okD;
		
		do { 
			
			Integer staraVr = brojacDeca.get();
			Integer novaVr = staraVr - 1;
			
			okD = novaVr >= 0;
			
			if(okD) {
				okD = brojacDeca.compareAndSet(staraVr, novaVr);
				
			}
			else
				Thread.yield();
		} while(!okD);
	}
	
	public void spustiDecaka(Integer t) {
		
		brojacDeca.incrementAndGet();
		
		boolean ok;
		
		do {
			
			Integer staraVr = tezina.get();
			Integer novaVr = staraVr + t;
			
			ok = tezina.compareAndSet(staraVr, novaVr);
			
		} while(!ok);
	}
	/*
	public void popniNekog(Integer t, Dete dete) {
		
		boolean ok;
		
		do {
			
			Integer staraVr = tezina.get();
			Integer novaVr = staraVr - t;
			
			ok = novaVr >= 0;
			
			if(ok) {
				if(d == null) {
					this.d = dete;
					ok = tezina.compareAndSet(staraVr, novaVr);
				} else if(d.getPol() == dete.getPol()) {
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
			
			Integer staraVr = brojacDeca.get();
			Integer nova = staraVr - 1;
			
			okD = brojacDeca.compareAndSet(staraVr, nova);
			
			if(okD) {
				if(dete.getPol() == Pol.MUSKI)
					brojacD++;
				else
					brojacDev++;
			}
			
		} while(!okD);

	}
	
	public void spustiNekog(Integer t, Dete dete) {
		brojacDeca.incrementAndGet();
		boolean ok;
		
		do {
			
			Integer staraT = tezina.get();
			Integer novaT = staraT + t;
			
			ok = tezina.compareAndSet(staraT, novaT);
			
			if(ok) {
				if(dete.getPol() == Pol.MUSKI) {
					brojacD--;
					
					if(brojacD == 0)
						this.d = null;
				} else if(dete.getPol() == Pol.ZENSKI) {
					brojacDev--;
					brojacDeca.incrementAndGet();
					if(brojacDev == 0)
						this.d = null;
					
				}
			} else {
				Thread.yield();
			}
			
		} while(!ok);
	}*/
	
	

}
