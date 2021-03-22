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

public class P1 {
	static class Task {
		public static final String INPUT_FILE = "p1.in";
		public static final String OUTPUT_FILE = "p1.out";
		public static final int NMAX = 100000;
		public static final int INF = (int) 1e9;

		int n;
		int m;
		int k;

		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] adj = new ArrayList[NMAX];
		ArrayList<Integer> occupied = new ArrayList<Integer>();
		ArrayList<Integer> blocked = new ArrayList<Integer>();
		ArrayList<Integer> permutations = new ArrayList<Integer>();

		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(
								INPUT_FILE)));
				n = sc.nextInt();
				m = sc.nextInt();
				k = sc.nextInt();

				// Se citesc orasele din care lorzii pornesc cautarile
				for (int i = 0; i < k; i++) {
					occupied.add(sc.nextInt());
				}

				// Se citesc permutarile 
				for (int i = 0; i < n - 1; i++) {
					permutations.add(sc.nextInt());
				}

				for (int i = 1; i <= n; i++) {
					adj[i] = new ArrayList<>();
				}

				// Se citesc muchiile si se adauga in listele de adiacenta
				for (int i = 1; i <= m; i++) {
					int x, y;
					x = sc.nextInt();
					y = sc.nextInt();
					adj[x].add(y);
					adj[y].add(x);
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
		

		private boolean dfs() {
			boolean[] visited = new boolean[n + 1];

			/* Parcurgem graful in adancime de la nodul 1. Daca explore
			intoarce false, inseamna ca s-a ajuns la un oras blocat */
			if (explore(1, visited) == false) {
				return false;
			}
			return true;
		}


		private boolean explore(int u, boolean[] visited) {
			// Se seteaza nodul ca fiind vizitat
			visited[u] = true;

			// Se cerceteaza nodurile adiacente lui u
			for (Integer v : adj[u]) {
				/* S-a ajuns la un nod blocat anterior in permutare
				si acesta se ignora, nu se exploreaza */
				if (blocked.contains(v)) {
					continue;
				}
				
				// S-a ajuns la un nod ocupat de lorzi si explorarea esueaza
				if (occupied.contains(v)) {
					return false;
				}

				// Nodul se exploreaza
				if (visited[v] == false) {
					if (explore(v, visited) == false) {
						return false;
					}
				}
			}

			return true;
		}
		
		private Integer getResult() {

			/* Se parcurge graful pentru fiecare permutare. Daca parcurgerea ajunge
			la un oras ocupat de lorzi se blocheaza nodul din permutare. Daca aceasta
			reuseste s-au aflat orajele ce trebuiesc blocate. */
			for (Integer p : permutations) {
				if (dfs() == false) {
					blocked.add(p);
				} else {
					break;
				}
			}

			// Se returneaza numarul de orase blocate
			return blocked.size();
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
