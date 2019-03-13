package com.example.stu.hellocharts171032ywj;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;

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
        initLineChart();
        initPicChart();
        initColumn();
    }

    private void initColumn() {//柱状图
        ColumnChartView columnChartView=columnView.findViewById(R.id.ccv_main_market);//初始化控件
        String[] year=new String[]{"2013","2014","2015","2016","2017"};//X坐标轴刻度字符
        List<AxisValue> axisXValues=new ArrayList<>();//集合类存储X坐标轴刻度字符
        for (int i=0;i<year.length;i++){
            axisXValues.add(new AxisValue(i).setLabel(year[i]));
        }
        Axis axisX=new Axis(axisXValues);//定义X坐标轴刻度
        int[] columnY={500,1000,1500,2000,2500,3000};//Y坐标轴刻度字符
        List<AxisValue> axisYValues=new ArrayList<>();//集合类存储Y坐标轴刻度字符
        for (int i=0;i<columnY.length;i++){
            axisYValues.add(new AxisValue(i).setValue(columnY[i]));
        }
        Axis axisY=new Axis(axisYValues);//定义Y坐标轴刻度
        int[] columnVaues={924,1367,1200,1500,1000};//定义柱状条数据
        int[] columnColor={ChartUtils.COLOR_BLUE,ChartUtils.COLOR_GREEN,ChartUtils.COLOR_ORANGE,
                ChartUtils.COLOR_RED,ChartUtils.COLOR_VIOLET};//定义柱状条颜色
        List<Column> columns=new ArrayList<>();//集合类存储柱状条
        for (int i=0;i<columnVaues.length;i++){
            List<SubcolumnValue> subcolumnValues=new ArrayList<>();
            subcolumnValues.add(new SubcolumnValue(columnVaues[i],columnColor[i]));
            columns.add(new Column(subcolumnValues).setHasLabelsOnlyForSelected(true));
        }
        ColumnChartData columnChartData=new ColumnChartData(columns);//使用柱状条对象创建数据对象
        columnChartData.setAxisXBottom(axisX);//设置X轴
        columnChartData.setAxisYLeft(axisY);//设置Y轴
        columnChartView.setColumnChartData(columnChartData);//数据对象赋值给视图
    }

    private void initPicChart() {//饼状图
        PieChartView pieChartView=picView.findViewById(R.id.pcv_main_edu);
        int[] pieData={8,24,35,23,10};//初步数据
        int[] color={Color.parseColor("#356fb3"),Color.parseColor("b53633"),
                Color.parseColor("#86aa3d"),Color.parseColor("#6a4b90"),
                Color.parseColor("#2e9cba")};//切片颜色
        List<SliceValue> sliceValues=new ArrayList<>();
        for (int i=0;i<pieData.length;i++){
            SliceValue sliceValue=new SliceValue((float)pieData[i],color[i]);
            sliceValues.add(sliceValue);
        }
        final PieChartData pieChartData=new PieChartData();//定义饼状图数据对象
        pieChartData.setValues(sliceValues);//设置饼状图的切片数据
        pieChartData.setHasLabels(true);//设置为显示数据
        pieChartData.setHasCenterCircle(true);//设置中间有圆
        pieChartData.setCenterText1("数据");//中间圆文字
        pieChartView.setPieChartData(pieChartData);//将数据对象赋值给视图
        pieChartView.setOnValueTouchListener(new PieChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int i, SliceValue sliceValue) {
                String[] stateChar={"高等教育","职业教育","语音教育","K12教育","其他"};
                pieChartData.setCenterText1(stateChar[i]);
            }

            @Override
            public void onValueDeselected() {

            }
        });
    }

    private void initLineChart() {//折线图
        LineChartView lineChartView=lineView.findViewById(R.id.lcv_main_temperature);//初始化控件
        String[] lineData={"周一","周二","周三","周四","周五","周六","周日"};//X坐标轴刻度字符
        List<AxisValue> axisXValues=new ArrayList<>();//集合类存储X坐标轴刻度字符
        for (int i=0;i<lineData.length;i++){
            axisXValues.add(new AxisValue(i).setLabel(lineData[i]));
        }
        Axis axisX=new Axis();//定义X坐标轴刻度
        axisX.setValues(axisXValues);
        Axis axisY=new Axis();//定义Y坐标轴刻度，但未设置刻度

        int[] temperature={24,27,26,25,26,27,24};//折线数据
        List<PointValue> pointValues=new ArrayList<>();//集合类存储折线数据
        for (int i=0;i<temperature.length;i++){
            pointValues.add(new PointValue(i,temperature[i]));
        }
        Line line=new Line();//定义单条折线
        line.setValues(pointValues);
        List<Line> lines=new ArrayList<>();//定义折线集
        lines.add(line);

        LineChartData data=new LineChartData();//定义折线图数据对象
        data.setAxisXBottom(axisX);//设置折线图X坐标
        data.setAxisYLeft(axisY);//设置折线图Y坐标
        data.setLines(lines);//设置折线图折线数据
        lineChartView.setLineChartData(data);//将折线图数据对象赋值给折线图视图
        lineChartView.setVisibility(View.VISIBLE);//设置视图可见
    }

    private void initTabLayout() {//选项卡界面初始化
        LayoutInflater mInflater=LayoutInflater.from(this);//布局加载器
        lineView=mInflater.inflate(R.layout.layout_line_chart,null);//加载线型图布局文件
        picView=mInflater.inflate(R.layout.layout_pie_chart,null);//加载饼状图布局文件
        columnView=mInflater.inflate(R.layout.layout_column_chart,null);//加载柱状图布局文件
        List<View> mViewList=new ArrayList<>();//将布局文件对象储存到集合类中
        mViewList.add(lineView);
        mViewList.add(picView);
        mViewList.add(columnView);
        List<String> mTitleList=new ArrayList<>();//定义选项卡每个卡片的标题
        mTitleList.add("线型图");
        mTitleList.add("饼状图");
        mTitleList.add("柱状图");
        ViewPagerAdapter mAdapter=new ViewPagerAdapter(mViewList,mTitleList);//定义视图页适配器
        mViewPagerMainCharts.setAdapter(mAdapter);//设置视图页适配器
        mTabLayoutMainTabs.setTabMode(TabLayout.MODE_FIXED);//设置选项卡模式
        mTabLayoutMainTabs.addTab(mTabLayoutMainTabs.newTab().setText(mTitleList.get(0)));
        mTabLayoutMainTabs.addTab(mTabLayoutMainTabs.newTab().setText(mTitleList.get(1)));
        mTabLayoutMainTabs.addTab(mTabLayoutMainTabs.newTab().setText(mTitleList.get(2)));
        mTabLayoutMainTabs.setupWithViewPager(mViewPagerMainCharts);//将视图页设置给选项卡
    }

    private void initView() {
        mTabLayoutMainTabs=(TabLayout)findViewById(R.id.tabLayout_main_tabs);
        mViewPagerMainCharts=(ViewPager)findViewById(R.id.viewPager_main_charts);
    }
}
