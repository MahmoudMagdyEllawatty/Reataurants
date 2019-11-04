package comhala.halawyat.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.franmontiel.localechanger.LocaleChanger;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import comhala.halawyat.R;
import comhala.halawyat.helper.AreaSliderAdapter;
import comhala.halawyat.helper.SlideAdapter;
import comhala.halawyat.network.App_Requets;
import comhala.halawyat.network.model.AreaModel;
import comhala.halawyat.network.model.Singupmodel;
import comhala.halawyat.ui.adapter.AreaAdapter;

public class Areas extends AppCompatActivity implements App_Requets.Request_Complete {
    SliderView sliderView;
    public static RecyclerView recyclerView;
    public static String type="nll";
    private EditText search;
    private AreaAdapter recyclerAdapter;
   List< AreaModel.Datum> fliter_list;
    List< AreaModel.Datum> lists;
    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = LocaleChanger.configureBaseContext(newBase);
        LocaleChanger.setLocale(Locale.getDefault());
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areas);
        InitsViews();
    }

    public void InitsViews() {
        type=getIntent().getStringExtra("ty");
        sliderView = findViewById(R.id.imageSlider);
        final AreaSliderAdapter adapter = new AreaSliderAdapter(Areas.this);
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE);
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        sliderView.startAutoCycle();
        search=findViewById(R.id.t_ser);
        recyclerView = findViewById(R.id.rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(Areas.this, RecyclerView.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        new App_Requets(Areas.this).Do_Areas(new HashMap<>());

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               // filter(charSequence.toString());
            }
            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());

            }
        });
    }
    private void filter(String text) {
        fliter_list = new ArrayList<>();

        for (AreaModel.Datum item : lists) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                fliter_list.add(item);
            }
        }

        recyclerAdapter.filterList(fliter_list);
    }
    @Override
    public void Done_Request(Object object) {
        AreaModel.Get_Area get_signup = (AreaModel.Get_Area) object;
        if (get_signup.getStatus() == 200) {
            Toast.makeText(Areas.this, get_signup.getMsg(), Toast.LENGTH_LONG).show();
            lists=new ArrayList<>();
            lists.addAll(get_signup.getData());
            recyclerAdapter = new AreaAdapter(lists, Areas.this);

            recyclerView.setAdapter(recyclerAdapter);


        } else {
            Toast.makeText(Areas.this, get_signup.getMsg(), Toast.LENGTH_LONG).show();

        }

    }
}
