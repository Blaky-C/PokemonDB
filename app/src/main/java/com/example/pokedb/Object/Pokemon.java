package com.example.pokedb.Object;

/**
 * Created by Jack on 2017/10/20.
 */

public class Pokemon {

    String id;
    String name;
    int imageId;

    public Pokemon() {
    }

    public Pokemon(String id, String name, int imageId) {
        this.id = id;
        this.name = name;
        this.imageId = imageId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
