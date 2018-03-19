package com.example.sourabh.androidservicesassignment;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sourabh on 18-Mar-18.
 */

public class FileDownloadService extends Service {

    String status="";

    public String mRetStatus()
    {
        Log.i("tag1","now:"+status);
        return status;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("tag1","In onBind: thread id:"+Thread.currentThread().getId());
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("tag1","In onUnbind: thread id:"+Thread.currentThread().getId());
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Log.i("tag1","In onStartCommand: thread id:"+Thread.currentThread().getId());
        //super.onStartCommand(intent, flags, startId);
        Toast.makeText(this,"Service started...",Toast.LENGTH_SHORT).show();
        List<String> links=new ArrayList<>();
        links.add(intent.getStringExtra("link1"));
        links.add(intent.getStringExtra("link2"));
        links.add(intent.getStringExtra("link3"));
        links.add(intent.getStringExtra("link4"));
        links.add(intent.getStringExtra("link5"));
        Thread th=new Thread(new MyThreadClass(startId,links));
        th.start();

        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"Service stopped...",Toast.LENGTH_SHORT).show();
    }



    //Binder implementation
    class MyServiceBinder extends Binder
    {
        FileDownloadService getService()
        {
            return FileDownloadService.this;
        }
    }

    private IBinder mBinder=new MyServiceBinder();


    //Thread implementation
    final public class MyThreadClass implements Runnable
    {
        int service_id, iDownloadCount=0;
        List<String> links;
        MyThreadClass(int id, List<String> inputlinks)
        {
            service_id=id;
            links=inputlinks;
        }

        @Override
        public void run()
        {
            synchronized (this)
            {
                mActualWork();
                stopSelf(service_id);
            }
        }

        void mReturnStatus()
        {
            status = "Current status: " + iDownloadCount+"/5 downloads complete.";
        }

        private void mActualWork() {
            try {
                /*Log.i("tag1","thread id:"+Thread.currentThread().getId());
                Log.i("tag1","link1:"+links.get(0));
                Log.i("tag1","link1:"+links.get(1));
                Log.i("tag1","link1:"+links.get(2));
                Log.i("tag1","link1:"+links.get(3));
                Log.i("tag1","link1:"+links.get(4));*/
                mDownloadFile1(links.get(0), "link1.pdf");
                iDownloadCount++;
                mReturnStatus();
                wait(3000);
                mDownloadFile1(links.get(1), "link2.pdf");
                iDownloadCount++;
                mReturnStatus();
                wait(3000);
                mDownloadFile1(links.get(2), "link3.pdf");
                iDownloadCount++;
                mReturnStatus();
                wait(3000);
                mDownloadFile1(links.get(3), "link4.pdf");
                iDownloadCount++;
                mReturnStatus();
                wait(3000);
                mDownloadFile1(links.get(4), "link5.pdf");
                iDownloadCount++;
                mReturnStatus();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }

        private int mDownloadFile1(String inputurl, String name)
        {
            try {
                Uri uri=Uri.parse(inputurl);
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); // to notify when download is complete
                request.allowScanningByMediaScanner();// if you want to be available from media players
                DownloadManager manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                assert manager != null;
                manager.enqueue(request);
                return Activity.RESULT_OK;
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                return Activity.RESULT_CANCELED;
            }
        }

        private int mDownloadFile(String inputurl, String name) {
            int result=0;
            File output = new File(getFilesDir(), name);
            if (output.exists()) {
                if(!output.delete())
                {
                    return Activity.RESULT_CANCELED;
                }
            }

            InputStream stream = null;
            FileOutputStream fos = null;
            try {

                URL url = new URL(inputurl);
                stream = url.openConnection().getInputStream();
                //InputStreamReader reader = new InputStreamReader(stream);
                fos = new FileOutputStream(output.getPath());
                int bufferSize = 1024;
                byte[] buffer = new byte[bufferSize];
                int next;
                while ((next = stream.read(buffer)) != -1) {
                    fos.write(buffer,0,next);
                }
                // Successful finished
                result = Activity.RESULT_OK;

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return result;
        }
    }
}
