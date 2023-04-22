package com.projeto.estoque.model;

import android.database.Cursor;

import static com.projeto.estoque.database.TabelasSql.COLUMN_ID;

public class Categoria extends ModelBase {
    public static final String TABLE_NAME_CATEGORIA = "categoria";

    public Categoria(Cursor cursor) {
        super(cursor);
        this.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        this.setDescricao(cursor.getString(cursor.getColumnIndex("descricaoCategoria")));
    }

    public Categoria(){

    }
}
