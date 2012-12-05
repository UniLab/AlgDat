package grafi;

import grafi.exception.VerticeNonEsisteException;

import java.util.Iterator;
import java.util.Set;

/**
 * @author sflesca
 * 
 */
public class GrafoMA<A extends Arco> extends Grafo<A> {

	Arco[][] M;

	public GrafoMA(int n) {
		super(n);
		M = new Arco[n][n];
	}

	public Iterator<A> archi() {
		return new IteratorArchi();
	}

	public Iterator<A> adiacenti(int v) {
		if (!(v >= 0) && (v < M.length))
			throw new VerticeNonEsisteException();
		return new IteratorAdiacenti(v);
	}

	public void aggiungiArco(Arco a) {
		if (M[a.getIn()][a.getFin()] == null) m++;
		M[a.getIn()][a.getFin()] = a;
	}

	public boolean rimuoviArco(Arco a) {
		if (M[a.getIn()][a.getFin()] != null) {
			M[a.getIn()][a.getFin()] = null;
			m--;
			return true;
		}
		return false;
	}

	public boolean arco(Arco a) {
		return M[a.getIn()][a.getFin()] != null;
	}

	public boolean arco(int v1, int v2) {
		return M[v1][v2] != null;
	}

	protected class IteratorAdiacenti implements Iterator<A> {

		boolean hasNext = true;
		A curr = null;
		int curfin;
		int in;

		public IteratorAdiacenti(int in) {
			this.in = in;
			curfin = -1;
			avanza();
		}

		public boolean hasNext() { return hasNext; }

		public A next() {
			if (!hasNext) return null;
			A tmp = curr;
			avanza();
			return tmp;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		@SuppressWarnings("unchecked")
		private void avanza() {
			for (curfin++; curfin < M[in].length; curfin++)
				if (M[in][curfin] != null)
					break;
			if (curfin < M[in].length)
				curr = (A)M[in][curfin];
			else
				hasNext = false;
		}
	}

	protected class IteratorArchi implements Iterator<A> {

		int currin = 0;
		Iterator<A> it = adiacenti(currin);
		boolean hasNext = true;
		A curr = null;

		public IteratorArchi() { avanza(); }

		public boolean hasNext() { return hasNext; }

		public A next() {
			if (!hasNext) return null;
			A tmp = curr;
			avanza();
			return tmp;
		}

		private void avanza() {
			if (it.hasNext()) curr = it.next();
			else {
				for (currin++; currin < M.length; currin++) {
					it = adiacenti(currin);
					if (it.hasNext()) {
						curr = it.next();
						break;
					}
				}
				if (currin >= M.length) {
					hasNext = false;
					curr = null;
				}
			}
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
