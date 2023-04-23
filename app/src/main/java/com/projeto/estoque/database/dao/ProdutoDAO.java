package com.projeto.estoque.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.projeto.estoque.database.HelperDb;
import com.projeto.estoque.database.TabelasSql;
import com.projeto.estoque.model.Categoria;
import com.projeto.estoque.model.Embalagem;
import com.projeto.estoque.model.Marca;
import com.projeto.estoque.model.Produto;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.projeto.estoque.database.TabelasSql.COLUMN_ATIVO;
import static com.projeto.estoque.database.TabelasSql.COLUMN_CODIGO_BARRA;
import static com.projeto.estoque.database.TabelasSql.COLUMN_DATA;
import static com.projeto.estoque.database.TabelasSql.COLUMN_DESCRICAO;
import static com.projeto.estoque.database.TabelasSql.COLUMN_EMBALAGEM_ID;
import static com.projeto.estoque.database.TabelasSql.COLUMN_ID;
import static com.projeto.estoque.database.TabelasSql.COLUMN_MARCA_ID;
import static com.projeto.estoque.database.TabelasSql.COLUMN_PRECO_UNIT;
import static com.projeto.estoque.database.TabelasSql.sdf;
import static com.projeto.estoque.model.Embalagem.TABLE_NAME_EMBALAGEM;
import static com.projeto.estoque.model.Marca.TABLE_NAME_MARCA;
import static com.projeto.estoque.model.Produto.TABLE_NAME_PRODUTO;

public class ProdutoDAO implements IBaseDao<Produto> {
    
    private HelperDb database;
    
    public ProdutoDAO(Context context) {
        this.database = new HelperDb(context);
    }


    @Override
    public void salvar(Produto produto) {
        salvarEmb(produto);
        salvarMarca(produto);

        long insert = this.database.Insert(TABLE_NAME_PRODUTO, produto.getContentValues(true));
        if (insert > 0){
            Log.d(TABLE_NAME_PRODUTO, "id: " + produto.getId());
        }else {
            Log.e(TABLE_NAME_PRODUTO, "error id: " + produto.getId());
        }

    }

    private void salvarEmb(Produto produto) {
        if(!exist(produto.getEmbalagem().getId(),TABLE_NAME_EMBALAGEM)) {
            boolean salvo = database.Insert(TABLE_NAME_EMBALAGEM, getContentValuesEmb(produto)) > 0;
            if (salvo) {
                Log.d(TABLE_NAME_EMBALAGEM, "id: " + produto.getEmbalagem().getId());
            } else {
                Log.e(TABLE_NAME_EMBALAGEM, "error id: " + produto.getEmbalagem().getId());
            }
        }
    }

    private void salvarMarca(Produto produto) {
        if(!exist(produto.getMarca().getId(),TABLE_NAME_MARCA)) {
            boolean salvo = database.Insert(TABLE_NAME_MARCA, getContentValuesMarca(produto)) > 0;
            if (salvo) {
                Log.d(TABLE_NAME_MARCA, "id: " + produto.getMarca().getId());
            } else {
                Log.e(TABLE_NAME_MARCA, "error id: " + produto.getMarca().getId());
            }
        }
    }

    public boolean exist(Long id, String nomeTabela){
        Cursor cursor = null;
        try {
            cursor = database.QuerySelect("SELECT COUNT(*) FROM " + nomeTabela + " WHERE id = " + id, null);
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            return count > 0;
        } catch (Exception e) {
            Log.d(TAG, "exist: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return false;
    }

    @NotNull
    private ContentValues getContentValuesEmb(Produto produto) {
        ContentValues valuesEmb = new ContentValues();
        valuesEmb.put(COLUMN_ID, produto.getEmbalagem().getId());
        valuesEmb.put(COLUMN_DESCRICAO, produto.getEmbalagem().getDescricao());
        valuesEmb.put(COLUMN_ATIVO, true);
        return valuesEmb;
    }

    @NotNull
    private ContentValues getContentValuesMarca(Produto produto) {
        ContentValues valuesEmb = new ContentValues();
        valuesEmb.put(COLUMN_ID, produto.getMarca().getId());
        valuesEmb.put(COLUMN_DESCRICAO, produto.getMarca().getDescricao());
        valuesEmb.put(COLUMN_ATIVO, true);
        return valuesEmb;
    }

    @Override
    public void atualizar(Produto produto) {
        String[] args = {String.valueOf(produto.getId())};
        this.database.Update(TABLE_NAME_PRODUTO, produto.getContentValues(false),"where id = ?",args);
    }

    @Override
    public List<Produto> buscarTodos() {
        List<Produto> produtos = new ArrayList<>();
        Cursor cursor = null;

        try {

            String sql = "SELECT p.* , m.id AS marca_id, m.descricao AS descricaoMarca," +
                    " e.id AS embalagem_id," +
                    " e.descricao AS descricaoEmbalagem " +
                    "FROM Produto p " +
                    "LEFT JOIN Marca m ON p.marca_id = m.id " +
                    "LEFT JOIN Embalagem e ON p.embalagem_id = e.id ";


            cursor = this.database.QuerySelect(sql, null);
            // Verificar se há resultados
            if (cursor != null && cursor.moveToFirst()) {
                // Converter cada registro retornado em um objeto Estoque e adicioná-lo na lista
                do {
                    Produto produto = getDados(cursor);
                    produtos.add(produto);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            Log.d(TAG, "buscarTodos: ");

        }finally {
            // Fechar o cursor
            if (cursor != null) {
                cursor.close();
            }
        }


        return produtos;
    }

    @Override
    public Produto buscar(Long id) {
        Produto produto = null;
        Cursor cursor = null;

        try {
            String[] args = {String.valueOf(id)};
            cursor = this.database.Select(TABLE_NAME_PRODUTO, campos(), "where id = ?", args, null, null, COLUMN_DATA);
            // Verificar se há resultados
            if (cursor != null && cursor.moveToFirst()) {
                // Converter cada registro retornado em um objeto Estoque e adicioná-lo na lista
                produto = getDados(cursor);
            }
        }catch (Exception e){
            Log.d(TAG, "buscarTodos: ");

        }finally {
            // Fechar o cursor
            if (cursor != null) {
                cursor.close();
            }
        }
        return produto;
    }

    private Produto getDados(Cursor cursor) throws ParseException {
        Produto produto = new Produto();
        produto.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        produto.setDescricao(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRICAO)));
        produto.setPrecoUnit(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRECO_UNIT)));
        produto.setCodigoBarra(cursor.getString(cursor.getColumnIndex(COLUMN_CODIGO_BARRA)));
        //produto.setCategoria(new Categoria(cursor));
        produto.setMarca(new Marca(cursor));
        produto.setEmbalagem(new Embalagem(cursor));
        if (cursor.getString(cursor.getColumnIndex(COLUMN_DATA)) != null) {
            produto.setData(sdf.parse(cursor.getString(cursor.getColumnIndex(COLUMN_DATA))));
        }
        produto.setAtivo(cursor.getInt(cursor.getColumnIndex(COLUMN_ATIVO)) > 0);
        return produto;
    }

    @Override
    public String[] campos() {
        return TabelasSql.campos(TABLE_NAME_PRODUTO);
    }
}
