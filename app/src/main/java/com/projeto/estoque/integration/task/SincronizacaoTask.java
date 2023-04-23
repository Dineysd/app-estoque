package com.projeto.estoque.integration.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.projeto.estoque.database.dao.ProdutoDAO;
import com.projeto.estoque.integration.repository.ProdutoRepository;
import com.projeto.estoque.model.Produto;
import com.projeto.estoque.model.dto.ProdutoDTO;
import com.projeto.estoque.model.dto.ProdutoPaginacaoDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SincronizacaoTask extends AsyncTask<Void, Integer, Void> {

    private ProdutoRepository produtoRepository;
    private Context context;
    private int totalPaginas;
    private ProgressDialog progressDialog;
    private int tot_registro =0;

    public SincronizacaoTask(Context context, int totalPaginas) {
        this.produtoRepository = new ProdutoRepository(context);
        this.context = context;
        this.totalPaginas = totalPaginas;
        this.progressDialog = new ProgressDialog(context);
        this.progressDialog.setCancelable(false);
        this.progressDialog.setIndeterminate(false);
        this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        this.progressDialog.setMessage("Sincronizando produtos...");
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            int pagina = 1;
            ProdutoPaginacaoDTO produtoPaginacaoDTO = produtoRepository.listarProdutos(pagina, totalPaginas);
            int count = 0;
            tot_registro = produtoPaginacaoDTO.getTotal_registro();
            this.progressDialog.setMax(tot_registro);
            float total = tot_registro >= totalPaginas? (tot_registro/totalPaginas): 1;

            while (pagina <= total+1) {
                List<ProdutoDTO> produtos = produtoPaginacaoDTO.getProdutos();

                // Processa os produtos recebidos
                for (ProdutoDTO dto: produtos) {
                    new ProdutoDAO(context).salvar(new Produto(dto));
                    publishProgress(count);
                    count++;
                }

                pagina++;
                if(pagina <= total+1) {
                    produtoPaginacaoDTO = produtoRepository.listarProdutos(pagina, totalPaginas);
                }else{
                    produtoPaginacaoDTO.setProdutos(new ArrayList<ProdutoDTO>());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            progressDialog.dismiss();
            cancel(true);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressDialog.setProgress(values[0]);
        progressDialog.setMessage("Sincronizando produtos... (" + values[0] + "/" + tot_registro + ")");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
    }
}

