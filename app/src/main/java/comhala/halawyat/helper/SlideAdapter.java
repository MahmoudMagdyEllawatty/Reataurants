package comhala.halawyat.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smarteist.autoimageslider.*;

import comhala.halawyat.R;
import comhala.halawyat.ui.ChooseLanguage;

public class SlideAdapter  extends SliderViewAdapter<SliderAdapterVH> {
    private Context mcontext;

    public SlideAdapter(Context context) {
        this.mcontext = context;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {

        switch (position) {
            case 0:
                viewHolder.textViewDescription.setText(R.string.tb1);
                viewHolder.imageViewBackground.setImageResource(R.drawable.banner1);

                break;
            case 1:
                viewHolder.textViewDescription.setText(R.string.tb2);

                viewHolder.imageViewBackground.setImageResource(R.drawable.banner2);

                break;
            case 2:
                viewHolder.textViewDescription.setText(R.string.tb3);

                viewHolder.imageViewBackground.setImageResource(R.drawable.banner3);

                break;
            default:
                viewHolder.imageViewBackground.setImageResource(R.drawable.banner1);
                break;

        }

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return 3;
    }


}

