package app.devmedia.com.br.appdevmedia.validation;

import android.app.Activity;
import android.widget.EditText;

/**
 * Created by erick.amorim on 01/08/2017.
 */

public class LoginValidation {

    protected String login;
    protected String senha;
    protected EditText edtLogin, edtSenha;
    protected Activity activity;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public EditText getEdtLogin() {
        return edtLogin;
    }

    public void setEdtLogin(EditText edtLogin) {
        this.edtLogin = edtLogin;
    }

    public EditText getEdtSenha() {
        return edtSenha;
    }

    public void setEdtSenha(EditText edtSenha) {
        this.edtSenha = edtSenha;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
