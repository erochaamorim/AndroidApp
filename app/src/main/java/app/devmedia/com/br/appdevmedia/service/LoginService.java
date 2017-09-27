package app.devmedia.com.br.appdevmedia.service;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;

import app.devmedia.com.br.appdevmedia.async.AsyncUsuario;
import app.devmedia.com.br.appdevmedia.util.Constantes;
import app.devmedia.com.br.appdevmedia.util.Util;
import app.devmedia.com.br.appdevmedia.validation.LoginValidation;

/**
 * Created by erick.amorim on 01/08/2017.
 */

public class LoginService {

    public void validarCamposLogin(LoginValidation validation) {

        boolean camposValidos = true;
        String login = validation.getLogin();
        String senha = validation.getSenha();
        EditText edtLogin = validation.getEdtLogin();
        EditText edtSenha = validation.getEdtSenha();
        Activity activity = validation.getActivity();

        if(login == null || "".equals(login) ) {

            edtLogin.setError("Campo obrigatório.");
            camposValidos = false;

        }
        if(senha == null || "".equals(senha) ) {

            edtSenha.setError("Campo obrigatório.");
            camposValidos = false;

        }
        if(camposValidos) {

            new AsyncUsuario(validation).execute(Constantes.URL_WS_LOGIN);

        }

    }

    public void deslogar() {

    }

}
