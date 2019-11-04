package comhala.halawyat.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.franmontiel.localechanger.LocaleChanger;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import comhala.halawyat.R;
import comhala.halawyat.network.App_Requets;
import comhala.halawyat.network.model.addcartmodel;

public class ChooseDelivery extends AppCompatActivity implements App_Requets.Request_Complete {
    @BindView(R.id.t_chooseAddress)
    TextView T_address;
    @BindView(R.id.t_choosetime)
    TextView t_choosetime;
    String address, lat, lang, time, email, code;
    @BindView(R.id.t_makeOrder)
    Button t_makeOrder;
    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = LocaleChanger.configureBaseContext(newBase);
        LocaleChanger.setLocale(Locale.getDefault());
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_delivery);
        ButterKnife.bind(this);
        InitsViews();

    }

    public void InitsViews() {
        T_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseDelivery.this, MapPage.class);
                startActivityForResult(intent, 1);
            }
        });
        t_makeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {


                    if (address.length() > 0 && time.length() > 0) {

                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("order_date", time);
                        hashMap.put("latitude", lat);
                        hashMap.put("longitude", lang);
                        hashMap.put("location", address);
                        new App_Requets(ChooseDelivery.this).Do_Order(hashMap);
                    } else {
                        Toast.makeText(ChooseDelivery.this, "من فضلك اختر الوقت و عنوان التوصيل", Toast.LENGTH_LONG).show();


                    }
                }
                catch (Exception f){
                    Toast.makeText(ChooseDelivery.this, "من فضلك اختر الوقت و عنوان التوصيل", Toast.LENGTH_LONG).show();

                }
            }
        });

        t_choosetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(ChooseDelivery.this);
                dialog.setContentView(R.layout.timepickker);
                final Window window1 = dialog.getWindow();
                window1.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                TimePicker picker = dialog.findViewById(R.id.datePicker1);
                picker.setIs24HourView(true);
                TextView btnGet = dialog.findViewById(R.id.t_choosetime);
                dialog.show();
                btnGet.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(View v) {
                        int hour, minute;
                        if (Build.VERSION.SDK_INT >= 23 ){
                            hour = picker.getHour();
                            minute = picker.getMinute();
                        }
                        else{
                            hour = picker.getCurrentHour();
                            minute = picker.getCurrentMinute();

                        }
                        dialog.dismiss();
                        t_choosetime.setText( hour +":"+ minute+":"+"00");
                        Date c = Calendar.getInstance().getTime();

                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", new Locale("en"));
                        String formattedDate = df.format(c);
                        if (minute<=9){
                            time=formattedDate+" "+ hour +":"+ "0"+minute+":"+"00";

                        }
                        else {
                            time=formattedDate+" "+ hour +":"+ minute+":"+"00";

                        }
                        System.out.println("Current time => " + time);

                    }
                });

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                address = data.getStringExtra("address");
                T_address.setText(address);
                lat = data.getStringExtra("lat");
                lang = data.getStringExtra("lang");
                Log.e("key", address);
            }

            if (resultCode == Activity.RESULT_CANCELED) {

            }
        }
    }//onActiv


    @Override
    public void Done_Request(Object object) {
        if (object instanceof addcartmodel) {
            addcartmodel get_signup = (addcartmodel) object;
            if (get_signup.getStatus() == 200) {
                Toast.makeText(ChooseDelivery.this, get_signup.getMsg(), Toast.LENGTH_LONG).show();
                Intent intent=new Intent(ChooseDelivery.this,StoreDetail.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();



            } else {
                Toast.makeText(ChooseDelivery.this, get_signup.getMsg(), Toast.LENGTH_LONG).show();

            }

        }
    }
}
