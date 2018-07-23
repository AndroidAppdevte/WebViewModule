package com.scqkzqtz.webview.web.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.SeekBar;

import com.scqkzqtz.webview.R;
import com.scqkzqtz.webview.databinding.ChangeWebTextWindowBinding;
import com.scqkzqtz.webview.web.model.WebViewConfig;


/**
 * 网页设置字体大小弹框
 * Created by zsx on 2018/7/20.
 */

public class WebSetSizePop extends PopupWindow {
    private ChangeWebTextWindowBinding binding;
    private Context context;
    private Window window = null;
    private WebViewConfig config;

    public WebSetSizePop(Activity context, WebViewConfig config) {
        this.context = context;
        this.config = config;
        this.window = context.getWindow();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = binding.inflate(inflater);

        this.setContentView(binding.getRoot());
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setAnimationStyle(R.style.AnimBottom);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 设置pop弹出窗体的背景
        this.setBackgroundDrawable(dw);
        this.setOutsideTouchable(true);

        initView();
    }

    private void initView() {
        if (config.getShowCollection()) binding.webCollectBtn.setVisibility(View.VISIBLE);
        else binding.webCollectBtn.setVisibility(View.GONE);
        if (config.getFontScaling()) {
            binding.rellaySize.setVisibility(View.VISIBLE);
            binding.view.setVisibility(View.VISIBLE);
        } else {
            binding.rellaySize.setVisibility(View.GONE);
            binding.view.setVisibility(View.GONE);
        }

        binding.webviewSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        binding.webCollectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickButtonListener != null)
                    onClickButtonListener.onClick(0);
            }
        });
        binding.webShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickButtonListener != null)
                    onClickButtonListener.onClick(1);
            }
        });
        binding.webCopyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickButtonListener != null)
                    onClickButtonListener.onClick(2);
            }
        });

        binding.sizeProgress.setOnSeekBarChangeListener(new SeekBarListener());
        binding.sizeProgress.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float TouchPosition = motionEvent.getX() / view.getMeasuredWidth();

                boolean isUp = false;
                if (TouchPosition <= 0.2) {
                    Text_Size = 75;
                } else if (TouchPosition > 0.2 && TouchPosition <= 0.5) {
                    Text_Size = 100;
                } else if (TouchPosition > 0.5 && TouchPosition <= 0.8) {
                    Text_Size = 125;
                } else {
                    Text_Size = 150;
                }
                binding.sizeProgress.setProgress((Text_Size / 25 - 3) * 33);
                if (motionEvent.getAction() == MotionEvent.ACTION_UP)
                    isUp = true;

                if (onClickSizeListener != null)
                    onClickSizeListener.onClick(Text_Size, isUp);

                return false;
            }
        });

        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.alpha = 1f;
                window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                window.setAttributes(lp);
            }
        });
    }

    /**
     * @param view
     * @param theChoice 当前选择的位置
     */
    public void show(View view, int theChoice) {
        Text_Size = theChoice;
        binding.sizeProgress.setProgress((Text_Size / 25 - 3) * 33);

        // 产生背景变暗效果
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 0.8f;
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setAttributes(lp);

        showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 设置收藏状态
     *
     * @param isCollect
     */
    public void setCollectStatus(boolean isCollect) {
        binding.webCollectBtn.setCompoundDrawablesWithIntrinsicBounds(null
                , context.getResources().getDrawable(isCollect ? R.mipmap.web_collect_yes : R.mipmap.web_collect_no), null, null);
    }

    private int Text_Size = 1;

    private class SeekBarListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
            if (progress <= 20) {
                Text_Size = 75;
            } else if (progress > 20 && progress <= 50) {
                Text_Size = 100;
            } else if (progress > 50 && progress <= 80) {
                Text_Size = 125;
            } else {
                Text_Size = 150;
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            binding.sizeProgress.setProgress((Text_Size / 25 - 3) * 33);
        }
    }

    public interface OnClickSizeListener {
        void onClick(int position, boolean isUp);//点击列表监听
    }

    public interface OnClickButtonListener {
        void onClick(int position);//点击收藏/分享/复制链接
    }

    private OnClickButtonListener onClickButtonListener;
    private OnClickSizeListener onClickSizeListener;

    public void setOnClickButtonListener(OnClickButtonListener onClickButtonListener) {
        this.onClickButtonListener = onClickButtonListener;
    }

    public void setOnClickSizeListener(OnClickSizeListener onClickSizeListener) {
        this.onClickSizeListener = onClickSizeListener;
    }
}

