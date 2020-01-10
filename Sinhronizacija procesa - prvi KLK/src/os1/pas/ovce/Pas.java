package os1.pas.ovce;

public class Pas extends Zivotinja implements Runnable {

	private Object pomoc = new Object();
	private Object pas = new Object();
	private Integer brojac = 0;

	@Override
	public void run() {

		while (!Thread.interrupted()) {

			synchronized (pas) {

				while (brojac == 0) {
					try {
						pas.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				pas.notifyAll();

			}

		}

	}
	
	public void pomoc(Ovca o) {
		brojac++;
		synchronized (pomoc) {
			o.x = 0;
			o.y = 0;
			brojac--;
			pomoc.notifyAll();
		}
	}

}
