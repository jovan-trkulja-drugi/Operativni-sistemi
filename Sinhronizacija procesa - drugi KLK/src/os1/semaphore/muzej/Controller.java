package os1.semaphore.muzej;

import java.util.concurrent.Semaphore;

public class Controller {
	
	private Semaphore mutexMuzej = new Semaphore(1);
	
	private Semaphore englez = new Semaphore(1);
	private Semaphore italijan = new Semaphore(1);
	private Semaphore nemac = new Semaphore(1);
	
	private int brojacE = 0;
	private int brojacI = 0;
	private int brojacN = 0;
	
	public Controller() {
		
	}
	
	public void popniEngleza() {
		
		englez.acquireUninterruptibly();
		
		try {
			brojacE++;
			if(brojacE == 1) {
				mutexMuzej.acquireUninterruptibly();
			}
		} finally {
			englez.release();
		}
	}
	
	public void sidjiEnglez() {
		
		englez.acquireUninterruptibly();
		
		try {
			brojacE--;
			if(brojacE == 0)
				mutexMuzej.release();
		} finally {
			englez.release();
		}
	}
	
	public void udjiNemac() {
		
		nemac.acquireUninterruptibly();
		
		try {
			
			brojacN++;
			if(brojacN == 1)
				mutexMuzej.acquireUninterruptibly();
		} finally {
			nemac.release();
		}
	
	}
	
	public void sidjiNemac() {
		
		nemac.acquireUninterruptibly();
		
		try {
			brojacN--;
			if(brojacN == 0)
				mutexMuzej.release();
		} finally {
			nemac.release();
		}
	}
	
	public void udjiItalijan() {
		
		italijan.acquireUninterruptibly();
		
		try {
			
			brojacI++;
			if(brojacI == 1)
				mutexMuzej.acquireUninterruptibly();
		} finally {
			italijan.release();
		}
	}
	
	public void sidjiItalijan() {
		
		italijan.acquireUninterruptibly();
		
		try {
			brojacI--;
			if(brojacI == 0)
				mutexMuzej.release();
		} finally {
			italijan.release();
		}
	}

}
