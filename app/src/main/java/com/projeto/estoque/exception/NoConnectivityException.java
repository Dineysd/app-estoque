package com.projeto.estoque.exception;

import java.io.IOException;

public class NoConnectivityException extends IOException {

    public NoConnectivityException() {
        super("Não há conexão de internet disponível. Verifique sua conexão e tente novamente.");
    }

    public NoConnectivityException(Throwable cause) {
        super("Não há conexão de internet disponível. Verifique sua conexão e tente novamente.", cause);
    }

}

