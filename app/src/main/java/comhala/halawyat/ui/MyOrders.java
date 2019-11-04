package comhala.halawyat.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.franmontiel.localechanger.LocaleChanger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import comhala.halawyat.R;
import comhala.halawyat.network.App_Requets;
import comhala.halawyat.network.model.MyOrdersModel;
import comhala.halawyat.ui.adapter.MyOrderAdapter;

public class MyOrders extends AppCompatActivity implements App_Requets.Request_Complete {
    ImageView img_back;
    LinearLayoutManager mLayoutManager;
    private int currentPage = 1;
    RecyclerView recyclerView;
    HashMap<String, String> hashMap;
    MyOrderAdapter myOrderAdapter;
    Intent intent;

    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = LocaleChanger.configureBaseContext(newBase);
        LocaleChanger.setLocale(Locale.getDefault());
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        InitsBViews();
    }

    public void InitsBViews() {
        recyclerView = findViewById(R.id.recycler_view);
        img_back = findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mLayoutManager = new LinearLayoutManager(MyOrders.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        hashMap = new HashMap<>();
        new App_Requets(this).Get_MyOrders(hashMap);
    }

    @Override
    public void Done_Request(Object object) {
        if (object instanceof MyOrdersModel.Get_MyOrder) {
            MyOrdersModel.Get_MyOrder get_homeAPI = (MyOrdersModel.Get_MyOrder) object;

            if (get_homeAPI.getStatus() == 200) {
                try {

                    myOrderAdapter = new MyOrderAdapter(get_homeAPI.getData(), MyOrders.this);
                    myOrderAdapter.setHasStableIds(true);
                    recyclerView.setAdapter(myOrderAdapter);

            } catch (Exception x) {
                finish();
                Toast.makeText(MyOrders.this, "لا يوجد طلبات ", Toast.LENGTH_LONG).show();
            }
            }

        }
    }

}
