package alberi;

import java.util.Iterator;
import java.util.List;

/**
 * @author sflesca
 *
 */
public interface Albero<T> {
	
	public T val();

	public void setVal(T val);
	
	public Albero<T> padre();

	public void setPadre(Albero<T> p);

	public int pos();

	public void setPos(int p);
	
	public Albero<T> figlio(int pos);

	public void setFiglio(Albero<T> a, int pos);

	public void rimuoviFiglio(int pos);

	public void pota();
	
	public Iterator<Albero<T>> figli();
	
	public int grado();
	
	public int gradoMax();
	
	public List<T> visitaAnticipata();
	
	public List<T> visitaPosticipata();
	
	public List<T> visitaLivelli();

}
