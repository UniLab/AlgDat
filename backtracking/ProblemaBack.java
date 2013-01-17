package backtracking;

public abstract class ProblemaBack {

	public boolean risolvi(int l) {
		if (!primoVal(l)) return false;
		while (true) {
			if (verificaVincoli(l)) {
				if (solCompleta(l)) return true;
				if (risolvi(l + 1)) return true;
			}
			if (!succVal(l)) return false;
		}
	}

	public boolean risolvi() {
		int l = 0;
		if (!primoVal(l)) return false;
		while (true) {
			if (verificaVincoli(l)) {
				if (solCompleta(l)) return true;
				if (primoVal(l+1)) { l++; continue; }
			}
			while (l >= 0 && !succVal(l)) l--;
			if (l == -1) return false;
		}
	}

	protected abstract boolean primoVal(int l);

	protected abstract boolean succVal(int l);

	protected abstract boolean verificaVincoli(int l);

	protected abstract boolean solCompleta(int l);

}
