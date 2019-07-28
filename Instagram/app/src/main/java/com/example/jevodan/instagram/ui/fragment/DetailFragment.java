package com.example.jevodan.instagram.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.jevodan.instagram.App;
import com.example.jevodan.instagram.R;
import com.example.jevodan.instagram.mvp.presenter.DetailFragmentPresenter;
import com.example.jevodan.instagram.mvp.view.DetailView;
import com.example.jevodan.instagram.ui.BackButtonListener;
import com.example.jevodan.instagram.ui.image.GlideImageLoader;
import com.example.jevodan.instagram.ui.image.IImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.example.jevodan.instagram.tools.Constants.PICTURE;
import static com.example.jevodan.instagram.tools.Constants.TITLE;

public class DetailFragment extends MvpAppCompatFragment implements DetailView, BackButtonListener {


    public static DetailFragment newInstance(String picture, String title) {
        DetailFragment fragment = new DetailFragment();
        Bundle arg = new Bundle();
        arg.putString(PICTURE, picture);
        arg.putString(TITLE, title);
        fragment.setArguments(arg);
        return fragment;
    }

    @InjectPresenter
    DetailFragmentPresenter presenter;

    @BindView(R.id.picture)
    ImageView imageView;

    @BindView(R.id.text_view_name)
    TextView title;


    IImageLoader<ImageView> imageLoader = new GlideImageLoader();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, null);
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * Получаем title и picture из аргументов, сохраненных ранее в бандлах
     *
     * @return Presenter
     */
    @ProvidePresenter
    public DetailFragmentPresenter providePresenter() {
        String picture = getArguments().getString(PICTURE);
        String title = getArguments().getString(TITLE);
        DetailFragmentPresenter presenter = new DetailFragmentPresenter(AndroidSchedulers.mainThread(), picture, title);
        App.getInstance().getAppComponent().inject(presenter);
        return presenter;
    }

    @Override
    public void backClick() {
        presenter.backClick();
    }

    /**
     * Загружаем картинку
     * @param url - адрес картинки
     */
    @Override
    public void setPicture(String url) {
        imageLoader.loadInto(url, imageView);
    }

    /**
     * Показываем название картинки
     * @param name -название картинки
     */
    @Override
    public void showMessage(String name) {
        title.setText(name);
    }
}
