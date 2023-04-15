package com.projeto.estoque.model;

import android.content.ContentValues;

import java.util.Date;
import java.util.List;

import static com.projeto.estoque.database.TabelasSql.COLUMN_ATIVO;
import static com.projeto.estoque.database.TabelasSql.COLUMN_DATA;
import static com.projeto.estoque.database.TabelasSql.COLUMN_DESCRICAO;
import static com.projeto.estoque.database.TabelasSql.COLUMN_ID;
import static com.projeto.estoque.database.TabelasSql.COLUMN_MARCA_ID;
import static com.projeto.estoque.database.TabelasSql.COLUMN_PRECO_UNIT;
import static com.projeto.estoque.database.TabelasSql.COLUMN_PRODUTO_ID;
import static com.projeto.estoque.database.TabelasSql.COLUMN_SALDO;
import static com.projeto.estoque.database.TabelasSql.COLUMN_TOTAL_MERCADORIA;
import static com.projeto.estoque.database.TabelasSql.sdf;

public class Estoque extends ModelBase{

    public static final String TABLE_NAME_ESTOQUE = "estoque";
    private Long id;
    private Date data;
    private Produto produto;
    private List<EntradaProduto> entradas;
    private List<SaidaProduto> saida;
    private long saldo;
    private Double totalMercadoria;
    private String descricao;
    private Marca marca;
    private Double precoUnit;

    public Estoque(){
        this.marca = new Marca();
        this.produto = new Produto();
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<EntradaProduto> getEntradas() {
        return entradas;
    }

    public void setEntradas(List<EntradaProduto> entradas) {
        this.entradas = entradas;
    }

    public List<SaidaProduto> getSaida() {
        return saida;
    }

    public void setSaida(List<SaidaProduto> saida) {
        this.saida = saida;
    }

    public long getSaldo() {
        return saldo;
    }

    public void setSaldo(long saldo) {
        this.saldo = saldo;
    }

    public Double getTotalMercadoria() {
        return totalMercadoria;
    }

    public void setTotalMercadoria(Double totalMercadoria) {
        this.totalMercadoria = totalMercadoria;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Double getPrecoUnit() {
        return precoUnit;
    }

    public void setPrecoUnit(Double precoUnit) {
        this.precoUnit = precoUnit;
    }


    public ContentValues getContentValues(boolean inserir) {
        ContentValues values = new ContentValues();
        if(inserir) {
            values.put(COLUMN_ID, this.id);
        }
        values.put(COLUMN_DATA, data == null?null: sdf.format(this.data));
        values.put(COLUMN_PRODUTO_ID, this.produto.getId());
        values.put(COLUMN_SALDO, this.saldo);
        values.put(COLUMN_TOTAL_MERCADORIA, this.totalMercadoria);
        values.put(COLUMN_DESCRICAO, this.descricao);
        values.put(COLUMN_MARCA_ID, this.produto.getMarca().getId());
        values.put(COLUMN_PRECO_UNIT, this.precoUnit);
        values.put(COLUMN_ATIVO, ativo? 1:0);
        return values;
    }


}
