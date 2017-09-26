package app.devmedia.com.br.appdevmedia.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erick.amorim on 26/09/2017.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    protected List<Fragment> fragments = new ArrayList<>();
    protected List<String> titulos = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {

        super(fm);

    }

    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);

    }

    @Override
    public int getCount() {

        return fragments.size();

    }

    public void addFragment(Fragment fragment, String titulo) {

        fragments.add(fragment);
        titulos.add(titulo);

    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titulos.get(position);

    }

}
