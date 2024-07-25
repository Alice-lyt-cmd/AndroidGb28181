package com.alice.androidgb28181;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alice.androidgb28181.aop.Permissions;
import com.alice.androidgb28181.app.AppActivity;
import com.alice.androidgb28181.camera.CameraSettingActivity;
import com.alice.cameralib.library.view.OpenGlView;
import com.hjq.permissions.Permission;

public class MainActivity extends AppActivity {
    String TGA = "MainActivity";
    private OpenGlView mSurfaceView;


    @Override
    public void onRightClick(View view) {
        super.onRightClick(view);
        startActivityForResult(CameraSettingActivity.class, new OnActivityCallback() {
            @Override
            public void onActivityResult(int resultCode, @Nullable Intent data) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mSurfaceView = (OpenGlView) findViewById(R.id.surfaceView);
        mSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
                Log.e(TGA, "surfaceCreated " + (cameraService == null));
                if (cameraService != null) {
                    cameraService.startPreview(mSurfaceView);
                }

            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
                if (cameraService != null) {
                    cameraService.stopPreview();
                }
            }
        });

    }

    @Permissions({Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_EXTERNAL_STORAGE, Permission.CAMERA, Permission.RECORD_AUDIO})
    @Override
    protected void initData() {
        //绑定相机服务
        Intent intent = new Intent(this, CameraService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

    }

    CameraService cameraService;
    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            CameraService.LocalBinder binder = (CameraService.LocalBinder) service;
            cameraService = binder.getService();
            Log.e(TGA, "onServiceConnected" + mSurfaceView.getHolder().isCreating());
            cameraService.startPreview(mSurfaceView);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            cameraService = null;
        }
    };
}