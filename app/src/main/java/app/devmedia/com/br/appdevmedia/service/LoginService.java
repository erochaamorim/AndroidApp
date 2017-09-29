package app.devmedia.com.br.appdevmedia.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import app.devmedia.com.br.appdevmedia.MainActivity;
import app.devmedia.com.br.appdevmedia.async.AsyncUsuarioHttpClient;
import app.devmedia.com.br.appdevmedia.util.Util;
import app.devmedia.com.br.appdevmedia.validation.LoginValidation;

/**
 * Created by erick.amorim on 01/08/2017.
 */

public class LoginService {

    public void validarCamposLogin(LoginValidation validation) {

        boolean camposValidos = true;
        final String login = validation.getLogin();
        final String senha = validation.getSenha();
        EditText edtLogin = validation.getEdtLogin();
        EditText edtSenha = validation.getEdtSenha();
        final Activity activity = validation.getActivity();

        if(login == null || "".equals(login) ) {

            edtLogin.setError("Campo obrigatório.");
            camposValidos = false;

        }
        if(senha == null || "".equals(senha) ) {

            edtSenha.setError("Campo obrigatório.");
            camposValidos = false;

        }
        if(camposValidos) {

            RequestParams params = new RequestParams();
            params.put("login", login);
            params.put("senha", senha);
            AsyncUsuarioHttpClient.post("user/login", params, new TextHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {

                    if(Boolean.valueOf(responseString) ) {

                        SharedPreferences.Editor editor = activity.getSharedPreferences("pref", Context.MODE_PRIVATE).edit();
                        editor.putString("login", login );
                        editor.putString("senha", senha );
                        editor.commit();
                        Intent i = new Intent(activity, MainActivity.class);
                        activity.startActivity(i);
                        activity.finish();

                    } else {

                        Util.showMsgToast(activity, "Login/Senha inválidos.");

                    }

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable error) {

                    Log.e(LoginService.class.getName(), "Erro no login do usuário. Status code: " + String.valueOf(statusCode) + ".", error);
                    Util.showMsgToast(activity, "Erro na autenticação." );

                }

            });

        }

    }

    public void deslogar() {

    }

}
