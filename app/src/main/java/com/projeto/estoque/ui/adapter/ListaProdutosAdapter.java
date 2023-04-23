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
			holder.descricao_marca = convertView.findViewById(R.id.tvMarca);
			holder.descricao_emb = convertView.findViewById(R.id.tvEmb);
			holder.cod_barra = convertView.findViewById(R.id.tvCodBarra);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		Produto produto = produtos.get(position);
		holder.descricao.setText(produto.getId()+" - "+produto.getDescricao());
		double preco = (produto.getPrecoUnit()/1000);
		holder.preco.setText("Preco: "+String.format(Locale.getDefault(), "R$ %.2f",preco ));
		String dataFormatada = produto.getData()== null? null:
				new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(produto.getData());
		holder.data.setText("Data: "+dataFormatada);
		holder.descricao_marca.setText("Marca: "+produto.getMarca().getDescricao());
		holder.cod_barra.setText("Cod.barra: "+produto.getCodigoBarra());
		holder.descricao_emb.setText("Emb.: "+produto.getEmbalagem().getDescricao());
		
		return convertView;
		
	}

	private static class ViewHolder {
		TextView data, descricao, preco, descricao_marca, cod_barra, descricao_emb;

	}
	
	
}
