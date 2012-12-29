package alberi;

import java.util.*;

/**
 * @author sflesca
 *
 */
public interface AlberoBin<T> extends Albero<T> {
	
	public AlberoBin<T> padreBin();
	
	public AlberoBin<T> sin();

	public void setSin(AlberoBin<T> a);
	
	public AlberoBin<T> des();

	public void setDes(AlberoBin<T> a);
	
	public List<T> visitaInfissa();
	
	public Iterator<T> iteratorVI();

	public Iterator<T> iteratorVPre();

	public Iterator<T> iteratorVPost();

}
