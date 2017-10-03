package app.devmedia.com.br.appdevmedia.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import app.devmedia.com.br.appdevmedia.R;
import app.devmedia.com.br.appdevmedia.entidades.Profissao;

/**
 * Created by erick.amorim on 03/10/2017.
 */

public class ProfissaoArrayAdapter extends ArrayAdapter<Profissao> {

    protected List<Profissao> profissoes;
    protected Context context;

    public ProfissaoArrayAdapter(@NonNull Context context, @LayoutRes int resource, List<Profissao> profissoes) {
        super(context, resource);
        this.profissoes = profissoes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return profissoes.size();
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getViewAux(position, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return getViewAux(position, parent);
    }

    @NonNull
    private View getViewAux(int position, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View linha = layoutInflater.inflate(R.layout.linha_profissao, parent, false);

        TextView txtProfissao = (TextView) linha.findViewById(R.id.txtProfissao);
        txtProfissao.setText(profissoes.get(position).getDescricao() );

        return linha;
    }


    @Nullable
    @Override
    public Profissao getItem(int position) {
        return profissoes.get(position);
    }
}
