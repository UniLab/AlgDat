package backtracking;

/*

Dato un insieme S di n studenti e un insieme M di m esami, determinare, se esiste,
un sottoinsieme S* di S che verifichi le seguenti proprietà:
 - Per ogni coppia (s1,s2) di studenti in S*, s1 ed s2 non hanno esami in comune in carriera;
 - Il numero totale di esami sostenuti da tutti gli studenti in S* è pari ad m.
Gli studenti e gli esami sono rappresentati mediante una matrice n*m di boolean,
in cui l'elemento (i,j) è true se e solo se lo studente i ha sostenuto l'esame j.

*/

public class StudentiEsami extends ProblemaBack {
	
	private boolean[][] m;
	private boolean[] studentiInseriti;
	int[] sol;

	public StudentiEsami(boolean[][] m) {
		this.m = m;
		studentiInseriti = new boolean[m.length];
		sol = new int[m.length];
	}

	protected boolean primoVal(int l) {
		int p = primoStudenteNonInserito(0);
		sol[l] = p;
		studentiInseriti[p] = true;
		return true;
	}

	protected boolean succVal(int l) {
		studentiInseriti[sol[l]] = false;
		int p = primoStudenteNonInserito(sol[l] + 1);
		if (p == studentiInseriti.length) return false;
		sol[l] = p;
		studentiInseriti[p] = true;
		return true;
	}

	protected boolean verificaVincoli(int l) {
		for (int i = 0; i < l; i++)
			for (int j = 0; j < m[0].length; j++)
				if (m[sol[i]][j] && m[sol[l]][j]) return false;
		return true;
	}

	protected boolean solCompleta(int l) {
		for (int i = 0; i < m[0].length; i++)
			if (!esameSostenuto(l, i)) return false;
		return true;
	}

	private int primoStudenteNonInserito(int s) {
		while (s < studentiInseriti.length && studentiInseriti[s]) s++;
		return s;
	}

	private boolean esameSostenuto(int l, int e) {
		for (int i = 0; i <= l; i++)
			if (m[sol[i]][e]) return true;
		return false;
	}

}
