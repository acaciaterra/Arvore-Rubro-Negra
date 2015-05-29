class Nodo {
	// Uma árvore é composta de vários Nodos (ou nós) ligados entre si e cada um deles conterá:

	public Integer v; // Um valor inteiro, que será a chave para busca, inserção, remoção...
	public Nodo p, esq, dir; // Um Nodo que representa o pai do Nodo em questão,
													// o Nodo que se encontra à esquerda e o que se encontra à direita do mesmo
	public boolean ver; // Uma flag booleana, que quando tiver valor "true",
										 // significará que o Nodo em questão é de cor vermelha, caso contrário é preto

/*  public Nodo(int n){
		this.v = n;
		this.ver = false;
		this.esq = this.dir = this.p = null;
	} */

	public Nodo(int n, boolean ver) {
		this.v = n;
		this.ver = ver;
		this.p = this.esq = this.dir = Arvore.nil;
	}

	public Nodo encontra(int n) {
		// Encontra o Nodo que contém o valor que foi enviado como parâmetro, ou o mais próximo disso
		// Bem útil para encontrar o local em que será colocado um novo Nodo com determinado valor :p
		if (n < this.v && this.esq != Arvore.nil) return this.esq.encontra(n);
		else if (n > this.v && this.dir != Arvore.nil) return this.dir.encontra(n);
		else return this;
	}

 	/* public void mostra(){
		// Basicamente, uma inorderWalk
		if (this.esq != Arvore.nil) this.esq.mostra();
		System.out.println(this + ": " + this.esq + " <- (" + this.v + " + " + (this.ver ? "V" : "P") + ") -> " + this.dir);
		if (this.dir != Arvore.nil) this.dir.mostra();
	} */

	public Nodo minimo() {
		// Vai mostrar o valor mais baixo presente na árvore a partir do Nodo que está rodando no momento
		if (this.esq != Arvore.nil) return esq.minimo();
		else return this;
	}

 public Nodo maximo() {
	// Vai mostrar o valor mais alto presente na árvore a partir do Nodo que está rodando no momento
		if (this.dir != Arvore.nil) return dir.maximo();
		else return this;
	}

	public void inorderWalk() {
		// Deve percorrer a minha árvore e printar
		// os valores de todos os nodos pertencentes a ela
		// de forma ordenada
		if (this.esq != Arvore.nil) this.esq.inorderWalk();
		System.out.println(this.v);
		if (this.dir != Arvore.nil) this.dir.inorderWalk();
	}

	 public Nodo predecessor() {
		// Informa o valor do Nodo que antecede (em termos de valores)
		// o atual Nodo
		if (this.esq != Arvore.nil) return this.esq.maximo();
		else return this;
	}

	public Nodo sucessor(){
		// Informa o valor do Nodo que sucede (em termos de valores)
		// o atual Nodo
		if (this.dir != Arvore.nil) return this.dir.minimo();
		else return this;
	}

	// a verificar
  public void encontra50(Integer q, int aux, Arvore res) {
		if(q >= 50) return;

		if (this.esq != Arvore.nil) {
			this.esq.encontra50(q, aux, res);
		}
		if (this.v > aux && q < 50) {
			res.adiciona(this.v);
			q++;
		}
		if (this.dir != Arvore.nil) {
			this.dir.encontra50(q, aux, res);
		}
	}

	// Método para poder enxergar a árvore em gráfico, não que eu saiba exatamente como funciona...
	public void grafico() {
		if (this.ver) {
				System.out.println("\t" + this.v + " [style = filled, fillcolor = red];");
		} else {
				System.out.println("\t" + this.v + " [style = filled, fillcolor = black, fontcolor = white];");
		}

		if (this.esq != Arvore.nil) {
				System.out.println("\t" + this.v + " -> " + this.esq.v + " [label = \" esq\"];");
				this.esq.grafico();
		}
		else {
				System.out.println("\t" + this.v + " -> nil [label = \" esq\"];");
		}

		if (this.dir != Arvore.nil) {
				System.out.println("\t" + this.v + " -> " + this.dir.v + " [label = \" dir\"];");
				this.dir.grafico();
		}
		else {
				System.out.println("\t" + this.v + " -> nil [label = \" dir\"];");
		}
	}
}
