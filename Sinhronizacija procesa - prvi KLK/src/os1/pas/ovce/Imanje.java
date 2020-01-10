package os1.pas.ovce;

public final class Imanje {
	public static final int VELICINA = 6;

	private final Zivotinja pas;
	private final Zivotinja[] ovce;

	public Imanje(Zivotinja pas, Zivotinja... ovce) {
		this.pas = pas;
		this.ovce = ovce;
	}

	private static final String SLIKA;

	static {
		StringBuilder builder = new StringBuilder();
		for (int i = -VELICINA; i < VELICINA; i++) {
			builder.append(':');
		}
		builder.append('\n');

		for (int j = 1 - VELICINA; j < VELICINA; j++) {
			builder.append(':');
			for (int i = 1 - VELICINA; i < VELICINA; i++) {
				builder.append(':');
			}
			builder.append(':');
			builder.append('\n');
		}
		for (int i = -VELICINA; i <= VELICINA; i++) {
			builder.append(':');
		}
		builder.append('\n');
		SLIKA = builder.toString();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(SLIKA);
		char ch = '0';
		for (Zivotinja ovca : ovce) {
			nacrtaj(builder, ovca.getX(), ovca.getY(), ch++);
		}
		nacrtaj(builder, pas.getX(), pas.getY(), 'P');
		return builder.toString();
	}

	private void nacrtaj(StringBuilder b, int x, int y, char oznaka) {
		if (x > -VELICINA && x < VELICINA && y > -VELICINA && y < VELICINA) {
			x = x + VELICINA;
			y = y + VELICINA;
			b.setCharAt(y * 2 * (VELICINA + 1) + x, oznaka);
		}
	}
}
