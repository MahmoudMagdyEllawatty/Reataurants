package comhala.halawyat.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.franmontiel.localechanger.LocaleChanger;
import com.franmontiel.localechanger.utils.ActivityRecreationHelper;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Locale;

import comhala.halawyat.R;
import comhala.halawyat.helper.ConstantsOfApp;
import comhala.halawyat.network.App_Requets;
import comhala.halawyat.network.model.LgoinModel;
import comhala.halawyat.network.model.Singupmodel;

public class Login extends AppCompatActivity implements View.OnClickListener ,App_Requets.Request_Complete{
    TextView T_forgetpass,bt_signup,t_skip;
    EditText et_mobile, et_Pass;
    String  spassword, sphone;
    private Button bt_Login;
    private Button singup;

    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = LocaleChanger.configureBaseContext(newBase);
        LocaleChanger.setLocale(Locale.getDefault());
        super.attachBaseContext(newBase);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitsViews();

    }

    public void InitsViews() {
        bt_signup=findViewById(R.id.bt_signup);
        T_forgetpass=findViewById(R.id.T_forgetpass);
        et_Pass =  findViewById(R.id.et_Pass);
        et_mobile =  findViewById(R.id.et_mobile);
        bt_Login =  findViewById(R.id.bt_login);

        T_forgetpass.setOnClickListener(this);
        t_skip=findViewById(R.id.T_skip);
        t_skip.setOnClickListener(this);
        bt_signup.setOnClickListener(this);
        bt_Login.setOnClickListener(this);



    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.T_skip:
                startActivity(new Intent(Login.this,Regions.class));
                finish();
                break;
            case R.id.bt_signup:
                startActivity(new Intent(Login.this,SignUp.class));
                break;
            case R.id.T_forgetpass:
                startActivity(new Intent(Login.this,Forgetpassword.class));
                break;
            case R.id.bt_login:
                sphone = et_mobile.getText().toString();
                spassword = et_Pass.getText().toString();
                if (IsValid()) {
                   HashMap<String,String> hashMap = new HashMap<>();
                    hashMap.put("phone", sphone);
                    hashMap.put("password", spassword);
                    hashMap.put("firebase_token",ConstantsOfApp.GetDeviceToken() );
                    new App_Requets(Login.this).Do_Login(hashMap);

                }
                break;
        }
    }
    public boolean IsValid() {
        boolean Is_Valid = true;
        if (sphone.length() == 0) {
            Is_Valid = false;
            et_mobile.setError(getString(R.string.phonerequired));
            return Is_Valid;
        } else if (sphone.length() < 8) {
            Is_Valid = false;
            et_mobile.setError(getString(R.string.phone_number));
            return Is_Valid;
        }else if (spassword.length() < 6) {
            Is_Valid = false;
            et_Pass.setError(getString(R.string.passwordmust));

            return Is_Valid;
        } else if (spassword.length() == 0) {
            Is_Valid = false;
            et_Pass.setError(getString(R.string.passwordreqiured));
            return Is_Valid;
        }
        return Is_Valid;
    }

    @Override
    public void Done_Request(Object object) {
        if (object instanceof LgoinModel.Get_Login){
            LgoinModel.Get_Login   signup=(LgoinModel.Get_Login)object;
            if (signup.getStatus()==200){
                Hawk.put("user",signup.getData());
                ConstantsOfApp.setAccessToken(signup.getData().getJwt());
                Toast.makeText(Login.this,signup.getMsg(),Toast.LENGTH_LONG).show();
                Intent intent=new Intent(Login.this,Regions.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(Login.this,signup.getMsg(),Toast.LENGTH_LONG).show();

            }
        }
    }
}

