/*  Gherasie Stefania Elena 323CB
	Tema2 PA */
	
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;


public class P3 {
	static class Task {
		public static final String INPUT_FILE = "p3.in";
		public static final String OUTPUT_FILE = "p3.out";
		public static final int NMAX = 100000;
		public static final int INF = (int) 1e9;

		private int n;
		private int m;
		private int energy;

		// Succesiunea de parcurgere a nodurilor pe calea minima
		LinkedList<Integer> foundWay;

		public class Edge implements Comparable<Edge> {
			public int node;
			public int cost;

			Edge(int _node, int _cost) {
				node = _node;
				cost = _cost;
			}

			public int compareTo(Edge edge) {
				return Integer.compare(cost, edge.cost);
			}
		}

		@SuppressWarnings("unchecked")
		ArrayList<Edge>[] adj = new ArrayList[NMAX];

		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(
								INPUT_FILE)));
				n = sc.nextInt();
				m = sc.nextInt();
				energy = sc.nextInt();

				for (int i = 1; i <= n; i++) {
					adj[i] = new ArrayList<>();
				}

				for (int i = 1; i <= m; i++) {
					int x, y, p;
					x = sc.nextInt();
					y = sc.nextInt();
					p = sc.nextInt();
					// Adaugam muchie de la x la y cu costul p
					adj[x].add(new Edge(y, p));
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(Double result) {
			try {
				StringBuilder sb = new StringBuilder();

				// Printam energia ramasa
				sb.append(result);
				sb.append("\n");

				// Printam toate nodurile din calea optima
				while (!foundWay.isEmpty()) {
					sb.append(foundWay.removeFirst() + " ");
				}
				sb.append("\n");

				// Adaugam in fisier
				BufferedWriter bw = new BufferedWriter(new FileWriter(
								OUTPUT_FILE));
				bw.write(sb.toString());
				bw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}


		private void dijkstra(int source, ArrayList<Integer> dist, int[] p) {
			// Initializam distantele la infinit si parintii la 0
			for (int i = 0; i <= n; i++) {
				dist.add(INF);
				p[i] = 0;
			}

			// Folosim un priority queue de Edge stocate in functie de cost
			PriorityQueue<Edge> pq = new PriorityQueue<>();

			// Inseram nodul souce in pq si ii setam distanta la 0
			dist.set(source, 0);
			pq.add(new Edge(source, 0));

			// Cat timp inca avem noduri de procesat
			while (!pq.isEmpty()) {
				// Scoatem elementul din varful cozii si costul lui
				int u = pq.peek().node;
				int cost = pq.poll().cost;
				
				if (cost > dist.get(u)) {
					continue; 
				}

				// Parcurgem toti vecinii nodului
				for (Edge edge : adj[u]) {
					/* Daca distanta se imbunatateste, actualizam distanta pana la vecin si
					   il adaugam in coada. */
					if (cost + edge.cost < dist.get(edge.node)) {
						dist.set(edge.node, cost + edge.cost);
						pq.add(new Edge(edge.node, dist.get(edge.node)));
						// Adaugam nodul in vectorul de parinti
						p[edge.node] = u;
					}
				}
			}
		}
		
		private Double getResult() {
			// Distantele optime de la o sursa la fiecare alt nod
			ArrayList<Integer> d = new ArrayList<>();
			// Parintii fiecarui nod in calea optima
			int[] p = new int[n + 1];
			// Calea optima gasita de la nodul 1 la n
			foundWay = new LinkedList<Integer>();
			
			// Apelam Dijkstra de la nodul 1 pt a calcula distantele minime
			dijkstra(1, d, p);

			int i = n;
			double percentage = 1;
			// Se gasesc parintii nodului n
			while (p[i] != 0) {
				// Se adauga in calea de parcurgere
				foundWay.addFirst(i);

				// Se cauta muchia de la parinte la copilul din cale
				for (Edge edge : adj[p[i]]) {
					if (edge.node == i) {
						// se calculeaza procentul de energie ramasa
						percentage *= 1 - ((double)edge.cost / 100);
					}
				}

				// Fiul devine parinte pentru a continua pe calea drumului minim
				i = p[i];
			}

			// Se adauga si nodul de inceput 1
			foundWay.addFirst(1);

			// Se returneaza energia ramasa
			return percentage * energy;
		}


		public void solve() {
			readInput();
			writeOutput(getResult());
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}
