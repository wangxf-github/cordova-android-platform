package com.cordova.mixpl.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cordova.mixpl.R;
import com.cordova.mixpl.adapter.AppAdapter;
import com.cordova.mixpl.model.App;


/**
 * “我的”碎片页面
 *
 * @author wwj_748
 */
public class Tab2 extends Fragment implements AdapterView.OnItemClickListener{

    private GridView gview;
    private List<Map<String, Object>> data_list;
    // 图片封装为一个数组
    private int[] icon = {R.drawable.address_book, R.drawable.calendar,
            R.drawable.camera, R.drawable.clock, R.drawable.games_control,
            R.drawable.messenger, R.drawable.ringtone, R.drawable.settings,
            R.drawable.speech_balloon, R.drawable.weather, R.drawable.world,
            R.drawable.youtube};
    private String[] iconName = {"测试", "日历", "照相机", "时钟", "游戏", "短信", "铃声",
            "设置", "语音", "天气", "浏览器", "视频"};
    private String[] ids = {"text", "calenter", "photo", "clock", "game", "msg", "voice",
            "setting", "voice", "weather", "explore", "video"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_tab2_fragment, container,
                false);
        gview = (GridView) view.findViewById(R.id.gview);
//        //新建List
//        data_list = new ArrayList<Map<String, Object>>();
//        //获取数据
//        getData();
//        //新建适配器
//        String [] from ={"image","text"};
//        int [] to = {R.id.image,R.id.text};
//        sim_adapter = new SimpleAdapter(this.getActivity(), data_list, R.layout.item, from, to);
        AppAdapter appAdapter = new AppAdapter(iconName,ids,icon,this.getActivity());
        //配置适配器
        gview.setAdapter(appAdapter);
        setOnclick();
        return view;
    }
    private void setOnclick() {
        gview.setOnItemClickListener(this);
    }

    public List<Map<String, Object>> getData(){
        //cion和iconName的长度是相同的，这里任选其一都可以
        for(int i=0;i<icon.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }

        return data_list;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this.getActivity(),id+"sd",Toast.LENGTH_SHORT).show();
    }
}
