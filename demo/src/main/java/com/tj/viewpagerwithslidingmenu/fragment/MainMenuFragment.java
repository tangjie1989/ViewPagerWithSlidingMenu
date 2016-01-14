package com.tj.viewpagerwithslidingmenu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.tj.viewpagerwithslidingmenu.R;
import com.tj.viewpagerwithslidingmenu.util.ScreenInfoUtil;

/**
 * slide menu 背景菜单fragment
 */

public class MainMenuFragment extends Fragment {

	private ListView menuListView;
	private Context context;
	private LayoutInflater inflater;

	public static MainMenuFragment newInstance(Bundle args) {
		MainMenuFragment myFragment = new MainMenuFragment();
		myFragment.setArguments(args);
		return myFragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getActivity();
		inflater = LayoutInflater.from(context);
		
		Bundle bundle = getArguments();

		if (bundle != null) {
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return createAndInitMenuListView();
	}

	private View createAndInitMenuListView() {
		
		LinearLayout mainView = (LinearLayout)inflater.inflate(R.layout.fragment_main_menu, null);

		menuListView = new ListView(context);

		menuListView.setCacheColorHint(0);
		menuListView.setDividerHeight(0);
		menuListView.setFastScrollEnabled(false);
//		menuListView.setBackgroundColor(getResources().getColor(R.color.red));
		
        int widths = ScreenInfoUtil.getScreenWidth(context);
        int heights = ScreenInfoUtil.getScreenHeight(context);
        
        int widthoffSet = getResources().getDimensionPixelSize(R.dimen.slidingmenu_offset);
        
        //menu view 所占大小 宽度为屏幕宽度减去content view最小偏移量(距离右边) 高度 = 屏幕高度 - 状态栏高度 - 屏幕高度/7(预留menuview top 和 bottom边距)
        
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams( widths - widthoffSet, heights - ScreenInfoUtil.getStatusBarHeight(context) - (heights/7));
		lp.topMargin = heights/14;
		
		mainView.addView(menuListView,lp);
		return mainView;

	}

}
