package com.testfunction.lumberjillexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        L.v(L.initTrace());
        L.d("from LumberJill init calling setUpView()");
        setUpView();
    }

    private void setUpView() {
        Button mButtonSendVerbose   = (Button) findViewById(R.id.button_send_verbose);
        Button mButtonSendDebug     = (Button) findViewById(R.id.button_send_debug);
        Button mButtonSendInfo      = (Button) findViewById(R.id.button_send_info);
        Button mButtonSendWarn      = (Button) findViewById(R.id.button_send_warn);
        Button mButtonSendError     = (Button) findViewById(R.id.button_send_error);
        Button mButtonSendWtf       = (Button) findViewById(R.id.button_send_wtf);
        Button mButtonSendException = (Button) findViewById(R.id.button_send_exception);

        mButtonSendVerbose.setOnClickListener(this);
        mButtonSendDebug.setOnClickListener(this);
        mButtonSendInfo.setOnClickListener(this);
        mButtonSendWarn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("Warn", "Standard warn log");
                L.w("from LumberJill sent from warn via View.OnClickListener and not the main implemented OnClickListener");
            }
        });
        mButtonSendError.setOnClickListener(this);
        mButtonSendWtf.setOnClickListener(this);
        mButtonSendException.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == null) {
            L.e("from LumberJill View is null from onClick. Returning.");
            return;
        }

        switch(v.getId()) {
            case R.id.button_send_verbose:
                L.v(new String[]{"from LumberJill sent from verbose", "element two"});
                break;
            case R.id.button_send_debug:
                sendDebug();
                break;
            case R.id.button_send_info:
                L.i(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
                break;
            case R.id.button_send_error:
                L.e(new int[][]{new int[]{0,1,2}, new int[]{3,4,5}});
                break;
            case R.id.button_send_wtf:
                L.wtf(v);
                break;
            case R.id.button_send_exception:
                try {
                    throw new RuntimeException("exception thrown by button");
                } catch (RuntimeException e) {
                    L.e("from LumberJill : "+ L.getException(e));
                }
                break;
            default:
                L.d("from LumberJill log call not sent from one of the designated buttons but was sent by:" + v);
        }
    }

    private void sendDebug() {
        L.d("from LumberJill sent from debug via sendDebug and not onClick");
        L.d(L.initTrace());
    }

}