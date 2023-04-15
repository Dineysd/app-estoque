package com.projeto.estoque.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class UtilsPermissao {

    public static final int REQUEST_CODE_PERMISSOES = 100;

    private static final String[] PERMISSOES_PRINCIPAIS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    public static boolean verificarPermissoes(Context context) {
        boolean todasPermissoesConcedidas = true;

        for (String permissao : PERMISSOES_PRINCIPAIS) {
            if (ContextCompat.checkSelfPermission(context, permissao)
                    != PackageManager.PERMISSION_GRANTED) {
                todasPermissoesConcedidas = false;
                break;
            }
        }

        return todasPermissoesConcedidas;
    }

    public static void solicitarPermissoes(Activity activity) {
        ActivityCompat.requestPermissions(activity, PERMISSOES_PRINCIPAIS,
                REQUEST_CODE_PERMISSOES);
    }
}

