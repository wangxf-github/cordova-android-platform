package com.cordova.mixpl.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cordova.mixpl.R;


/**
 * 我想知道的碎片页面
 * @author wwj_748
 *
 */
public class Tab1 extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_tab1_fragment, container,
				false);
		return view;
	}


}
