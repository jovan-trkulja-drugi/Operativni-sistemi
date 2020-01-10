package os1.restoran;

public class Sendvic extends Thread {

	private Kuhinja k;

	public Sendvic(Kuhinja k) {
		this.k = k;
	}

	@Override
	public void run() {

		while (!Thread.interrupted()) {

			try {

				Thread.sleep(6000);
				k.addHleb();
				System.out.println("Dodao sam hleb");

			} catch (InterruptedException e) {

			}
		}
	}

}
