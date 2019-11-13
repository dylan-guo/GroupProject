package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    private List<Project> projectList;
    public boolean isOpen;
    private int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBar();
        initData();
        listView();
    }

    private void listView() {
        recyclerView = findViewById(R.id.main_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainAdapter = new MainAdapter(R.layout.item_main_context, R.layout.item_main_head, projectList);
        recyclerView.setAdapter(mainAdapter);
        itemClick();

    }

    private void itemClick() {

        mainAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (!projectList.get(position).isHeader) {
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(intent);
                } else {
                    if (isOpen) {
                        //展开
                        for (int i = 0; i < projectList.size(); i++) {
                            if (!projectList.get(i).isHeader) {
                                if (projectList.get(i).t.getLink().equals("已完成")) {
                                    projectList.set(i, new Project(new Project.projectItem(projectList.get(i).t.getTitle(), true, projectList.get(i).t.getLink(), projectList.get(i).t.getImgId())));
                                }
                            } else {
                                projectList.get(i).setHeadImgId(R.drawable.bottom);
                            }
                        }
                        mainAdapter.setNewData(projectList);
                        isOpen = false;
                    } else {
                        //折叠
                        for (int i = 0; i < projectList.size(); i++) {
                            if (!projectList.get(i).isHeader) {
                                if (projectList.get(i).t.getLink().equals("已完成")) {
                                    projectList.set(i, new Project(new Project.projectItem(projectList.get(i).t.getTitle(), false, projectList.get(i).t.getLink(), projectList.get(i).t.getImgId())));
                                }
                            } else {
                                projectList.get(i).setHeadImgId(R.drawable.right);
                            }
                        }
                        mainAdapter.setNewData(projectList);
                        isOpen = true;
                    }

                    mainAdapter.notifyDataSetChanged();
//                    for (int i = 1; i < projectList.size(); i++) {
//
//                      if(!projectList.get(i).isHeader){
//                          Log.i(""+projectList.get(i).t.getLink(),projectList.get(i).t.getLink()+"");
//                          if (projectList.get(i).t.getLink().equals("已完成")){
//
//                              goneList=new ArrayList<>();
//                              String title=projectList.get(i).t.getTitle();
//                              goneList.add(new Project(new Project.projectItem(title, true, "已完成", R.drawable.select)));
//                              projectList.remove(i);
//                              mainAdapter.notifyDataSetChanged();
//                          }
//
//                      }
//
//                    }
                }
            }
        });

        mainAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                String type = projectList.get(position).t.getLink();
                String title = projectList.get(position).t.getTitle();
                if (view.getId() == R.id.mainItem_img) {
                    if (type.equals("未完成")) {
                        if (!isOpen) {
                            projectList.remove(position);
                            projectList.add(mainAdapter.number, new Project(new Project.projectItem(title, true, "已完成", R.drawable.select)));
                        } else {
                            projectList.remove(position);
                            projectList.add(mainAdapter.number, new Project(new Project.projectItem(title, false, "已完成", R.drawable.select)));

                        }

                    } else if (type.equals("已完成")) {
                        projectList.remove(position);
                        projectList.add(1, new Project(new Project.projectItem(title, true, "未完成", R.drawable.noselect)));

                    }
                }
                for (int i = 1; i < projectList.size(); i++) {
                    if (!projectList.get(i).isHeader) {
                        Log.i("" + projectList.get(i).t.getTitle(), projectList.get(i).t.getLink() + "");
                    }
                }
                mainAdapter.notifyDataSetChanged();
            }
        });
    }

    //系统状态栏
    private void startBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        setAndroidNativeLightStatusBar(MainActivity.this, true);
    }

    private void initData() {
        projectList = new ArrayList<>();

        projectList.add(new Project(true, "未完成", R.drawable.right));
        projectList.add(new Project(new Project.projectItem("内容1", true, "未完成", R.drawable.noselect)));
        projectList.add(new Project(new Project.projectItem("内容2", true, "未完成", R.drawable.noselect)));
        projectList.add(new Project(new Project.projectItem("内容3", true, "未完成", R.drawable.noselect)));

        projectList.add(new Project(true, "已完成", R.drawable.right));
        projectList.add(new Project(new Project.projectItem("内容4", true, "已完成", R.drawable.select)));


    }

    private static void setAndroidNativeLightStatusBar(Activity activity, boolean dark) {
        View decor = activity.getWindow().getDecorView();
        if (dark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }
}
