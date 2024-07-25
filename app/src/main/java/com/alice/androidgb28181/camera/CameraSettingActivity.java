package com.alice.androidgb28181.camera;

import android.view.View;

import com.alice.androidgb28181.R;
import com.alice.androidgb28181.app.AppActivity;
import com.hjq.widget.layout.SettingBar;

public class CameraSettingActivity extends AppActivity {
    private SettingBar mSbOsd;
    private SettingBar mSbGbInfo;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_camera_setting;
    }

    @Override
    protected void initView() {

        mSbOsd = (SettingBar) findViewById(R.id.sb_osd);
        mSbGbInfo = (SettingBar) findViewById(R.id.sb_gb_info);
        setOnClickListener(mSbGbInfo, mSbOsd);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.sb_osd:
                break;
            case R.id.sb_gb_info:
                startActivity(GbInfoActivity.class);
                break;
        }
    }

}
