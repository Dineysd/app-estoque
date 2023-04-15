package com.projeto.estoque.presenter;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Patterns;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void login(String email, String password) {
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.showEmailError("E-mail inválido");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            view.showPasswordError("Senha inválida");
            return;
        }


        // Aqui, você pode adicionar a lógica para autenticar o usuário

        view.showLoginSuccessMessage("Login realizado com sucesso");


    }

}

