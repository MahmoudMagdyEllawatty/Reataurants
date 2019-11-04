package comhala.halawyat.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import comhala.halawyat.R;
import comhala.halawyat.network.model.ShopModel;
import comhala.halawyat.ui.StoreDetail;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.MyViewHolder> {

    private List<ShopModel.Datum> moviesList;
    Context mContext;
    public static ShopModel.Datum t_store;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView item_price, discount;
        public ImageView item_img;
        public RatingBar ratingBar;

        public MyViewHolder(View view) {
            super(view);
            item_price = (TextView) view.findViewById(R.id.item_name);
            item_img = view.findViewById(R.id.item_img);
            ratingBar = view.findViewById(R.id.ratingBar);
        }
    }


    public StoreAdapter(List<ShopModel.Datum> moviesList, Context mContext) {
        this.moviesList = moviesList;
        this.mContext = mContext;
    }

    @Override
    public StoreAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store, parent, false);

        return new StoreAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StoreAdapter.MyViewHolder holder, int position) {
        ShopModel.Datum movie = moviesList.get(position);
        holder.item_price.setText(movie.getName());
        holder.ratingBar.setRating(Float.parseFloat(movie.getRate() + ""));
        try {


            if (!TextUtils.isEmpty(movie.getImage())) {
                Picasso.get()
                        .load(movie.getImage())
                        .placeholder(R.drawable.logo)
                        .error(R.drawable.logo)
                        .into(holder.item_img);
            }
        } catch (Exception x) {

        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t_store = movie;
                Intent intent = new Intent(mContext, StoreDetail.class);

                mContext.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public void filterList(List<ShopModel.Datum> filterdNames) {
        this.moviesList = filterdNames;
        notifyDataSetChanged();
    }
}
