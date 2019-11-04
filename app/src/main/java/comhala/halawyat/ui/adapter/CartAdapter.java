package comhala.halawyat.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import comhala.halawyat.R;
import comhala.halawyat.network.App_Requets;
import comhala.halawyat.network.model.GetCartModel;
import comhala.halawyat.ui.CartPage;
import comhala.halawyat.ui.StoreDetail;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<GetCartModel.Datum> marketList;
    Context mContext;

    int minteger = 1;
    String id;
    Double price=0.0;
    int ty = 0;
    int tott = 0;

    public CartAdapter(Context mContext, List<GetCartModel.Datum> albumList) {
        this.mContext = mContext;
        this.marketList = albumList;
        price=StoreDetail.price;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GetCartModel.Datum market = marketList.get(position);

        holder.tname_pro.setText(market.getName());
        holder.txtPrice.setText(market.getPrice() + "  د ك ");
        holder.t_qunity.setText(market.getQuantity());
        Double dprice=Double.parseDouble(market.getPrice())*Double.parseDouble(market.getQuantity());
        price=price+dprice;


        CartPage.totall.setText(mContext.getString(R.string.DeliveryCharge)+" : "+ StoreDetail.price+" "+mContext.getString(R.string.Di)+"\n"+mContext.getString(R.string.totalprice)+" : "+price+" "+mContext.getString(R.string.Di));
        try {

            if (!market.getImage().toString().equals("null")) {
                Picasso.get().load(market.getImage()).placeholder(R.drawable.logo).into(holder.imagepro);

            }
        } catch (Exception x) {

        }

        holder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                minteger = Integer.parseInt(holder.t_qunity.getText().toString());
                minteger = minteger + 1;
                holder.t_qunity.setText("" + minteger);
                market.setQuantity(minteger+"");
                price=StoreDetail.price;
                notifyDataSetChanged();
                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("product_id",market.getProductId()+"");
                hashMap.put("quantity",minteger+"");
                new App_Requets(mContext).Do_AddCart(hashMap);


            }
        });
        holder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                minteger = Integer.parseInt(holder.t_qunity.getText().toString());

                if (minteger > 1) {
                    minteger = minteger - 1;
                    holder.t_qunity.setText("" + minteger);
                    market.setQuantity(minteger+"");
                    notifyDataSetChanged();
                    price=StoreDetail.price;
                    HashMap<String,String>hashMap=new HashMap<>();
                    hashMap.put("product_id",market.getProductId()+"");
                    hashMap.put("quantity",minteger+"");
                    new App_Requets(mContext).Do_AddCart(hashMap);

                }
            }
        });
        holder.detelte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marketList.remove(market);
                notifyDataSetChanged();
                price=StoreDetail.price;
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("product_id",market.getProductId()+"");
                new App_Requets(mContext).Do_RemoveCart(hashMap);
            }
        });

    }

    @Override
    public int getItemCount() {
        return marketList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tname_pro;
        public TextView t_qunity;
        public TextView txtPrice;
        public ImageView increase;
        public ImageView decrease;
        public ImageView imagepro;
        public ImageView detelte;

        public ViewHolder(View view) {
            super(view);

            tname_pro = view.findViewById(R.id.tname_pro);
            t_qunity = view.findViewById(R.id.t_qunity);
            txtPrice = view.findViewById(R.id.Price_Pro);
            imagepro = view.findViewById(R.id.img_product);
            decrease = view.findViewById(R.id.decrease);
            increase = view.findViewById(R.id.increase);
            detelte = view.findViewById(R.id.imageView_detle);

        }
    }


}
