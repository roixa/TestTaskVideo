package com.roix.testtaskvideo;

import android.util.Log;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by roix on 18.11.2016.
 */

@Root(name = "Object",strict = false)
public class Item {
    @Attribute
    private String id;

    @Attribute
    private String name;

    @Attribute
    private String author;

    @Attribute
    private String path;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Attribute
    private String version;

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        Log.d("@@@","setPath"+path);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }




}
