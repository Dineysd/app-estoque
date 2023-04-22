package com.projeto.estoque.model.dto;

public class ProdutoDTO {
        private String codigo_produto;
        private String descricao_produto;
        private String codigo_fornecedor;
        private String codigo_embalagem;
        private String descricao_embalagem;
        private int quantidade_embalagem;
        private String codigo_barra;
        private boolean estoque_baixo;
        private int quantidade_caixa;
        private int saldo_estoque;
        private boolean permite_vender;
        private boolean reservado;
        private double peso;
        private String status;
        private String codigo_marca;
        private double preco_custo;

        public ProdutoDTO(String codigoProduto, String descricaoProduto, String codigoFornecedor,
        String codigoEmbalagem, String descricaoEmbalagem, int quantidadeEmbalagem,
        String codigoBarra, boolean estoqueBaixo, int quantidadeCaixa, int saldoEstoque,
        boolean permiteVender, boolean reservado, double peso, String status, String codigoMarca,
        double precoCusto) {
        this.codigo_produto = codigoProduto;
        this.descricao_produto = descricaoProduto;
        this.codigo_fornecedor = codigoFornecedor;
        this.codigo_embalagem = codigoEmbalagem;
        this.descricao_embalagem = descricaoEmbalagem;
        this.quantidade_embalagem = quantidadeEmbalagem;
        this.codigo_barra = codigoBarra;
        this.estoque_baixo = estoqueBaixo;
        this.quantidade_caixa = quantidadeCaixa;
        this.saldo_estoque = saldoEstoque;
        this.permite_vender = permiteVender;
        this.reservado = reservado;
        this.peso = peso;
        this.status = status;
        this.codigo_marca = codigoMarca;
        this.preco_custo = precoCusto;
        }
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

    public boolean isEstoqueBaixo() {
        return estoque_baixo;
    }

    public int getQuantidadeCaixa() {
        return quantidade_caixa;
    }

    public int getSaldoEstoque() {
        return saldo_estoque;
    }

    public boolean isPermiteVender() {
        return permite_vender;
    }

    public boolean isReservado() {
        return reservado;
    }

    public double getPeso() {
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




