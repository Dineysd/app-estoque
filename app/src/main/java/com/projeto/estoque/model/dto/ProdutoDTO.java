package com.projeto.estoque.model.dto;

public class ProdutoDTO {
        private String codigo_produto;
        private String descricao_produto;
        private String codigo_fornecedor;
        private String codigo_embalagem;
        private String descricao_embalagem;
        private int quantidade_embalagem;
        private String codigo_barra;
        private int estoque_baixo;
        private int quantidade_caixa;
        private int saldo_estoque;
        private int permite_vender;
        private int reservado;
        private String peso;
        private String status;
        private String codigo_marca;
        private double preco_custo;
    // Getters and setters

    public String getCodigoProduto() {
        return codigo_produto;
    }

    public String getDescricaoProduto() {
        return descricao_produto;
    }

    public String getCodigoFornecedor() {
        return codigo_fornecedor;
    }

    public String getCodigoEmbalagem() {
        return codigo_embalagem;
    }

    public String getDescricaoEmbalagem() {
        return descricao_embalagem;
    }

    public int getQuantidadeEmbalagem() {
        return quantidade_embalagem;
    }

    public String getCodigoBarra() {
        return codigo_barra;
    }

    public int isEstoqueBaixo() {
        return estoque_baixo;
    }

    public int getQuantidadeCaixa() {
        return quantidade_caixa;
    }

    public int getSaldoEstoque() {
        return saldo_estoque;
    }

    public int isPermiteVender() {
        return permite_vender;
    }

    public int isReservado() {
        return reservado;
    }

    public String getPeso() {
        return peso;
    }

    public String getStatus() {
        return status;
    }

    public String getCodigoMarca() {
        return codigo_marca;
    }

    public double getPrecoCusto() {
        return preco_custo;
    }
}




