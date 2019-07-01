package com.example.jevodan.countries.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.jevodan.countries.R;
import com.example.jevodan.countries.mvp.presenter.MainPresenter;
import com.example.jevodan.countries.mvp.view.MainView;
import com.example.jevodan.countries.ui.adapter.CountryAdapter;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    MainPresenter presenter;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.userTextView)
    TextView userLogin;

    @BindView(R.id.myPhoto)
    ImageView photo;

    CountryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //  init();
    }

    @ProvidePresenter
    public MainPresenter providePresenter() {
        return new MainPresenter(AndroidSchedulers.mainThread());
    }


    @Override
    public void updateList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG);

    }

    @Override
    public void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CountryAdapter(presenter.getReposListPresenter());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showUser(String login, String url) {
        userLogin.setText(login);
        presenter.loadUserRepo(url);
    }

    @Override
    public void showPicture(String url) {
        Log.d("5555","url:" + url);
        Picasso.get().load(url)
                .error(R.drawable.ic_launcher_background)
                .into(photo);
    }

}
