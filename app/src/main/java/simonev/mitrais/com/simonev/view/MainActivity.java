package simonev.mitrais.com.simonev.view;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import butterknife.BindInt;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import simonev.mitrais.com.simonev.R2;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R2.id.toolbar) Toolbar toolbar;
    @BindView(R2.id.fab) FloatingActionButton fab;
    @BindView(R2.id.drawer_layout) DrawerLayout drawer;
    @BindView(R2.id.nav_view) NavigationView navigationView;
    @BindView(R2.id.nav_dashboard) View menu01;
    @BindView(R2.id.nav_in_out) View menu02;
    @BindView(R2.id.nav_card_center) View menu03;
    @BindView(R2.id.nav_e_wallet_center) View menu04;
    @BindView(R2.id.action_settings) View menuSettings;
    @BindString(R2.string.navigation_drawer_open) String navOpen;
    @BindString(R2.string.navigation_drawer_close) String navClose;

    @OnClick(R2.id.fab) void onClickFab(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R2.layout.activity_main);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, Integer.valueOf(navOpen), Integer.valueOf(navClose));
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }



    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R2.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == menuSettings.getId()) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == menu01.getId()) {
            // Handle the camera action
        } else if (id == menu02.getId()) {

        } else if (id == menu03.getId()) {

        } else if (id == menu04.getId()) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
