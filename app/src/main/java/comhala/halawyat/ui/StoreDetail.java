package comhala.halawyat.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.franmontiel.localechanger.LocaleChanger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import comhala.halawyat.R;
import comhala.halawyat.helper.ConstantsOfApp;
import comhala.halawyat.network.App_Requets;
import comhala.halawyat.network.model.ProductModel;
import comhala.halawyat.network.model.ShopModel;
import comhala.halawyat.network.model.StoreDetailModel;
import comhala.halawyat.network.model.addcartmodel;
import comhala.halawyat.ui.adapter.AreaAdapter;
import comhala.halawyat.ui.adapter.CategoryAdapter;
import comhala.halawyat.ui.adapter.ProdectAdapter;
import comhala.halawyat.ui.adapter.StoreAdapter;

public class StoreDetail extends AppCompatActivity implements App_Requets.Request_Complete {
    RecyclerView recyclerView,rec_pro;
   public static  TextView t_all,t_name,t_name1,t_deleviry;
   RatingBar rate;
   ImageView t_cart;
   List<StoreDetailModel.Product>list;
   public  static  double price;

    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = LocaleChanger.configureBaseContext(newBase);
        LocaleChanger.setLocale(Locale.getDefault());
        super.attachBaseContext(newBase);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);
        InitsViews();
    }

    public void InitsViews() {
        recyclerView = findViewById(R.id.rec_ser);
        rec_pro=findViewById(R.id.rec_pro);
        rate=findViewById(R.id.rate);
        t_name=findViewById(R.id.t_name);
        t_name1=findViewById(R.id.t_name1);
        t_deleviry=findViewById(R.id.t_deleviry);
        t_all=findViewById(R.id.t_all);
        ImageView img=findViewById(R.id.t_back);
        t_cart=findViewById(R.id.t_cart);
        t_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ConstantsOfApp.GetAccessToken().isEmpty()) {
                    startActivity(new Intent(StoreDetail.this,CartPage.class));


                }
                else {
                    ConstantsOfApp.showLoginDialog(StoreDetail.this).show();
                }
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rec_pro.setLayoutManager(new LinearLayoutManager(StoreDetail.this, RecyclerView.VERTICAL, false));
        rec_pro.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(StoreDetail.this, RecyclerView.HORIZONTAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        HashMap<String, String> hashMap = new HashMap<>();
       hashMap.put("shop_id", StoreAdapter.t_store.getId() + "");

        new App_Requets(StoreDetail.this).Do_Shops_Details(hashMap);
        t_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t_all.setBackgroundResource(R.drawable.line);
                CategoryAdapter.selectedItem=-1;
                rec_pro.setAdapter(new ProdectAdapter(list, StoreDetail.this));

                recyclerView.getAdapter().notifyDataSetChanged();


            }
        });
    }

    @Override
    public void Done_Request(Object object) {
        if (object instanceof StoreDetailModel.Get_Store) {
            StoreDetailModel.Get_Store get_signup = (StoreDetailModel.Get_Store) object;
            if (get_signup.getStatus() == 200) {
                list=new ArrayList<>();
                rate.setRating(Float.parseFloat(get_signup.getData().getRate()+""));
                t_name.setText(get_signup.getData().getName());
                t_name1.setText(get_signup.getData().getName());
                t_deleviry.setText(getString(R.string.DeliveryCharge)+" "+get_signup.getData().getShipping()+" "+getString(R.string.Di));
                price=Double.parseDouble(get_signup.getData().getShipping());

                list.addAll(get_signup.getData().getProducts());
                Toast.makeText(StoreDetail.this, get_signup.getMsg(), Toast.LENGTH_LONG).show();
                recyclerView.setAdapter(new CategoryAdapter(get_signup.getData().getCategories(), StoreDetail.this));
                rec_pro.setAdapter(new ProdectAdapter(get_signup.getData().getProducts(), StoreDetail.this));


            } else {
                Toast.makeText(StoreDetail.this, get_signup.getMsg(), Toast.LENGTH_LONG).show();

            }

        }
        if (object instanceof ProductModel) {
            ProductModel get_signup = (ProductModel) object;
            if (get_signup.getStatus() == 200) {
                Toast.makeText(StoreDetail.this, get_signup.getMsg(), Toast.LENGTH_LONG).show();
                rec_pro.setAdapter(new ProdectAdapter(get_signup.getData(), StoreDetail.this));


            } else {
                Toast.makeText(StoreDetail.this, get_signup.getMsg(), Toast.LENGTH_LONG).show();

            }

        }
        if (object instanceof addcartmodel) {
            addcartmodel get_signup = (addcartmodel) object;
            if (get_signup.getStatus() == 200) {
                Toast.makeText(StoreDetail.this, get_signup.getMsg(), Toast.LENGTH_LONG).show();



            } else {
                Toast.makeText(StoreDetail.this, get_signup.getMsg(), Toast.LENGTH_LONG).show();

            }

        }
    }
}
