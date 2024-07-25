package com.alice.androidgb28181.camera;

import android.view.View;

import com.alice.androidgb28181.R;
import com.alice.androidgb28181.app.AppActivity;
import com.alice.androidgb28181.bean.PlatformInfo;
import com.alice.androidgb28181.other.MmkvHelper;
import com.hjq.shape.view.ShapeTextView;
import com.hjq.widget.layout.SettingBar;
import com.hjq.widget.view.PasswordEditText;
import com.hjq.widget.view.RegexEditText;
import com.tencent.mmkv.MMKV;

public class GbInfoActivity extends AppActivity {
    private SettingBar mSbGbInfo;
    private RegexEditText mReServerurl;
    private RegexEditText mReServerport;
    private RegexEditText mReDomain;
    private RegexEditText mReServerid;
    private RegexEditText mReUsrename;
    private PasswordEditText mPePassword;
    private RegexEditText mReDeviceid;
    private RegexEditText mReChannelid;
    private RegexEditText mReRegisterTime;
    private RegexEditText mReHeartTime;
    private ShapeTextView mStSave;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gb_info;
    }

    @Override
    protected void initView() {

        mSbGbInfo = (SettingBar) findViewById(R.id.sb_gb_info);
        mReServerurl = (RegexEditText) findViewById(R.id.re_serverurl);
        mReServerport = (RegexEditText) findViewById(R.id.re_serverport);
        mReDomain = (RegexEditText) findViewById(R.id.re_domain);
        mReServerid = (RegexEditText) findViewById(R.id.re_serverid);
        mReUsrename = (RegexEditText) findViewById(R.id.re_usrename);
        mPePassword = (PasswordEditText) findViewById(R.id.pe_password);
        mReDeviceid = (RegexEditText) findViewById(R.id.re_deviceid);
        mReChannelid = (RegexEditText) findViewById(R.id.re_channelid);
        mReRegisterTime = (RegexEditText) findViewById(R.id.re_register_time);
        mReHeartTime = (RegexEditText) findViewById(R.id.re_heart_time);
        mStSave = (ShapeTextView) findViewById(R.id.st_save);

        setOnClickListener(mStSave);
    }

    @Override
    protected void initData() {
        PlatformInfo gb_info = MmkvHelper.getInstance().getObjectClass("GB_INFO", PlatformInfo.class);
        if(gb_info!=null){
            mReServerurl.setText(gb_info.getServerurl());
            mReServerport.setText(gb_info.getServerPort()+"");
            mReDomain.setText(gb_info.getDomain());
            mReServerid.setText(gb_info.getServerId());
            mReUsrename.setText(gb_info.getUserName());
            mPePassword.setText(gb_info.getPassword());
            mReDeviceid.setText(gb_info.getDeviceId());
            mReChannelid.setText(gb_info.getChannelId());
            mReRegisterTime.setText(gb_info.getRegisterInterval()+"");
            mReHeartTime.setText(gb_info.getHeartbeatInterval()+"");
        }

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.st_save:
                save();
                break;
        }
    }

    /**
     * 保存
     */
    private void save() {
        String url = mReServerurl.getText().toString();
        String port = mReServerport.getText().toString();
        String domain = mReDomain.getText().toString();
        String serverid = mReServerid.getText().toString();
        String username = mReUsrename.getText().toString();
        String password = mPePassword.getText().toString();
        String deviceid = mReDeviceid.getText().toString();
        String channelid = mReChannelid.getText().toString();
        String registerTime = mReRegisterTime.getText().toString();
        String heartTime = mReHeartTime.getText().toString();

        PlatformInfo gb_info = new PlatformInfo();
        gb_info.setServerurl(url);
        gb_info.setServerPort(Integer.valueOf(port));
        gb_info.setDomain(domain);
        gb_info.setServerId(serverid);
        gb_info.setUserName(username);
        gb_info.setPassword(password);
        gb_info.setDeviceId(deviceid);
        gb_info.setChannelId(channelid);
        gb_info.setRegisterInterval(Integer.valueOf(registerTime));
        gb_info.setHeartbeatInterval(Integer.valueOf(heartTime));
        MmkvHelper.getInstance().putObject("GB_INFO",gb_info);
        finish();
    }
}
