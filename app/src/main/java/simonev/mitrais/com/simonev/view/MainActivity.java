package simonev.mitrais.com.simonev.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.BindDrawable;
import butterknife.BindInt;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import simonev.mitrais.com.simonev.R;
import simonev.mitrais.com.simonev.R2;


public class MainActivity extends AppCompatActivity {

    @BindView(R2.id.toolbar) Toolbar toolbar;
    @BindView(R2.id.drawer_layout) DrawerLayout drawer;
    @BindView(R2.id.nav_view) NavigationView navigationView;

    @SuppressLint("ResourceType")
    @BindInt(R2.id.nav_main) int navCamera;
    @SuppressLint("ResourceType")
    @BindInt(R.id.nav_detail) int navGallery;
    @SuppressLint("ResourceType")
    @BindInt(R.id.nav_detail02) int navSlideShow;
    @SuppressLint("ResourceType")
    @BindInt(R.id.nav_detail03) int navManage;
    @SuppressLint("ResourceType")
    @BindInt(R.id.nav_share) int navShare;
    @SuppressLint("ResourceType")
    @BindInt(R.id.nav_send) int navSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R2.layout.activity_main);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == navCamera) {
                // Handle the camera action
            } else if (id == navGallery) {

            } else if (id == navSlideShow) {

            } else if (id == navManage) {

            } else if (id == navShare) {

            } else if (id == navSend) {

            }

            drawer.closeDrawer(GravityCompat.START);
            return true;
        });

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
}
