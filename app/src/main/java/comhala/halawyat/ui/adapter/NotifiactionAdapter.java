package comhala.halawyat.ui.adapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import comhala.halawyat.R;
import comhala.halawyat.helper.DateTimeUtils;
import comhala.halawyat.network.model.NotificationModel;

public class NotifiactionAdapter extends RecyclerView.Adapter<NotifiactionAdapter.MyViewHolder> {

    private List<NotificationModel.Datum> moviesList;
    Context context;




    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, displayInteger, genre;
        public ImageView img, increase, decrease, imgdelet;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.datte);
            genre = (TextView) view.findViewById(R.id.mess);



        }
    }


    public NotifiactionAdapter(List<NotificationModel.Datum> moviesList, Context context1) {
        this.moviesList = moviesList;
        this.context = context1;

    }

    @Override
    public NotifiactionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customnot, parent, false);


        return new NotifiactionAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final NotifiactionAdapter.MyViewHolder holder, final int position) {

        final NotificationModel.Datum movie = moviesList.get(position);

        holder.title.setText(movie.getCreatedAt());


        holder.genre.setText(movie.getBody());


    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


}