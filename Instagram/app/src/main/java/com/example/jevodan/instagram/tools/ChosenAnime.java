package com.example.jevodan.instagram.tools;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jevodan.instagram.R;

public class ChosenAnime {

    /**
     * Метод анимации сердечек при клике
     *
     * @param heart - Вью сердечка
     * @param favor - скрытый TextView
     *              для синхронизации
     */
    public static void imageAnime(ImageView heart, TextView favor) {

        Animation zoomin = AnimationUtils.loadAnimation(heart.getContext(), R.anim.zoomin);
        Animation zoomout = AnimationUtils.loadAnimation(heart.getContext(), R.anim.zoomout);
        heart.setAnimation(zoomin);
        heart.setAnimation(zoomout);
        heart.startAnimation(zoomin);

        if (favor.getText().equals("1") || favor.getText().equals("true")) {
            favor.setText("0");
            heart.setImageResource(R.drawable.ic_favorite_border_red_500_18dp);

        } else {
            favor.setText("1");
            heart.setImageResource(R.drawable.ic_favorite_red_500_18dp);
        }

        heart.startAnimation(zoomout);
    }
}
