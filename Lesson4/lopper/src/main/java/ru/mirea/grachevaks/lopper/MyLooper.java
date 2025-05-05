package ru.mirea.grachevaks.lopper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.os.Looper;

public class MyLooper extends Thread{
    public Handler mHandler;
    private Handler mainHandler;

    public MyLooper(Handler mainThreadHandler) {
        this.mainHandler = mainThreadHandler;
    }
    public void run() {
        Looper.prepare();
        mHandler = new Handler(Looper.myLooper()) {

            @Override
            public void handleMessage(Message msg) {
                final String ageStr = msg.getData().getString("AGE");
                final String jobStr = msg.getData().getString("JOB");
                mHandler.postDelayed(() -> {
                    String result = String.format("Возраст - %s, Профессия - %s", ageStr, jobStr);

                    Log.d("MyLooper", "Получил: возраст=" + ageStr + ", профессия=" + jobStr);

                    Message message = Message.obtain();
                    Bundle b = new Bundle();
                    b.putString("result", result);
                    message.setData(b);
                    mainHandler.sendMessage(message);
                }, 20000);

            }
        };
        Looper.loop();
    }
}
