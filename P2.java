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
import java.util.PriorityQueue;
import java.util.Scanner;

public class P2 {
	static class Task {
		public static final String INPUT_FILE = "p2.in";
		public static final String OUTPUT_FILE = "p2.out";
		public static final int NMAX = 100000;
		public static final int INF = (int) 1e9;

		int n;
		int m;
		int source;
		int destination;

		public class Edge {
			public int node;
			public int cost;

			Edge(int _node, int _cost) {
				node = _node;
				cost = _cost;
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
				source = sc.nextInt();
				destination = sc.nextInt();

				for (int i = 1; i <= n; i++) {
					adj[i] = new ArrayList<>();
				}

				for (int i = 1; i <= m; i++) {
					int x, y, w;
					x = sc.nextInt();
					y = sc.nextInt();
					w = sc.nextInt();
					adj[x].add(new Edge(y, w));
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(Integer result) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(
								OUTPUT_FILE));
				bw.write(result + "\n");
				bw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}


		private void bellmanFord(int source, ArrayList<Integer> d) {
			// Pastram o coada cu varfurile modificate (relaxate)
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
			// Vector pentru a marca nodurile care au fost relaxate
			boolean[] marked = new boolean[n + 1];

			/* Initializam distantele catre toate nodurile cu infinit
			   si impunem ca nu sunt marcate */
			for (int i = 0; i <= n; i++) {
				d.add(INF);
				marked[i] = false;
			}

			// Setez distanta pana la sursa la 0
			d.set(source, 0);

			// Se adauga sursa in coada si o marcam
			pq.add(source);
			marked[source] = true;

			// Relaxam muchiile 
			while (!pq.isEmpty()) {
				// extragem minimul din coada
				int u = pq.poll();
				marked[u] = false;

				// Se relaxeaza arcele ce pleaca din u 
				for (Edge edge : adj[u]) {
					int v = edge.node, cost = edge.cost;
					
					// relaxarea arcelor
					if (d.get(u) + cost < d.get(v)) {
						d.set(v, d.get(u) + cost);
						// marcam nodul adiacent si il introducem in coada
						if (marked[v] == false) {
							marked[v] = true;
							pq.add(v);
						}
					}
				}
			}

		}
		
		private Integer getResult() {
			ArrayList<Integer> d = new ArrayList<>();

			bellmanFord(source, d);

			return d.get(destination);
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
