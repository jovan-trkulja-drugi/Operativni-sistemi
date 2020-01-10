package os1.pete.vezbe;

public class Zivotinja {
	
	protected int x = 0;
	protected int y = 0;
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double getUdaljenost() {
		return 20.0 * Math.sqrt(x * x + y * y);
	}

}
