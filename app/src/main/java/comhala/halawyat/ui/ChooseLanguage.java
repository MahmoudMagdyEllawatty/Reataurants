package comhala.halawyat.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.franmontiel.localechanger.LocaleChanger;
import com.franmontiel.localechanger.utils.ActivityRecreationHelper;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import comhala.halawyat.R;
import comhala.halawyat.helper.ConstantsOfApp;
import comhala.halawyat.helper.SlideAdapter;

public class ChooseLanguage extends AppCompatActivity {
    SliderView sliderView;
    public static TextView t_banner;
    @BindView(R.id.t_arabic)
    TextView t_arabic;
    @BindView(R.id.t_english)
    TextView t_english;

    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = LocaleChanger.configureBaseContext(newBase);
        LocaleChanger.setLocale(Locale.getDefault());
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_language);
        ButterKnife.bind(this);
        InitsViews();
    }

    public void InitsViews() {
        sliderView = findViewById(R.id.imageSlider);
        t_banner = findViewById(R.id.t_banner);
        final SlideAdapter adapter = new SlideAdapter(ChooseLanguage.this);
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE);
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        sliderView.startAutoCycle();
        t_arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocaleChanger.setLocale(new Locale("ar"));
                ConstantsOfApp.setDEFAULT_LANGUAGE("ar");
                ActivityRecreationHelper.recreate(ChooseLanguage.this, true);
                Log.e("lang", Locale.getDefault().toString());
                startActivity(new Intent(ChooseLanguage.this, Regions.class));
                finish();
            }
        });
        t_english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocaleChanger.setLocale(new Locale("en"));
                ConstantsOfApp.setDEFAULT_LANGUAGE("en");
                ActivityRecreationHelper.recreate(ChooseLanguage.this, true);
                Log.e("lang", Locale.getDefault().toString());
                startActivity(new Intent(ChooseLanguage.this, Regions.class));
                finish();

            }
        });

    }
}
