package app.devmedia.com.br.appdevmedia.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import app.devmedia.com.br.appdevmedia.R;
import app.devmedia.com.br.appdevmedia.entidades.Produto;
import app.devmedia.com.br.appdevmedia.util.Constantes;

/**
 * Created by erick.amorim on 16/10/2017.
 */

public class ProdutoRecyclerAdapter extends RecyclerView.Adapter<ProdutoRecyclerAdapter.ViewHolder> {

    protected final List<Produto> produtos;

    public ProdutoRecyclerAdapter(final List<Produto> produtos) {

        this.produtos = produtos;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduto;
        TextView txtTitulo, txtDescricao, txtPreco;
        Button btnDetalhes;


        public ViewHolder(final View itemView) {
            super(itemView);
            imgProduto = (ImageView) itemView.findViewById(R.id.imgProduto);
            txtTitulo = (TextView) itemView.findViewById(R.id.txtTitulo);
            txtDescricao = (TextView) itemView.findViewById(R.id.txtDescricao);
            txtPreco = (TextView) itemView.findViewById(R.id.txtPreco);
            btnDetalhes = (Button) itemView.findViewById(R.id.btnDetalhes);
            btnDetalhes.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Card clicado.", Toast.LENGTH_SHORT).show();
                }

            });
        }
    }

    @Override
    public ProdutoRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.linha_produto, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ProdutoRecyclerAdapter.ViewHolder produtoViewHolder, int position) {
        Produto produto = this.produtos.get(position);
        produtoViewHolder.txtTitulo.setText(produto.getTitulo() );
        produtoViewHolder.txtDescricao.setText(produto.getDescricao() );
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        produtoViewHolder.txtPreco.setText(format.format(produto.getValor() ) );
        ImageView imgProduto = produtoViewHolder.imgProduto;
        Picasso.with(imgProduto.getContext() ).load(Constantes.URL_WEB_BASE + produto.getUrlImg()).into(imgProduto);
    }

    @Override
    public int getItemCount() {
        return this.produtos.size();
    }
}
