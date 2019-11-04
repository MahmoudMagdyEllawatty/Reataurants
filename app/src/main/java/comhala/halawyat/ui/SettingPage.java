package comhala.halawyat.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.franmontiel.localechanger.LocaleChanger;

import java.util.HashMap;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import comhala.halawyat.R;
import comhala.halawyat.network.App_Requets;
import comhala.halawyat.network.model.SettingModel;

public class SettingPage extends AppCompatActivity implements App_Requets.Request_Complete {
    @BindView(R.id.t_vx)
    TextView tx_terms;
    @BindView(R.id.bt_back)
    ImageView bt_back;
    @BindView(R.id.tds)
    TextView title;
    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = LocaleChanger.configureBaseContext(newBase);
        LocaleChanger.setLocale(Locale.getDefault());
        super.attachBaseContext(newBase);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);
        ButterKnife.bind(this);
        InitsViews();

    }
    public  void InitsViews(){
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (getIntent().getStringExtra("type").equals("1")){
            title.setText(getString(R.string.menu_share));
        }
        if (getIntent().getStringExtra("type").equals("2")){
            title.setText(getString(R.string.menu_send));
        }
        if (getIntent().getStringExtra("type").equals("3")){
            title.setText(getString(R.string.menu_home2));
        }

        new App_Requets(SettingPage.this). Do_settings(new HashMap<>());

    }

    @Override
    public void Done_Request(Object object) {
        if (object instanceof SettingModel.GetSetting){
            SettingModel.GetSetting get_terms=(SettingModel.GetSetting)object;
            if (get_terms.getStatus()==200){
                if (getIntent().getStringExtra("type").equals("1")){
                    tx_terms.setText(get_terms.getData().getTerms());
                }
                if (getIntent().getStringExtra("type").equals("2")){
                    tx_terms.setText(get_terms.getData().getCommonQuestions());

                }
                if (getIntent().getStringExtra("type").equals("3")){
                    tx_terms.setText(get_terms.getData().getAboutUs());

                }

            }
        }
    }
}
