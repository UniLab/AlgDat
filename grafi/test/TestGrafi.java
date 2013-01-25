package grafi.test;

import java.util.Iterator;

import grafi.*;

/**
 * @author sflesca
 *
 */
public class TestGrafi {

	public static void main(String[]args) {
/*
		Grafo<Arco> g = new GrafoLista<Arco>(10);
		g.aggiungiArco(new Arco(0,1));
		g.aggiungiArco(new Arco(1,0));
		g.aggiungiArco(new Arco(0,7));
		g.aggiungiArco(new Arco(7,0));
		g.aggiungiArco(new Arco(2,6));
		g.aggiungiArco(new Arco(6,2));
		g.aggiungiArco(new Arco(3,4));
		g.aggiungiArco(new Arco(4,3));
		g.aggiungiArco(new Arco(4,8));
		System.out.println("Numero componenti connesse: " + Grafi.numComponentiConnesse(g));
		
/*
		Grafo<Arco> g = new GrafoLista<Arco>(12);
		g.aggiungiArco(new Arco(0,1));
		g.aggiungiArco(new Arco(0,8));
		g.aggiungiArco(new Arco(0,10));
		g.aggiungiArco(new Arco(1,2));
		g.aggiungiArco(new Arco(1,5));
		g.aggiungiArco(new Arco(1,9));
		g.aggiungiArco(new Arco(2,4));
		g.aggiungiArco(new Arco(3,0));
		g.aggiungiArco(new Arco(3,2));
		g.aggiungiArco(new Arco(3,4));
		g.aggiungiArco(new Arco(4,7));
		g.aggiungiArco(new Arco(4,11));
		g.aggiungiArco(new Arco(5,1));
		g.aggiungiArco(new Arco(5,2));
		g.aggiungiArco(new Arco(5,11));
		g.aggiungiArco(new Arco(6,0));
		g.aggiungiArco(new Arco(6,3));
		g.aggiungiArco(new Arco(6,11));
		g.aggiungiArco(new Arco(8,6));
		g.aggiungiArco(new Arco(8,9));
		g.aggiungiArco(new Arco(9,1));
		g.aggiungiArco(new Arco(9,7));
		g.aggiungiArco(new Arco(9,10));
		System.out.println("Numero componenti fortemente connesse: " + Grafi.numComponentiFortementeConnesse(g));

/*
		Grafo<Arco> g = new GrafoMA<Arco>(8);
		g.aggiungiArco(new Arco(1,4));
		g.aggiungiArco(new Arco(1,2));
		g.aggiungiArco(new Arco(0,7));
		g.aggiungiArco(new Arco(2,0));
		g.aggiungiArco(new Arco(2,3));
		g.aggiungiArco(new Arco(4,3));
		g.aggiungiArco(new Arco(3,6));
		g.aggiungiArco(new Arco(3,5));

		int[] tempiEsecuzione = {4, 6, 3, 2, 10, 7, 12, 4};

		System.out.println(Grafi.tempoEsecuzione(g, tempiEsecuzione));

/*
		Grafo<ArcoPesato> gp = new GrafoLista<ArcoPesato>(5);
		gp.aggiungiArco(new ArcoPesato(0,1,2.0));
		gp.aggiungiArco(new ArcoPesato(0,4,3.5));
		gp.aggiungiArco(new ArcoPesato(1,4,5.5));
		gp.aggiungiArco(new ArcoPesato(2,0,7.5));
		gp.aggiungiArco(new ArcoPesato(3,0,1.5));
		gp.aggiungiArco(new ArcoPesato(3,1,4.0));

		Iterator<ArcoPesato> itp = gp.archi();
		while (itp.hasNext())
			System.out.println(itp.next());
		
		System.out.println("\nVisita in profondità dal nodo 3: " + gp.depthFirstSearch(3));
		System.out.println("Visita in ampiezza dal nodo 3: " + gp.breadthFirstSearch(3));
		System.out.println("\nDistanze Dijkstra dal nodo 3: " + Grafi.dijkstra(gp, 3));
		double[][] dist = Grafi.floydWarshall(gp);
		System.out.println("\nDistanze Floyd-Warshall per ogni coppia di nodi:");
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++)
				System.out.print(dist[i][j] + " ");
			System.out.println();
		}
/**/
	}

}
