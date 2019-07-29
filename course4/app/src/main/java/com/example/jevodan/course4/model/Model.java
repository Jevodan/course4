package com.example.jevodan.course4.model;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class Model {

    List<Integer> counters;

    public Model(){
        counters = new ArrayList<>();
        counters.add(0);
        counters.add(0);
        counters.add(0);
    }

    public Observable<Integer> getAt(int pos){
        return Observable
                .fromCallable(() -> counters.get(pos))
                .subscribeOn(Schedulers.io());
    }

    public void setAt(int pos, int value){
        counters.set(pos, value);
    }

}
