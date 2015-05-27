class Nodo {
	public int v; // Valor
	public Nodo p, esq, dir; // Respectivamente, pai, esquerda e direita
	public boolean ver; // Se true, então é vermelho

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

	public Nodo encontra(int n){
		if (this.v > this.v && this.dir != Arvore.nil) return this.dir.encontra(n);
		else if (this.v < this.v && this.esq != Arvore.nil) return this.esq.encontra(n);
		else return this;
	}

	/* public void mostra(){
		System.out.println(this + ": " + this.esq + " <- (" + this.v + " + " + (this.ver ? "V" : "P") + ") -> " + this.dir);
		if (this.esq != Arvore.nil) this.esq.mostra();
		if (this.dir != Arvore.nil) this.dir.mostra();
	} */

	public Nodo minimo(){
		if (this.esq != Arvore.nil) return esq.minimo();
		else return this;
	}

  /* public Nodo maximo(){
		if (this.dir != Arvore.nil) return dir.minimo();
		else return this;
	} */

	public void inorderWalk(){
		if (this.esq != Arvore.nil) this.esq.inorderWalk();
		System.out.println(this.v);
		if (this.dir != Arvore.nil) this.dir.inorderWalk();
	}

	/* public Nodo predecessor(){
		if (this.esq != Arvore.nil) return this.esq.maximo();
		else return this;
	}

	public Nodo sucessor(){
		if (this.dir != Arvore.nil) return this.dir.minimo();
		else return this;
	} */
}
