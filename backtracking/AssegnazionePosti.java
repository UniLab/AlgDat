package backtracking;

/*

Dati n studenti ed un'aula d'esame quadrata composta da sqrt(n) * sqrt(n) posti,
determinare, se esiste, una disposizione degli studenti all'interno dell'aula
in modo tale da impedire che due studenti "amici tra loro" siano seduti in
posti adiacenti (in verticale o in orizzontale). Le amicizie tra gli studenti
sono rappresentate all'interno di una matrice di boolean in cui l'elemento (i,j)
è true se e solo se lo studente i è amico dello studente j.

*/

public class AssegnazionePosti extends ProblemaBack {
	
	private boolean[][] m;
	boolean[] studentiAssegnati;
	int[][] sol;

	public AssegnazionePosti(boolean[][] m) {
		this.m = m;
		sol = new int[(int)Math.sqrt(m.length)][(int)Math.sqrt(m.length)];
		studentiAssegnati = new boolean[m.length];
	}

	protected boolean primoVal(int l) {
		int r = l / sol.length;
		int c = l % sol.length;
		int p = primoStudenteNonAssegnato(0);
		sol[r][c] = p;
		studentiAssegnati[p] = true;
		return true;
	}

	protected boolean succVal(int l) {
		int r = l / sol.length;
		int c = l % sol.length;
		studentiAssegnati[sol[r][c]] = false;
		int p = primoStudenteNonAssegnato(sol[r][c] + 1);
		if (p == studentiAssegnati.length) return false;
		sol[r][c] = p;
		studentiAssegnati[p] = true;
		return true;
	}

	protected boolean verificaVincoli(int l) {
		int r = l / sol.length;
		int c = l % sol.length;
		if ((r - 1 >= 0 && m[sol[r - 1][c]][sol[r][c]]) ||
			(c - 1 >= 0 && m[sol[r][c - 1]][sol[r][c]])) return false;
		return true;
	}

	protected boolean solCompleta(int l) {
		return l == m.length - 1;
	}

	private int primoStudenteNonAssegnato(int s) {
		while (s < studentiAssegnati.length && studentiAssegnati[s]) s++;
		return s;
	}

}
