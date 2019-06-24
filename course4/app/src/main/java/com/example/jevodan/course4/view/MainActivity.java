package com.example.jevodan.course4.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.jevodan.course4.event.FirstEvent;
import com.example.jevodan.course4.event.SecondEvent;
import com.example.jevodan.course4.presenter.MainPresenter;
import com.example.jevodan.course4.R;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    public static final int INDEX1 = 0;
    public static final int INDEX2 = 1;
    public static final int INDEX3 = 2;
    @BindView(R.id.btnCounter1)
    Button button1;
    @BindView(R.id.btnCounter2)
    Button button2;
    @BindView(R.id.btnCounter3)
    Button button3;
    @BindView(R.id.textView_rxbin)
    TextView textViewRx;
    @BindView(R.id.editText_rxbin)
    EditText editTextRx;

    @InjectPresenter
    MainPresenter presenter;

    private Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Disposable disposable = RxTextView.textChanges(editTextRx)
                .subscribe(charSequence -> textViewRx.setText(charSequence));
        bus = new Bus();
        bus.register(this);
    }

    @OnClick(R.id.button_eventBus)
    public void eventBus1(View view){
        bus.post(new FirstEvent()); // post публикует
    }

    @Subscribe
    public void listenBus1(FirstEvent event){
        textViewRx.setText("Ответ EventBus1"); // subscribe отвечает
    }

    @OnClick(R.id.button_eventBus2)
    public void eventBus2(View view){
        bus.post(new SecondEvent("Ответ EventBus2"));
    }

    @Subscribe
    public void listenBus2(SecondEvent event){
        textViewRx.setText(event.getMessage());
    }

    @ProvidePresenter
    public MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

    @OnClick(R.id.btnCounter1)
    public void counterClick1() {
        presenter.counterClick1(INDEX1);
    }

    @OnClick(R.id.btnCounter2)
    public void counterClick2() {
        presenter.counterClick2(INDEX2);
    }

    @OnClick(R.id.btnCounter3)
    public void counterClick3() {
        presenter.counterClick3(INDEX3);
    }

    @Override
    public void setButtonText1(Integer value) {
        button1.setText(String.format(getString(R.string.count_format), value));
    }

    @Override
    public void setButtonText2(Integer value) {
        button2.setText(String.format(getString(R.string.count_format), value));
    }

    @Override
    public void setButtonText3(Integer value) {
        button3.setText(String.format(getString(R.string.count_format), value));
    }
}

