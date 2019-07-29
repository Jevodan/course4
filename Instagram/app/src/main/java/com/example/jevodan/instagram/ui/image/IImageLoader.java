package com.example.jevodan.instagram.ui.image;


public interface IImageLoader<T> {
    void loadInto(String url, T container);
}
