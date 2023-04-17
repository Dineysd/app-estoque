package com.projeto.estoque.presenter;

import android.content.Context;

import com.projeto.estoque.database.dao.ProdutoDAO;
import com.projeto.estoque.model.Produto;

import java.util.List;

public class ProdutoPresenter implements ProdutoContract.Presenter{
	
	private final ProdutoContract.View view;
	private final Context context;
	private final ProdutoDAO produtoDAO;
	
	public ProdutoPresenter(ProdutoContract.View view, Context context) {
		this.view = view;
		this.context = context;
		this.produtoDAO = new ProdutoDAO(context);
	}
	
	public void carregarProdutos() {
		List<Produto> produtos = produtoDAO.buscarTodos();
		if (produtos.isEmpty()) {
			view.exibirMensagem("Não há produtos cadastrados.");
		} else {
			view.exibirProdutos(produtos);
		}
	}
}
