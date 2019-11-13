package com.example.groupproject;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;

import java.lang.reflect.Field;

public class BaseActivity extends AppCompatActivity implements SlidingPaneLayout.PanelSlideListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initSlideBackClose();//滑动返回的设置
        super.onCreate(savedInstanceState);
    }

    private void initSlideBackClose() {
        if (isSupportSwipeBack()) {
            SlidingPaneLayout slidingPaneLayout = new SlidingPaneLayout(this);
            // 通过反射改变mOverhangSize的值为0，
            // 这个mOverhangSize值为菜单到右边屏幕的最短距离，
            // 默认是32dp，现在给它改成0
            try {
                Field overhangSize = SlidingPaneLayout.class.getDeclaredField("mOverhangSize");
                overhangSize.setAccessible(true);
                overhangSize.set(slidingPaneLayout, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            slidingPaneLayout.setPanelSlideListener(this);
            slidingPaneLayout.setSliderFadeColor(getResources()
                    .getColor(android.R.color.transparent));
            // 左侧的透明视图
            View leftView = new View(this);
            leftView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            slidingPaneLayout.addView(leftView, 0);
            ViewGroup decorView = (ViewGroup) getWindow().getDecorView();
            // 右侧的内容视图
            ViewGroup decorChild = (ViewGroup) decorView.getChildAt(0);
            decorChild.setBackgroundColor(getResources()
                    .getColor(android.R.color.white));
            decorView.removeView(decorChild);
            decorView.addView(slidingPaneLayout);
            // 为 SlidingPaneLayout 添加内容视图
            slidingPaneLayout.addView(decorChild, 1);
        }
    }

    //设置是否使用滑动返回
    protected boolean isSupportSwipeBack() {
        return true;
    }

    @Override
    public void onPanelSlide(View panel, float slideOffset) {
    }

    @Override
    public void onPanelOpened(View panel) {
        finish();
    }

    @Override
    public void onPanelClosed(View panel) {
    }

}