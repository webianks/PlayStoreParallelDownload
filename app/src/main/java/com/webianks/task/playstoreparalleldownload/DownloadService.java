package com.webianks.task.playstoreparalleldownload;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by R Ankit on 02-04-2017.
 */

public class DownloadService extends Service {

    private String TAG = DownloadService.class.getSimpleName();

    @Override
    public void onCreate() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String url = intent.getStringExtra("url");
        new DownloadingTask().execute(url);

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }


    class DownloadingTask extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... urls) {

            Log.d(TAG, "onPreExecute: "+urls[0]);

            try {
                run(urls[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            stopSelf();
        }

    }

    public void run(String url) throws Exception {

        int count;

        try {

            String root = Environment.getExternalStorageDirectory().toString();
            System.out.println("Downloading");
            URL urlFormed = new URL(url);

            URLConnection conection = urlFormed.openConnection();
            conection.connect();

            // getting file length
            int lenghtOfFile = conection.getContentLength();

            // input stream to read file - with 8k buffer
            InputStream input = new BufferedInputStream(urlFormed.openStream(), 8192);

            // Output stream to write file

            //OutputStream output = new FileOutputStream(root+"temp.apk");
            byte data[] = new byte[1024];

            long total = 0;
            while ((count = input.read(data)) != -1) {
                total += count;

                // writing data to file
                //output.write(data, 0, count);
                System.out.format("%d%% done\n", (100 * total) / lenghtOfFile);

            }

            // flushing output
            //output.flush();

            // closing streams
           //output.close();
            input.close();


        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }
    }


}
