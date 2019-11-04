package comhala.halawyat.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.franmontiel.localechanger.LocaleChanger;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import comhala.halawyat.R;
import comhala.halawyat.ui.adapter.AreaAdapter;

public class Regions extends AppCompatActivity {
    public static TextView T_area;
    Button bt_search;

    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = LocaleChanger.configureBaseContext(newBase);
        LocaleChanger.setLocale(Locale.getDefault());
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regions);
        ButterKnife.bind(this);
        InitsViews();

    }

    public void InitsViews() {
        T_area = findViewById(R.id.vhoosearea);
        bt_search=findViewById(R.id.t_serach);
        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!T_area.getText().toString().equals(getString(R.string.choosearea))){

                    startActivity(new Intent(Regions.this,homePage.class));
                    finish();

                }
                else {
                    Toast.makeText(Regions.this,getString(R.string.choosearea),Toast.LENGTH_LONG).show();

                }
            }
        });
        T_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Regions.this,Areas.class);
                intent.putExtra("ty","0");
                startActivity(intent);
            }
        });


    }
}
