package com.projeto.estoque.model;

import android.content.ContentValues;

import java.util.Date;

import static com.projeto.estoque.database.TabelasSql.COLUMN_ATIVO;
import static com.projeto.estoque.database.TabelasSql.COLUMN_CATEGORIA_ID;
import static com.projeto.estoque.database.TabelasSql.COLUMN_DATA;
import static com.projeto.estoque.database.TabelasSql.COLUMN_DESCRICAO;
import static com.projeto.estoque.database.TabelasSql.COLUMN_EMBALAGEM_ID;
import static com.projeto.estoque.database.TabelasSql.COLUMN_ID;
import static com.projeto.estoque.database.TabelasSql.COLUMN_MARCA_ID;
import static com.projeto.estoque.database.TabelasSql.COLUMN_PRECO_UNIT;
import static com.projeto.estoque.database.TabelasSql.sdf;

public class Produto extends ModelBase{

    public static final String TABLE_NAME_PRODUTO = "produto";
    private Double precoUnit;
    private Date data;
    private Marca marca;
    private Categoria categoria;
    private Embalagem embalagem;
    
    public Produto(){
        this.marca = new Marca();
        this.categoria = new Categoria();
        this.embalagem = new Embalagem();
    }

    public Double getPrecoUnit() {
        return precoUnit;
    }

    public void setPrecoUnit(Double precoUni) {
        this.precoUnit = precoUni;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Embalagem getEmbalagem() {
        return embalagem;
    }

    public void setEmbalagem(Embalagem embalagem) {
        this.embalagem = embalagem;
    }

    public ContentValues getContentValues(boolean inserir) {
        ContentValues values = new ContentValues();
        if(inserir) {
            values.put(COLUMN_ID, this.id);
        }
        values.put(COLUMN_DESCRICAO, this.descricao);
        values.put(COLUMN_PRECO_UNIT, precoUnit);
        values.put(COLUMN_DATA, data == null?null: sdf.format(this.data));
        values.put(COLUMN_MARCA_ID, marca.getId());
        values.put(COLUMN_CATEGORIA_ID, categoria.getId());
        values.put(COLUMN_EMBALAGEM_ID, embalagem.getId());
        values.put(COLUMN_ATIVO, ativo? 1:0);
        return values;
    }
}
