package com.webianks.task.playstoreparalleldownload;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

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

        String url = " ";
        int position = -1;

        if (intent != null) {
            url = intent.getStringExtra("url");
            position = intent.getIntExtra("position", -1);
        }

        String[] params = new String[]{url, String.valueOf(position)};
        new DownloadingTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);

        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service done", Toast.LENGTH_SHORT).show();
    }


    class DownloadingTask extends AsyncTask<String, Long, Void> {

        @Override
        protected Void doInBackground(String... urls) {

            Log.d(TAG, "doInBackground: " + urls[0]);

            String url = urls[0];
            int position = Integer.valueOf(urls[1]);

            int count;
            try {

               // String root = Environment.getExternalStorageDirectory().toString();

                System.out.println("Downloading: " + url);
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
                long download_percentage_old = 00;
                while ((count = input.read(data)) != -1) {
                    total += count;

                    // writing data to file
                    //output.write(data, 0, count);
                    long download_percentage_new = (100 * total) / lenghtOfFile;
                    if (download_percentage_old != download_percentage_new) {
                        download_percentage_old = download_percentage_new;
                        Long values[] = new Long[]{download_percentage_old, Long.valueOf(position)};
                        publishProgress(values);
                    }
                }
                // flushing output
                //output.flush();

                // closing streams
                //output.close();
                input.close();
            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Long... values) {
            super.onProgressUpdate(values);

            Intent intent = new Intent("download_progress");
            intent.putExtra("percentage", values[0]);
            intent.putExtra("position", values[1]);
            LocalBroadcastManager.getInstance(DownloadService.this).sendBroadcast(intent);

        }

        @Override
        protected void onPostExecute(Void result) {
            Log.d(TAG, "onPostExecute");
            stopSelf();
        }
    }

}
