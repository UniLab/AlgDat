package grafi;

import java.util.*;

/**
 * @author sflesca
 * 
 */
public abstract class Grafo<A extends Arco> {

	protected int n;
	protected int m;

	public Grafo(int n) {
		this.n = n;
		this.m = 0;
	}

	public abstract Iterator<A> archi();

	public abstract Iterator<A> adiacenti(int v);

	public abstract void aggiungiArco(A a);

	public abstract boolean rimuoviArco(A a);

	public abstract boolean arco(A a);

	public abstract boolean arco(int v1, int v2);

	public int getN() { return n; }

	public int getM() { return m; }
	
	public List<Integer> depthFirstSearch(int nodoPartenza) {
		// Complessità:
		// 	Lista-> O(m + n) = O(m) se m = Omega(n)
		// 	Matrice -> O(n^2) (Per ogni vertice analizzo una riga della matrice)
		List<Integer> risultato = new LinkedList<Integer>();
		boolean[] marcati = new boolean[n];
		for (int i = 0; i < n; i++) marcati[i] = false;
		depthFirstSearch(nodoPartenza, risultato, marcati);
		return risultato;
	}

	protected void depthFirstSearch(int nodoPartenza, List<Integer> risultato, boolean[] visitati) {
		risultato.add(nodoPartenza);
		visitati[nodoPartenza] = true;
		Iterator<A> itAdiacenti = adiacenti(nodoPartenza);
		while (itAdiacenti.hasNext()) {
			int ad = itAdiacenti.next().getFin();
			if (!visitati[ad]) depthFirstSearch(ad, risultato, visitati);
		}
	}
	
	public List<Integer> breadthFirstSearch(int nodoPartenza) {
		// Complessità:
		// 	Lista-> O(m + n) = O(m) se m = Omega(n)
		// 	Matrice -> O(n^2) (Per ogni vertice analizzo una riga della matrice)
		List<Integer> risultato = new LinkedList<Integer>();
		boolean[] visitati = new boolean[n];
		for (int i = 0; i < n; i++) visitati[i] = false;
		Queue<Integer> coda = new LinkedList<Integer>();
		risultato.add(nodoPartenza);
		visitati[nodoPartenza] = true;
		coda.offer(nodoPartenza);
		while (!coda.isEmpty()) {
			int nodo = coda.poll();
			Iterator<A> itAdiacenti = adiacenti(nodo);
			while (itAdiacenti.hasNext()) {
				int ad = itAdiacenti.next().getFin();
				if (!visitati[ad]) {
					risultato.add(ad);
					visitati[ad] = true;
					coda.offer(ad);
				}
			}
		}
		return risultato;
	}
}
