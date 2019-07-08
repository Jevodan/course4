package com.example.jevodan.countries.ui.adapter;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jevodan.countries.R;
import com.example.jevodan.countries.mvp.presenter.IPhotoListPresenter;
import com.example.jevodan.countries.mvp.presenter.IReposListPresenter;
import com.example.jevodan.countries.mvp.view.CountryRowView;
import com.example.jevodan.countries.mvp.view.PhotoRowView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private IPhotoListPresenter presenter;

    public PhotoAdapter(IPhotoListPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.pos = position;
        presenter.bind(holder);
       // RxView.clicks(holder.itemView).map(o -> holder).subscribe(presenter.getClickSubject());
    }

    @Override
    public int getItemCount() {
        return presenter.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements PhotoRowView {

        int pos = 0;

        @BindView(R.id.title_view)
        TextView titleTextView;

        @BindView(R.id.image_view)
        ImageView photo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public int getPos() {
            return pos;
        }

        @Override
        public void setTitle(String title) {
            titleTextView.setText(title);
        }

        @Override
        public void setPictureUrl(String url) {
            Log.d("444",url);
            Picasso.get().load(url)
                    .error(R.drawable.ic_launcher_background)
                    .into(photo);

        }

    }

}
