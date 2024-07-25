package com.alice.androidgb28181;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.alice.cameralib.library.CameraHelp;
import com.alice.cameralib.library.view.OpenGlView;
import com.hjq.http.lifecycle.LifecycleService;

/**
 * camera 控制类
 */
public class CameraService extends LifecycleService {
    String TAG = "CameraService";
    CameraHelp cameraHelp;
    private IBinder binder = new LocalBinder();

    public class LocalBinder extends Binder {
        public CameraService getService() {
            return CameraService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void startPreview(OpenGlView openGlView){
        Log.e(TAG,"startPreview");
        cameraHelp = new CameraHelp(openGlView);
        cameraHelp.startPreview();
    }

    public void stopPreview(){
        cameraHelp.stopPreview();
    }
}
