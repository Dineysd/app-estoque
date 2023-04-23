package com.projeto.estoque.integration.repository;

import android.content.Context;

import com.projeto.estoque.integration.config.RetrofitConfig;
import com.projeto.estoque.integration.service.ProdutoService;
import com.projeto.estoque.model.dto.ProdutoDTO;
import com.projeto.estoque.model.dto.ProdutoPaginacaoDTO;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class ProdutoRepository {
    private ProdutoService service;

    public ProdutoRepository(Context ctx) {
        service = new RetrofitConfig(ctx).createRepository(ProdutoService.class);
    }

    public ProdutoPaginacaoDTO listarProdutos(int pagina, int totalPaginas) throws IOException {
        Call<ProdutoPaginacaoDTO> call = service.listarProdutos(pagina, totalPaginas);
        Response<ProdutoPaginacaoDTO> response = call.execute();
        if (!response.isSuccessful()) {
            throw new IOException("Erro ao listar produtos: " + response.code() + " " + response.message());
        }
        return response.body();
    }

    public void criarProduto(ProdutoDTO produtoDTO) throws IOException {
        Call<Void> call = service.criarProduto(produtoDTO);
        Response<Void> response = call.execute();
        if (!response.isSuccessful()) {
            throw new IOException("Erro ao criar produto: " + response.code() + " " + response.message());
        }
    }

    public void atualizarProduto(long id, ProdutoDTO produtoDTO) throws IOException {
        Call<Void> call = service.atualizarProduto(id, produtoDTO);
        Response<Void> response = call.execute();
        if (!response.isSuccessful()) {
            throw new IOException("Erro ao atualizar produto: " + response.code() + " " + response.message());
        }
    }

    public void excluirProduto(long id) throws IOException {
        Call<Void> call = service.excluirProduto(id);
        Response<Void> response = call.execute();
        if (!response.isSuccessful()) {
            throw new IOException("Erro ao excluir produto: " + response.code() + " " + response.message());
        }
    }
}
