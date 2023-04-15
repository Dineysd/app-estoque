package com.projeto.crud_teste.database.dao;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.projeto.crud_teste.database.HelperDb;
import com.projeto.crud_teste.model.Produto;

import java.text.ParseException;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.projeto.crud_teste.database.TabelasSql.COLUMN_ATIVO;
import static com.projeto.crud_teste.database.TabelasSql.COLUMN_CATEGORIA_ID;
import static com.projeto.crud_teste.database.TabelasSql.COLUMN_DATA;
import static com.projeto.crud_teste.database.TabelasSql.COLUMN_DESCRICAO;
import static com.projeto.crud_teste.database.TabelasSql.COLUMN_EMBALAGEM_ID;
import static com.projeto.crud_teste.database.TabelasSql.COLUMN_ID;
import static com.projeto.crud_teste.database.TabelasSql.COLUMN_MARCA_ID;
import static com.projeto.crud_teste.database.TabelasSql.COLUMN_PRECO_UNIT;
import static com.projeto.crud_teste.database.TabelasSql.sdf;
import static com.projeto.crud_teste.model.Produto.TABLE_NAME_PRODUTO;

public class ProdutoDAO implements IBaseDao<Produto> {

    private Context context;
    private HelperDb database;


    public ProdutoDAO(Context context) {
        this.database = new HelperDb(context);
        this.context = context;
    }


    @Override
    public void salvar(Produto produto) {
        this.database.Insert(TABLE_NAME_PRODUTO,produto.getContentValues(true));

    }

    @Override
    public void atualizar(Produto produto) {
        String[] args = {String.valueOf(produto.getId())};
        this.database.Update(TABLE_NAME_PRODUTO, produto.getContentValues(false),"where id = ?",args);
    }

    @Override
    public List<Produto> buscarTodos() {
        return null;
    }

    @Override
    public Produto buscar(Long id) {
        Produto produto = null;
        Cursor cursor = null;

        try {
            String[] args = {String.valueOf(id)};
            cursor = this.database.Select(TABLE_NAME_PRODUTO, campos(), "where id = ?", args, null, null, COLUMN_DATA);
            // Verificar se há resultados
            if (cursor.moveToFirst()) {
                // Converter cada registro retornado em um objeto Estoque e adicioná-lo na lista
                produto = getDadosEstoque(cursor);
            }
        }catch (Exception e){
            Log.d(TAG, "buscarTodos: ");

        }finally {
            // Fechar o cursor
            cursor.close();
        }
        return produto;
    }

    private Produto getDadosEstoque(Cursor cursor) throws ParseException {
        Produto produto = new Produto();
        produto.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        produto.setDescricao(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRICAO)));
        produto.setPrecoUnit(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRECO_UNIT)));
        produto.getCategoria().setId(cursor.getLong(cursor.getColumnIndex(COLUMN_CATEGORIA_ID)));
        produto.getMarca().setId(cursor.getLong(cursor.getColumnIndex(COLUMN_MARCA_ID)));
        produto.getEmbalagem().setId(cursor.getLong(cursor.getColumnIndex(COLUMN_EMBALAGEM_ID)));
        if (cursor.getString(cursor.getColumnIndex(COLUMN_DATA)) != null) {
            produto.setData(sdf.parse(cursor.getString(cursor.getColumnIndex(COLUMN_DATA))));
        }
        produto.setAtivo(cursor.getInt(cursor.getColumnIndex(COLUMN_ATIVO)) > 0);
        return produto;
    }

    @Override
    public String[] campos() {
        return new String[]{
                COLUMN_ID, COLUMN_DESCRICAO,
                COLUMN_PRECO_UNIT, COLUMN_DATA,
                COLUMN_MARCA_ID, COLUMN_CATEGORIA_ID, COLUMN_EMBALAGEM_ID, COLUMN_ATIVO};
    }
}