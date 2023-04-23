package com.projeto.estoque.model.dto;

import java.util.List;

public class ProdutoPaginacaoDTO {

    private int total_registro;
    private List<ProdutoDTO> produtos;

    public int getTotal_registro() {
        return total_registro;
    }

    public void setTotal_registro(int total_registro) {
        this.total_registro = total_registro;
    }

    public List<ProdutoDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoDTO> produtos) {
        this.produtos = produtos;
    }
}

