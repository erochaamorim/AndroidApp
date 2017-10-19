package app.devmedia.com.br.appdevmedia.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;

import app.devmedia.com.br.appdevmedia.R;
import app.devmedia.com.br.appdevmedia.util.Constantes;
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
        CardHeader header = new CardHeader(getContext());
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
}
