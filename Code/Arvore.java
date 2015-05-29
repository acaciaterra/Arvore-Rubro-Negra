class Arvore {
	public Nodo raiz;
	public static Nodo nil = new Nodo(0, false); // Nodo sentinela. Todos os nodos no último nível da árvore apontarão para a sentinela
																							// que é sempre de cor preta com valor 0.

	public Arvore () {
		this.raiz = Arvore.nil;
	}

	public Arvore (int v) {
		this.raiz = new Nodo(v, false);
	}

	private void rotacao_esq(Nodo x) {
		Nodo y = x.dir;
		x.dir = y.esq;
		if (y.esq != Arvore.nil) y.esq.p = x;
		y.p = x.p;
		if (x.p == Arvore.nil) this.raiz = y;
		else if (x == x.p.esq) x.p.esq = y;
		else x.p.dir = y;
		y.esq = x;
		x.p = y;
	}

	private void rotacao_dir(Nodo x) {
		Nodo y = x.esq;
		x.esq = y.dir;
		if (y.dir != Arvore.nil) y.dir.p = x;
		y.p = x.p;
		if (x.p == Arvore.nil) this.raiz = y;
		else if (x == x.p.esq) x.p.esq = y;
		else x.p.dir = y;
		y.dir = x;
		x.p = y;
	}

	public void adiciona (int n) {
		if (this.raiz == Arvore.nil) {
			this.raiz = new Nodo (n, false);
		} else {
			Nodo a = this.encontra(n);
			if (n < a.v) {
				a.esq = new Nodo(n, true);
				a.esq.p = a;
				this.fixaadicao(a.esq);
			}
			else if (n > a.v) {
				a.dir = new Nodo(n, true);
				a.dir.p = a;
				this.fixaadicao(a.dir);
			}
		}
	}

	public void transplant (Nodo x, Nodo y) {
		if (x.p == Arvore.nil) this.raiz = y;
		else if (x == x.p.esq) x.p.esq = y;
		else x.p.dir = y;
		y.p = x.p;
	}

	public Nodo encontra (int n) {
		return this.raiz.encontra(n);
	}

	private void fixaadicao(Nodo z) {
			Nodo y;
			while (z.p.ver) {
					if (z.p == z.p.p.esq) {
							y = z.p.p.dir;
							if (y.ver) { // caso 1 (tio é vermelho):
								// muda a cor do pai e do tipo para preto e dos avós para vermelho.
								// Então, sobe dois níveis na árvore.
									z.p.ver = false;
									y.ver = false;
									z.p.p.ver = true;
									z = z.p.p;
							}
							else { // Ou seja, tio é preto
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
							y = z.p.p.esq;
							if (y.ver) { // caso 1
									y.ver = z.p.ver = false;
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

			if(z.v == n) {
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
						cordey = y.ver;
						x = y.dir;

						if (y.p == z) x.p = y;
						else {
								this.transplant(y, y.dir);
								y.dir = z.dir;
								y.dir.p = y;
						}
						this.transplant(z, y);
						y.esq = z.esq;
						y.esq.p = y;
						y.ver = z.ver;
				}

				if (!cordey) this.fixaremocao(x);
			}
	}

		private void fixaremocao(Nodo n) {
				Nodo x;

				while (n != this.raiz && !n.ver) {
						if (n == n.p.esq) {
								x = n.p.dir;

								if (x.ver) { // caso 1
										x.ver = false;
										n.p.ver = true;
										this.rotacao_esq(n.p);
										x = n.p.dir;
								}
								if (!x.esq.ver && !x.dir.ver) { // caso 2
										x.ver = true;
										n = n.p;
								}
								else {
										if (!x.dir.ver) { // caso 3
												x.esq.ver = false;
												x.ver = true;
												this.rotacao_dir(x);
												x = n.p.dir;
										}
										// caso 4
										x.ver = n.p.ver;
										n.p.ver = false;
										x.dir.ver = false;
										this.rotacao_esq(n.p);
										n = this.raiz;
								}
						}
						else {
								x = n.p.esq;

								if (x.ver) { // caso 1
										x.ver = false;
										n.p.ver = true;
										this.rotacao_dir(n.p);
										x = n.p.esq;
								}
								if (!x.esq.ver && !x.dir.ver) { // caso 2
										x.ver = true;
										n = n.p;
								}
								else {
										if (!x.esq.ver) { // caso 3
												x.dir.ver = false;
												x.ver = true;
												this.rotacao_esq(x);
												x = n.p.esq;
										}
										// caso 4
										x.ver = n.p.ver;
										n.p.ver = false;
										x.esq.ver = false;
										this.rotacao_dir(n.p);
										n = this.raiz;
								}
						}
				}
				n.ver = false;
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

		public void grafico() {
			System.out.println("digraph Arvore {");
			this.raiz.grafico();
			System.out.println("\tnil [style = filled, fillcolor = black, fontcolor = white];");
			System.out.println("}");
		}
}
