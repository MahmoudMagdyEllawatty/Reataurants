package comhala.halawyat.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.franmontiel.localechanger.LocaleChanger;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import comhala.halawyat.R;
import comhala.halawyat.network.App_Requets;
import comhala.halawyat.network.model.MyOrdersModel;
import comhala.halawyat.network.model.NotificationModel;
import comhala.halawyat.ui.adapter.MyOrderAdapter;
import comhala.halawyat.ui.adapter.NotifiactionAdapter;

public class Notifications extends AppCompatActivity implements App_Requets.Request_Complete {
    private List<NotificationModel.Datum> mDataList;
    private RecyclerView mRecyclerView;

    ImageView imageViewback;
    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = LocaleChanger.configureBaseContext(newBase);
        LocaleChanger.setLocale(Locale.getDefault());
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);

        imageViewback = findViewById(R.id.addres);

        imageViewback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        HashMap hashMap = new HashMap<>();
        new App_Requets(this).Do_Get_Notifation(hashMap);
    }

    @Override
    public void Done_Request(Object object) {
        if (object instanceof NotificationModel.Get_Notif) {
            NotificationModel.Get_Notif get_homeAPI = ( NotificationModel.Get_Notif) object;

            if (get_homeAPI.getStatus() == 200) {
                try {


                    mRecyclerView.setAdapter(new NotifiactionAdapter(get_homeAPI.getData(),Notifications.this));

                } catch (Exception x) {
                    finish();
                    Toast.makeText(Notifications.this, "لا يوجد اشعارات ", Toast.LENGTH_LONG).show();
                }
            }
            else {
                Toast.makeText(Notifications.this, "لا يوجد اشعارات ", Toast.LENGTH_LONG).show();

            }

        }
    }
}