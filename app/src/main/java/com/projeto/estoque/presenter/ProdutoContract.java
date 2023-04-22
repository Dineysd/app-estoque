package com.projeto.estoque.presenter;

import com.projeto.estoque.model.Produto;

import java.util.List;

public interface ProdutoContract {
	interface View {
		void exibirProdutos(List<Produto> produtos);
		void exibirMensagem(String mensagem);
	}
	
	interface Presenter{
		void carregarProdutos();
	}
	
}
