package com.example.sourabh.accelerometerasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    AsyncTaskExec obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGen=findViewById(R.id.btnGenerate);
        btnGen.setOnClickListener(this);
        Button btnCan=findViewById(R.id.btnCancel);
        btnCan.setOnClickListener(this);

        TextView tv =findViewById(R.id.tvLog);
        tv.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btnGenerate:
                obj=new AsyncTaskExec(this);
                EditText et=findViewById(R.id.etCount);
                int count= Integer.parseInt(et.getText().toString());
                obj.execute(count);
                break;
            case R.id.btnCancel:
                if(obj!=null) {
                    obj.cancel(true);
                    TextView tv =findViewById(R.id.tvXValue);
                    tv.setText("");
                    tv =findViewById(R.id.tvYValue);
                    tv.setText("");
                    tv =findViewById(R.id.tvZValue);
                    tv.setText("");
                    tv=findViewById(R.id.tvLog);
                    tv.setText(R.string.cancelled);
                }
                break;
        }
    }

    private static class AsyncTaskExec extends AsyncTask<Integer, String, String>
    {
        double xval=0,yval=0,zval=0;
        StringBuilder logv=new StringBuilder();

        private WeakReference<MainActivity> activityReference;

        // only retain a weak reference to the activity
        AsyncTaskExec(MainActivity context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected void onPreExecute()
        {
            MainActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;
            if(!isCancelled()) {
                TextView tv = activity.findViewById(R.id.tvLog);
                tv.setText("");
            }
        }

        @Override
        protected void onPostExecute(String s) {
            MainActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;
            if(!isCancelled()) {
                TextView tv = activity.findViewById(R.id.tvLog);
                tv.setText(logv.toString());
            }
        }

        @Override
        protected String doInBackground(Integer... params) {
            try{
                for(int i=0;i<params[0];i++) {
                    if(isCancelled())
                    {
                        break;
                    }
                    else{
                        Thread.sleep(1000);
                        Random r = new Random();
                        xval = Math.round(-999 + (999 - (-999)) * r.nextDouble());
                        yval = Math.round(-999 + (999 - (-999)) * r.nextDouble());
                        zval = Math.round(-999 + (999 - (-999)) * r.nextDouble());
                        publishProgress("");
                        logv.append("Simulation Count: ");
                        logv.append(i);
                        logv.append(System.getProperty("line.separator"));
                        String xx="X: "+xval+",Y: "+yval+",Z: "+zval+System.getProperty("line.separator");
                        logv.append(xx);
                    }
                }
            }
            catch(Exception e)
            {
                Log.e("",e.getMessage());
            }
            return "";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            MainActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;

            TextView tv =activity.findViewById(R.id.tvXValue);
            tv.setText(String.valueOf(xval));
            tv =activity.findViewById(R.id.tvYValue);
            tv.setText(String.valueOf(yval));
            tv =activity.findViewById(R.id.tvZValue);
            tv.setText(String.valueOf(zval));
        }
    }
}


