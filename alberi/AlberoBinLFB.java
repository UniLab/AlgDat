package alberi;

public class AlberoBinLFB<T> extends AlberoBinLF<T> implements AlberoBinBil<T> {

	private int bilanciamento = 0;

	public AlberoBinLFB() { super(); }

	public AlberoBinLFB(T val) { super(val); }

	public int bil() { return bilanciamento; }

	public void setBil(int b) { bilanciamento = b; }

}
