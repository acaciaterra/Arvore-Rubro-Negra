class Nodo {
	int v;
	Nodo p, esq, dir;
	boolean cor; // Se true, então é vermelho
	
	public Nodo(int n){
		this.v = n;
		this.cor = false;
		this.esq = this.dir = this.p = null;
	}


	public Nodo(int n, Nodo p){
		this(n);
		this.p = p;
		this.cor = true;		
	} 

	public Nodo busca(int n){
		if (this.n > this.v && this.dir != null) return this.dir.busca(n);
		else if (this.n < this.v && this.esq != null) return this.esq.busca(n);
		else return this;		
	}
	
	public void mostra(){
		System.out.println(this + ": " + this.esq + " <- (" + this.v + " + " + (this.cor ? "V" : "P") + ") -> " + this.dir);
		if (this.esq != null) this.esq.mostra();
		if (this.dir != null) this.dir.mostra();
	}
		
	public Nodo minimo(){
		if (this.esq != null) return esq.minimo();
		else return this;		
	}	
}
