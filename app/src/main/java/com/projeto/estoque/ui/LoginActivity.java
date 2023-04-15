package com.projeto.estoque.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.projeto.estoque.R;
import com.projeto.estoque.database.HelperDb;
import com.projeto.estoque.presenter.LoginContract;
import com.projeto.estoque.presenter.LoginPresenter;
import com.projeto.estoque.utils.ToastUtils;
import com.projeto.estoque.utils.UtilsPermissao;

import static com.projeto.estoque.database.TabelasSql.NAME_DATABASE;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{

    private LoginContract.Presenter presenter;

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(this);

        if (!UtilsPermissao.verificarPermissoes(this)) {
            UtilsPermissao.solicitarPermissoes(this);
        } else {
            // permissões já concedidas
            HelperDb.createDatabase(this);
        }

        // Inicializar os componentes
        emailEditText = findViewById(R.id.email_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = getEmail();
                String password = getPassword();
                presenter.login(email, password);
            }
        });
    }

    @Override
    public String getEmail() {
        return emailEditText.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return passwordEditText.getText().toString().trim();
    }

    @Override
    public void showEmailError(String errorMessage) {
        ToastUtils.showErrorToast(this, errorMessage);

    }

    @Override
    public void showPasswordError(String errorMessage) {
        ToastUtils.showErrorToast(this, errorMessage);

    }

    @Override
    public void showLoginSuccessMessage(String login_realizado_com_sucesso) {
        ToastUtils.showSuccessToast(this, login_realizado_com_sucesso);

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == UtilsPermissao.REQUEST_CODE_PERMISSOES) {
            boolean todasPermissoesConcedidas = true;

            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    todasPermissoesConcedidas = false;
                    break;
                }
            }

            if (!todasPermissoesConcedidas) {
                ToastUtils.showErrorToast(this, "Permissões não concedidas.");
            } else {
                // permissões concedidas
                HelperDb.createDatabase(this);
            }
        }
    }
}
