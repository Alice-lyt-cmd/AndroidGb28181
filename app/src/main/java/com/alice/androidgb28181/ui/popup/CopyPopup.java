package com.alice.androidgb28181.ui.popup;

import android.content.Context;

import com.hjq.base.BasePopupWindow;
import com.alice.androidgb28181.R;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2019/10/18
 *    desc   : 可进行拷贝的副本
 */
public final class CopyPopup {

    public static final class Builder
            extends BasePopupWindow.Builder<Builder> {

        public Builder(Context context) {
            super(context);

            setContentView(R.layout.copy_popup);
        }
    }
}