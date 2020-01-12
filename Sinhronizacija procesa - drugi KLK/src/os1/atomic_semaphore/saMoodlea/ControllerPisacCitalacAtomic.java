package os1.atomic_semaphore.saMoodlea;

import java.util.concurrent.atomic.AtomicInteger;

public class ControllerPisacCitalacAtomic {
	
	private AtomicInteger baza = new AtomicInteger(0);
	
	private AtomicInteger citaoci = new AtomicInteger();
	
	private int brCitaoci = 0;
	
	public void pokusajPisanje() {
		
		boolean ok;
		
		do {
			
			int broj = baza.get();
			
			ok = broj == 0;
			
			if(ok) {
				ok = baza.compareAndSet(broj, 1);
			}
			
			if(!ok) {
				Thread.yield();
			}
			
		} while(!ok);
		
	}
	
	public void zavrsioPisanje() {
		
		boolean ok;
		
		do {
			
			int broj = baza.get();
			
			ok = broj == 1;
			
			if(ok)
				ok = baza.compareAndSet(broj, 0);
			if(!ok)
				Thread.yield();
			
		} while(!ok);
		
	}
	
	public void pokusajCitanje() {
		
		boolean ok;
		do {
			
			int br = baza.get();
			
			ok = br == 0 || br == 2;
			
			if(ok)
				ok = baza.compareAndSet(br, 2);
			if(!ok)
				Thread.yield();
			
		} while(!ok);
		
		citaoci.incrementAndGet();
		brCitaoci++;
	}
	
	public void zavrsioCitanje() {
		
		boolean ok;
		boolean poslednji = false;
		do {
			
			int broj = baza.get();
			
			ok = broj == 2;
			
			if(ok) {
				brCitaoci--;
				if(brCitaoci == 0)
					poslednji = true;
			}
			if(!ok)
				Thread.yield();
			
		} while(!ok);
		
		if(poslednji == true)
			baza.set(0);
		
	}

}
