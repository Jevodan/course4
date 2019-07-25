package com.example.jevodan.instagram.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.jevodan.instagram.App;
import com.example.jevodan.instagram.R;
import com.example.jevodan.instagram.mvp.model.entity.Datum;
import com.example.jevodan.instagram.mvp.model.entity.User;
import com.example.jevodan.instagram.mvp.presenter.MainPresenter;
import com.example.jevodan.instagram.mvp.view.MainView;
import com.example.jevodan.instagram.tools.Constants;
import com.example.jevodan.instagram.ui.adapter.PhotoAdapter;
import com.example.jevodan.instagram.ui.image.GlideImageLoader;
import com.example.jevodan.instagram.ui.image.IImageLoader;
import com.facebook.stetho.Stetho;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    MainPresenter presenter;

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
        presenter.showChosen();
    }

    @OnClick(R.id.button_all)
    public void btnClick2() {
        presenter.loadData(Constants.USER_ID, false);
    }

    PhotoAdapter adapter;

    IImageLoader<ImageView> imageLoader = new GlideImageLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getInstance().initStetho();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void init() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new PhotoAdapter(presenter.getPhotoListPresenter());
        recyclerView.setAdapter(adapter);
    }

    @ProvidePresenter
    public MainPresenter providePresenter() {
        MainPresenter presenter = new MainPresenter(AndroidSchedulers.mainThread());
        App.getInstance().getAppComponent().inject(presenter);
        return presenter;
    }

    @Override
    public void showMessage(User user) {
        login.setText(user.getFullName());
        idUser.setText(user.getId());
        username.setText(user.getUsername());
        imageLoader.loadInto(user.getProfilePicture(), avatar);
    }

    @Override
    public void showPhoto(List<Datum> data) {

    }

    @Override
    public void updateList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        loading.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        loading.setVisibility(View.INVISIBLE);
    }
}
