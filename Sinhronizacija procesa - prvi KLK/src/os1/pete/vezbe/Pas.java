package os1.pete.vezbe;

public class Pas extends Zivotinja implements Runnable {

	private boolean laje = false;
	protected int pomoc = 0;

//	private String lajeP = "";
//	private String pomocP = "";

	@Override
	public void run() {

		synchronized (this) {
			try {

				while (!Thread.interrupted()) {

					while (pomoc == 0) {
						wait();
					}

					while (pomoc > 0) {
						laje = true;
					}
					
					laje = false;

				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean getLaje() {
		return laje;
	}

	public void probudi() {
		synchronized (this) {
			this.notify();
		}
	}

}
