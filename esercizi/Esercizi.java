package esercizi;

import java.util.Arrays;
import java.util.LinkedList;

public class Esercizi {

	public static int potenza(int a, int b) {
		// Calcola a^b
		// Ipotesi: a >= 0, b >= 0
		// Complessità:
		// 	modello a costi logaritmici: O(b * log(a) * log(b))
		// 	modello a costi costanti: O(log(b))
		// [log(a), log(b) = numero di bit di a, b rispettivamente]
		int ris = 1;
		int p = a;
		while (b > 0) {
			if ((b & 1) == 1) ris *= p;
			p *= p;
			b = b >> 1;
		}
		return ris;
	}

	public static int iperExp(int n) {
		// Calcola 2^(2^n)
		// Ipotesi: n >= 0
		// Complessità:
		// 	modello a costi logaritmici: O(2^n)
		// 	modello a costi costanti: O(n)
		if (n == 0) return 2;
		int p = iperExp(n - 1);
		return p * p;
	}

	public static int[] kMax(int[] v, int k) {
		// Complessità: O(n) se k è costante
		// In generale O(k * n)
		int[] max = new int[k];
		for (int i = 0; i < k; i++) max[i] = v[i];
		radixSort(max);
		for (int i = k; i < v.length; i++) {
			int pos = k - 1;
			while (pos >= 0 && max[pos] > v[i]) pos--;
			int tmp = v[i], tmp2;
			while (pos >= 0) {
				tmp2 = max[pos];
				max[pos--] = tmp;
				tmp = tmp2;
			}
		}
		return max;
	}

	public static void permutazioniConRipetizioni(int[] v) {
		permutazioniConRipetizioni(v, 0);
	}
	private static void permutazioniConRipetizioni(int[] v, int pos) {
		if (pos == v.length) System.out.println(Arrays.toString(v));
		else {
			for (int i = pos; i < v.length; i++) {
				scambia(v, i, pos);
				permutazioniConRipetizioni(v, pos + 1);
				scambia(v, i, pos);
			}
		}
	}

	public static void permutazioniSenzaRipetizioni(int[] v) {
		permutazioniSenzaRipetizioni(v, 0);
	}
	private static void permutazioniSenzaRipetizioni(int[] v, int pos) {
		if (pos == v.length) System.out.println(Arrays.toString(v));
		else {
			for (int i = pos; i < v.length; i++) {
				// Se tra pos (incluso) e i (escluso) esiste un elemento
				// uguale a quello contenuto in i, significa che non devo
				// scambiare i e pos perché otterrei lo stesso sottoinsieme
				// di permutazioni già considerato
				if (!considerata(v, pos, i)) {
					scambia(v, i, pos);
					permutazioniSenzaRipetizioni(v, pos + 1);
					scambia(v, i, pos);
				}
			}
		}
	}
	
	private static void scambia(int[] v, int i, int j) {
		int tmp = v[i];
		v[i] = v[j];
		v[j] = tmp;
	}

	private static boolean considerata(int[] v, int i, int j) {
		for (int k = i; k < j; k++)
			if (v[k] == v[j]) return true;
		return false;
	}

	public static void radixSort(int[] v) {
		// Ipotesi: v contiene solo valori senza segno
		// Complessità: O(n) se il valore massimo è limitato
		// (in Java ogni intero è <= Integer.MAX_VALUE)
		int esp2 = 3;
		int base = 8; // 2^esp2
		int iMax = 32 / esp2 + (32 % esp2 != 0 ? 1 : 0);
		for (int i = 0; i < iMax; i++)
			bucketSort(v, base, i * esp2);
	}

	private static class LinkedListInteger extends LinkedList<Integer> { }
	private static void bucketSort(int[] v, int b, int s) {
		// Ipotesi: b è potenza di 2
		LinkedListInteger[] w = new LinkedListInteger[b];
		for (int i = 0; i < b; i++) w[i] = new LinkedListInteger();
		for (int i = 0; i < v.length; i++)
			w[(v[i] >> s) & (b - 1)].addLast(v[i]);
		int pos = 0;
		for (int i = 0; i < v.length; i++) {
			while (w[pos].isEmpty()) pos++;
			v[i] = w[pos].removeFirst();
		}
	}

	public static void main(String[]args) {
		int[] u = {1, 2, 3, 4};
		System.out.println("Permutazioni con ripetizioni di " + Arrays.toString(u) + ":");
		permutazioniConRipetizioni(u);
		int[] v = {1, 2, 2, 3, 3, 4};
		System.out.println("Permutazioni senza ripetizioni di " + Arrays.toString(v) + ":");
		permutazioniSenzaRipetizioni(v);
		int[] w = {34534390, 3556, 958659, 87, 38745, 902, 3847, 5986, 3, 654, 34536, 435, 12, 0, 9842, 74365};
		System.out.println(Arrays.toString(w));
		System.out.println("Primi 5 massimi: " + Arrays.toString(kMax(w, 5)));
		System.out.println("Radix Sort:");
		radixSort(w);
		System.out.println(Arrays.toString(w));
		System.out.println("4^7 = " + potenza(4, 7));
		System.out.println("2^(2^4) = " + iperExp(4));
	}
}
