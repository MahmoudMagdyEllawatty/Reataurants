package comhala.halawyat.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smarteist.autoimageslider.SliderViewAdapter;

import comhala.halawyat.R;

public class AreaSliderAdapter extends SliderViewAdapter<SliderAdapterVH> {
    private Context mcontext;

    public AreaSliderAdapter(Context context) {
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
                viewHolder.imageViewBackground.setImageResource(R.drawable.src_images_recipes_aawad);

                break;
            case 1:

                viewHolder.imageViewBackground.setImageResource(R.drawable.src_images_recipes_ajeentskkr);

                break;
            case 2:

                viewHolder.imageViewBackground.setImageResource(R.drawable.src_images_recipes_almhlbyt);

                break;
            case 3:

                viewHolder.imageViewBackground.setImageResource(R.drawable.src_images_recipes_bskootsableh);

                break;
            case 4:

                viewHolder.imageViewBackground.setImageResource(R.drawable.src_images_recipes_bskoyt_balshokolatt);

                break;
            default:
                viewHolder.imageViewBackground.setImageResource(R.drawable.src_images_recipes_bskoyt_balshokolatt);
                break;

        }

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return 5;
    }


}


