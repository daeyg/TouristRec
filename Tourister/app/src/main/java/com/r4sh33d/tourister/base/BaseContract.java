package com.r4sh33d.tourister.base;

import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;

public interface BaseContract {

    interface view {

        void dismissProgressDialogLoading();

        AlertDialog showError(CharSequence message);

        AlertDialog showSuccessDialog(String message, DialogInterface.OnClickListener
                positiveClickListener);

        void showProgressDialogLoading(String message);

        void showToast(String message);

        void hideKeyboard();

        void showLoading(String message);

        void dismissLoading();

        void showLoading();

        void showLoading(@StringRes int resId);

        void showToolbar();

        void hideToolbar();
    }
}
