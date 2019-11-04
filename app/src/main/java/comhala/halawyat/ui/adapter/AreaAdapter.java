package comhala.halawyat.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import comhala.halawyat.R;
import comhala.halawyat.network.model.AreaModel;
import comhala.halawyat.ui.Areas;
import comhala.halawyat.ui.Regions;
import comhala.halawyat.ui.homePage;

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.MyViewHolder> {

    private List<AreaModel.Datum> moviesList;
    Context mContext;
    public static AreaModel.Datum area;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView item_price, discount;
        public ImageView item_img;

        public MyViewHolder(View view) {
            super(view);
            item_price = (TextView) view.findViewById(R.id.item_name);
        }
    }


    public AreaAdapter(List<AreaModel.Datum> moviesList, Context mContext) {
        this.moviesList = moviesList;
        this.mContext = mContext;
    }

    @Override
    public AreaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categoryitem, parent, false);

        return new AreaAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AreaAdapter.MyViewHolder holder, int position) {
        AreaModel.Datum movie = moviesList.get(position);
        holder.item_price.setText(movie.getName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Areas.type.equals("0")) {
                    area = movie;
                    Regions.T_area.setText(movie.getName());
                    ((Activity) mContext).finish();
                }
                else  if (Areas.type.equals("1")) {
                    area = movie;

                    homePage.t_choose.setText(movie.getName());
                    Intent intent=new Intent(mContext,homePage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    mContext.startActivity(intent);
                    ((Activity) mContext).finish();
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public void filterList(List<AreaModel.Datum> filterdNames) {
        this.moviesList = filterdNames;
        notifyDataSetChanged();
    }
}