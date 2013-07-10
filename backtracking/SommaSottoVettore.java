package backtracking;

/*	

Problema: Dato un insieme S di numeri interi positivi e un intero N, determinare un
sottoinsieme Y di S tale che la somma degli elementi di Y sia uguale a N.
Esempio: S = {8, 5, 1, 4}, N = 9 -> Y1 = {8, 1}, Y2 = {5, 4}.

*/

public class SommaSottoVettore extends ProblemaBack {

	private int[] vettore;
	private int k;
	private boolean[] sol;
	private int somma = 0;

	public SommaSottoVettore(int[] vettore, int k) {
		this.vettore = vettore;
		this.k = k;
		this.sol = new boolean[vettore.length];
	}

	protected boolean primoVal(int l) {
		if (l < sol.length) {
			sol[l] = true;
			somma += vettore[l];
			return true;
		}
		return false;
	}

	protected boolean succVal(int l) {
		if (sol[l]) {
			sol[l] = false;
			somma -= vettore[l];
			return true;
		}
		return false;
	}

	protected boolean verificaVincoli(int l) {
		return somma <= k;
	}

	protected boolean solCompleta(int l) {
		return somma == k;
	}

}
