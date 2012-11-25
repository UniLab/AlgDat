package alberi;

import java.util.Iterator;

public class Vettore<T> {

	private Object[] items;
	private int size = 0;

	public Vettore(int sizeMax) {
		items = new Object[sizeMax];
	}

	public void remove(int pos) {
		if (items[pos] != null) {
			items[pos] = null;
			size--;
		}
	}

	public void set(int pos, T t) {
		if (items[pos] == null) size++;
		items[pos] = t;
	}

	@SuppressWarnings("unchecked")
	public T get(int pos) { return (T)items[pos]; }

	public Iterator<T> iterator() { return new IteratorNonNulli(); }

	public int size() { return size; }

	private class IteratorNonNulli implements Iterator<T> {

		protected boolean hasNext;
		protected int pos = -1;

		public IteratorNonNulli() { succ(); }

		public boolean hasNext() { return hasNext; }

		@SuppressWarnings("unchecked")
		public T next() {
			if (!hasNext()) return null;
			Object tmp = items[pos];
			succ();
			return (T)tmp;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		private void succ() {
			hasNext = false;
			pos++;
			while (pos < items.length) {
				if (items[pos] != null) {
					hasNext = true;
					return;
				}
				pos++;
			}
		}
	}
}
