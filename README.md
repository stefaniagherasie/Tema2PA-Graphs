# Tema2PA-Graphs
[Tema2 Proiectarea Algoritmilor (2019-2020, seria CB)] 

O serie de probleme folosind algoritmi cunoscuti de grafuri


#### ᐅ IMPLEMENTARE PROBLEMA P1

Aceasta problema presupune gasirea de orase ce trebuie inchise pentru a-l
proteja pe Robin Hood. 

Am creat ArrayList-uri pentru a retine orasele ocupate de lorzi (occupied),
nodurile care vor fi blocate (blocked) si permutarile date.

Pentru fiecare permutare s-a realizat o parcurgere in adancime (dfs) prin 
subarborele dominat de nodul 1. Astfel la parcurgerea prin arbore s-a cautat
daca se atinge un nod ocupat, caz in care parcurgerea dfs esua si permutarea
curenta se adauga in nodurile blocate. Daca in timpul parcurgeri se intalnea
un nod blocat anterior, nu se mai explora pentru a simula ca acesta are toate
muchiile adiacente blocate. Cand se iesea cu succes din parcurgere, se atingea
numarul minim de noduri care trebuiau inchise.

**Complexitate**: `O(n*m)`
<br><br>

#### ᐅ IMPLEMENTARE PROBLEMA P2

Aceasta problema presupune gasirea drumului optim pentru care Robin Hood 
depune cel mai putin efort. Aceasta este o problema de drum minim punct
la punct.

Avand in vedere ca drumurile pot avea si costuri negative, s-a folosit
algoritmul `Bellman-Ford`, putin optimizat. S-a calculat distanta de la nodul
sursa la toate celelalte noduri din graf si s-a afisat distanta corespunzatoare
nodului destinatie.

**Complexitate** (complexitatea vine de la Bellman Ford):  `O(n^2)` 
<br><br>

#### ᐅ IMPLEMENTARE PROBLEMA P3

Aceasta problema presupune gasirea unui drum optim astfel incat Robin Hood 
sa ramana cu energie cat mai mare. Avand in vedere ca procentele date sunt
strict pozitive, am folosit algoritmul `Dijkstra` pentru drumuri minime.

S-a calculat distanta cea mai mica mergand pe principiul ca procentele trebuie
sa fie cat mai mici pentru ca energia finala sa fie cat mai mare. S-a tinut
in vectorul de parinti succesiunea parcurgerii.
<br>
Apoi, se transfera in ArrayList-ul `foundWay` calea drumului optim, calculandu-se
si procentul de energie ramas dupa formula din enunt, afisandu-se energia 
ramasa si calea.

**Complexitate** (complexitatea vine de la Dijkstra)
- `O(n*logn)` - grafuri rare
- `O(n^2)` - grafuri dense
<br>        

#### Mentiuni

Am folosit scheletul de cod pus la dispozitie de echipa de PA pentru algoritmul
Dijkstra si parti din DFS. Bellman-Ford optimizat a fost luat din pseudocodul
din curs. Am mai preluam anumite implementari din laborator (cum ar fi Edge-ul).
