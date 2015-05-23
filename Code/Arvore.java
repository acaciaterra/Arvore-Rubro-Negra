class Arvore {
	public Nodo raiz;
	public static Nodo nil = new Nodo(0, false);

	public Arvore (int n){
		this.raiz = new Nodo(n, false);
	}

	public void rotacao_esq (Nodo x) {
		Nodo y = x.dir;
		x.dir = y.esq;
		if (y.esq != Arvore.nil) y.esq.p = x;
		y.p = x.p;
		if (x.p == Arvore.nil) raiz = y;
		else if (x == x.p.esq) x.p.esq = y;
		else x.p.dir = y;
		y.esq = x;
		x.p = y;
	}

	public void rotacao_dir (Nodo x) {
		Nodo y = x.esq;
		x.esq = y.dir;
		if (y.dir != Arvore.nil) y.dir.p = x;
		y.p = x.p;
		if (x.p == Arvore.nil) raiz = y;
		else if (x == x.p.dir) x.p.dir = y;
		else x.p.esq = y;
		y.dir = x;
		x.p = y;
	}

	public void adiciona (int n) {
		Nodo a = this.busca(n);
		if (this.v > a.v) {
			a.dir = new Nodo(n, true);
			a.dir.p = a;
			this.fixaadicao(a.dir);
		} else if (this.v < a.v) {
			a.esq = new Nodo(n, true);
			a.esq.p = a;
			this.fixaadicao(a.esq);
		}
	}

	public void transplant (Nodo x, Nodo y) {
		if (x.p == Arvore.nil) this.raiz = y;
		else if (x == x.p.esq) x.pai.esq = y;
		else x.p.dir = v;
		if (y != Arvore.nil) y.p = x.p;
	}

	public Nodo encontra (int n) {
		return this.raiz.encontra(n);
	}

	private void fixaadicao (Nodo z) {
		Nodo y;
		while(z.p.ver) {
			if(z.p == z.p.p.left) {
				y = z.p.p.right;
				if(y.ver){ // caso 1 / tio vermelho
					z.p.ver = false;
					y.ver = false;
					z.p.p.ver = true;
					z = z.p.p;
				}
				else { // tio preto
					if (z == z.p.dir) { // caso 2
						z = z.p;
						this.rotacao_esq(z);
					}
					// caso 3
					z.p.ver = false;
					z.p.p.ver = true;
					this.rotacao_dir(z.p.p);
				}
			}
			else {
				y = z.p.p.left;
				if(y.ver){ // caso 1
					z.p.ver = false;
					y.ver = false;
					z.p.p.ver = true;
					z = z.p.p;
				}
				else {
					if (z == z.p.esq) { // caso 2
						z = z.p;
						this.rotacao_dir(z);
					}
					// caso 3
					z.p.ver = false;
					z.p.p.ver = true;
					this.rotacao_esq(z.p.p);
				}
			}
		}
		this.raiz.ver = false;
	}

	public void remove(int n) {
		Nodo z = this.encontra(n);
		Nodo x, y = z;
		boolean cordey = y.ver;

		if (z.esq == Arvore.nil) {
			x = z.dir;
			this.transplant(z, z.dir);
		}
		else if (z.dir == Arvore.nil) {
			x = z.esq;
			this.transplant(z, z.esq);
		}
		else {
			y = z.sucessor();
			cordey = y.red;
			x = y.right;

			if (y.p == z) x.p = y;
			else {
				this.transplant(y, y.dir);
				y.dir = z.dir;
				y.dir.p = y;
			}
			this.transplant(z,y);
			y.esq = z.esq;
			y.esq.p = y;
			y.ver = z.ver;
		}

		if(!cordey) this.fixaremocao(x);
	}




}
