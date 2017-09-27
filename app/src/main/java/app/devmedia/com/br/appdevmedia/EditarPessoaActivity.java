package app.devmedia.com.br.appdevmedia;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import app.devmedia.com.br.appdevmedia.entidades.Pessoa;
import app.devmedia.com.br.appdevmedia.entidades.Profissao;
import app.devmedia.com.br.appdevmedia.entidades.Sexo;
import app.devmedia.com.br.appdevmedia.entidades.TipoPessoa;
import app.devmedia.com.br.appdevmedia.fragment.DatePickerFragment;
import app.devmedia.com.br.appdevmedia.util.Mask;
import app.devmedia.com.br.appdevmedia.util.Util;

public class EditarPessoaActivity extends AppCompatActivity {

    protected Pessoa pessoa;
    protected Spinner spnProfissao;
    protected TextView txtCpfCnpj;
    protected EditText edtNome, edtEndereco, edtCpfCnpj, edtDataNasc;
    protected RadioGroup rbgCpfCnpj, rbgSexo;
    protected RadioButton rbtCpf, rbtCnpj, rbtFem, rbtMasc;
    protected TextWatcher cpfMask, cnpjMask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_pessoa);

        getSupportActionBar().setTitle("Editar Pessoa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        pessoa = (Pessoa) getIntent().getExtras().getSerializable("pessoa");
        spnProfissao = (Spinner) findViewById(R.id.spnProfissao);
        txtCpfCnpj = (TextView) findViewById(R.id.txtCpfCnpj);
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtEndereco = (EditText) findViewById(R.id.edtEndereco);
        edtCpfCnpj = (EditText) findViewById(R.id.edtCpfCnpj);
        edtDataNasc = (EditText) findViewById(R.id.edtDataNasc);
        rbgCpfCnpj = (RadioGroup) findViewById(R.id.rbgCpfCnpj);
        rbgSexo = (RadioGroup) findViewById(R.id.rbgSexo);
        rbtCpf = (RadioButton) findViewById(R.id.rbtCpf);
        rbtCnpj = (RadioButton) findViewById(R.id.rbtCnpj);
        rbtFem = (RadioButton) findViewById(R.id.rbtFeminino);
        rbtMasc = (RadioButton) findViewById(R.id.rbtMasculino);
        cpfMask = Mask.insert("###.###.###-##", edtCpfCnpj);
        edtCpfCnpj.addTextChangedListener(cpfMask);
        cnpjMask = Mask.insert("##.###.###/####-##", edtCpfCnpj);
        rbgCpfCnpj.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                edtCpfCnpj.setText("");
                edtCpfCnpj.requestFocus();

                if(rbgCpfCnpj.getCheckedRadioButtonId() == rbtCpf.getId() ) {

                    txtCpfCnpj.setText("CPF");
                    edtCpfCnpj.removeTextChangedListener(cnpjMask);
                    edtCpfCnpj.addTextChangedListener(cpfMask);

                } else {

                    txtCpfCnpj.setText("CNPJ");
                    edtCpfCnpj.removeTextChangedListener(cpfMask);
                    edtCpfCnpj.addTextChangedListener(cnpjMask);

                }

            }

        });
        edtCpfCnpj.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(rbgCpfCnpj.getCheckedRadioButtonId() == rbtCpf.getId() ) {

                    if(edtCpfCnpj.getText().length() < 14) {

                        edtCpfCnpj.setText("");

                    }

                } else {

                    if(edtCpfCnpj.getText().length() < 18) {

                        edtCpfCnpj.setText("");

                    }

                }

            }

        });
        this.initProfissoes();
        this.initCampos();

    }

    protected void initCampos() {

        edtNome.setText(pessoa.getNome() );
        edtEndereco.setText(pessoa.getEndereco() );
        edtCpfCnpj.setText(pessoa.getCpfCnpj() );
        switch(pessoa.getTipoPessoa() ) {

            case FISICA:

                txtCpfCnpj.setText("CPF:");
                rbtCpf.setChecked(true);
                break;

            case JURIDICA:

                txtCpfCnpj.setText("CNPJ:");
                rbtCnpj.setChecked(true);
                break;

        }
        switch(pessoa.getSexo() ) {

            case FEMININO:

                rbtFem.setChecked(true);
                break;

            case MASCULINO:

                rbtMasc.setChecked(true);
                break;

        }
        spnProfissao.setSelection(pessoa.getProfissao().ordinal() );
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        edtDataNasc.setText(dateFormat.format(pessoa.getDtNasc() ) );

    }

    protected void initProfissoes() {

        Profissao[] profissoes = Profissao.values();
        ArrayAdapter adapter = new ArrayAdapter<Profissao>(EditarPessoaActivity.this, android.R.layout.simple_spinner_dropdown_item, profissoes);
        spnProfissao.setAdapter(adapter );

    }

    public void setData(View view) {

        DatePickerFragment datePickerFragment = new DatePickerFragment();
        Bundle bundle = new Bundle();
        Calendar cal = Calendar.getInstance();
        bundle.putInt("ano", cal.get(Calendar.YEAR) );
        bundle.putInt("mes", cal.get(Calendar.MONTH) );
        bundle.putInt("dia", cal.get(Calendar.DAY_OF_MONTH) );
        datePickerFragment.setArguments(bundle);
        datePickerFragment.setDateListener(dateListener);
        datePickerFragment.show(getFragmentManager(), "Data Nasc.");

    }

    protected DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {


        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            edtDataNasc.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

        }

    };

    public void atualizarPessoa(View v) {

        montarPessoa();
        if(!validarPessoa(pessoa) ) {

            Util.showMsgToast(this, "Atualização efetuada com sucesso.");
            Intent i = new Intent(this, ListaPessoaActivity.class);
            startActivity(i);
            finish();

        }

    }

    protected void montarPessoa() {

        pessoa.setNome(edtNome.getText().toString() );
        pessoa.setEndereco(edtEndereco.getText().toString() );
        pessoa.setCpfCnpj(edtCpfCnpj.getText().toString() );
        switch(rbgCpfCnpj.getCheckedRadioButtonId() ) {

            case R.id.rbtCpf:

                pessoa.setTipoPessoa(TipoPessoa.FISICA);
                break;

            case R.id.rbtCnpj:

                pessoa.setTipoPessoa(TipoPessoa.JURIDICA);
                break;

        }
        switch(rbgSexo.getCheckedRadioButtonId() ) {

            case R.id.rbtFeminino:

                pessoa.setSexo(Sexo.FEMININO);
                break;

            case R.id.rbtMasculino:

                pessoa.setSexo(Sexo.MASCULINO);
                break;

        }
        pessoa.setProfissao((Profissao) spnProfissao.getSelectedItem() );
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {

            pessoa.setDtNasc(dateFormat.parse(edtDataNasc.getText().toString() ) );

        } catch (ParseException e) {

            e.printStackTrace();

        }

    }

    protected boolean validarPessoa(Pessoa pessoa) {

        boolean erro = false;
        if(pessoa.getNome() == null || "".equals(pessoa.getNome() ) ) {

            erro = true;
            edtNome.setError("Campo \"Nome\" obrigatório!");

        }
        if(pessoa.getEndereco() == null || "".equals(pessoa.getEndereco() ) ) {

            erro = true;
            edtEndereco.setError("Campo \"Endereço\" obrigatório!");

        }
        if(pessoa.getCpfCnpj() == null || "".equals(pessoa.getCpfCnpj() ) ) {

            erro = true;
            switch(rbgCpfCnpj.getCheckedRadioButtonId() ) {

                case R.id.rbtCpf:

                    edtCpfCnpj.setError("Campo \"CPF\" obrigatório!");
                    break;

                case R.id.rbtCnpj:

                    edtCpfCnpj.setError("Campo \"CNPJ\" obrigatório!");
                    break;

            }


        } else {

            switch(rbgCpfCnpj.getCheckedRadioButtonId() ) {

                case R.id.rbtCpf:

                    if(edtCpfCnpj.getText().toString().length() < 14) {

                        erro = true;
                        edtCpfCnpj.setError("Campo \"CPF\" deve ter 11 caracteres!");

                    }
                    break;

                case R.id.rbtCnpj:

                    if(edtCpfCnpj.getText().toString().length() < 14) {

                        erro = true;
                        edtCpfCnpj.setError("Campo \"CNPJ\" deve ter 14 caracteres!");

                    }
                    break;


            }

        }
        if(pessoa.getDtNasc() == null ) {

            erro = true;
            edtDataNasc.setError("Campo \"Data Nasc.\" obrigatório!");

        } else {

            edtDataNasc.setError(null);

        }
        return erro;

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

}
