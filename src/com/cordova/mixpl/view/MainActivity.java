package com.cordova.mixpl.view;


import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cordova.mixpl.BuildConfig;
import com.cordova.mixpl.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;



/**
 * 主Activity
 *
 * @author wwj_748
 */
public class MainActivity extends FragmentActivity implements OnClickListener, SlidingMenu.OnOpenedListener, SlidingMenu.OnClosedListener {

    // 三个tab布局
    private RadioButton bottom_tab_1, bottom_tab_2, bottom_tab_3;

    // 底部标签切换的Fragment
    private Fragment tab1, tab2, tab3,
            currentFragment;
    // 底部标签图片
    private ImageView
//            bottom_tab_1_img, bottom_tab_2_img, bottom_tab_3_img,
            menuImg;
    // 底部标签的文本
    private TextView
//            bottom_tab_1_title, bottom_tab_2_title, bottom_tab_3_title,
            tab_title;

    private SlidingMenu slidingMenu;

    private boolean isShowSlideMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        if(BuildConfig.LOG_DEBUG){
            Log.e("Test","Test");
            System.out.println("Test");
        }


        initUI();
        initTab();
        initMenu();

    }

    /**
     * 初始化侧滑菜单
     */
    private void initMenu() {
        isShowSlideMenu = true;
        slidingMenu = new SlidingMenu(this);//创建对象
        slidingMenu.setMode(SlidingMenu.LEFT);//设定模式，SlidingMenu在左边
        slidingMenu.setBehindOffset(getWindowManager().getDefaultDisplay().getWidth() / 4);//配置slidingmenu偏移出来的尺寸
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//全屏都可以拖拽触摸，打开slidingmenu
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);//附加到当前的Aty上去
        slidingMenu.setMenu(R.layout.slidemenu);
        slidingMenu.setBehindScrollScale((float) 0.7);
        slidingMenu.setFadeDegree(0.55f);
//        slidingMenu.setShadowDrawable(R.drawable.bg_menu_left);//设置阴影图片
//        slidingMenu.setShadowWidthRes(R.dimen.sliding_menu_offset);//设置阴影图片的宽度
        menuImg = (ImageView) findViewById(R.id.pic_main_menu);
        menuImg.setOnClickListener(this);

    }

    /**
     * 初始化UI
     */
    private void initUI() {
        bottom_tab_1 = (RadioButton) findViewById(R.id.bottom_tab_1);
        bottom_tab_2 = (RadioButton) findViewById(R.id.bottom_tab_2);
        bottom_tab_3 = (RadioButton) findViewById(R.id.bottom_tab_3);
        bottom_tab_1.setOnClickListener(this);
        bottom_tab_2.setOnClickListener(this);
        bottom_tab_3.setOnClickListener(this);

        tab_title = (TextView) findViewById(R.id.tab_title);

    }

    /**
     * 初始化底部标签
     */
    private void initTab() {
        if (tab1 == null) {
            tab1 = new Tab1();
        }

        if (!tab1.isAdded()) {
            //设置标题和tab
            tab_title.setText(R.string.Tab1);
            bottom_tab_1.setChecked(true);
            // 提交事务
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_layout, tab1).commit();

            // 记录当前Fragment
            currentFragment = tab1;

        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bottom_tab_1:
                clickTab1Layout();
                break;
            case R.id.bottom_tab_2:
                clickTab2Layout();
                break;
            case R.id.bottom_tab_3:
                clickTab3Layout();
                break;
            case R.id.pic_main_menu:
                showSlideMenu();
                break;
            default:
                break;
        }
    }

    //打开slidemenu菜单
    private void showSlideMenu() {
        slidingMenu.showMenu(isShowSlideMenu);
    }


    /**
     * 点击第一个tab
     */
    private void clickTab1Layout() {
        if (tab1 == null) {
            tab1 = new Tab1();
        }
        //设置标题
        tab_title.setText(R.string.Tab1);
        //替换fragment
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), tab1);

    }

    /**
     * 点击第二个tab
     */
    private void clickTab2Layout() {
        if (tab2 == null) {
            tab2 = new Tab2();
        }
//设置标题
        tab_title.setText(R.string.Tab2);
        //替换fragment
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), tab2);

    }


    /**
     * 点击第三个tab
     */
    private void clickTab3Layout() {
        if (tab3 == null) {
            tab3 = new Tab3();
        }
        //设置标题
        tab_title.setText(R.string.Tab3);
        //替换fragment
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), tab3);

    }


    /**
     * 添加或者显示碎片
     *
     * @param transaction
     * @param fragment
     */
    private void addOrShowFragment(FragmentTransaction transaction,
                                   Fragment fragment) {
        if (currentFragment == fragment)
            return;

        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.hide(currentFragment)
                    .add(R.id.content_layout, fragment).commit();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }

        currentFragment = fragment;
    }

    @Override
    public void onClosed() {
        isShowSlideMenu = true;
    }

    @Override
    public void onOpened() {
        isShowSlideMenu = false;
    }


}
