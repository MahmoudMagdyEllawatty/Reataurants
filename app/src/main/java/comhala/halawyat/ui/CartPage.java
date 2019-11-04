package comhala.halawyat.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.franmontiel.localechanger.LocaleChanger;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import comhala.halawyat.R;
import comhala.halawyat.network.App_Requets;
import comhala.halawyat.network.model.GetCartModel;
import comhala.halawyat.network.model.ProductModel;
import comhala.halawyat.network.model.addcartmodel;
import comhala.halawyat.ui.adapter.CartAdapter;
import comhala.halawyat.ui.adapter.ProdectAdapter;

public class CartPage extends AppCompatActivity implements App_Requets.Request_Complete {
    RecyclerView recyclerView;
    CartAdapter recyclerViewAdapter;
    String selected_language;
    public static List<GetCartModel.Datum> albumList;
    ImageView baack;
    TextView bt_Make_Order;
    RelativeLayout relativeLayout_Empty;
    public static TextView txtitle, toatl, totall;
    public static String wallet, otal;
    public static Double toal = 0.0;

    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = LocaleChanger.configureBaseContext(newBase);
        LocaleChanger.setLocale(Locale.getDefault());
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_page);
        InitViews();


    }

    public void InitViews() {

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        txtitle = findViewById(R.id.parts);
        toatl = findViewById(R.id.toatl);
        totall = findViewById(R.id.totall);
        bt_Make_Order = findViewById(R.id.make_order);


        bt_Make_Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(CartPage.this, ChooseDelivery.class));

            }
        });
        new App_Requets(CartPage.this).Do_GetCart(new HashMap<>());
    }

    @Override
    public void Done_Request(Object object) {
        if (object instanceof GetCartModel.Get_Cart) {
            GetCartModel.Get_Cart get_signup = (GetCartModel.Get_Cart) object;
            if (get_signup.getStatus() == 200) {
                Toast.makeText(CartPage.this, get_signup.getMsg(), Toast.LENGTH_LONG).show();
                try {
                    if (get_signup.getData().size() > 0) {
                        recyclerView.setAdapter(new CartAdapter(CartPage.this, get_signup.getData()));


                    } else {
                        finish();
                        Toast.makeText(CartPage.this, "لا يوجد منتجات بالسلة", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception x) {
                    finish();
                    Toast.makeText(CartPage.this, "لا يوجد منتجات بالسلة", Toast.LENGTH_LONG).show();
                }


            } else {
                Toast.makeText(CartPage.this, get_signup.getMsg(), Toast.LENGTH_LONG).show();

            }

        }
        if (object instanceof addcartmodel) {
            addcartmodel get_signup = (addcartmodel) object;
            if (get_signup.getStatus() == 200) {
                Toast.makeText(CartPage.this, get_signup.getMsg(), Toast.LENGTH_LONG).show();
                if (recyclerView.getAdapter().getItemCount() == 0) {
                    finish();
                }


            } else {
                Toast.makeText(CartPage.this, get_signup.getMsg(), Toast.LENGTH_LONG).show();

            }

        }
    }
}