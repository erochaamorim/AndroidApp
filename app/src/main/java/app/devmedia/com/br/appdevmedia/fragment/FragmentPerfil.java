package app.devmedia.com.br.appdevmedia.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import app.devmedia.com.br.appdevmedia.R;
import app.devmedia.com.br.appdevmedia.entidades.User;
import app.devmedia.com.br.appdevmedia.util.Constantes;

/**
 * Created by erick.amorim on 25/09/2017.
 */

public class FragmentPerfil extends Fragment {

    protected Button btnCadastrar;
    protected EditText edtNome;
    protected TextInputLayout txtNome;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        btnCadastrar = (Button) view.findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!validarNome() ) {

                    return;

                }
                StringEntity stringEntity = null;
                final Gson gson = new Gson();
                try {

                    String json = gson.toJson(criarUser() );
                    stringEntity = new StringEntity(json );

                } catch (Exception e) {

                    e.printStackTrace();

                }
                new AsyncHttpClient().post(null, Constantes.URL_WS_BASE+"user/add", stringEntity, "application/json", new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                        Log.d("response", response.toString() );
                        User user = (User) gson.fromJson(response.toString(), User.class);
                        Log.d("user", user.toString() );

                    }

                });

            }

        });

        edtNome = (EditText) view.findViewById(R.id.edtNome);
        txtNome = (TextInputLayout) view.findViewById(R.id.txtNome);

        return view;

    }

    protected boolean validarNome() {

        if(edtNome.getText().toString().trim().isEmpty() ) {

            txtNome.setError("Campo nome obrigatório.");
            return false;

        } else {

            txtNome.setErrorEnabled(false);
            return true;

        }

    }

    private User criarUser() {

        User user = new User();
        user.setId(1);
        user.setNome("Fulano");
        user.setSexo('M');
        user.setCodProfissao(1);
        user.setEmail("fulano@de.tal");
        user.setMiniBio("Nasceu, viveu e morreu.");
        return user;

    }

}
