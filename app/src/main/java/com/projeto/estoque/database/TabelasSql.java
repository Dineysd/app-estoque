package com.projeto.estoque.database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.projeto.estoque.model.Categoria.TABLE_NAME_CATEGORIA;
import static com.projeto.estoque.model.Embalagem.TABLE_NAME_EMBALAGEM;
import static com.projeto.estoque.model.EntradaProduto.TABLE_NAME_ENTRADA_PRODUTO;
import static com.projeto.estoque.model.Estoque.TABLE_NAME_ESTOQUE;
import static com.projeto.estoque.model.Marca.TABLE_NAME_MARCA;
import static com.projeto.estoque.model.Produto.TABLE_NAME_PRODUTO;
import static com.projeto.estoque.model.SaidaProduto.TABLE_NAME_SAIDA_PRODUTO;

public class TabelasSql {

    public static final String NAME_DATABASE ="db_estoque.db";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PRODUTO_ID = "produto_id";
    public static final String COLUMN_SALDO = "saldo";
    public static final String COLUMN_TOTAL_MERCADORIA = "total_mercadoria";
    public static final String COLUMN_DESCRICAO = "descricao";
    public static final String COLUMN_MARCA_ID = "marca_id";
    public static final String COLUMN_PRECO_UNIT = "preco_unit";
    public static final String COLUMN_PRECOUNI = "preco_uni";
    public static final String COLUMN_CATEGORIA_ID = "categoria_id";
    public static final String COLUMN_EMBALAGEM_ID = "embalagem_id";
    public static final String COLUMN_QUANTIDADE = "quantidade";
    public static final String COLUMN_DATA ="data";
    public static final String COLUMN_DATA_ENTRADA = "data_entrada";
    public static final String COLUMN_DATA_SAIDA = "data_saida";
    public static final String COLUMN_ATIVO = "ativo";
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    final String CREATE_TABLE_ENTRADA =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_ENTRADA_PRODUTO + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_PRODUTO_ID + " INTEGER,"
                    + COLUMN_DESCRICAO + " TEXT,"
                    + COLUMN_QUANTIDADE + " INTEGER,"
                    + COLUMN_DATA_ENTRADA + " DATE,"
                    + COLUMN_ATIVO + " BOOLEAN,"
                    + "FOREIGN KEY(" + COLUMN_PRODUTO_ID + ") REFERENCES " + TABLE_NAME_PRODUTO + "(" + COLUMN_ID + ")"
                    + ");";

    final String CREATE_TABLE_SAIDA =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_SAIDA_PRODUTO + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_PRODUTO_ID + " INTEGER,"
                    + COLUMN_DESCRICAO + " TEXT,"
                    + COLUMN_QUANTIDADE + " INTEGER,"
                    + COLUMN_DATA_SAIDA + " DATE,"
                    + COLUMN_ATIVO + " BOOLEAN,"
                    + "FOREIGN KEY(" + COLUMN_PRODUTO_ID + ") REFERENCES " + TABLE_NAME_PRODUTO + "(" + COLUMN_ID + ")"
                    + ");";

    final String CREATE_TABLE_ESTOQUE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_ESTOQUE + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_PRODUTO_ID + " INTEGER,"
                    + COLUMN_SALDO + " INTEGER,"
                    + COLUMN_TOTAL_MERCADORIA + " REAL,"
                    + COLUMN_DESCRICAO + " TEXT,"
                    + COLUMN_MARCA_ID + " INTEGER,"
                    + COLUMN_PRECO_UNIT + " REAL,"
                    + COLUMN_DATA + " DATE,"
                    + COLUMN_ATIVO + " BOOLEAN,"
                    + "FOREIGN KEY(" + COLUMN_PRODUTO_ID + ") REFERENCES produto(id),"
                    + "FOREIGN KEY(" + COLUMN_MARCA_ID + ") REFERENCES marca(id)"
                    + ")";


    final String CREATE_TABLE_PRODUTO =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_PRODUTO + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_DESCRICAO + " TEXT,"
                    + COLUMN_PRECO_UNIT + " REAL,"
                    + COLUMN_MARCA_ID + " INTEGER,"
                    + COLUMN_CATEGORIA_ID + " INTEGER,"
                    + COLUMN_EMBALAGEM_ID + " INTEGER,"
                    + COLUMN_DATA + " DATE,"
                    + COLUMN_ATIVO + " BOOLEAN,"
                    + "FOREIGN KEY(" + COLUMN_MARCA_ID + ") REFERENCES marca(id),"
                    + "FOREIGN KEY(" + COLUMN_CATEGORIA_ID + ") REFERENCES categoria(id),"
                    + "FOREIGN KEY(" + COLUMN_EMBALAGEM_ID + ") REFERENCES embalagem(id)"
                    + ")";

    final String CREATE_TABLE_CATEGORIA =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_CATEGORIA + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_DESCRICAO + " TEXT,"
                    + COLUMN_ATIVO + " BOOLEAN"
                    + ")";

    final String CREATE_TABLE_MARCA =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_MARCA + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_DESCRICAO + " TEXT,"
                    + COLUMN_ATIVO + " BOOLEAN"
                    + ")";

    final String CREATE_TABLE_EMBALAGEM =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_EMBALAGEM + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_DESCRICAO + " TEXT,"
                    + COLUMN_ATIVO + " BOOLEAN"
                    + ")";

    public ArrayList<String> getListTabelas(){
        ArrayList<String> tabelas = new ArrayList<>();
        tabelas.add(CREATE_TABLE_MARCA);
        tabelas.add(CREATE_TABLE_EMBALAGEM);
        tabelas.add(CREATE_TABLE_CATEGORIA);
        tabelas.add(CREATE_TABLE_PRODUTO);
        tabelas.add(CREATE_TABLE_ESTOQUE);
        tabelas.add(CREATE_TABLE_ENTRADA);
        tabelas.add(CREATE_TABLE_SAIDA);

        return tabelas;
    }
}
