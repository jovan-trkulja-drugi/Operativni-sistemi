package os1.atomics.muzej;

import java.util.concurrent.atomic.AtomicInteger;

public class ControllerMuzej {
	
	private AtomicInteger grupa = new AtomicInteger(0);
	private AtomicInteger osobe = new AtomicInteger(0);
	
	public ControllerMuzej() {
		
	}
	
	public void udjiE() {
		
		boolean ok;
		
		do {
			
			int broj = grupa.get();
			
			ok = broj == 0 || broj == 1;
			
			if(ok) {
				ok = grupa.compareAndSet(broj, 1);
			} 
			if(!ok)
				Thread.yield();
			
		} while(!ok);
		
		osobe.incrementAndGet();
	}
	
	public void udjiN() {
		
		boolean ok;
		
		do {
			
			int broj = grupa.get();
			
			ok = broj == 0 || broj == 2;
			
			if(ok) {
				ok = grupa.compareAndSet(broj, 2);
			} 
			if(!ok)
				Thread.yield();
			
		} while(!ok);
		
		osobe.incrementAndGet();	
		
	
	}

	public void udjiI() {
		
		boolean ok;
		
		do {
			
			int broj = grupa.get();
			
			ok = broj == 0 || broj == 3;
			
			if(ok) {
				ok = grupa.compareAndSet(broj, 3);
			} 
			if(!ok)
				Thread.yield();
			
		} while(!ok);
		
		osobe.incrementAndGet();
	}
	
	public void izadjiE() {
		
		boolean ok;
		boolean poslednji = false;
		
		do {
			
			int broj = osobe.get();
			int nova = broj - 1;
			
			ok = nova >= 0;
			
			if(ok) {
				ok = osobe.compareAndSet(broj, nova);
				if(ok && nova == 0)
					poslednji = true;
			} if(!ok) {
				Thread.yield();
			}
		} while(!ok && !poslednji);
		if(poslednji) {
			grupa.set(0);
		}
		
	}
	
	public void izadjiN() {
		
		boolean ok;
		boolean poslednji = false;
		
		do {
			
			int broj = osobe.get();
			int nova = broj - 1;
			
			ok = nova >= 0;
			
			if(ok) {
				ok = osobe.compareAndSet(broj, nova);
				if(ok && nova == 0)
					poslednji = true;
			} if(!ok) {
				Thread.yield();
			}
		} while(!ok && !poslednji);
		if(poslednji) {
			grupa.set(0);
		}
		
	}

	public void izadjiI() {
		boolean ok;
		boolean poslednji = false;
		
		do {
			
			int broj = osobe.get();
			int nova = broj - 1;
			
			ok = nova >= 0;
			
			if(ok) {
				ok = osobe.compareAndSet(broj, nova);
				if(ok && nova == 0)
					poslednji = true;
			} if(!ok) {
				Thread.yield();
			}
		} while(!ok && !poslednji);
		if(poslednji) {
			grupa.set(0);
		}
	}
}
	
