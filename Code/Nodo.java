class Nodo {
	int v; // Valor
	Nodo p, esq, dir; // Respectivamente, pai, esquerda e direita
	boolean ver; // Se true, então é vermelho

/*  public Nodo(int n){
		this.v = n;
		this.ver = false;
		this.esq = this.dir = this.p = null;
	} */


	public Nodo(int n, boolean ver){
		this.v = n;
		this.ver = ver;
		this.p = this.esq = this.dir = Arvore.nil;
	}

	public Nodo busca(int n){
		if (this.n > this.v && this.dir != Arvore.nil) return this.dir.busca(n);
		else if (this.n < this.v && this.esq != Arvore.nil) return this.esq.busca(n);
		else return this;
	}

	public void mostra(){
		System.out.println(this + ": " + this.esq + " <- (" + this.v + " + " + (this.ver ? "V" : "P") + ") -> " + this.dir);
		if (this.esq != Arvore.nil) this.esq.mostra();
		if (this.dir != Arvore.nil) this.dir.mostra();
	}

	public Nodo minimo(){
		if (this.esq != Arvore.nil) return esq.minimo();
		else return this;
	}

/*  public Nodo maximo(){
		if (this.dir != Arvore.nil) return dir.minimo();
		else return this;
	} */
}
