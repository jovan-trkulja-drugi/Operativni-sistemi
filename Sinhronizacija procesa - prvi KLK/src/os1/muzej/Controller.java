package os1.muzej;

import java.util.ArrayList;
import java.util.List;

import os1.muzej.Muzej.Englez;
import os1.muzej.Muzej.Italijan;
import os1.muzej.Muzej.Nemac;

public class Controller {

	private List<Englez> englezi;
	private List<Italijan> ittalijani;
	private List<Nemac> nemci;

	private Object lockE = new Object();
	private Object lockI = new Object();
	private Object lockN = new Object();
	
	private Object unlockE = new Object();
	private Object unlockI = new Object();
	private Object unlockN = new Object();

	public Controller() {
		englezi = new ArrayList<>();
		ittalijani = new ArrayList<>();
		nemci = new ArrayList<>();
	}

	public void udji(Englez e) {

		synchronized (lockE) {

			while (ittalijani.size() > 0 || nemci.size() > 0) {
				try {
					lockE.wait();
				} catch (InterruptedException e1) {

				}
			}

			englezi.add(e);
			lockE.notifyAll();

		}

	}

	public void udji(Italijan e) {

		synchronized (lockI) {

			while (englezi.size() > 0 || nemci.size() > 0) {
				try {
					lockI.wait();
				} catch (InterruptedException e1) {

				}
			}

			ittalijani.add(e);
			lockI.notifyAll();

		}

	}

	public void udji(Nemac e) {

		synchronized (lockN) {

			while (englezi.size() > 0 || ittalijani.size() > 0) {
				try {
					lockN.wait();
				} catch (InterruptedException e1) {

				}
			}

			nemci.add(e);
			lockN.notifyAll();

		}

	}

	public synchronized void izlazi(Italijan i) {

		synchronized (unlockI) {
			
			if (ittalijani.size() > 1) {

				ittalijani.remove(i);

				
			} else {
				ittalijani.remove(i);
				ittalijani.removeAll(ittalijani);
				
				synchronized (lockE) {
					lockE.notify();
				}

				synchronized (lockN) {
					lockN.notify();
				}

			}
			
			unlockI.notifyAll();
		}

	}

	public synchronized void izlazi(Englez i) {

		synchronized (unlockE) {
			
			if (englezi.size() > 1) {

				englezi.remove(i);

			} else {

				englezi.remove(i);
				englezi.removeAll(englezi);
				
				synchronized (lockN) {
					lockN.notify();
				}

				synchronized (lockI) {
					lockI.notify();
				}

			}
			
			unlockE.notifyAll();
		}

	}

	public synchronized void izlazi(Nemac i) {

		synchronized (unlockN) {
			
			if (nemci.size() > 1) {

				nemci.remove(i);

			} else {

				nemci.remove(i);

				nemci.removeAll(nemci);

				synchronized (lockE) {
					lockE.notify();
				}

				synchronized (lockI) {
					lockI.notify();
				}

			}
			
			unlockN.notifyAll();
			
		}

	}

}
