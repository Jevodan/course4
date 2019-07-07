package ru.geekbrains.gb_android_libraries.mvp.model.entity.realm;

import com.google.gson.annotations.Expose;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class RealmRepository extends RealmObject {

    @PrimaryKey
    private String id;
    private String name;

    public RealmRepository() {
      }

    public RealmRepository(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
