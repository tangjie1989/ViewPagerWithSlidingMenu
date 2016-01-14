package com.tj.viewpagerwithslidingmenu;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;
import com.tj.viewpagerwithslidingmenu.fragment.MainMenuFragment;

public class BaseActivity extends SlidingFragmentActivity {
	
	protected MainMenuFragment mFrag;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

//		setTitle(mTitleRes);
		
		// set the Behind View
		setBehindContentView(R.layout.main_menu_layout);
		
		if (savedInstanceState == null) {
			FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
			mFrag = new MainMenuFragment();
			t.replace(R.id.menu_frame, mFrag);
			t.commit();
		} else {
			mFrag = (MainMenuFragment)this.getSupportFragmentManager().findFragmentById(R.id.menu_frame);
		}

		// customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		
		sm.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {
			@Override
			public void onOpened() {
				if(mFrag != null){
					//更新emnu fragment操作
				}
			}
		});
	}

}
