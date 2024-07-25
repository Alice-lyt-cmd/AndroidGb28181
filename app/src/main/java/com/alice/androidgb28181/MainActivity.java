package com.alice.androidgb28181;
import com.alice.androidgb28181.aop.Permissions;
import com.alice.androidgb28181.app.AppActivity;
import com.hjq.permissions.Permission;

public class MainActivity extends AppActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }
    @Permissions({Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_EXTERNAL_STORAGE, Permission.CAMERA, Permission.RECORD_AUDIO})
    @Override
    protected void initData() {

    }
}