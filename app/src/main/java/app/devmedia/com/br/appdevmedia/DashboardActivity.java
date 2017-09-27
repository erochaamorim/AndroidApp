package app.devmedia.com.br.appdevmedia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

    }

    public void callCadastro(View view) {

        Intent i = new Intent(this, PessoaActivity.class);
        startActivity(i);

    }

    public void callList(View view) {

        Intent i = new Intent(this, ListaPessoaActivity.class);
        startActivity(i);

    }

}
