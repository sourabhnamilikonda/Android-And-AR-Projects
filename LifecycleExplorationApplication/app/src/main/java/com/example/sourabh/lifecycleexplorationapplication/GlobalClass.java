package com.example.sourabh.lifecycleexplorationapplication;

import android.app.Application;

/**
 * Created by sourabh on 13-Feb-18.
 */

public class GlobalClass extends Application {
    private Integer iThreadCount=0;

    public Integer getThreadCount()
    {
        return iThreadCount;
    }

    public void setThreadCount(Integer cnt)
    {
        iThreadCount = cnt;
    }
}
