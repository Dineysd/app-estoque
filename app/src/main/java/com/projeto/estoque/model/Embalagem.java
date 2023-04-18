package com.projeto.estoque.model;

import android.database.Cursor;

import static com.projeto.estoque.database.TabelasSql.COLUMN_ID;

public class Embalagem extends ModelBase{
    public static final String TABLE_NAME_EMBALAGEM = "embalagem";

    public Embalagem(Cursor cursor) {
        super(cursor);
        this.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        this.setDescricao(cursor.getString(cursor.getColumnIndex("descricaoEmbalagem")));
    }

    public Embalagem() {
    }
}
