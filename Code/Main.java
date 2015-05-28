import java.util.*;

class Main {
  public static void main(String args[]) {
    Random gerador = new Random();
    long inicio, aux, media=0, total;

    Arvore a = new Arvore();

    inicio = System.nanoTime();

    for(int i =0; i < 200000; i++) {
      aux = System.nanoTime();
      a.adiciona(gerador.nextInt(2147483647));
      media += System.nanoTime() - aux;
    }

    media /= 200000;
    System.out.printf("Média de tempo de inserção: %.10f\n", media/ 10e9);

    media = 0;

    for (int i = 0; i <= 10000; i++) {
      aux = System.nanoTime();
      a.encontra(gerador.nextInt(2147483647));
      media += System.nanoTime() - aux;
    }

    total = System.nanoTime() - inicio;
    media /= 10000;

    System.out.printf("Média de tempo de busca: %.10f\n", media/ 10e9);

    System.out.printf("Tempo total: %.10f/n", total/ 10e9);
  }
}
