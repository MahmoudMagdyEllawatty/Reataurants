package comhala.halawyat.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.franmontiel.localechanger.LocaleChanger;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.HashMap;
import java.util.Locale;

import comhala.halawyat.R;
import comhala.halawyat.network.App_Requets;
import comhala.halawyat.network.model.addcartmodel;

public class ContactUs extends AppCompatActivity implements App_Requets.Request_Complete{

    EditText etname, etemail, etsubject, etmessage, etphone;
    String name, email, subject, message, phone;
    TextView button_sent;
    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = LocaleChanger.configureBaseContext(newBase);
        LocaleChanger.setLocale(Locale.getDefault());
        super.attachBaseContext(newBase);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        InitsViews();
    }

    public void InitsViews() {
        etname = findViewById(R.id.et_name);
        etemail = findViewById(R.id.et_email);
        etsubject = findViewById(R.id.subject);
        etphone = findViewById(R.id.et_phone);
        etmessage = findViewById(R.id.problem);

        button_sent = findViewById(R.id.sentt);
        ImageView img_back = findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        button_sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = etmessage.getText().toString();
                email = etemail.getText().toString();
                name = etname.getText().toString();
                phone = etphone.getText().toString();
                subject = etsubject.getText().toString();
                if (message.length() > 0 && subject.length() > 0 && email.length() > 0 && name.length() > 0) {

                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("email", email);
                    hashMap.put("title", subject);
                    hashMap.put("message", message);
                    hashMap.put("phone", phone);
                    new App_Requets(ContactUs.this).Do_Contactus(hashMap);

                } else {
                    Toast.makeText(ContactUs.this, "من فضلك قم بإدخال جميع البيانات المطلوبة بطريقة صحيحة ", Toast.LENGTH_LONG).show();

                }

            }
        });
    }

    @Override
    public void Done_Request(Object object) {
        if (object instanceof addcartmodel) {
            addcartmodel get_signup = (addcartmodel) object;
            if (get_signup.getStatus() == 200) {
                Toast.makeText(ContactUs.this, get_signup.getMsg(), Toast.LENGTH_LONG).show();
                finish();



            } else {
                Toast.makeText(ContactUs.this, get_signup.getMsg(), Toast.LENGTH_LONG).show();

            }

        }
    }
}