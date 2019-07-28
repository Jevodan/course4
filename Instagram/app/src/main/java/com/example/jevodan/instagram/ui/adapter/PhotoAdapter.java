package com.example.jevodan.instagram.ui.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private IPhotoListPresenter presenter;
    IImageLoader<ImageView> imageLoader = new GlideImageLoader();

    /**
     * Конструктор класса
     *
     * @param presenter - получаем презентер
     */
    public PhotoAdapter(IPhotoListPresenter presenter) {
        this.presenter = presenter;
    }

    /**
     * "надуваем холдер данными"
     *
     * @param parent   - родительская вью для получения контекса
     * @param viewType - ?
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent, false));
    }

    /**
     * Формируем позицию в списке, наполняя данными список
     *
     * @param holder   - холдер
     * @param position - текущая позиция в списке
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.pos = position;
        presenter.bind(holder);
        RxView.clicks(holder.itemView).map(o -> holder).subscribe(presenter.getClickSubject());

        RxView.clicks(holder.itemView.findViewById(R.id.heart)).map(o -> {
            ChosenAnime.imageAnime(holder.heart, holder.favor);
            return holder;
        }).subscribe(presenter.getClickFavor());
    }

    /**
     * @return размер списка данных
     */
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

        @BindView(R.id.favor)
        TextView favor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public int getPos() {
            return pos;
        }

        /**
         * Устанавливаем название фотографии
         *
         * @param title - название фотографии
         */
        @Override
        public void setTitle(String title) {
            titleTextView.setText(title);
        }

        /**
         * Устанавливаем url фотографии
         *
         * @param url - url фотографии
         */
        @Override
        public void setPictureUrl(String url) {
            imageLoader.loadInto(url, photo);
        }

        /**
         * Меняем картинку сердечка
         * избранное / или нет
         *
         * @param favorr - флаг true / false
         */
        @Override
        public void setFavor(Boolean favorr) {
            favor.setText(favorr.toString());
            if (favor.getText().equals("1") || favor.getText().equals("true"))
                heart.setImageResource(R.drawable.ic_favorite_red_500_18dp);
            else
                heart.setImageResource(R.drawable.ic_favorite_border_red_500_18dp);

        }

    }

}
