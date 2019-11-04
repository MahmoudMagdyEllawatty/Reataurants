package comhala.halawyat.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import comhala.halawyat.R;
import comhala.halawyat.helper.ConstantsOfApp;
import comhala.halawyat.network.App_Requets;
import comhala.halawyat.network.model.ShopModel;
import comhala.halawyat.network.model.StoreDetailModel;
import comhala.halawyat.ui.StoreDetail;

public class ProdectAdapter  extends RecyclerView.Adapter<ProdectAdapter.MyViewHolder> {

    private List<StoreDetailModel.Product> moviesList;
    Context mContext;
    public static ShopModel.Datum t_store;
    int minteger = 1;
    Double price;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView item_price, discount,discount1;
        public ImageView item_img;
        public RatingBar ratingBar;
        public ImageButton increase;
        public ImageButton decrease;
        public TextView t_qunity,t_addcart;
        public MyViewHolder(View view) {
            super(view);
            item_price = (TextView) view.findViewById(R.id.item_name);
            item_img = view.findViewById(R.id.item_img);
            discount= (TextView) view.findViewById(R.id.item_price);
            discount1= (TextView) view.findViewById(R.id.item_price1);
            decrease= view.findViewById(R.id.t_decrease);
            increase= view.findViewById(R.id.t_increase);
            ratingBar = view.findViewById(R.id.ratingBar);
            t_qunity = view.findViewById(R.id.t_qnty);
            t_addcart= view.findViewById(R.id.t_addcart);

        }
    }


    public ProdectAdapter(List<StoreDetailModel.Product> moviesList, Context mContext) {
        this.moviesList = moviesList;
        this.mContext = mContext;
    }

    @Override
    public ProdectAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.productitem, parent, false);

        return new ProdectAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProdectAdapter.MyViewHolder holder, int position) {
        StoreDetailModel.Product movie = moviesList.get(position);
        holder.item_price.setText(movie.getName());
        holder.discount.setText(movie.getDescription());
        holder.discount1.setText(movie.getPrice()+" "+mContext.getString(R.string.Di));

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
        holder.t_addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ConstantsOfApp.GetAccessToken().isEmpty()) {
                    HashMap<String,String>hashMap=new HashMap<>();
                    hashMap.put("product_id",movie.getId()+"");
                    hashMap.put("quantity",minteger+"");
                    new App_Requets(mContext).Do_AddCart(hashMap);

                }
                else {
                    ConstantsOfApp.showLoginDialog(mContext).show();
                }            }
        });
        holder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                minteger=Integer.parseInt(holder.t_qunity.getText().toString());
                minteger = minteger + 1;
                holder.t_qunity.setText("" + minteger);

                    price=Double.parseDouble(movie.getPrice());





            }
        });
        holder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                minteger=Integer.parseInt(holder.t_qunity.getText().toString());

                if (minteger > 1) {
                    minteger = minteger - 1;
                    holder.t_qunity.setText("" + minteger);

                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public void filterList(List<StoreDetailModel.Product> filterdNames) {
        this.moviesList = filterdNames;
        notifyDataSetChanged();
    }
}

