package alberi;

public interface AlberoBinBil<T> extends AlberoBin<T> {
	
	/* Fattore di bilanciamento */
	public int bil();

	public void setBil(int b);

}
