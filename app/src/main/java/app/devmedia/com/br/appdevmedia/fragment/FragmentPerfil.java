package app.devmedia.com.br.appdevmedia.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import app.devmedia.com.br.appdevmedia.R;

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

}
