package app.devmedia.com.br.appdevmedia;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
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
import android.widget.Toast;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import app.devmedia.com.br.appdevmedia.adapter.ViewPagerAdapter;
import app.devmedia.com.br.appdevmedia.async.AsyncUsuario;
import app.devmedia.com.br.appdevmedia.fragment.FragmentCompras;
import app.devmedia.com.br.appdevmedia.fragment.FragmentPerfil;
import app.devmedia.com.br.appdevmedia.fragment.FragmentProdutos;

public class MainActivity extends AppCompatActivity {

    protected Toolbar toolbar;
    protected TabLayout tabLayout;
    protected ViewPager viewPager;
    protected Drawer drawer;

    protected final static long ID_ND_FOOTER = 500;

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

        AccountHeader drawerHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName("Erick Rocha Amorim").withEmail("erochaamorim@gmail.com").withIcon(getResources().getDrawable(R.drawable.e))
                )
                .build();

        final PrimaryDrawerItem itemPerfil = new PrimaryDrawerItem()
                .withIdentifier(2)
                .withName(R.string.nav_perfi)
                .withIcon(FontAwesome.Icon.faw_user);
        final PrimaryDrawerItem itemProdutos = new PrimaryDrawerItem()
                .withIdentifier(1)
                .withName(R.string.nav_produtos)
                .withIcon(FontAwesome.Icon.faw_shopping_cart)
                .withBadge("43")
                .withBadgeStyle(new BadgeStyle()
                        .withTextColor(Color.WHITE)
                        .withColorRes(R.color.md_orange_700));
        final PrimaryDrawerItem itemCompras = new PrimaryDrawerItem()
                .withName("Últimas Compras")
                .withIcon(FontAwesome.Icon.faw_cart_arrow_down)
                .withBadge("2")
                .withBadgeStyle(new BadgeStyle()
                        .withTextColor(Color.WHITE)
                        .withColorRes(R.color.md_orange_700));

        drawer = new DrawerBuilder()
                .withAccountHeader(drawerHeader)
                .withActivity(MainActivity.this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        new SectionDrawerItem().withName("Conta do Usuário"),
                        itemPerfil,
                        new SectionDrawerItem().withName("Ações do Sistema"),
                        itemProdutos,
                        itemCompras
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        configurarItensDrawer(position, drawerItem );
                        return true;
                    }
                })
                .build();
        drawer.addStickyFooterItem(new PrimaryDrawerItem()
                .withName("Sobre o App")
                .withIdentifier(ID_ND_FOOTER));

    }

    protected void configurarItensDrawer(int position, IDrawerItem drawerItem) {

        switch((int) drawerItem.getIdentifier()) {

            case 1:

                viewPager.setCurrentItem(0);
                break;

            case 2:

                viewPager.setCurrentItem(1);
                break;

            case (int) ID_ND_FOOTER:

                String versao = "Indisponível";
                try {
                    PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
                    versao = info.versionName;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, "Versão: " + versao, Toast.LENGTH_SHORT).show();
                break;

        }
        drawer.closeDrawer();
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
        viewPagerAdapter.addFragment(new FragmentCompras(), "Compras");
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
