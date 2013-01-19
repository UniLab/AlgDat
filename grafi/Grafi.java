package grafi;

import java.util.*;

public class Grafi {

	private Grafi() { }

	public static boolean eConnesso(Grafo g) {
		// Per grafi non orientati
		List<Integer> l = g.depthFirstSearch(0);
		return l.size() == g.getN();
	}

	public static boolean aciclico(Grafo g) {
		int[] gradi = calcoloGradoDiEntrata(g);
		boolean[] rimossi = new boolean[g.getN()];
		int daRimuovere = rimovibile(gradi, rimossi);
		while (daRimuovere != -1) {
			rimossi[daRimuovere] = true;
			Iterator<Arco> it = g.adiacenti(daRimuovere);
			while (it.hasNext())
				gradi[it.next().getFin()]--;
			daRimuovere = rimovibile(gradi, rimossi);
		}
		for (int i = 0; i < rimossi.length; i++)
			if (!rimossi[i]) return false;
		return true;
	}

	private static int[] calcoloGradoDiEntrata(Grafo g) {
		int[] gradi = new int[g.getN()];
		for (int i = 0; i < gradi.length; i++) gradi[i] = 0;
		Iterator<Arco> it = g.archi();
		while (it.hasNext())
			gradi[it.next().getFin()]++;
		return gradi;
	}

	private static int rimovibile(int[] gradi, boolean[] rimossi) {
		for (int i = 0; i < gradi.length; i++)
			if (gradi[i] == 0 && !rimossi[i])
				return i;
		return -1;
	}

	/*
	
	Dato un grafo g che rappresenta un insieme di operazioni
	e un numero arbitrario di "esecutori", calcolare il tempo minimo
	necessario ad eseguire tutte le operazioni rispettando le
	relazioni di propedeuticità. Il tempo necessario ad eseguire ogni singola
	operazione del grafo è memorizzato in un array passato come secondo parametro.

	Il metodo tempoEsecuzione è ricavato dal metodo aciclico, con la differenza
	che ad ogni passo vengono rimossi TUTTI i nodi con grado di entrata = 0,
	non solo il primo, ottenendo un sottoinsieme di operazioni che possono
	essere svolte simultaneamente.

	*/
	public static int tempoEsecuzione(Grafo g, int[] executionTimes) {
		int[] gradi = calcoloGradoDiEntrata(g);
		boolean[] rimossi = new boolean[g.getN()];
		int[] startTimes = new int[g.getN()];
		for (int i = 0; i < startTimes.length; i++) startTimes[i] = 0;
		LinkedList<Integer> daRimuovere = rimovibili(gradi, rimossi);
		while (!daRimuovere.isEmpty()) {
			while (!daRimuovere.isEmpty()) {
				int curr = daRimuovere.removeFirst();
				rimossi[curr] = true;
				Iterator<Arco> it = g.adiacenti(curr);
				while (it.hasNext()) {
					int succ = it.next().getFin();
					gradi[succ]--;
					if (startTimes[succ] < startTimes[curr] + executionTimes[curr])
						startTimes[succ] = startTimes[curr] + executionTimes[curr];
				}
			}
			daRimuovere = rimovibili(gradi, rimossi);
		}
		for (int i = 0; i < rimossi.length; i++)
			if (!rimossi[i]) return -1; // Grafo ciclico
		int[] endTimes = new int[g.getN()];
		for (int i = 0; i < endTimes.length; i++) endTimes[i] = startTimes[i] + executionTimes[i];
		return max(endTimes);
	}

	private static LinkedList<Integer> rimovibili(int[] gradi, boolean[] rimossi) {
		LinkedList<Integer> daRimuovere = new LinkedList<Integer>();
		for (int i = 0; i < gradi.length; i++)
			if (gradi[i] == 0 && !rimossi[i])
				daRimuovere.add(i);
		return daRimuovere;
	}

	private static int max(int[] v) {
		int iMax = 0;
		for (int i = 1; i < v.length; i++)
			if (v[iMax] < v[i]) iMax = i;
		return v[iMax];
	}
}
