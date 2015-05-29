import java.util.*;

class Main {
  public static final int totalnodos = 100; // Nodos a serem inseridos randomicamente
  public static final int MAX = 2147483647; // 2^31 - 1 (para testes)

  public static void main(String args[]) {

    Random gerador = new Random(); // Para gerar números aleatórios
    long inicio, aux, media = 0, total;
    int chave;

    Arvore n50 = new Arvore();
    Arvore a = new Arvore();

    inicio = System.nanoTime(); // Contagem de tempo total

    // Inserir 200000 nodos
    for(int i = 0; i < totalnodos; i++) {
      aux = System.nanoTime(); // Contagem de tempo de inserção
      a.adiciona(gerador.nextInt(MAX));
      media += System.nanoTime() - aux;
    }

    media /= totalnodos;
    System.out.println("Média de tempo de inserção: " + media/ 10e9 + "\n");

    media = 0;

    // Buscar 10000 nodos
    for (int i = 0; i <= 10000; i++) {
      aux = System.nanoTime(); // Contagem de tempo de busca
      a.encontra(gerador.nextInt(MAX));
      media += System.nanoTime() - aux;
    }

    System.out.println("Média de tempo de busca: " + media/ 10e9 + "\n");

    chave = 1000; // A partir deste nodo, serão procurados 50
    aux = System.nanoTime(); // Contagem de tempo da busca de 50 nodos
    n50 = a.encontra50(chave);
    media += System.nanoTime() - aux;
  //  n50.grafico();

    System.out.println("Média de tempo de busca de 50 nodos à partir de uma chave: " + media/ 10e9 + "\n");


    total = System.nanoTime() - inicio;
    media /= 10000;


    System.out.println("Tempo total: " + total/ 10e9 + "\n");

    a.grafico();
  }
}
