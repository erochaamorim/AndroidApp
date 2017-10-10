package app.devmedia.com.br.appdevmedia.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.squareup.picasso.Picasso;

import app.devmedia.com.br.appdevmedia.R;

/**
 * Created by erick.amorim on 25/09/2017.
 */

public class FragmentProdutos extends Fragment {

    protected ImageView imgProdutos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_produtos, container, false);
        return view;

    }
}
