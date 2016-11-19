package com.roix.testtaskvideo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements MainView {

    private Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter=new Presenter(this,getCacheDir());

    }

    @Override
    public void showContent(Item video, Item audio) {

    }
}
