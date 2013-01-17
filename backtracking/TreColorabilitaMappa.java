package backtracking;

/*

Data una mappa costituita da n territori distinti, determinare, se esiste,
una possibile soluzione al problema di tre-colorabilità, ovvero se è possibile
colorare la mappa in modo che i territori confinanti abbiano colore diverso,
avendo a disposizione tre colori. La mappa è rappresentata da una matrice di
boolean di dimensione n*n in cui l'elemento (i,j) è true se e solo se il
territorio i è confinante con il territorio j.

*/

public class TreColorabilitaMappa extends ProblemaBack {
	
	public enum Colore {ROSSO, VERDE, BLU};
	private boolean[][] m;
	Colore[] sol;

	public TreColorabilitaMappa(boolean[][] m) {
		this.m = m;
		sol = new Colore[m.length];
	}

	protected boolean primoVal(int l) {
		sol[l] = Colore.ROSSO;
		return true;
	}

	protected boolean succVal(int l) {
		if (sol[l] == Colore.BLU) return false;
		if (sol[l] == Colore.ROSSO) sol[l] = Colore.VERDE;
		if (sol[l] == Colore.VERDE) sol[l] = Colore.BLU;
		return true;
	}

	protected boolean verificaVincoli(int l) {
		for (int i = 0; i < l; i++)
			if (m[l][i] && sol[l] == sol[i]) return false;
		return true;
	}

	protected boolean solCompleta(int l) {
		return l == sol.length - 1;
	}

}
