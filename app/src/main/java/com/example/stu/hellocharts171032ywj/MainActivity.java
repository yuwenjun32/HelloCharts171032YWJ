package com.example.stu.hellocharts171032ywj;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private TabLayout mTabLayoutMainTabs;
    private ViewPager mViewPagerMainCharts;
    private View lineView,picView,columnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initTabLayout();
    }

    private void initTabLayout() {//选项卡界面初始化
        LayoutInflater mInflater=LayoutInflater.from(this);//布局加载器
        lineView=mInflater.inflate(R.layout.layout_line_chart,null);//加载线型图布局文件
        picView=mInflater.inflate(R.layout.layout_pie_chart,null);//加载饼状图布局文件
        columnView=mInflater.inflate(R.layout.layout_column_chart,null);//加载柱状图布局文件
    }

    private void initView() {
        mTabLayoutMainTabs=(TabLayout)findViewById(R.id.tabLayout_main_tabs);
        mViewPagerMainCharts=(ViewPager)findViewById(R.id.viewPager_main_charts);
    }
}
