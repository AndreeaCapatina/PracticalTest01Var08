package ro.pub.cs.system.eim.practivaltest01var08;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PracticalTest01Var08Service extends Service {
    public PracticalTest01Var08Service() {
    }


    private ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int firstNumber = intent.getIntExtra("firstNumber", -1);
        int secondNumber = intent.getIntExtra("secondNumber", -1);
        processingThread = new ProcessingThread(this, Constant.PRAG);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }
}
