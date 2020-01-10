package os1.restoran;

public class GrilovaniTofu extends Thread {

	private Kuhinja k;

	public GrilovaniTofu(Kuhinja k) {
		this.k = k;
	}

	@Override
	public void run() {

		while (!Thread.interrupted()) {

			try {

				Thread.sleep(7000);
				k.addSir();
				System.out.println("Dodao sam sir");

			} catch (InterruptedException e) {

			}
		}
	}

}
