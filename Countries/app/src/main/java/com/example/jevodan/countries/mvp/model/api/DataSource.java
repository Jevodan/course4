package com.example.jevodan.countries.mvp.model.api;

import com.example.jevodan.countries.mvp.model.entity.Country;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class DataSource {

    private List<Country> countries;

    public DataSource() {
        countries = new ArrayList<>();
        countries.add(new Country("Russia", "RU"));
        countries.add(new Country("America", "AM"));
        countries.add(new Country("China", "CH"));
        countries.add(new Country("France", "FR"));
        countries.add(new Country("England", "EN"));
        countries.add(new Country("Germany", "GE"));
        countries.add(new Country("Italy", "IT"));
    }

    public List<Country> loadCountries() {
        // Эмуляция сети
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Timber.e(e);
        }
        return countries;
    }
}
