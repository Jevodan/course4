package com.example.jevodan.instagram;

import com.arellomobile.mvp.MvpView;
import com.example.jevodan.instagram.di.DaggerTestComponent;
import com.example.jevodan.instagram.di.TestComponent;
import com.example.jevodan.instagram.di.TestRepoModule;
import com.example.jevodan.instagram.mvp.model.entity.Caption;
import com.example.jevodan.instagram.mvp.model.entity.Datum;
import com.example.jevodan.instagram.mvp.model.entity.Images;
import com.example.jevodan.instagram.mvp.model.entity.InstDataSource;
import com.example.jevodan.instagram.mvp.model.entity.StandardResolution;
import com.example.jevodan.instagram.mvp.model.entity.User;
import com.example.jevodan.instagram.mvp.model.entity.room.tables.RoomPhoto;
import com.example.jevodan.instagram.mvp.model.repo.IDataRepo;
import com.example.jevodan.instagram.mvp.presenter.MainFragmentPresenter;
import com.example.jevodan.instagram.mvp.view.MainView;
import com.example.jevodan.instagram.ui.activity.MainActivity;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;
import timber.log.Timber;

public class MainFragmentPresenterTest {

    public static final String URL = "https://scontent.cdninstagram.com/vp/5d1d0928bf0f23c356049a2af5bedb53/5DE82F8A/t51.2885-15/e35/s150x150/66285635_449258429256419_4891166467125841736_n.jpg?_nc_ht=scontent.cdninstagram.com";
    private MainFragmentPresenter presenter;
    private TestScheduler testScheduler;

    /**
     * подделываем вьюшку
     */
    @Mock
    MainView mainView;

    @BeforeClass
    public static void setupClass() {
        Timber.plant(new Timber.DebugTree());
        Timber.d("setup class");
    }

    @AfterClass
    public static void tearDownClass() {
        Timber.d("tearDownClass");
    }

    @Before //выполняется перед каждым тестом
    public void setUp() {
        Timber.d("setup");
        MockitoAnnotations.initMocks(this); //инициализация теста и всех моков
        testScheduler = new TestScheduler();
        presenter = Mockito.spy(new MainFragmentPresenter(testScheduler)); //создаем наследника презентера
    }

    @After //выполняется после каждого теста
    public void TearDown() {
        Timber.d("tearDown");
    }

    @Test
    public void loadData() {
        InstDataSource data = createTestData();

        TestComponent component = DaggerTestComponent
                .builder()
                .testRepoModule(new TestRepoModule() {
                    @Override
                    public IDataRepo dataRepo() {
                        IDataRepo repo = super.dataRepo();
                        Mockito.when(repo.getPhoto(data.getData()
                                .get(0)
                                .getUser()
                                .getId(), true, false)).thenReturn(Single.just(data));
                        return repo;
                    }
                }).build();

        component.inject(this);
        component.inject(presenter);
        presenter.attachView(mainView); //аттачим вьюшку

        // после аттач,в коде вызывается init и лоад дата:
        Mockito.verify(mainView).init();
        Mockito.verify(presenter).loadData(data.getData().get(0).getUser().getId(), true, false);

        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS); //проматываем секунду,чтобы синглы выше отработали

        Mockito.verify(mainView, Mockito.times(1)).showLoading(); //times - сколько раз должен был быть вызван.
        Mockito.verify(mainView).showMessage(data.getData().get(0).getUser());
        Mockito.verify(mainView).updateList();
        Mockito.verify(mainView).hideLoading();

    }

    private InstDataSource createTestData() {
        InstDataSource instDataSource = new InstDataSource();
        List<Datum> listData = new ArrayList<>();

        Datum datum = new Datum();
        User user = new User();
        user.setFullName("Alexander");
        user.setId("15867753227");
        user.setProfilePicture("https://scontent.cdninstagram.com/vp/782184b0ba5207be000b9c605b1879b8/5DE1781B/t51.2885-19/s150x150/65733330_1226139414253257_8636406919826767872_n.jpg?_nc_ht=scontent.cdninstagram.com");
        user.setUsername("jevodan");
        Images images = new Images();
        StandardResolution standardResolution = new StandardResolution();
        Caption caption = new Caption();
        caption.setText("Zilok");
        caption.setFavor(true);
        standardResolution.setUrl(URL);
        images.setStandardResolution(standardResolution);
        datum.setImages(images);
        datum.setCaption(caption);
        datum.setUser(user);
        listData.add(datum);

        instDataSource.setData(listData);
        return instDataSource;
    }

}
