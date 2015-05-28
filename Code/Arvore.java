class Arvore {
	public Nodo raiz;
	public static Nodo nil = new Nodo(0, false);

	public Arvore () {
		this.raiz = Arvore.nil;
	}

	public Arvore (int v) {
		this.raiz = new Nodo(v, false);
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
		if (this.raiz == Arvore.nil) {
			this.raiz = new Nodo (n, false);
		} else {
			Nodo a = this.encontra(n);
			if (n > a.v) {
				a.dir = new Nodo(n, true);
				a.dir.p = a;
				this.fixaadicao(a.dir);
			} else if (n < a.v) {
				a.esq = new Nodo(n, true);
				a.esq.p = a;
				this.fixaadicao(a.esq);
			}
		}
	}

	public void transplant (Nodo x, Nodo y) {
		if (x.p == Arvore.nil) this.raiz = y;
		else if (x == x.p.esq) x.p.esq = y;
		else x.p.dir = y;
		if (y != Arvore.nil) y.p = x.p;
	}

	public Nodo encontra (int n) {
		return this.raiz.encontra(n);
	}

	private void fixaadicao (Nodo z) {
		Nodo y;
		while(z.p.ver) {
			if(z.p == z.p.p.esq) {
				y = z.p.p.dir;
				if(y.ver){ // caso 1 / tio vermelho
					z.p.ver = false;
					y.ver = false;
					z.p.p.ver = true;
					z = z.p.p;
				}	else { // tio preto
					if (z == z.p.dir) { // caso 2
						z = z.p;
						this.rotacao_esq(z);
					}
					// caso 3
					z.p.ver = false;
					z.p.p.ver = true;
					this.rotacao_dir(z.p.p);
				}
			}	else {
				y = z.p.p.esq;
				if(y.ver){ // caso 1
					z.p.ver = false;
					y.ver = false;
					z.p.p.ver = true;
					z = z.p.p;
				}	else {
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
		}	else if (z.dir == Arvore.nil) {
			x = z.esq;
			this.transplant(z, z.esq);
		}	else {
			y = z.sucessor();
			cordey = y.ver;
			x = y.dir;

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

		private void fixaremocao(Nodo x) {
			Nodo w;

			while( x != this.raiz &&  !x.ver) {
				if(x == x.p.esq) {
					w = x.p.dir;

					if(w.ver) { //caso 1
						w.ver = false;
						x.p.ver = true;
						this.rotacao_esq(x.p);
						w = x.p.dir;
					}
					if (!w.esq.ver && !w.dir.ver) { //caso 2
						w.ver = true;
						x = x.p;
					}	else {
						if (!w.dir.ver) { // caso 3
							w.esq.ver = false;
							w.ver = true;
							this.rotacao_esq(w);
							w = x.p.dir;
						}
						//caso 4
						w.ver = x.p.ver;
						x.p.ver = false;
						w.dir.ver = false;
						this.rotacao_esq(x.p);
						x = this.raiz;
					}
				}	else {
					w = x.p.esq;

					if(w.ver) { //caso 1
						w.ver = false;
						x.p.ver = true;
						this.rotacao_dir(x.p);
						w = x.p.esq;
					}
					if (!w.esq.ver && !w.dir.ver) { //caso 2
						w.ver = true;
						x = x.p;
					}	else {
						if (!w.dir.ver) { // caso 3
							w.dir.ver = false;
							w.ver = true;
							this.rotacao_esq(w);
							w = x.p.esq;
						}
						//caso 4
						w.ver = x.p.ver;
						x.p.ver = false;
						w.esq.ver = false;
						this.rotacao_dir(x.p);
						x = this.raiz;
					}
				}
			}
			x.ver = false;
		}

		public void inorderWalk() {
			this.raiz.inorderWalk();
		}

		public Nodo minimo() {
			return this.raiz.minimo();
		}

		public Nodo maximo() {
			return this.raiz.maximo();
		}

		/* public Arvore busca50 (int v) {
			Arvore x = new Arvore();
			Contador a = new Contador(0);

			this.raiz.inorderWalk(x, a, v);

			return x;
		}*/
}
