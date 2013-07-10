package backtracking;

/*

Scrivere una sottoclasse di ProblemaBack che descriva il problema delle n-regine
(cioè, sistemare n regine su una scacchiera n × n in modo che nessuna sia sotto scacco, cioè non
esistano due regine sistemate sulla stessa riga o sulla stessa colonna o sulla stessa diagonale).

*/

public class NRegine extends ProblemaBack {

	private boolean[][] sol;
	private int regine[];
	private int n;

	public NRegine(int n) {
		this.n = n;
		sol = new boolean[n][n];
		regine = new int[n];
	}

	protected boolean primoVal(int l) {
		sol[l][0] = true;
		regine[l] = 0;
		return true;
	}

	protected boolean succVal(int l) {
		sol[l][regine[l]] = false;
		if (regine[l] < sol.length - 1) {
			regine[l] += 1;
			sol[l][regine[l]] = true;
			return true;
		}
		return false;
	}

	protected boolean verificaVincoli(int l) {
		for (int i = 0; i < l; i++) {
			if (regine[i] == regine[l]) return false;   // stessa colonna
			if ((regine[i] - regine[l]) == (l - i)) return false;   // stessa diagonale principale
			if ((regine[l] - regine[i]) == (l - i)) return false;   // stessa diagonale secondaria
		}
		return true;
	}

	protected boolean solCompleta(int l) {
		return l == n - 1;
	}

}
