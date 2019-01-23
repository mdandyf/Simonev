package simonev.mitrais.com.simonev.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import simonev.mitrais.com.simonev.R;
import simonev.mitrais.com.simonev.R2;
import simonev.mitrais.com.simonev.view.fragment.DetailFragment;
import simonev.mitrais.com.simonev.view.fragment.MainFragment;

public class MainActivity extends FragmentActivity implements
        MainFragment.OnFragmentInteractionListener, DetailFragment.OnFragmentInteractionListener {

    @BindView(R2.id.toolbar) Toolbar toolbar;
    @BindView(R2.id.drawer_layout) DrawerLayout drawer;
    @BindView(R2.id.nav_view) NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R2.layout.activity_main);
        /*((AppCompatActivity)getApplicationContext()).setSupportActionBar(toolbar);*/
        ButterKnife.bind(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(item -> {
            setNewFragment(item);
            return true;
        });
        navigationView.setCheckedItem(R.id.nav_main);

        toolbar.inflateMenu(R2.menu.main);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.action_settings:
                        intent = new Intent(getApplicationContext(), SettingsActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.action_logout:
                        intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreatePanelMenu(featureId, menu);
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
    public void onFragmentInteraction(String name) {

    }

    private void setNewFragment(MenuItem menu) {
        int id = menu.getItemId();

        Fragment fragment = null;
        FragmentTransaction fragmentTransaction = null;
        try {
            if (id == R.id.nav_main) {
                // Handle the main
                fragmentChange(MainFragment.class, R.id.flContent);
            } else if (id == R.id.nav_detail) {
                // Handle the fragment detail
                fragmentChange(DetailFragment.class, R.id.flContent);
            } else if (id == R.id.nav_detail02) {
                // Handle the fragment detail 02
                fragmentChange(MainFragment.class, R.id.flContent);
            } else if (id == R.id.nav_detail03) {
                // Handle the fragment detail 03
                fragmentChange(DetailFragment.class, R.id.flContent);
            } else if (id == R.id.nav_share) {
                // Handle the fragment share
                fragmentChange(MainFragment.class, R.id.flContent);
            } else if (id == R.id.nav_send) {
                // Handle the fragment send
                fragmentChange(DetailFragment.class, R.id.flContent);
            }
        } catch (Exception e) {
            throw new RuntimeException("Exception in fragment transaction " + e);
        }

        menu.setChecked(true);
        drawer.closeDrawer(GravityCompat.START);
    }

    private void fragmentChange(Class fragmentClass, int container) throws InstantiationException, IllegalAccessException {
        // Create new fragment and transaction
        Fragment fragment = (Fragment) fragmentClass.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(container, fragment);
        //transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }
}
