package comhala.halawyat.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.franmontiel.localechanger.LocaleChanger;

import java.util.HashMap;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import comhala.halawyat.R;
import comhala.halawyat.helper.ConstantsOfApp;
import comhala.halawyat.network.App_Requets;
import comhala.halawyat.network.model.Singupmodel;
import comhala.halawyat.network.model.addcartmodel;

public class ChangePassword extends AppCompatActivity implements View.OnClickListener, App_Requets.Request_Complete {
    @BindView(R.id.bt_back)
    ImageView bt_back;
    @BindView(R.id.btn_email)
    Button btn_email;
    @BindView(R.id.et_phone)
    EditText et_email;
    String password;

    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = LocaleChanger.configureBaseContext(newBase);
        LocaleChanger.setLocale(Locale.getDefault());
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        InitsViews();
    }

    public void InitsViews() {
        bt_back.setOnClickListener(this);
        btn_email.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finish();
                break;
            case R.id.btn_email:
                password = et_email.getText().toString();
                if (IsValid()) {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("password", password);
                    hashMap.put("password_confirmation", password);


                    new App_Requets(ChangePassword.this).Do_Coangepassw(hashMap);
                }

                break;
        }
    }

    public boolean IsValid() {
        boolean Is_Valid = true;
        if (password.length() == 0) {
            Is_Valid = false;
            et_email.setError(getString(R.string.emalireuired));
            return Is_Valid;
        }
        return Is_Valid;

    }

    @Override
    public void Done_Request(Object object) {
        addcartmodel get_signup = (addcartmodel) object;
        if (get_signup.getStatus() == 200) {
            Toast.makeText(ChangePassword.this, get_signup.getMsg(), Toast.LENGTH_LONG).show();
            finish();

        } else {
            Toast.makeText(ChangePassword.this, get_signup.getMsg(), Toast.LENGTH_LONG).show();

        }

    }
}
