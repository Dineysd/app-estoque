package com.projeto.estoque.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.projeto.estoque.R;
import com.projeto.estoque.model.Produto;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ListaProdutosAdapter extends BaseAdapter {
	
	private List<Produto> produtos;
	private LayoutInflater inflater;
	
	public ListaProdutosAdapter(Context context, List<Produto> produtos) {
		this.produtos = produtos;
		this.inflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return produtos.size();
	}
	
	@Override
	public Object getItem(int position) {
		return produtos.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return produtos.get(position).getId();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_produto, parent, false);
			holder = new ViewHolder();
			holder.descricao = convertView.findViewById(R.id.tvDescricao);
			holder.preco = convertView.findViewById(R.id.tvPreco);
			holder.data = convertView.findViewById(R.id.tvData);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		Produto produto = produtos.get(position);
		holder.descricao.setText(produto.getDescricao());
		holder.preco.setText(String.format(Locale.getDefault(), "R$ %.2f", produto.getPrecoUnit()));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String dataFormatada = sdf.format(produto.getData());
		holder.data.setText(dataFormatada);
		
		return convertView;
		
	}

	private static class ViewHolder {
		TextView data;
		TextView descricao;
		TextView preco;
	}
	
	
}
