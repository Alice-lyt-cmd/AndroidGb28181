package com.alice.androidgb28181.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.alice.androidgb28181.R;
import com.alice.androidgb28181.app.AppAdapter;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2020/08/28
 *    desc   : 引导页适配器
 */
public final class GuideAdapter extends AppAdapter<Integer> {

    public GuideAdapter(Context context) {
        super(context);
        addItem(R.drawable.guide_1_bg);
        addItem(R.drawable.guide_2_bg);
        addItem(R.drawable.guide_3_bg);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder();
    }

    private final class ViewHolder extends AppAdapter<?>.ViewHolder {

        private final ImageView mImageView;

        private ViewHolder() {
            super(R.layout.guide_item);
            mImageView = (ImageView) getItemView();
        }

        @Override
        public void onBindView(int position) {
            mImageView.setImageResource(getItem(position));
        }
    }
}