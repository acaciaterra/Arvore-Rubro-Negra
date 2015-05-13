class Arvore {
	Nodo raiz;
	
	public Arvore (int n){
		raiz = new Nodo(k);
	}

	public void rotacao_esq (Nodo x) {
		Nodo y = x.dir;
		x.dir = y.esq;
		if (y.esq != null) y.esq.p = x;
		y.p = x.p;
		if (x.p == null) raiz = y;
		else if (x == x.p.esq) x.p.esq = y;
		else x.p.dir = y;
		y.esq = x;
		x.p = y;
	}
	
	public void rotacao_dir (Nodo x) {
		Nodo y = x.esq;
		x.esq = y.dir;
		if (y.dir != null) y.dir.p = x;
		y.p = x.p;
		if (x.p == null) raiz = y;
		else if (x == x.p.dir) x.p.dir = y;
		else x.p.esq = y;
		y.dir = x;
		x.p = y;
	}
	
	public void adiciona (int n) {
		Nodo a = raiz.busca(n);
		if (this.k > a.v) this.fixaadicao(a.dir = new Nodo(n, a));
		else if (this.k < a.v) this.fixaadicao(a.esq = new Nodo(n, a));
	}
	
	public void transplant (Nodo x, Nodo y) {
		if (x.p == null) this.raiz = y;
		else if (x == x.p.esq) x.pai.esq = y;
		else x.p.dir = v;
		if (y != null) y.p = x.p;
	}
	
	public Nodo encontra (int n) {
		Nodo a = raiz.busca(n);
		if (a.v != n) return null;
		else return a;
	}
}
