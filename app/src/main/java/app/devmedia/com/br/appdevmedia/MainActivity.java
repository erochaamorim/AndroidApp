package app.devmedia.com.br.appdevmedia;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import app.devmedia.com.br.appdevmedia.adapter.ViewPagerAdapter;
import app.devmedia.com.br.appdevmedia.async.AsyncUsuario;
import app.devmedia.com.br.appdevmedia.fragment.FragmentPerfil;
import app.devmedia.com.br.appdevmedia.fragment.FragmentProdutos;

public class MainActivity extends AppCompatActivity {

    protected Toolbar toolbar;
    protected TabLayout tabLayout;
    protected ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        configurarViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    protected void configurarViewPager(ViewPager viewPager) {

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager() );
        viewPagerAdapter.addFragment(new FragmentProdutos(), "Produtos" );
        viewPagerAdapter.addFragment(new FragmentPerfil(), "Perfil" );
        viewPager.setAdapter(viewPagerAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
