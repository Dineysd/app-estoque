package com.projeto.estoque.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.projeto.estoque.R;

public class ToastUtils {

    public static void showErrorToast(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        View view = toast.getView();
        view.setBackgroundColor(ContextCompat.getColor(context, R.color.error));
        TextView text = view.findViewById(android.R.id.message);
        text.setTextColor(Color.WHITE);
        text.setTextSize(18);

        text.setTypeface(null, Typeface.BOLD);
        view.setPadding(16, 8, 16, 8);
        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(26);
        shape.setColor(Color.parseColor("#CCFF0000"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(shape);
        }
        toast.show();
    }

    public static void showSuccessToast(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        View view = toast.getView();
        view.setBackgroundColor(ContextCompat.getColor(context, R.color.success));
        TextView text = view.findViewById(android.R.id.message);
        text.setTextColor(Color.WHITE);
        text.setTextSize(18);

        text.setTypeface(null, Typeface.BOLD);
        view.setPadding(16, 8, 16, 8);
        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(26);
        shape.setColor(Color.parseColor("#CC00FF00"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(shape);
        }
        toast.show();
    }
}
