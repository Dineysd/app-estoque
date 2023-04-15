package com.projeto.crud_teste.model;

import java.util.Date;

public class SaidaProduto extends ModelBase{
    public static final String TABLE_NAME_SAIDA_PRODUTO = "saidaProduto";
    private Date data_saida;
    private Produto produto;
    private Long quantidade;

    public static String getTableNameSaidaProduto() {
        return TABLE_NAME_SAIDA_PRODUTO;
    }

    public Date getData_saida() {
        return data_saida;
    }

    public void setData_saida(Date data_saida) {
        this.data_saida = data_saida;
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
