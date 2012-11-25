package alberi;

import java.util.*;

import alberi.eccezioni.AlberiDiversiException;

/**
 * @author sflesca
 *
 */
public class AlberoLF<T> implements Albero<T> {

	protected int numMaxFigli;
	protected Vettore<AlberoLF<T>> figli;
	protected AlberoLF<T> padre = null;
	protected int posFiglio = -1;
	protected T val = null;

	public AlberoLF(int numMaxFigli) {
		this.numMaxFigli = numMaxFigli;
		figli = new Vettore<AlberoLF<T>>(numMaxFigli);
	}

	public AlberoLF(int numMaxFigli, T val) {
		this.numMaxFigli = numMaxFigli;
		figli = new Vettore<AlberoLF<T>>(numMaxFigli);
		this.val = val;
	}

	public T val() { return val; }
	
	public void setVal(T val) { this.val = val; }

	public Albero<T> padre() { return padre; }

	public Albero<T> figlio(int pos) {
		if ((pos >= numMaxFigli) || (pos < 0))
			throw new ArrayIndexOutOfBoundsException();
		return figli.get(pos);
	}

	public Iterator<Albero<T>> figli() {
		return new IteratorFigli(figli.iterator());
	}

	public int grado() { return figli.size(); }

	public int gradoMax() { return numMaxFigli; }

	public boolean setFiglio(AlberoLF<T> a, int pos) throws AlberiDiversiException {
		if ((pos >= numMaxFigli) || (pos < 0))
			throw new ArrayIndexOutOfBoundsException();
		if (a == null) return true;
		if (a.gradoMax() != gradoMax())
			throw new AlberiDiversiException();
		if (figlio(pos) != null) return false;
		figli.set(pos, a);
		a.padre = this;
		a.posFiglio = pos;
		return true;
	}

	public void pota() {
		if (padre != null) {
			padre.figli.remove(posFiglio);
			padre = null;
			posFiglio = -1;
		}
	}

	protected class IteratorFigli implements Iterator<Albero<T>> {

		protected Iterator<AlberoLF<T>> it;

		public IteratorFigli(Iterator<AlberoLF<T>> it) {
			this.it = it;
		}

		public boolean hasNext() { return it.hasNext(); }

		public Albero<T> next() { return it.next(); }

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public List<T> visitaAnticipata() {
		List<T> l = new LinkedList<T>();
		visitaAnticipata(l);
		return l;
	}
	
	private void visitaAnticipata(List<T> l) {
		Iterator<Albero<T>> it = figli();
		l.add(val());
		while (it.hasNext())
			((AlberoLF<T>)it.next()).visitaAnticipata(l);
	}

	public List<T> visitaPosticipata() {
		List<T> l = new LinkedList<T>();
		visitaPosticipata(l);
		return l;
	}

	private void visitaPosticipata(List<T> l) {
		Iterator<Albero<T>> it = figli();
		while (it.hasNext())
			((AlberoLF<T>)it.next()).visitaAnticipata(l);
		l.add(val());
	}

	public List<T> visitaLivelli() {
		List<T> ris = new LinkedList<T>();
		Queue<Albero<T>> daVisitare = new LinkedList<Albero<T>>();
		daVisitare.offer(this);
		while (!daVisitare.isEmpty()) {
			Albero<T> curr = daVisitare.poll();
			ris.add(curr.val());
			Iterator<Albero<T>> itFigli = curr.figli();
			while (itFigli.hasNext())
				daVisitare.offer(itFigli.next());
		}
		return ris;
	}
}
