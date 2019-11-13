package com.example.groupproject;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class MainAdapter extends BaseSectionQuickAdapter<Project, BaseViewHolder> {
    public int number;


    public MainAdapter(int layoutResId, int sectionHeadResId, List<Project> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, Project item) {
        number = helper.getLayoutPosition();

        if (getData().size() - 1 == number && item.header.equals("已完成")) {
            helper.getView(R.id.mainItem_linear_head).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.mainItem_linear_head).setVisibility(View.VISIBLE);
            helper.setText(R.id.mainItem_head, item.header);

        }
        if (item.header.equals("未完成")) {
            helper.getView(R.id.mainItem_linear_head).setVisibility(View.GONE);
        }

       helper.setImageResource(R.id.mainItem_arrow_head,item.getHeadImgId());

    }


    @Override
    protected void convert(@NonNull BaseViewHolder helper, Project item) {
        Project.projectItem t = item.t;
        helper.setText(R.id.mainItem_txt_title, t.getTitle()).addOnClickListener(R.id.mainItem_img);
        helper.setImageResource(R.id.mainItem_img, t.getImgId());
        if (t.getSelect()) {
            helper.getView(R.id.mainItem_linear).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.mainItem_linear).setVisibility(View.GONE);
        }
    }
}
