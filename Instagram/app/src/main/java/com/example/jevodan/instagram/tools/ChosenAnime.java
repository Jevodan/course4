package com.example.jevodan.instagram.tools;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.jevodan.instagram.R;

public class ChosenAnime {

    public static void imageAnime(ImageView heart){
        Animation zoomin = AnimationUtils.loadAnimation(heart.getContext(), R.anim.zoomin);
        Animation zoomout = AnimationUtils.loadAnimation(heart.getContext(), R.anim.zoomout);
        heart.setAnimation(zoomin);
        heart.setAnimation(zoomout);
        heart.startAnimation(zoomin);
        heart.setImageResource(R.drawable.ic_favorite_red_500_18dp);
        heart.startAnimation(zoomout);
    }
}
