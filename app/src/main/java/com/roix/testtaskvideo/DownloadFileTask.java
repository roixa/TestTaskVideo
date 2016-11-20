package com.roix.testtaskvideo;

import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Deque;

import okhttp3.ResponseBody;

/**
 * Created by roix on 19.11.2016.
 */

public class DownloadFileTask extends AsyncTask<Item,Integer,Item>{

    private File cacheDir;
    private ResponseBody responseBody;
    private DownloadCallback callback;

    public DownloadFileTask(File cachedir,DownloadCallback callback,ResponseBody responseBody){
        this.cacheDir=cachedir;
        this.responseBody=responseBody;
        this.callback=callback;
    }


    @Override
    protected Item doInBackground(Item... params) {
        Item item=params[0];
        writeResponseBodyToDisk(responseBody,Constants.clip,item.getPath());
        return item;
    }


    private boolean writeResponseBodyToDisk(ResponseBody body,String type,String path) {
        try {
            //File futureStudioIconFile = new File( type+File.separator + path);
            Log.d("@@@","path" +cacheDir.getPath()+File.separator+type+File.separator+ path);
            File futureStudioIconFile = new File( cacheDir,path);
            if (futureStudioIconFile.exists()) return true;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[4096];
                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;
                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                    Log.d("@@@", "file download: " + fileSizeDownloaded + " of " + fileSize);
                }
                outputStream.flush();
                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    protected void onPostExecute(Item item) {
        super.onPostExecute(item);
        callback.onLoadResult(item);

    }

    public  interface DownloadCallback{
        void onLoadResult(Item item);
    }

}
