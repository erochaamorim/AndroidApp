package app.devmedia.com.br.appdevmedia;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import app.devmedia.com.br.appdevmedia.entidades.Pessoa;
import app.devmedia.com.br.appdevmedia.util.TipoMsg;
import app.devmedia.com.br.appdevmedia.util.Util;

public class ListaPessoaActivity extends AppCompatActivity {

    protected ListView lstPessoas;
    protected List<Pessoa> listaPessoas;
    protected ArrayAdapter<String> adapter;

    public int getPosicaoSelecionada() {

        return posicaoSelecionada;

    }

    protected int posicaoSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pessoa);
        getSupportActionBar().setTitle("Lista de Pessoas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        lstPessoas = (ListView) findViewById(R.id.lstPessoas);
        Context c = getApplicationContext();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        setArrayAdapterPessoas();
        lstPessoas.setOnItemClickListener(clickListenerPessoas);
        lstPessoas.setOnCreateContextMenuListener(contextMenuListener);
        lstPessoas.setOnItemLongClickListener(longClickListenerPessoas);

    }

    protected void setArrayAdapterPessoas() {

        List<String> valores = new ArrayList<String>();
        for (Pessoa p: listaPessoas) {

            valores.add(p.getNome() );

        }
        adapter.clear();
        adapter.addAll(valores);
        lstPessoas.setAdapter(adapter);

    }

    protected AdapterView.OnItemClickListener clickListenerPessoas = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Pessoa pessoa = new Pessoa();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            StringBuilder info = new StringBuilder();
            info.append("Nome: " + pessoa.getNome() );
            info.append("\nEndereço: " + pessoa.getEndereco() );
            info.append("\nCPF/CNPJ: " + pessoa.getCpfCnpj() );
            info.append("\nSexo: " + pessoa.getSexo().getDescricao() );
            info.append("\nProfissão: " + pessoa.getProfissao().getDescricao() );
            info.append("\nDt.Nasc: " + dateFormat.format(pessoa.getDtNasc() ) );
            Util.showMsgAlertOK(ListaPessoaActivity.this, pessoa.getNome(), info.toString(), TipoMsg.INFO );

        }

    };

    protected View.OnCreateContextMenuListener contextMenuListener = new View.OnCreateContextMenuListener() {

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            menu.setHeaderTitle("Opções");
            menu.add(1, 10, 1, "Editar");
            menu.add(1, 20, 2, "Deletar");

        }

    };

    protected AdapterView.OnItemLongClickListener longClickListenerPessoas = new AdapterView.OnItemLongClickListener() {

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            posicaoSelecionada = position;
            return false;

        }

    };

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch(item.getItemId() ) {

            case 10:

                Pessoa pessoa = new Pessoa();
                Intent i = new Intent(this, EditarPessoaActivity.class);
                i.putExtra("pessoa", pessoa);
                startActivity(i);
                finish();
                break;

            case 20:

                Util.showMsgConfirm(this,"Remover Pessoa", "Deseja realmente apagar o cadastro dessa pessoa?", TipoMsg.ALERTA, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        setArrayAdapterPessoas();
                        adapter.notifyDataSetChanged();
                        Util.showMsgToast(ListaPessoaActivity.this, "O cadastro da pessoa foi removido com sucesso.");

                    }

                });
                break;

        }
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {

            case android.R.id.home:
                finish();
                break;

        }
        return true;

    }

    public void addNewPessoa(View v) {

        Intent i = new Intent(this, PessoaActivity.class);
        startActivity(i);
        finish();

    }

}
