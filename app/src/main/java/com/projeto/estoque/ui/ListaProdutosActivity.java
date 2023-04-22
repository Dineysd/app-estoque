package com.projeto.estoque.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.projeto.estoque.R;
import com.projeto.estoque.model.Produto;
import com.projeto.estoque.presenter.ProdutoContract;
import com.projeto.estoque.presenter.ProdutoPresenter;
import com.projeto.estoque.ui.adapter.ListaProdutosAdapter;
import com.projeto.estoque.utils.ToastUtils;

import java.util.List;

public class ListaProdutosActivity extends AppCompatActivity implements ProdutoContract.View {
	
	private ProdutoContract.Presenter presenter;
	private ListView plistView;
	private ListaProdutosAdapter ProdutoAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_produtos);
		Toolbar toolbar = findViewById(R.id.toolbar);
		
		setSupportActionBar(toolbar);
		
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		
		plistView = findViewById(R.id.list_view);
		
		this.presenter = new ProdutoPresenter(this,this);
		
		presenter.carregarProdutos();
	}
	
	@Override
	public void exibirProdutos(List<Produto> produtos) {
		// Cria o adapter para a ListView
		if (ProdutoAdapter == null) {
			ProdutoAdapter = new ListaProdutosAdapter(getBaseContext(), produtos);
			plistView.setAdapter(ProdutoAdapter);
		} else {
			plistView.deferNotifyDataSetChanged();
		}
	
	}
	
	@Override
	public void exibirMensagem(String mensagem) {
		ToastUtils.showErrorToast(getBaseContext(), mensagem);
		finish();
	}
}
