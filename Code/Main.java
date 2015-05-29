import java.util.*;

class Main {
  public static final int totalnodos = 100; // Nodos a serem inseridos randomicamente
  public static final int MAX = 2147483647; // 2^31 - 1

  public static void main(String args[]) {
    /* Arvore a = new Arvore();

    a.adiciona(1);
    a.adiciona(11);
    a.adiciona(21);
    a.adiciona(12);
    a.adiciona(12);
    a.adiciona(16);
    a.adiciona(71);
    a.adiciona(41);

    a.remove(3);

    a.inorderWalk();

    a.grafico(); */

    Random gerador = new Random();
    long inicio, aux, media=0, total;

    Arvore a = new Arvore();

    inicio = System.nanoTime();

    for(int i = 0; i < totalnodos; i++) {
      aux = System.nanoTime();
      a.adiciona(gerador.nextInt(MAX));
      media += System.nanoTime() - aux;
    }

    media /= totalnodos;
    System.out.println("Média de tempo de inserção: " + media/ 10e9 + "\n");

    media = 0;

    for (int i = 0; i <= 10000; i++) {
      aux = System.nanoTime();
      a.encontra(gerador.nextInt(MAX));
      media += System.nanoTime() - aux;
    }

    total = System.nanoTime() - inicio;
    media /= 10000;

    System.out.println("Média de tempo de busca: " + media/ 10e9 + "\n");

    System.out.println("Tempo total: " + total/ 10e9 + "\n");

  //  a.grafico();
  }
}
