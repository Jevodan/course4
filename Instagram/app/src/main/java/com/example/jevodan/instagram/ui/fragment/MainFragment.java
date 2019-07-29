package com.example.jevodan.instagram.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.jevodan.instagram.App;
import com.example.jevodan.instagram.R;
import com.example.jevodan.instagram.mvp.model.entity.User;
import com.example.jevodan.instagram.mvp.presenter.MainFragmentPresenter;
import com.example.jevodan.instagram.mvp.presenter.MainPresenter;
import com.example.jevodan.instagram.mvp.view.MainView;
import com.example.jevodan.instagram.tools.Constants;
import com.example.jevodan.instagram.ui.adapter.PhotoAdapter;
import com.example.jevodan.instagram.ui.image.GlideImageLoader;
import com.example.jevodan.instagram.ui.image.IImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainFragment extends MvpAppCompatFragment implements MainView {

    public static MainFragment newInstance(){
        return new MainFragment();
    }

    @InjectPresenter
    MainFragmentPresenter presenter;

    @BindView(R.id.username)
    TextView username;

    @BindView(R.id.id_user)
    TextView idUser;

    @BindView(R.id.login)
    TextView login;

    @BindView(R.id.image_view_avatar)
    ImageView avatar;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.preloading)
    RelativeLayout loading;

    @OnClick(R.id.button_chosen)
    public void btnClick1() {
        presenter.loadData(Constants.USER_ID, false, true);
    }

    @OnClick(R.id.button_all)
    public void btnClick2() {
        presenter.loadData(Constants.USER_ID, false, false);
    }

    PhotoAdapter adapter;

    IImageLoader<ImageView> imageLoader = new GlideImageLoader();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        App.getInstance().getAppComponent().inject(this);
        View view =  inflater.inflate(R.layout.fragment_main, null);
        App.getInstance().initStetho();
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * Прединициализация, вызывваемая из презентера
     */
    @Override
    public void init() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new PhotoAdapter(presenter.getPhotoListPresenter());
        recyclerView.setAdapter(adapter);
    }

    @ProvidePresenter
    public MainFragmentPresenter providePresenter() {
        MainFragmentPresenter presenter = new MainFragmentPresenter(AndroidSchedulers.mainThread());
        App.getInstance().getAppComponent().inject(presenter);
        return presenter;
    }

    /**
     * Выводим в шапке вью
     * имя,логин и картинку юзера
     * @param user - Класс POJO {@link User}
     */
    @Override
    public void showMessage(User user) {
        login.setText(user.getFullName());
        idUser.setText(user.getId());
        username.setText(user.getUsername());
        imageLoader.loadInto(user.getProfilePicture(), avatar);
    }

    /**
     * @deprecated
     * Тоаст для отображения названия картинки при клике
     * @param text - название картинки
     */
    @Override
    public void showPhoto(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    /**
     * обновление списка
     */
    @Override
    public void updateList() {
        adapter.notifyDataSetChanged();
    }

    /**
     * отображаем значок загрузки
     */
    @Override
    public void showLoading() {
        loading.setVisibility(View.VISIBLE);

    }

    /**
     * скрываем значок загрузки
     */
    @Override
    public void hideLoading() {
        loading.setVisibility(View.INVISIBLE);
    }

}
