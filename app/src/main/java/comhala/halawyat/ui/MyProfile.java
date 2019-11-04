package comhala.halawyat.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.franmontiel.localechanger.LocaleChanger;
import com.orhanobut.hawk.Hawk;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;

import comhala.halawyat.R;
import comhala.halawyat.helper.ConstantsOfApp;
import comhala.halawyat.network.App_Requets;
import comhala.halawyat.network.model.LgoinModel;
import in.mayanknagwanshi.imagepicker.ImageSelectActivity;

public class MyProfile extends AppCompatActivity implements App_Requets.Request_Complete {
    LgoinModel.Data user;
    private EditText email, phone, username;
    String semail, susername, sphone;
    ImageView img_back;
    private Button singup;
    ImageView img_pro;
    File file = null;
    TextView stnd;

    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = LocaleChanger.configureBaseContext(newBase);
        LocaleChanger.setLocale(Locale.getDefault());
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        InitsViews();
    }

    public void InitsViews() {
        user = Hawk.get("user");
        email = findViewById(R.id.et_email);
        phone = findViewById(R.id.et_mobile);
        username = findViewById(R.id.et_name);
        singup = findViewById(R.id.bt_login);
        img_back = findViewById(R.id.bt_back);
        img_pro = findViewById(R.id.logo);
        stnd = findViewById(R.id.t_choosetime);
        phone.setText(user.getPhone());
        email.setText(user.getEmail());
        username.setText(user.getName());
        stnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyProfile.this,ChangePassword.class));
            }
        });
        try {


            Picasso.get()
                    .load(user.getImage().toString())
                    .placeholder(R.drawable.avatar)
                    .error(R.drawable.avatar)
                    .into(img_pro);
        } catch (Exception g) {

        }
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        img_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyProfile.this, ImageSelectActivity.class);
                intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, false);//default is true
                intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);//default is true
                intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
                startActivityForResult(intent, 1213);
            }
        });
        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                semail = email.getText().toString();
                susername = username.getText().toString();
                sphone = phone.getText().toString();
                HashMap<String, String> hashMap = new HashMap<>();

                if (IsValid()) {
                    hashMap.put("phone", sphone);
                    hashMap.put("name", susername);
                    hashMap.put("email", semail);
                    if (file != null) {
                        new App_Requets(MyProfile.this).Do_Editpavatar(hashMap, file);

                    } else {
                        new App_Requets(MyProfile.this).Do_Editprofile(hashMap);

                    }

                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1213 && resultCode == Activity.RESULT_OK) {
            String filePath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            Bitmap selectedImage = BitmapFactory.decodeFile(filePath);
            img_pro.setImageBitmap(selectedImage);
            file = new File(filePath);
        }
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

        }
        return Is_Valid;
    }

    @Override
    public void Done_Request(Object object) {
        if (object instanceof LgoinModel.Get_Login) {
            LgoinModel.Get_Login signup = (LgoinModel.Get_Login) object;
            if (signup.getStatus() == 200) {
                Hawk.put("user", signup.getData());
                ConstantsOfApp.setAccessToken(signup.getData().getJwt());
                Toast.makeText(MyProfile.this, signup.getMsg(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MyProfile.this, MyProfile.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(MyProfile.this, signup.getMsg(), Toast.LENGTH_LONG).show();

            }
        }
    }
}
