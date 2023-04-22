package com.projeto.estoque.model;

import android.database.Cursor;

public abstract class ModelBase {
    public static final String TABLE_PADRAO = "padrao";

    protected long id;
    protected String descricao;
    protected boolean ativo;

    public ModelBase(Cursor cursor){
    }

    public ModelBase(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
