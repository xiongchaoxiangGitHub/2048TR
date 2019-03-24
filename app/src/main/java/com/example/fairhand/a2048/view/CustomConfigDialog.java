package com.example.fairhand.a2048.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fairhand.a2048.R;
import com.example.fairhand.a2048.app.Config;
import com.example.fairhand.a2048.util.SaveConfigUtil;

/**
 * @author FairHand
 * @date 2018/10/4
 * 自定义配置对话框
 */
public class CustomConfigDialog extends BaseDialog {
    
    private View.OnClickListener onPositiveClickListener;
    private View.OnClickListener onNegativeClickListener;
    private View.OnClickListener onCheckUpdateClickListener;
    
    /**
     * 游戏难度
     */
    private int difficulty = Config.GIRDColumnCount;
    
    /**
     * 游戏音效状态
     */
    private boolean volumeState = Config.VolumeState;
    
    public CustomConfigDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }
    
    @Override
    protected int setView() {
        return R.layout.dialog_custom_config;
    }
    
    @Override
    protected void initData() {
        init();
    }
    
    private void init() {
        Button cancel = findViewById(R.id.btn_return);
        Button confirm = findViewById(R.id.btn_confirm);
        Button difficulty4 = findViewById(R.id.btn_difficulty_4);
        Button difficulty5 = findViewById(R.id.btn_difficulty_5);
        Button difficulty6 = findViewById(R.id.btn_difficulty_6);
        Button volumeON = findViewById(R.id.btn_volume_on);
        Button volumeOFF = findViewById(R.id.btn_volume_off);
        TextView contactMe = findViewById(R.id.tv_send_advice);
        TextView getGoalTime = findViewById(R.id.tv_goal_get_time);
        Button update = findViewById(R.id.btn_update);
        // 根据游戏难度选中按钮
        switch (Config.GIRDColumnCount) {
            case 4:
                difficulty4.setBackgroundResource(R.drawable.bg_button_select);
                difficulty = 4;
                break;
            case 5:
                difficulty5.setBackgroundResource(R.drawable.bg_button_select);
                difficulty = 5;
                break;
            case 6:
                difficulty6.setBackgroundResource(R.drawable.bg_button_select);
                difficulty = 6;
                break;
            default:
                break;
        }
        // 根据配置参数选中按钮
        if (Config.VolumeState) {
            volumeON.setBackgroundResource(R.drawable.bg_button_select);
        } else {
            volumeOFF.setBackgroundResource(R.drawable.bg_button_select);
        }
        
        if (onNegativeClickListener != null) {
            cancel.setOnClickListener(onNegativeClickListener);
        }
        if (onPositiveClickListener != null) {
            confirm.setOnClickListener(onPositiveClickListener);
        }
        if (onCheckUpdateClickListener != null) {
            update.setOnClickListener(onCheckUpdateClickListener);
        }
        
        difficulty4.setOnClickListener(v -> {
            difficulty = 4;
            difficulty4.setBackgroundResource(R.drawable.bg_button_select);
            difficulty5.setBackgroundResource(R.drawable.bg_button);
            difficulty6.setBackgroundResource(R.drawable.bg_button);
        });
        difficulty5.setOnClickListener(v -> {
            difficulty = 5;
            difficulty4.setBackgroundResource(R.drawable.bg_button);
            difficulty5.setBackgroundResource(R.drawable.bg_button_select);
            difficulty6.setBackgroundResource(R.drawable.bg_button);
        });
        difficulty6.setOnClickListener(v -> {
            difficulty = 6;
            difficulty4.setBackgroundResource(R.drawable.bg_button);
            difficulty5.setBackgroundResource(R.drawable.bg_button);
            difficulty6.setBackgroundResource(R.drawable.bg_button_select);
        });
        volumeON.setOnClickListener(v -> {
            volumeState = true;
            volumeON.setBackgroundResource(R.drawable.bg_button_select);
            volumeOFF.setBackgroundResource(R.drawable.bg_button);
            SaveConfigUtil.putGameVolume(getContext(), true);
        });
        volumeOFF.setOnClickListener(v -> {
            volumeState = false;
            volumeON.setBackgroundResource(R.drawable.bg_button);
            volumeOFF.setBackgroundResource(R.drawable.bg_button_select);
            SaveConfigUtil.putGameVolume(getContext(), false);
        });
        contactMe.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://github.com/kylechandev/2048TR");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            getContext().startActivity(intent);
        });
        getGoalTime.setText(Config.GetGoalTime == 0 ? "暂未达成" : String.valueOf(Config.GetGoalTime));
        // 无限模式下游戏难度不可设置
        if (Config.CurrentGameMode == 1) {
            difficulty4.setBackgroundResource(R.drawable.bg_button_useless);
            difficulty5.setBackgroundResource(R.drawable.bg_button_useless);
            difficulty6.setBackgroundResource(R.drawable.bg_button_useless);
            difficulty4.setEnabled(false);
            difficulty5.setEnabled(false);
            difficulty6.setEnabled(false);
        }
    }
    
    /**
     * 确认按钮点击
     */
    public CustomConfigDialog setOnPositiveClickListener(
            View.OnClickListener onPositiveClickListener) {
        this.onPositiveClickListener = onPositiveClickListener;
        return this;
    }
    
    /**
     * 取消按钮点击
     */
    public CustomConfigDialog setOnNegativeClickListener(
            View.OnClickListener onNegativeClickListener) {
        this.onNegativeClickListener = onNegativeClickListener;
        return this;
    }
    
    /**
     * 更新按钮点击
     */
    public CustomConfigDialog setOnCheckUpdateClickListener(
            View.OnClickListener onCheckUpdateClickListener) {
        this.onCheckUpdateClickListener = onCheckUpdateClickListener;
        return this;
    }
    
    /**
     * 获取选择的游戏难度
     */
    public int getSelectDifficulty() {
        return difficulty;
    }
    
    /**
     * 获取游戏音效状态
     */
    public boolean getVolumeState() {
        return volumeState;
    }
    
}
