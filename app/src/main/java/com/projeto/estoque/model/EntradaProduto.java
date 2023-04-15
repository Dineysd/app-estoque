package com.projeto.crud_teste.model;

import java.util.Date;

public class EntradaProduto extends ModelBase{
    public static final String TABLE_NAME_ENTRADA_PRODUTO = "entradaProduto";
    private Date data_entrada;
    private Produto produto;
    private Long quantidade;

    public static String getTableNameEntradaProduto() {
        return TABLE_NAME_ENTRADA_PRODUTO;
    }

    public Date getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(Date data_entrada) {
        this.data_entrada = data_entrada;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }
}
