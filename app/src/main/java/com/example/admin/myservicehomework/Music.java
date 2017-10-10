package com.example.admin.myservicehomework;

/**
 * Created by Admin on 10/10/2017.
 */

public class Music {
    int path;
    String name;

    public Music(int path, String name) {
        this.path = path;
        this.name = name;
    }

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
