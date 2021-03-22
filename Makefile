build:
	javac -g P1.java P2.java P3.java
run-p1:
	java -Xss128M  P1
run-p2:
	java -Xss128M  P2
run-p3:
	java -Xss128M  P3
run-p4:

clean:
	rm -rf *.class