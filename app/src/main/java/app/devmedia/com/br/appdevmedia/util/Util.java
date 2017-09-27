package app.devmedia.com.br.appdevmedia.util;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import app.devmedia.com.br.appdevmedia.R;

/**
 * Created by era on 26/07/2017.
 */

public class Util {

    public static void showMsgToast(Activity activity, String txt) {

        LayoutInflater inflater = activity.getLayoutInflater();
        View toastBemVindo = inflater.inflate(R.layout.toast_template, (ViewGroup) activity.findViewById(R.id.layoutToastBemVindo));

        TextView txtToastBemVindo = (TextView) toastBemVindo.findViewById(R.id.txtToastBemVindo);
        txtToastBemVindo.setText(txt);

        Toast toast = new Toast(activity);
        toast.setView(toastBemVindo);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();

    }

    public static void showMsgAlertOK(final Activity activity, String titulo, String txt, TipoMsg tipoMsg) {

        AlertDialog alertDialog = null;

        switch(tipoMsg) {

            case SUCESSO:
                alertDialog = new AlertDialog.Builder(activity, R.style.AppTheme_Dark_Dialog_Sucesso).create();
                alertDialog.setIcon(R.mipmap.sucesso);
                break;

            case ALERTA:
                alertDialog = new AlertDialog.Builder(activity, R.style.AppTheme_Dark_Dialog_Alerta).create();
                alertDialog.setIcon(R.mipmap.alerta);
                break;

            case ERRO:
                alertDialog = new AlertDialog.Builder(activity, R.style.AppTheme_Dark_Dialog_Erro).create();
                alertDialog.setIcon(R.mipmap.erro);
                break;

            case INFO:
            default:
                alertDialog = new AlertDialog.Builder(activity, R.style.AppTheme_Dark_Dialog_Info).create();
                alertDialog.setIcon(R.mipmap.info);

        }

        alertDialog.setTitle(titulo);
        alertDialog.setMessage(txt);

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Util.showMsgToast(activity, "Seja bem vindo à nossa loja App v2.0!");
                dialog.dismiss();

            }
        });

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.copyFrom(alertDialog.getWindow().getAttributes());
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        alertDialog.show();
        alertDialog.getWindow().setAttributes(params);

    }

    public static void showMsgConfirm(final Activity activity, String titulo, String txt, TipoMsg tipoMsg, DialogInterface.OnClickListener listener) {

        AlertDialog alertDialog = null;

        switch(tipoMsg) {

            case SUCESSO:
                alertDialog = new AlertDialog.Builder(activity, R.style.AppTheme_Dark_Dialog_Sucesso).create();
                alertDialog.setIcon(R.mipmap.sucesso);
                break;

            case ALERTA:
                alertDialog = new AlertDialog.Builder(activity, R.style.AppTheme_Dark_Dialog_Alerta).create();
                alertDialog.setIcon(R.mipmap.alerta);
                break;

            case ERRO:
                alertDialog = new AlertDialog.Builder(activity, R.style.AppTheme_Dark_Dialog_Erro).create();
                alertDialog.setIcon(R.mipmap.erro);
                break;

            case INFO:
            default:
                alertDialog = new AlertDialog.Builder(activity, R.style.AppTheme_Dark_Dialog_Info).create();
                alertDialog.setIcon(R.mipmap.info);

        }

        alertDialog.setTitle(titulo);
        alertDialog.setMessage(txt);

        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Util.showMsgToast(activity, "Seja bem vindo à nossa loja App v2.0!");
                dialog.dismiss();

            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", listener);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.copyFrom(alertDialog.getWindow().getAttributes());
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        alertDialog.show();
        alertDialog.getWindow().setAttributes(params);

    }
}
