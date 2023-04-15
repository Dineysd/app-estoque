package com.projeto.estoque.presenter;

public interface LoginContract {
    interface View {
        String getEmail();
        String getPassword();
        void showEmailError(String errorMessage);
        void showPasswordError(String errorMessage);
        void showLoginSuccessMessage(String login_realizado_com_sucesso);
    }

    interface Presenter {
        void login(String email, String password);
    }
}

