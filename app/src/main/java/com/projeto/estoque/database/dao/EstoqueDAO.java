package com.projeto.crud_teste.database.dao;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.projeto.crud_teste.database.HelperDb;
import com.projeto.crud_teste.model.Estoque;
import com.projeto.crud_teste.model.Marca;
import com.projeto.crud_teste.model.Produto;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.projeto.crud_teste.database.TabelasSql.COLUMN_ATIVO;
import static com.projeto.crud_teste.database.TabelasSql.COLUMN_DATA;
import static com.projeto.crud_teste.database.TabelasSql.COLUMN_DESCRICAO;
import static com.projeto.crud_teste.database.TabelasSql.COLUMN_ID;
import static com.projeto.crud_teste.database.TabelasSql.COLUMN_MARCA_ID;
import static com.projeto.crud_teste.database.TabelasSql.COLUMN_PRECO_UNIT;
import static com.projeto.crud_teste.database.TabelasSql.COLUMN_PRODUTO_ID;
import static com.projeto.crud_teste.database.TabelasSql.COLUMN_SALDO;
import static com.projeto.crud_teste.database.TabelasSql.COLUMN_TOTAL_MERCADORIA;
import static com.projeto.crud_teste.database.TabelasSql.sdf;

public class EstoqueDAO implements IBaseDao<Estoque>{

    private Context context;
    private HelperDb database;


    public EstoqueDAO(Context context) {
        this.database = new HelperDb(context);
        this.context = context;
    }

    @Override
    public void salvar(Estoque estoque){
        this.database.Insert(Estoque.TABLE_NAME_ESTOQUE,estoque.getContentValues(true));
    }
    @Override
    public void atualizar(Estoque estoque){
        String[] args = {String.valueOf(estoque.getId())};
        this.database.Update(Estoque.TABLE_NAME_ESTOQUE, estoque.getContentValues(false),"where id = ?",args);
    }
    @Override
    public List<Estoque> buscarTodos(){
        List<Estoque> estoques = new ArrayList<>();
        Cursor cursor = null;

        try {
             cursor = this.database.Select(Estoque.TABLE_NAME_ESTOQUE, campos(), null, null, null, null, null);
            // Verificar se há resultados
            if (cursor.moveToFirst()) {
                // Converter cada registro retornado em um objeto Estoque e adicioná-lo na lista
                do {
                    Estoque estoque = getDadosEstoque(cursor);
                    estoques.add(estoque);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            Log.d(TAG, "buscarTodos: ");

        }finally {
            // Fechar o cursor
            cursor.close();
        }


        return estoques;
    }

    private Estoque getDadosEstoque(Cursor cursor) throws ParseException {
        Estoque estoque = new Estoque();
        estoque.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        estoque.setSaldo(cursor.getLong(cursor.getColumnIndex(COLUMN_SALDO)));
        estoque.setTotalMercadoria(cursor.getDouble(cursor.getColumnIndex(COLUMN_TOTAL_MERCADORIA)));
        estoque.setDescricao(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRICAO)));
        estoque.setPrecoUnit(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRECO_UNIT)));
        estoque.getProduto().setId(cursor.getLong(cursor.getColumnIndex(COLUMN_PRODUTO_ID)));
        estoque.getMarca().setId(cursor.getLong(cursor.getColumnIndex(COLUMN_MARCA_ID)));
        if (cursor.getString(cursor.getColumnIndex(COLUMN_DATA)) != null) {
            estoque.setData(sdf.parse(cursor.getString(cursor.getColumnIndex(COLUMN_DATA))));
        }
        estoque.setAtivo(cursor.getInt(cursor.getColumnIndex(COLUMN_ATIVO)) > 0);
        return estoque;
    }

    @Override
    public Estoque buscar(Long id){
        Estoque estoque = null;
        Cursor cursor = null;

        try {

            cursor = this.database.Select(Estoque.TABLE_NAME_ESTOQUE, campos(), "where id = ?", null, null, null, null);
            // Verificar se há resultados
            if (cursor.moveToFirst()) {
                // Converter cada registro retornado em um objeto Estoque e adicioná-lo na lista
                estoque = getDadosEstoque(cursor);
            }
        }catch (Exception e){
            Log.d(TAG, "buscarTodos: ");

        }finally {
            // Fechar o cursor
            cursor.close();
        }
        return estoque;
    }

    @Override
    public String[] campos() {
        return new String[]{
                COLUMN_ID, COLUMN_PRODUTO_ID, COLUMN_DATA,
                COLUMN_SALDO, COLUMN_TOTAL_MERCADORIA,
                COLUMN_DESCRICAO, COLUMN_MARCA_ID, COLUMN_PRECO_UNIT, COLUMN_ATIVO};
    }


}
