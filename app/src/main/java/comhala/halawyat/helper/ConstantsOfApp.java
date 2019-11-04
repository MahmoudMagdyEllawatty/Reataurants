package comhala.halawyat.helper;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.orhanobut.hawk.Hawk;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import comhala.halawyat.R;
import comhala.halawyat.ui.Login;
import okhttp3.Authenticator;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class ConstantsOfApp {
    public final static String ACCESS_TOKEN_KEY = "accessToken";
    public final static String Device_TOKEN_KEY = "deviceToken";
    public static String DEFAULT_LANGUAGE = "ar";


    public static String GetAccessToken() {
        return Hawk.get(ConstantsOfApp.ACCESS_TOKEN_KEY, "");
    }

    public static void setAccessToken(String accessToken) {
        Hawk.put(ConstantsOfApp.ACCESS_TOKEN_KEY, accessToken);
    }
    public static String GetDeviceToken() {
        return Hawk.get(ConstantsOfApp.Device_TOKEN_KEY, null);
    }

    public static void setDeviceToken(String accessToken) {
        Hawk.put(ConstantsOfApp.Device_TOKEN_KEY, accessToken);
    }
    public static void setDEFAULT_LANGUAGE(String accessToken) {
        Hawk.put(ConstantsOfApp.DEFAULT_LANGUAGE, accessToken);
    }
    public static String GetDEFAULT_LANGUAGE() {
        return Hawk.get(ConstantsOfApp.DEFAULT_LANGUAGE, "ar");
    }
    public static boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN    = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean PhoneValidator(String phone)
    {
        Pattern pattern;
        Matcher matcher;
        final String phone_PATTERN    = "^[0-9]{10,13}$";
        pattern = Pattern.compile(phone_PATTERN);
        matcher = pattern.matcher(phone);
        return matcher.matches();
    }
    public static KProgressHUD showLoadingDialog(Context context) {
        KProgressHUD hud=new KProgressHUD(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();

        return hud;
    }

    public static Dialog showLoginDialog(Context context) {
        Dialog   dialog_login = new Dialog(context);
        dialog_login.setContentView(R.layout.login);
        Button bt_cancel = dialog_login.findViewById(R.id.caclel);
        Button  bt_Ok = dialog_login.findViewById(R.id.t_ok);
        final Window window1 = dialog_login.getWindow();

        window1.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        bt_Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
                ((Activity)context). finish();
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_login.dismiss();
            }
        });

        return dialog_login;
    }


}
