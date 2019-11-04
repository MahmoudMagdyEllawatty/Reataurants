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

import comhala.halawyat.R;
import comhala.halawyat.helper.ConstantsOfApp;
import comhala.halawyat.network.App_Requets;
import comhala.halawyat.network.model.Singupmodel;

public class SignUp extends AppCompatActivity implements App_Requets.Request_Complete {
    private EditText email, password,phone,username;
    private Button singup;
    HashMap<String,String> hashMap;
    String semail, susername, spassword, sphone;
    ImageView img_back;
    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = LocaleChanger.configureBaseContext(newBase);
        LocaleChanger.setLocale(Locale.getDefault());
        super.attachBaseContext(newBase);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        onInitView();
    }

    private void onInitView() {
        email =  findViewById(R.id.et_email);
        password =  findViewById(R.id.et_Pass);
        phone =  findViewById(R.id.et_mobile);
        username =  findViewById(R.id.et_name);
        singup =  findViewById(R.id.bt_login);
        img_back=findViewById(R.id.bt_back);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                semail = email.getText().toString();
                susername = username.getText().toString();
                sphone = phone.getText().toString();
                spassword = password.getText().toString();
                if (IsValid()) {
                    hashMap = new HashMap<>();
                    hashMap.put("phone", sphone);
                    hashMap.put("password", spassword);
                    hashMap.put("name", susername);
                    hashMap.put("email", semail);

                    new App_Requets(SignUp.this).Do_Register(hashMap);

                }
            }
        });


    }


    public boolean IsValid() {
        boolean Is_Valid = true;
        if (susername.length() == 0) {
            Is_Valid = false;
            username.setError(getString(R.string.userreuired));
            return Is_Valid;
        } else if (susername.length() < 3) {
            Is_Valid = false;
            username.setError(getString(R.string.usermust));
            return Is_Valid;
        } else if (sphone.length() == 0) {
            Is_Valid = false;
            phone.setError(getString(R.string.phonerequired));
            return Is_Valid;
        } else if (sphone.length() < 8) {
            Is_Valid = false;
            phone.setError(getString(R.string.phone_number));
            return Is_Valid;
        } else if (semail.length() == 0) {
            Is_Valid = false;
            email.setError(getString(R.string.emalireuired));
            return Is_Valid;
        } else if (!ConstantsOfApp.emailValidator(semail)) {
            Is_Valid = false;
            email.setError(getString(R.string.emailsmustbe));
            return Is_Valid;
        } else if (spassword.length() < 6) {
            Is_Valid = false;
            password.setError(getString(R.string.passwordmust));

            return Is_Valid;
        } else if (spassword.length() == 0) {
            Is_Valid = false;
            password.setError(getString(R.string.passwordreqiured));
            return Is_Valid;
        }
        return Is_Valid;
    }

    @Override
    public void Done_Request(Object object) {
        if (object instanceof Singupmodel.Get_Signup){
            Singupmodel.Get_Signup   signup=(Singupmodel.Get_Signup)object;
            if (signup.getStatus()==200){
                Toast.makeText(SignUp.this,signup.getMsg(),Toast.LENGTH_LONG).show();
                Intent intent=new Intent(SignUp.this,VerfyCode.class);
                intent.putExtra("type","1");
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(SignUp.this,signup.getMsg(),Toast.LENGTH_LONG).show();

            }
        }
    }
}
