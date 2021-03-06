package com.roix.testtaskvideo;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView, MediaPlayer.OnCompletionListener {

    private Presenter presenter;
    private RecyclerView recyclerView;
    private FilesArrayAdapter adapter;
    private TextView status;
    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        status=(TextView)findViewById(R.id.textView);
        videoView=(VideoView) findViewById(R.id.videoView);
        videoView.setOnCompletionListener(this);
        videoView.setMediaController(new MediaController(this));

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new FilesArrayAdapter(this);
        recyclerView.setAdapter(adapter);
        presenter=new Presenter(this,getCacheDir());

    }



    @Override
    public void showContent(Item video, Item audio) {

        // videoView.setVideoURI(Uri.parse(videoSource));
        Log.d("MainActivity","showContent"+video.getPath());
        videoView.setVideoPath(getCacheDir()+"/"+video.getPath());


        videoView.requestFocus();
        videoView.start();
    }

    @Override
    public void showList(List<Item> list, int downloadedPosition) {
        String stat="downloaded "+ (downloadedPosition)+" from "+list.size();
        status.setText(stat);
        adapter.setData(list,downloadedPosition);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        presenter.onMediaComplit();
    }
}
