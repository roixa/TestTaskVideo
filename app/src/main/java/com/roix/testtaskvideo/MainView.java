package com.roix.testtaskvideo;

import java.util.List;

/**
 * Created by roix on 19.11.2016.
 */

public interface MainView {
    void showContent(Item video,Item audio);
    void showList(List<Item> list,int downloadedPosition);
}
