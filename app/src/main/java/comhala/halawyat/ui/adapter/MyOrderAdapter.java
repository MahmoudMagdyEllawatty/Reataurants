package comhala.halawyat.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import comhala.halawyat.R;
import comhala.halawyat.network.model.MyOrdersModel;
import comhala.halawyat.ui.OrderDetails;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.MyViewHolder> {

    private List<MyOrdersModel.Datum> moviesList;
    Context mctx;
    View itemView;
    public static MyOrdersModel.Datum order;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView item_num, item_date, item_total, item_stauts;
        public ImageView item_cancel;

        public MyViewHolder(View view) {
            super(view);
            item_num = (TextView) view.findViewById(R.id.item_name);
            item_date = (TextView) view.findViewById(R.id.item_price);
            item_cancel = view.findViewById(R.id.item_fav);
            item_total = view.findViewById(R.id.item_total);
            item_stauts = view.findViewById(R.id.item_stauts);

        }
    }


    public MyOrderAdapter(List<MyOrdersModel.Datum> moviesList, Context ctx) {
        this.moviesList = moviesList;
        this.mctx = ctx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.orderitem, parent, false);

        return new MyViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final MyOrdersModel.Datum movie = moviesList.get(position);
        holder.item_num.setText(mctx.getString(R.string.OrderNum) + " " + movie.getOrderNumber());

        holder.item_total.setText(movie.getTotalCost() + " " + mctx.getString(R.string.Di));

        holder.item_date.setText(mctx.getString(R.string.Shopname) + " " + movie.getShopName());
        if (movie.getStatus().equals("4")) {
            holder.item_stauts.setText(mctx.getString(R.string.cancleled));
            holder.item_stauts.setTextColor(mctx.getColor(R.color.error_clr));
        }
        if (movie.getStatus().equals("1")) {
            holder.item_stauts.setText(mctx.getString(R.string.Accepted));
            holder.item_stauts.setTextColor(mctx.getColor(R.color.accept_clr));
        }
        if (movie.getStatus().equals("0")) {
            holder.item_stauts.setText(mctx.getString(R.string.pending));
            holder.item_stauts.setTextColor(mctx.getColor(R.color.cancelled_clr));
        }
        if (movie.getStatus().equals("2")) {
            holder.item_stauts.setText(mctx.getString(R.string.reday));
            holder.item_stauts.setTextColor(mctx.getColor(R.color.divider));
        }
        if (movie.getStatus().equals("3")) {
            holder.item_stauts.setText(mctx.getString(R.string.Completed));
            holder.item_stauts.setTextColor(mctx.getColor(R.color.colorPrimary));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order = movie;
                mctx.startActivity(new Intent(mctx, OrderDetails.class));


            }
        });


    }

    public void filterList(List<MyOrdersModel.Datum> filterdNames) {
        this.moviesList = filterdNames;
        notifyDataSetChanged();
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