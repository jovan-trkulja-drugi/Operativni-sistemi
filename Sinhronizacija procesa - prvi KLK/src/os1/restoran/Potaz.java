package os1.restoran;

public class Potaz extends Thread {

	private Kuhinja k;

	public Potaz(Kuhinja k) {
		this.k = k;
	}

	@Override
	public void run() {

		while (!Thread.interrupted()) {
			try {

				Thread.sleep(8000);
				k.addPotaz();
				System.out.println("Dodao sam potaz");
			} catch (InterruptedException e) {

			}
		}
	}

}
