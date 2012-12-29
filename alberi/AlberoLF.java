package alberi;

import java.util.*;

import alberi.eccezioni.AlberiDiversiException;

/**
 * @author sflesca
 *
 */
public class AlberoLF<T> implements Albero<T> {

	protected int numMaxFigli;
	protected Vettore<Albero<T>> figli;
	protected Albero<T> padre = null;
	protected int posFiglio = -1;
	protected T val = null;

	public AlberoLF(int numMaxFigli) {
		this.numMaxFigli = numMaxFigli;
		figli = new Vettore<Albero<T>>(numMaxFigli);
	}

	public AlberoLF(int numMaxFigli, T val) {
		this.numMaxFigli = numMaxFigli;
		figli = new Vettore<Albero<T>>(numMaxFigli);
		this.val = val;
	}

	public T val() { return val; }
	public void setVal(T val) { this.val = val; }

	public Albero<T> padre() { return padre; }
	public void setPadre(Albero<T> p) { padre = p; }

	public int pos() { return posFiglio; }
	public void setPos(int p) { posFiglio = p; }

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

	public void setFiglio(Albero<T> a, int pos) {
		if ((pos >= numMaxFigli) || (pos < 0))
			throw new ArrayIndexOutOfBoundsException();
		if (a == null) return;
		if (a.gradoMax() != gradoMax()) throw new AlberiDiversiException();
		figli.set(pos, a);
		a.setPadre(this);
		a.setPos(pos);
	}

	public void rimuoviFiglio(int pos) {
		Albero<T> figlio = figli.get(pos);
		figlio.setPadre(null);
		figlio.setPos(-1);
		figli.remove(pos);
	}

	public void pota() {
		if (padre != null) padre.rimuoviFiglio(posFiglio);
	}

	protected class IteratorFigli implements Iterator<Albero<T>> {

		protected Iterator<Albero<T>> it;

		public IteratorFigli(Iterator<Albero<T>> it) {
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
			((AlberoLF<T>)it.next()).visitaPosticipata(l);
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
