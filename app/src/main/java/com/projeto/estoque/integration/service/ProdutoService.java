package com.projeto.estoque.integration.service;

import com.projeto.estoque.model.dto.ProdutoDTO;
import com.projeto.estoque.model.dto.ProdutoPaginacaoDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProdutoService {

    @GET("/produtos/paginacao")
    Call<ProdutoPaginacaoDTO> listarProdutos(@Query("pagina") int pagina, @Query("total_paginas") int totalPaginas);

    @POST("/produtos")
    Call<Void> criarProduto(@Body ProdutoDTO produtoDTO);

    @PUT("/produtos/{id}")
    Call<Void> atualizarProduto(@Path("id") long id, @Body ProdutoDTO produtoDTO);

    @DELETE("/produtos/{id}")
    Call<Void> excluirProduto(@Path("id") long id);
}


