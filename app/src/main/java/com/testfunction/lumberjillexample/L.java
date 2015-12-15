package com.testfunction.lumberjillexample;

import android.content.res.Resources;
import android.view.View;

import com.testfunction.lumberjill.LumberJill;
import com.testfunction.lumberjill.LumberJillCallbacks;

/**
 * Created by lodlock (iamhunted@gmail.com)
 * On 12/15/2015 11:14 AM
 * For the LumberJillExample (com.testfunction.lumberjillexample) project
 */
public class L extends LumberJill implements LumberJillCallbacks {

    private final Resources res;

    public L(Resources res) {
        this.res = res;
    }

    @Override
    public boolean isDebugMode() {
        return BuildConfig.DEBUG;
    }

    @Override
    public int getDefaultDepth() {
        return 5;
    }

    @Override
    public String objectToString(Object object) {
        String message = null;
        if (object instanceof View) {
            message = "View :"+res.getResourceEntryName(((View)object).getId());
        }
        return message;
    }
}
