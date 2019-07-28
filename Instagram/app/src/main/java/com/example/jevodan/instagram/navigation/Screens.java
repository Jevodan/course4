package com.example.jevodan.instagram.navigation;

import androidx.fragment.app.Fragment;

import com.example.jevodan.instagram.ui.fragment.DetailFragment;
import com.example.jevodan.instagram.ui.fragment.MainFragment;

import ru.terrakok.cicerone.android.support.SupportAppScreen;

/**
 * Класс содержит два скрина/фрагмента
 * при обращении возвращает нужный фрагмент
 */
public class Screens {

    public static class MainScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return MainFragment.newInstance();
        }
    }

    public static class DetailScreen extends SupportAppScreen {

        private String title;
        private String picture;

        public DetailScreen(String picture, String title) {
            this.title = title;
            this.picture = picture;
        }

        @Override
        public Fragment getFragment() {
            return DetailFragment.newInstance(picture, title);
        }
    }
}
