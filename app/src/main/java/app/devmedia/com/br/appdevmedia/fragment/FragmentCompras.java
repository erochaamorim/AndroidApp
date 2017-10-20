package app.devmedia.com.br.appdevmedia.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONArray;

import java.util.ArrayList;

import app.devmedia.com.br.appdevmedia.R;
import app.devmedia.com.br.appdevmedia.util.Constantes;
import it.gmariotti.cardslib.library.cards.actions.BaseSupplementalAction;
import it.gmariotti.cardslib.library.cards.actions.IconSupplementalAction;
import it.gmariotti.cardslib.library.cards.material.MaterialLargeImageCard;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardExpand;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.base.BaseCard;
import it.gmariotti.cardslib.library.view.CardView;
import it.gmariotti.cardslib.library.view.CardViewNative;

/**
 * Created by erick.amorim on 25/09/2017.
 */

public class FragmentCompras extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_compras, container, false);

        final RelativeLayout lytLoading = (RelativeLayout) view.findViewById(R.id.lytLoading);
        lytLoading.setVisibility(View.VISIBLE);

        Card card = new Card(getContext());
        CompraInnerHeader header = new CompraInnerHeader(getContext());
        header.setTitle("Card Demo");
        header.setPopupMenu(R.menu.menu_main, new CardHeader.OnClickCardHeaderPopupMenuListener(){
            @Override
            public void onMenuItemClick(BaseCard card, MenuItem item) {
                Toast.makeText(getActivity(), "Click on "+item.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        header.setPopupMenuPrepareListener(new CardHeader.OnPrepareCardHeaderPopupMenuListener() {
            @Override
            public boolean onPreparePopupMenu(BaseCard card, PopupMenu popupMenu) {
                popupMenu.getMenu().add("Refazer a compra");
                return true;
            }
        });
        card.addCardHeader(header);
        CardViewNative cardView = (CardViewNative) view.findViewById(R.id.carddemo);
        cardView.setCard(card);

        Card cardCollapse = new Card(getContext());
        CardHeader headerCollapse = new CardHeader(getContext());
        headerCollapse.setTitle("Card Collapse");
        headerCollapse.setOtherButtonVisible(true);
        headerCollapse.setOtherButtonDrawable(R.drawable.ic_notifications_none_black_24dp);
        headerCollapse.setOtherButtonClickListener(new CardHeader.OnClickCardHeaderOtherButtonListener() {
            @Override
            public void onButtonItemClick(Card card, View view) {
                Toast.makeText(getActivity(), "Click on Other Button", Toast.LENGTH_LONG).show();
            }
        });
        cardCollapse.addCardHeader(headerCollapse);
//        CardExpand expand = new CardExpand(getContext());
//        expand.setTitle("Collapse Expand");
//        cardCollapse.addCardExpand(expand);
        CardViewNative cardCollapseView = (CardViewNative) view.findViewById(R.id.cardCollapse);
        cardCollapseView.setCard(cardCollapse);

        ArrayList<BaseSupplementalAction> actions = new ArrayList<BaseSupplementalAction>();
        IconSupplementalAction t1 = new IconSupplementalAction(getActivity(), R.id.ic1);
        t1.setOnActionClickListener(new BaseSupplementalAction.OnActionClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(getActivity()," Click on Text SHARE ",Toast.LENGTH_SHORT).show();
            }
        });
        actions.add(t1);
        IconSupplementalAction t2 = new IconSupplementalAction(getActivity(), R.id.ic2);
        t2.setOnActionClickListener(new BaseSupplementalAction.OnActionClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(getActivity()," Click on Text LEARN ",Toast.LENGTH_SHORT).show();
            }
        });
        actions.add(t2);
        MaterialLargeImageCard materialCard =
                MaterialLargeImageCard.with(getActivity())
                        .setTextOverImage("Italian Beaches")
                        .useDrawableId(R.drawable.header)
                        .setupSupplementalActions(R.layout.carddemo_native_material_supplemental_actions_large_icon, actions )
                        .build();

        CardViewNative cardViewMaterial = (CardViewNative) view.findViewById(R.id.carddemo_largeimage);
        cardViewMaterial.setCard(materialCard);

        new AsyncHttpClient().get(Constantes.URL_WS_BASE+"produtos/index", new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                lytLoading.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast.makeText(getActivity(), "Falha: "+responseString, Toast.LENGTH_LONG).show();
            }
        });
        return view;

    }

    protected class CompraInnerHeader extends CardHeader {

        public CompraInnerHeader(Context context) {

            super(context, R.layout.linha_header_compra);

        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, View view) {

            ImageView imgProduto = (ImageView) view.findViewById(R.id.imgProduto);
            TextView txtTitulo = (TextView) view.findViewById(R.id.txtTitulo);
            TextView txtDescricao = (TextView) view.findViewById(R.id.txtDescricao);

            Picasso.with(imgProduto.getContext()).load(Constantes.URL_WEB_BASE + "img/produtos/mochila.jpg").into(imgProduto);
            txtTitulo.setText("Produto Teste");
            txtDescricao.setText("Descrição Teste");

        }

    }

}
