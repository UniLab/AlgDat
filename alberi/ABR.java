package alberi;

import java.util.List;

/**
 * @author sflesca
 *
 */
public class ABR<T extends Comparable<? super T>> implements Dizionario<T> {

	protected AlberoBin<T> coll = null;

	public ABR() { }

	public void inserisci(T x) {
		AlberoBin<T> tmp = cercaNodo(x);
		if (tmp == null) {
			// inserisco la radice
			coll = new AlberoBinLF<T>();
			((AlberoBinLF<T>)coll).setVal(x);
		} else if (!tmp.val().equals(x)) {
			AlberoBinLF<T> figlio = new AlberoBinLF<T>();
			figlio.setVal(x);
			if (x.compareTo(tmp.val()) < 0) {
				((AlberoBinLF<T>)tmp).setSin(figlio);
			} else {
				((AlberoBinLF<T>)tmp).setDes(figlio);
			}
		}
	}

	public void rimuovi(T x) {
		AlberoBin<T> curr = cercaNodo(x);
		if (curr == null || !curr.val().equals(x)) return;
		if (curr.sin() == null || curr.des() == null)
			rimuoviNodo(curr);
		else {
			AlberoBin<T> curr2 = max(curr.sin());
			((AlberoBinLF<T>)curr).setVal(curr2.val());
			rimuoviNodo(curr2);
		}
	}

	protected AlberoBin<T> max(AlberoBin<T> a) {
		while (a.des() != null) a = a.des();
		return a;
	}

	protected void rimuoviNodo(AlberoBin<T> a) {
		if (a.grado() == 0) ((AlberoBinLF<T>)a).pota();
		else {
			AlberoBinLF<T> figlio = (AlberoBinLF<T>)(a.sin() == null ? a.des() : a.sin());
			AlberoBinLF<T> padre = (AlberoBinLF<T>)((AlberoBinLF<T>)a.padre());
			int pos = ((AlberoBinLF<T>)a).posFiglio;
			((AlberoBinLF<T>)a).pota(); figlio.pota();
			padre.setFiglio(figlio, pos);
		}
	}

	public T cerca(T x) {
		AlberoBin<T> tmp = cercaNodo(x);
		if (tmp == null || !tmp.val().equals(x)) return null;
		return tmp.val();
	}

	protected AlberoBin<T> cercaNodo(T x) {
		if (coll == null) return null;
		AlberoBin<T> curr = coll;
		while (!curr.val().equals(x)) {
			if (curr.val().compareTo(x) < 0) {
				if (curr.des() == null) return curr;
				curr = curr.des();
			} else {
				if (curr.sin() == null) return curr;
				curr = curr.sin();
			}
		}
		return curr;
	}

	public String toString() {
		List<T> valoriInf = coll.visitaInfissa();
		List<T> valoriAnt = coll.visitaAnticipata();
		List<T> valoriPos = coll.visitaPosticipata();
		List<T> valoriLiv = coll.visitaLivelli();
		return "inf=" + valoriInf.toString() + " ant=" + valoriAnt.toString() +
			" pos=" + valoriPos.toString() + " liv=" + valoriLiv.toString();
	}
}
