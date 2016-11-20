package com.roix.testtaskvideo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    private Presenter presenter;
    private RecyclerView recyclerView;
    private FilesArrayAdapter adapter;
    private TextView status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        status=(TextView)findViewById(R.id.textView);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new FilesArrayAdapter(this);
        recyclerView.setAdapter(adapter);
        presenter=new Presenter(this,getCacheDir());

    }



    @Override
    public void showContent(Item video, Item audio) {

    }

    @Override
    public void showList(List<Item> list, int downloadedPosition) {
        String stat="downloaded "+ (downloadedPosition)+" from "+list.size();
        status.setText(stat);
        adapter.setData(list,downloadedPosition);
    }
}
