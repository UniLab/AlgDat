package alberi;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import alberi.eccezioni.AlberiDiversiException;

/**
 * @author sflesca
 *
 */
public class AlberoBinLF<T> extends AlberoLF<T> implements AlberoBin<T> {

	public AlberoBinLF() { super(2); }

	public AlberoBinLF(T val) { super(2, val); }

	@SuppressWarnings("unchecked")
	public AlberoBin<T> padreBin() { return (AlberoBin<T>)padre; }

	public AlberoBin<T> sin() { return (AlberoBin<T>)figlio(0); }
	public void setSin(AlberoBin<T> a) { setFiglio(a, 0); }

	public AlberoBin<T> des() { return (AlberoBin<T>)figlio(1); }
	public void setDes(AlberoBin<T> a) { setFiglio(a, 1); }
	
	public List<T> visitaInfissa() {
		List<T> l = new LinkedList<T>();
		visitaInfissa(l);
		return l;
	}
	
	private void visitaInfissa(List<T> l){
		AlberoBinLF<T> s = (AlberoBinLF<T>)sin();
		if (s != null) s.visitaInfissa(l);
		l.add(val);
		AlberoBinLF<T> d = (AlberoBinLF<T>)des();
		if (d != null) d.visitaInfissa(l);
	}

	public Iterator<T> iteratorVI() {
		return new IteratorVI(this);
	}

	protected class IteratorVI implements Iterator<T> {

		protected static final int SIN = 0;
		protected static final int DES = 1;
		protected static final int SU = 2;
		protected static final int STOP = 3;
		
		protected AlberoBin<T> curr;
		protected boolean hasNext;
		
		public IteratorVI(AlberoBin<T> a) {
			curr = a;
			hasNext = true;
		}

		public boolean hasNext() { return hasNext; }

		public T next() {
			if (!hasNext()) return null;
			T tmp = curr.val();
			succ();
			return tmp;
		}

		public void remove() { throw new UnsupportedOperationException(); }

		private void succ() {
			int dir = DES;
			while (dir != STOP) {
				switch (dir) {
				case DES:
					if (curr.des() == null) dir = SU;
					else {
						curr = curr.des();
						dir = SIN;
					}
					break;
				case SIN:
					if (curr.sin() == null) dir = STOP;
					else {
						curr = curr.sin();
						dir = SIN;
					}
					break;
				case SU:
					if (curr.padre() == null) {
						hasNext = false;
						dir = STOP;
					} else {
						if (((AlberoBin<T>)curr.padre()).sin() == curr) {
							curr = curr.padreBin();
							dir = STOP;
						} else {
							curr = curr.padreBin();
							dir = SU;
						}
					}
					break;
				}
			}	
		}		
	}

	public Iterator<T> iteratorVPre() {
		return new IteratorVPre(this);
	}

	protected class IteratorVPre implements Iterator<T> {

		protected static final int SIN = 0;
		protected static final int DES = 1;
		protected static final int SU = 2;
		protected static final int STOP = 3;
		
		protected AlberoBin<T> curr;
		protected boolean hasNext;
		
		public IteratorVPre(AlberoBin<T> a) {
			curr = a;
			hasNext = true;
		}

		public boolean hasNext() { return hasNext; }

		public T next() {
			if (!hasNext()) return null;
			T tmp = curr.val();
			succ();
			return tmp;
		}

		public void remove() { throw new UnsupportedOperationException(); }

		private void succ() {
			int dir = SIN;
			while (dir != STOP) {
				switch (dir) {
				case DES:
					if (curr.des() == null) dir = SU;
					else {
						curr = curr.des();
						dir = STOP;
					}
					break;
				case SIN:
					if (curr.sin() == null) dir = DES;
					else {
						curr = curr.sin();
						dir = STOP;
					}
					break;
				case SU:
					if (curr.padre() == null) {
						hasNext = false;
						dir = STOP;
					} else {
						if (((AlberoBin<T>)curr.padre()).sin() == curr) {
							curr = curr.padreBin();
							dir = DES;
						} else {
							curr = curr.padreBin();
							dir = SU;
						}
					}
					break;
				}
			}	
		}		
	}

	public Iterator<T> iteratorVPost() {
		return new IteratorVPost(this);
	}

	protected class IteratorVPost implements Iterator<T> {

		protected static final int SIN = 0;
		protected static final int DES = 1;
		protected static final int SU = 2;
		protected static final int STOP = 3;
		
		protected AlberoBin<T> curr;
		protected boolean hasNext;
		
		public IteratorVPost(AlberoBin<T> a) {
			curr = a;
			hasNext = true;
		}

		public boolean hasNext() { return hasNext; }

		public T next() {
			if (!hasNext()) return null;
			T tmp = curr.val();
			succ();
			return tmp;
		}

		public void remove() { throw new UnsupportedOperationException(); }

		private void succ() {
			int dir = SU;
			while (dir != STOP) {
				switch (dir) {
				case DES:
					if (curr.des() == null) dir = STOP;
					else {
						curr = curr.des();
						dir = SIN;
					}
					break;
				case SIN:
					if (curr.sin() == null) dir = DES;
					else {
						curr = curr.sin();
						dir = SIN;
					}
					break;
				case SU:
					if (curr.padre() == null) {
						hasNext = false;
						dir = STOP;
					} else {
						if (((AlberoBin<T>)curr.padre()).sin() == curr) {
							curr = curr.padreBin();
							dir = DES;
						} else {
							curr = curr.padreBin();
							dir = STOP;
						}
					}
					break;
				}
			}	
		}		
	}
}
