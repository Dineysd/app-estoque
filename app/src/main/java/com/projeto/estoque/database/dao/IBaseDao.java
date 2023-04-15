package com.projeto.crud_teste.database.dao;

import java.util.List;

interface IBaseDao<T> {

    void salvar(T t);
    void atualizar(T t);
    List<T> buscarTodos();
    T buscar(Long id);
    String[] campos();
}
