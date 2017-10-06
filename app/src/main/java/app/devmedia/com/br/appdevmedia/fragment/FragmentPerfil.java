package app.devmedia.com.br.appdevmedia.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import app.devmedia.com.br.appdevmedia.R;
import app.devmedia.com.br.appdevmedia.adapter.ProfissaoArrayAdapter;
import app.devmedia.com.br.appdevmedia.entidades.Profissao;
import app.devmedia.com.br.appdevmedia.entidades.User;
import app.devmedia.com.br.appdevmedia.util.Constantes;

/**
 * Created by erick.amorim on 25/09/2017.
 */

public class FragmentPerfil extends Fragment {

    protected Button btnCadastrar;
    protected EditText edtNome, edtEmail, edtMiniBio;
    protected RadioGroup rbgSexo;
    protected RadioButton rbtFem, rbtMasc;
    protected TextInputLayout txtNome;
    protected Spinner spnProfissao;
    protected List<Profissao> profissoes;
    protected RelativeLayout lytLoading;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        lytLoading = (RelativeLayout) view.findViewById(R.id.lytLoading);
        lytLoading.setVisibility(View.VISIBLE);

        final Gson gson = new Gson();

        btnCadastrar = (Button) view.findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!validarNome() ) {

                    return;

                }
                StringEntity stringEntity = null;
                try {

                    String json = gson.toJson(montarPessoa() );
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
        edtEmail = (EditText) view.findViewById(R.id.edtEmail);
        edtMiniBio = (EditText) view.findViewById(R.id.edtMiniBio);
        rbgSexo = (RadioGroup) view.findViewById(R.id.rbgSexo);
        rbtFem = (RadioButton) view.findViewById(R.id.rbtFeminino);
        rbtMasc = (RadioButton) view.findViewById(R.id.rbtMasculino);

        spnProfissao = (Spinner) view.findViewById(R.id.spnProfissao);
        new AsyncHttpClient().get(Constantes.URL_WS_BASE+"user/profissoes", new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                if(response != null) {

                    Type type = new TypeToken<List<Profissao>>() {}.getType();
                    profissoes = gson.fromJson(response.toString(), type);
                    spnProfissao.setAdapter(new ProfissaoArrayAdapter(getActivity(), R.layout.linha_profissao, profissoes) );

                } else {

                    Toast.makeText(getActivity(), "Houve um erro no carregamento da lista de profissões.", Toast.LENGTH_SHORT).show();

                }
                lytLoading.setVisibility(View.GONE);

            }

        });


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

    private User montarPessoa() {

        User user = new User();
        user.setNome(edtNome.getText().toString() );
        user.setEmail(edtNome.getText().toString() );
        user.setMiniBio(edtMiniBio.getText().toString() );
        user.setSexo(rbtFem.isChecked() ? 'F' : 'M');
        user.setProfissao((Profissao) spnProfissao.getSelectedItem());

        return user;

    }

}
