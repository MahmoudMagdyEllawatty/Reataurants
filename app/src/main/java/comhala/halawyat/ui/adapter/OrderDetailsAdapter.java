package comhala.halawyat.ui.adapter;

import android.content.Context;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.List;

import comhala.halawyat.R;
import comhala.halawyat.network.model.MyOrdersModel;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.MyViewHolder> {

    private List<MyOrdersModel.ProductArray> moviesList;
    Context mctx;
    View itemView;
    float rattte = 0;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView item_price, item_qnty, item_name, rate_item, item_status;
        public ImageView item_img, item_canel;

        public MyViewHolder(View view) {
            super(view);
            item_price = view.findViewById(R.id.item_price);
            item_qnty = view.findViewById(R.id.item_qnty);
            item_name = view.findViewById(R.id.item_name);
            item_img = view.findViewById(R.id.item_img);
            item_status = view.findViewById(R.id.item_status);

        }
    }


    public OrderDetailsAdapter(List<MyOrdersModel.ProductArray> moviesList, Context ctx) {
        this.moviesList = moviesList;
        this.mctx = ctx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemorder, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final MyOrdersModel.ProductArray movie = moviesList.get(position);
        holder.item_name.setText(movie.getName());
        holder.item_price.setText(movie.getPrice() + " " + mctx.getString(R.string.Di));
        holder.item_qnty.setText(mctx.getString(R.string.quantity) +
                " " + movie.getQuantity());
        if (!TextUtils.isEmpty(movie.getImage())) {
            Picasso.get()
                    .load(movie.getImage())
                    .placeholder(R.drawable.logog)
                    .error(R.drawable.logog)
                    .into(holder.item_img);
        }

    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
