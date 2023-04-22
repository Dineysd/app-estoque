package com.projeto.estoque.model;

import android.content.ContentValues;

import com.projeto.estoque.model.dto.ProdutoDTO;

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

    // novos atributos
    private String codigoProduto;
    private String codigoFornecedor;
    private String codigoEmbalagem;
    private String descricaoEmbalagem;
    private int quantidadeEmbalagem;
    private String codigoBarra;
    private boolean estoqueBaixo;
    private int quantidadeCaixa;
    private int saldoEstoque;
    private boolean permiteVender;
    private boolean reservado;
    private double peso;
    private String status;
    private String codigoMarca;
    private double precoCusto;

    public Produto() {}

    public Produto(ProdutoDTO dto) {
        this.codigoProduto = dto.getCodigoProduto();
        this.descricao = dto.getDescricaoProduto();
        this.codigoFornecedor = dto.getCodigoFornecedor();
        this.codigoEmbalagem = dto.getCodigoEmbalagem();
        this.descricaoEmbalagem = dto.getDescricaoEmbalagem();
        this.quantidadeEmbalagem = dto.getQuantidadeEmbalagem();
        this.codigoBarra = dto.getCodigoBarra();
        this.estoqueBaixo = dto.isEstoqueBaixo();
        this.quantidadeCaixa = dto.getQuantidadeCaixa();
        this.saldoEstoque = dto.getSaldoEstoque();
        this.permiteVender = dto.isPermiteVender();
        this.reservado = dto.isReservado();
        this.peso = dto.getPeso();
        this.status = dto.getStatus();
        this.codigoMarca = dto.getCodigoMarca();
        this.precoCusto = dto.getPrecoCusto();
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
