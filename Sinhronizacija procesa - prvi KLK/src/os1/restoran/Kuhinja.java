package os1.restoran;

public class Kuhinja {

	private Double brPotaz;
	private Integer brSir;
	private Integer brHleb;

	private Integer cena;

	private Object lockPotaz;
	private Object lockSir;
	private Object lockHleb;
	
	private Object lockPotazAdd;
	private Object lockSirAdd;
	private Object lockHlebAdd;
	
	private Object lockCena;

	public Kuhinja() {

		this.brHleb = 0;
		this.brSir = 0;
		this.brPotaz = 0.0;
		this.cena = 0;

		this.lockHleb = new Object();
		this.lockSir = new Object();
		this.lockPotaz = new Object();
		
		this.lockPotazAdd = new Object();
		this.lockSirAdd = new Object();
		this.lockHlebAdd = new Object();
		
		this.lockCena = new Object();
	}

	public void addPotaz() {
		synchronized (lockPotazAdd) {
			this.brPotaz += 10;
			lockPotazAdd.notifyAll();
		}
	}

	public void addSir() {
		synchronized (lockSirAdd) {
			this.brSir += 1;
			lockSirAdd.notifyAll();
		}
	}

	public void addHleb() {
		synchronized (lockHlebAdd) {
			this.brHleb += 1;
			lockHlebAdd.notifyAll();
		}
	}

	public Double getPotaz() {
		return this.brPotaz;
	}

	public Integer getSir() {
		return this.brSir;
	}

	public Integer getHleb() {
		return this.brHleb;
	}

	public Integer getCena() {
		return this.cena;
	}

	public void napraviSendvic() {

		synchronized (lockHleb) {

			try {

				while (this.brHleb < 2) {
					System.out.println("Cekam hleb");
					lockHleb.wait();
				}

				this.brHleb -= 2;

			} catch (InterruptedException e) {

			}
		}

		synchronized (lockSir) {

			try {

				while (this.brSir < 1) {
					System.out.println("Cekam sir");
					lockSir.wait();
				}

				this.brSir -= 1;

			} catch (InterruptedException e) {

			}
		}

		synchronized (lockPotaz) {

			try {

				while (this.brPotaz < 0.1) {
					System.out.println("Cekam potaz");
					lockPotaz.wait();
				}

				this.brPotaz -= 0.1;
			} catch (InterruptedException e) {

			}
		}
		
		synchronized (lockCena) {
			this.cena += 230;
			lockCena.notifyAll();
		}
	}

	public void napraviPotazOdBundeve() {

		synchronized (lockPotaz) {

			try {

				while (this.brPotaz < 0.5)
					lockPotaz.wait();

				this.brPotaz -= 0.5;
			} catch (InterruptedException e) {

			}
		}
		
		synchronized (lockHleb) {

			try {

				while (this.brHleb < 1)
					lockHleb.wait();

				brHleb -= 1;

			} catch (InterruptedException e) {

			}
		}
		
		synchronized (lockCena) {
			this.cena += 530;
			lockCena.notifyAll();
		}
	}
	
	public void napraviTofu() {
		
		synchronized (lockPotaz) {

			try {

				while (this.brPotaz < 0.3)
					lockPotaz.wait();

				brPotaz -= 0.3;
			} catch (InterruptedException e) {

			}
		}
		
		synchronized (lockSir) {

			try {

				while (this.brSir < 1)
					lockSir.wait();

				brSir -= 1;

			} catch (InterruptedException e) {

			}
		}
		
		synchronized (lockCena) {
			this.cena += 340;
			lockCena.notifyAll();
		}
	}

}
