package com.example.jevodan.instagram.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
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
import com.example.jevodan.instagram.mvp.view.ActivityView;
import com.example.jevodan.instagram.mvp.view.MainView;
import com.example.jevodan.instagram.navigation.Screens;
import com.example.jevodan.instagram.tools.Constants;
import com.example.jevodan.instagram.ui.BackButtonListener;
import com.example.jevodan.instagram.ui.adapter.PhotoAdapter;
import com.example.jevodan.instagram.ui.image.GlideImageLoader;
import com.example.jevodan.instagram.ui.image.IImageLoader;
import com.facebook.stetho.Stetho;

import javax.inject.Inject;

import butterknife.ButterKnife;

import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;

public class MainActivity extends MvpAppCompatActivity implements ActivityView {

    private Navigator navigator = new SupportAppNavigator(this, R.id.container);

    @Inject
    NavigatorHolder navigatorHolder;

    @InjectPresenter
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getInstance().getAppComponent().inject(this);
        App.getInstance().initStetho();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    /**
     * Метод  для возвращения презентера
     * с перредавапемыми данными
     *
     * @return - презентер вьюшки
     */
    @ProvidePresenter
    public MainPresenter providePresenter() {
        MainPresenter presenter = new MainPresenter(AndroidSchedulers.mainThread());
        App.getInstance().getAppComponent().inject(presenter);
        return presenter;
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        super.onPause();
        navigatorHolder.removeNavigator();
    }


    /**
     * берем текущий фрагмент и
     * прикручиваем кнопку "вернуться"
     */
    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if (fragment instanceof BackButtonListener) {
            ((BackButtonListener) fragment).backClick();
        }
    }
}
