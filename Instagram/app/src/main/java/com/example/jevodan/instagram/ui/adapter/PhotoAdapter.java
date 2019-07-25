package com.example.jevodan.instagram.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jevodan.instagram.R;
import com.example.jevodan.instagram.mvp.presenter.IPhotoListPresenter;
import com.example.jevodan.instagram.mvp.view.PhotoRowView;
import com.example.jevodan.instagram.tools.ChosenAnime;
import com.example.jevodan.instagram.ui.image.GlideImageLoader;
import com.example.jevodan.instagram.ui.image.IImageLoader;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private IPhotoListPresenter presenter;
    IImageLoader<ImageView> imageLoader = new GlideImageLoader();

    public PhotoAdapter(IPhotoListPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent, false));
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

        @BindView(R.id.heart)
        ImageView heart;

        @OnClick(R.id.heart)
        public void ivBack() {
            ChosenAnime.imageAnime(heart);

            Log.d("тест", String.valueOf(ViewHolder.this.getPos()));
        }

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
            imageLoader.loadInto(url, photo);
        }

    }

}
