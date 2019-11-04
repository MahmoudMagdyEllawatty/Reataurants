package comhala.halawyat.ui;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import androidx.annotation.NonNull;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.franmontiel.localechanger.LocaleChanger;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.orhanobut.hawk.Hawk;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import comhala.halawyat.helper.ConstantsOfApp;
import in.myinnos.customfontlibrary.TypefaceUtil;

import static android.content.ContentValues.TAG;

public class App extends Application {
    private static Context mContext;
    String lang;
    public static final List<Locale> SUPPORTED_LOCALES =
            Arrays.asList(
                    new Locale("ar"),
                    new Locale("en")
            );

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this.getApplicationContext();
        Hawk.init(mContext).build();
        AndroidNetworking.initialize(getApplicationContext());
       // AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        FirebaseApp.initializeApp(mContext);

        LocaleChanger.initialize(getApplicationContext(), SUPPORTED_LOCALES);
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/jannat.ttf");
        if (ConstantsOfApp.DEFAULT_LANGUAGE.equalsIgnoreCase("ar")) {
            lang = "ar";
            ConstantsOfApp.setDEFAULT_LANGUAGE("ar");
        } else {
            lang = "en";
            ConstantsOfApp.setDEFAULT_LANGUAGE("en");
        }
        LocaleChanger.setLocale(new Locale(lang));
        try {


            if (ConstantsOfApp.GetDeviceToken().equals(null)) {
                Toekne();
            }
        } catch (Exception x) {
            Toekne();
        }



    }
    public void Toekne() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (!task.isSuccessful()) {
                    Log.w(TAG, "getInstanceId failed", task.getException());
                    return;
                }

                // Get new Instance ID token
                String token = task.getResult().getToken();
                ConstantsOfApp.setDeviceToken(token);

                // Log and toast
                Log.d("tokenn : ", token);
            }
        });
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleChanger.onConfigurationChanged();
    }

    public static Context getAppContext() {
        return mContext;

    }

}
