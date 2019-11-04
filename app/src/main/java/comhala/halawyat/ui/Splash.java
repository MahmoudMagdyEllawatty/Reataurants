package comhala.halawyat.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.orhanobut.hawk.Hawk;

import comhala.halawyat.R;
import comhala.halawyat.helper.ConstantsOfApp;

import static android.content.ContentValues.TAG;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String h= Hawk.get("first","0");
                if (h.equals("0")){
                    Hawk.put("first","1");
                    startActivity(new Intent(Splash.this,ChooseLanguage.class));
                    finish();

                }
                else if (h.equals("1")){

                    startActivity(new Intent(Splash.this,Regions.class));
                    finish();
                }
            }
        },2000);


    }

}
