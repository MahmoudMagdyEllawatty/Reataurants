package comhala.halawyat.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.franmontiel.localechanger.LocaleChanger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import comhala.halawyat.R;
import comhala.halawyat.network.model.GetCartModel;
import comhala.halawyat.network.model.MyOrdersModel;
import comhala.halawyat.ui.adapter.MyOrderAdapter;
import comhala.halawyat.ui.adapter.OrderDetailsAdapter;

public class OrderDetails extends AppCompatActivity {

    TextView tbout, taddres, tdate, tstaus, T_sub, T_total, Tshiped;
    MyOrdersModel.Datum result;
    RecyclerView recyclerView;
    public static int status;

    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = LocaleChanger.configureBaseContext(newBase);
        LocaleChanger.setLocale(Locale.getDefault());
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        try {
            InitsViews();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void InitsViews() throws ParseException {
        tbout = findViewById(R.id.tabout);
        tstaus = findViewById(R.id.tstatus);
        taddres = findViewById(R.id.taddress);
        tdate = findViewById(R.id.tadate);
        T_sub = findViewById(R.id.tsubtotal);
        T_total = findViewById(R.id.Totall);
        Tshiped = findViewById(R.id.shiped);

        result = MyOrderAdapter.order;

       // tstaus.setText(result.getStatus());
        String Costship = result.getShippingFees() + " " + getString(R.string.Di);
        String totaol = result.getTotalCost() + " " + getString(R.string.Di);
        String Total_Cost = (Double.parseDouble(result.getTotalCost()) + (Double.parseDouble(result.getShippingFees()))) + " " + getString(R.string.Di);

        Tshiped.setText(Costship);
        T_sub.setText(totaol);
        T_total.setText(Total_Cost);

        String address =   getString(R.string.ordershippedto) + result.getLocation().toString();

        tbout.setText(result.getShopName());

        tstaus.setText(result.getOrderDate());
        taddres.setText(address);

        recyclerView = findViewById(R.id.recycler_view);
        ImageView img_back = findViewById(R.id.img_back);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(OrderDetails.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new OrderDetailsAdapter(result.getProductArray(), OrderDetails.this));

    }
}