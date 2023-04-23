package com.projeto.estoque.integration.config;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.projeto.estoque.exception.NoConnectivityException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private Retrofit retrofit;
    private Context context;

    public RetrofitConfig(Context context) {
        this.context = context;

        // Configuração padrão do Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api-python-production-c18f.up.railway.app")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();
    }

    // Método para retornar a instância do OkHttpClient
    private OkHttpClient getOkHttpClient() {
        // Configuração do HttpLoggingInterceptor para logs das requisições
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS) // tempo limite de conexão
                .readTimeout(30, TimeUnit.SECONDS) // tempo limite de leitura
                .writeTimeout(30, TimeUnit.SECONDS) // tempo limite de escrita
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        if (!isNetworkAvailable()) {
                            throw new NoConnectivityException();
                        }

                        return chain.proceed(chain.request());
                    }
                })
                .build();
    }

    // Método para verificar se há conexão com a internet
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        }

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    // Método para criar instância do repositório com a interface desejada
    public <T> T createRepository(Class<T> repositoryClass) {
        return retrofit.create(repositoryClass);
    }
}

