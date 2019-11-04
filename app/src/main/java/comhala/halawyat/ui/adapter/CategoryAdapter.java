package comhala.halawyat.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

import comhala.halawyat.R;
import comhala.halawyat.network.App_Requets;
import comhala.halawyat.network.model.AreaModel;
import comhala.halawyat.network.model.StoreDetailModel;
import comhala.halawyat.ui.Areas;
import comhala.halawyat.ui.Regions;
import comhala.halawyat.ui.StoreDetail;
import comhala.halawyat.ui.homePage;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    public static int selectedItem=-1;
    public boolean selected=false;
    public static String id;
    private OnItemClickListener mAdapterListener;
    private List<StoreDetailModel.Category> moviesList;
    Context mContext;
    public static StoreDetailModel.Category area;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView item_price, discount;
        public ImageView item_img;

        public MyViewHolder(View view) {
            super(view);
            item_price = (TextView) view.findViewById(R.id.item_name);
        }
    }


    public CategoryAdapter(List<StoreDetailModel.Category> moviesList, Context mContext) {
        this.moviesList = moviesList;
        this.mContext = mContext;
    }

    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cat, parent, false);

        return new CategoryAdapter.MyViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(CategoryAdapter.MyViewHolder holder, int position) {
        StoreDetailModel.Category movie = moviesList.get(position);
        holder.item_price.setText(movie.getName());
        if(selectedItem==position)
        {
            StoreDetail.t_all.setBackground(null);
            holder.itemView.setBackgroundResource(R.drawable.line);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("category_id",movie.getId()+"");
            hashMap.put("shop_id", StoreAdapter.t_store.getId() + "");

            new App_Requets(mContext).Do_Cate_Details(hashMap);

        }
        else {
            holder.itemView.setBackground(null);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!selected) {
                    if (selectedItem == position) {
                        selectedItem = 0;
                    } else {
                        selectedItem = position;

                    }
                    notifyDataSetChanged();
                    if (mAdapterListener != null) {
                        mAdapterListener.OnSelecetd(selectedItem, "");
                    }
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public interface OnItemClickListener {
        void OnSelecetd(int pos, String type);
    }

}
