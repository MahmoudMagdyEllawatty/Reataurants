package comhala.halawyat.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.franmontiel.localechanger.LocaleChanger;
import com.franmontiel.localechanger.utils.ActivityRecreationHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;

import comhala.halawyat.R;
import comhala.halawyat.helper.AreaSliderAdapter;
import comhala.halawyat.helper.ConstantsOfApp;
import comhala.halawyat.network.App_Requets;
import comhala.halawyat.network.model.AreaModel;
import comhala.halawyat.network.model.ShopModel;
import comhala.halawyat.ui.adapter.AreaAdapter;
import comhala.halawyat.ui.adapter.StoreAdapter;

public class homePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, App_Requets.Request_Complete {
    public static TextView t_choose;
    public static RecyclerView recyclerView;

    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = LocaleChanger.configureBaseContext(newBase);
        LocaleChanger.setLocale(Locale.getDefault());
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" ");

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        SliderView sliderView = findViewById(R.id.imageSlider);
        final AreaSliderAdapter adapter = new AreaSliderAdapter(homePage.this);
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE);
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        sliderView.startAutoCycle();

        t_choose = findViewById(R.id.t_choose);
        t_choose.setText(AreaAdapter.area.getName());
        t_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homePage.this, Areas.class);
                intent.putExtra("ty", "1");
                startActivity(intent);


            }
        });
        if (ConstantsOfApp.GetAccessToken().isEmpty()) {
            navigationView.getMenu().getItem(1).setVisible(false);
            navigationView.getMenu().getItem(2).setVisible(false);
            navigationView.getMenu().getItem(8).setVisible(false);
            navigationView.getMenu().getItem(9).setVisible(false);
        }
        recyclerView = findViewById(R.id.rec);
        recyclerView.setLayoutManager(new GridLayoutManager(homePage.this, 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("region_id", AreaAdapter.area.getId() + "");
        new App_Requets(homePage.this).Do_Shops(hashMap);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(homePage.this, homePage.class));
            finish();
        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(homePage.this, MyOrders.class));

        } else if (id == R.id.nav_slideshow) {
            startActivity(new Intent(homePage.this, MyProfile.class));

        } else if (id == R.id.nav_tools) {
            startActivity(new Intent(homePage.this, ContactUs.class));

        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(homePage.this, SettingPage.class);
            intent.putExtra("type", "1");
            startActivity(intent);

        } else if (id == R.id.nav_send) {
            Intent intent = new Intent(homePage.this, SettingPage.class);
            intent.putExtra("type", "2");
            startActivity(intent);
        } else if (id == R.id.nav_send1) {
            Intent intent = new Intent(homePage.this, SettingPage.class);
            intent.putExtra("type", "3");
            startActivity(intent);
        } else if (id == R.id.nav_send22) {
            if (Locale.getDefault().toString().equals("ar")) {
                LocaleChanger.setLocale(new Locale("en"));
                ConstantsOfApp.setDEFAULT_LANGUAGE("en");

            } else {
                LocaleChanger.setLocale(new Locale("ar"));
                ConstantsOfApp.setDEFAULT_LANGUAGE("ar");

            }
            ActivityRecreationHelper.recreate(homePage.this, true);

        } else if (id == R.id.nav_send23) {
            startActivity(new Intent(homePage.this, Notifications.class));

        }
        else if (id == R.id.nav_send2) {
            ConstantsOfApp.setAccessToken("");
            Intent intent = new Intent(homePage.this, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();




        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivityRecreationHelper.onResume(this);
    }

    @Override
    protected void onDestroy() {
        ActivityRecreationHelper.onDestroy(this);
        super.onDestroy();
    }

    @Override
    public void Done_Request(Object object) {
        if (object instanceof ShopModel.Get_Shops) {
            ShopModel.Get_Shops get_signup = (ShopModel.Get_Shops) object;
            if (get_signup.getStatus() == 200) {
                Toast.makeText(homePage.this, get_signup.getMsg(), Toast.LENGTH_LONG).show();
                recyclerView.setAdapter(new StoreAdapter(get_signup.getData(), homePage.this));


            } else {
                Toast.makeText(homePage.this, get_signup.getMsg(), Toast.LENGTH_LONG).show();

            }
        }
    }
}
