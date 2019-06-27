package com.example.jevodan.convertfile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.squareup.picasso.Picasso;
import java.io.File;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.os.Environment.DIRECTORY_PICTURES;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    String pathName;
    String fileName = "Mage";

    @BindView(R.id.button_convert)
    Button buttonConvert;

    @BindView(R.id.image)
    ImageView imageView;

    @BindView(R.id.image_png)
    ImageView imageViewPNG;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @OnClick(R.id.button_convert)
    public void buttonConvertClick() {
        Picasso.get()
                .load(R.drawable.progress_animation)
                .error(R.drawable.progress_animation)
                .placeholder(R.drawable.progress_animation)
                .into(imageViewPNG);
        presenter.goConvert(fileName);
    }

    @ProvidePresenter
    public MainPresenter providePresenter() {
        pathName = String.valueOf(getExternalFilesDir(DIRECTORY_PICTURES));
        return new MainPresenter(pathName);
    }

    @InjectPresenter
    MainPresenter presenter;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getViewImage(new File(pathName + "/" + fileName + ".jpg"), imageView);
    }

    @Override
    public void showImage() {
        getViewImage(new File(pathName + "/" + "new.png"), imageViewPNG);
    }

    @Override
    public void progressShow() {
        // progressBar.setVisibility(View.VISIBLE);  заменил анимацией
    }

    @Override
    public void progressHide() {
        //  progressBar.setVisibility(View.INVISIBLE);
    }

    private void getViewImage(File file, ImageView into) {
        Picasso.get()
                .load(file)
                .error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.ic_launcher_background)
                .into(into);
    }

}