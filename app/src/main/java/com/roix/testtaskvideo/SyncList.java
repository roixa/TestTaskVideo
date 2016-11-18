package com.roix.testtaskvideo;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by roix on 18.11.2016.
 */
@Root (strict = false)
public class SyncList {

    @ElementList(name= "SyncList",inline=true)
    private List<Item> list;

    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }
}
