package com.example.sourabh.mysecondapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.support.v4.view.GestureDetectorCompat;

public class EventHandlingActivity extends AppCompatActivity
        implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener{

    private static final int swipe_distance_threshold = 100;
    private static final int swipe_velocity_threshold = 100;

    private TextView msg;
    private GestureDetectorCompat gdc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_handling);

        msg = findViewById(R.id.textIDEvent);
        this.gdc = new GestureDetectorCompat(this,this);
        gdc.setOnDoubleTapListener(this);

        Button btn = findViewById(R.id.btnIDEvent);
        btn.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v)
                    {
                        TextView tv = findViewById(R.id.textIDEvent);
                        tv.setText("hi");
                    }
                }
        );

        btn.setOnLongClickListener(
                new Button.OnLongClickListener(){
                    public boolean onLongClick(View v)
                    {
                        TextView tv = findViewById(R.id.textIDEvent);
                        tv.setText("hi long");
                        return true;
                    }
                }
        );
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        msg.setText("onSingleTapConfirmed");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        msg.setText("onDoubleTap");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        msg.setText("onDoubleTapEvent");
        return true;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        msg.setText("onDown");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        msg.setText("onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        msg.setText("onSingleTapUp");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        msg.setText("onScroll");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        msg.setText("onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        float disX=motionEvent1.getX()-motionEvent.getX();
        float disY=motionEvent1.getY()-motionEvent.getY();
        if(Math.abs(disX)>Math.abs(disY) && Math.abs(disX)>swipe_distance_threshold && Math.abs(v)>swipe_velocity_threshold)
        {
            if(disX>0)
                onSwipeRight();
            else
                onSwipeLeft();
        }
        else
            msg.setText("onFling");
        return true;
    }

    private void onSwipeRight() {
        msg.setText("onSwipeRight");
    }

    private void onSwipeLeft() {
        msg.setText("onSwipeLeft");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gdc.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

}
