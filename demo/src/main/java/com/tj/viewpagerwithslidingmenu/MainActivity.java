package com.tj.viewpagerwithslidingmenu;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.SlidingMenu.CanvasTransformer;
import com.tj.viewpagerwithslidingmenu.fragment.ContentFragment;

public class MainActivity extends BaseActivity{
	
	private LayoutInflater inflater;
	private View mainView;
	private ViewPager mainViewPager;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		inflater = LayoutInflater.from(this);
		
		initMainView();
		
		setSlideMenuContentView();
		
		initSlideMenuBehindViewScrollAnimation();
	}
	
	private void initMainView(){
		
		mainView = inflater.inflate(R.layout.main_page_layout, null);
		
		mainViewPager = (ViewPager)mainView.findViewById(R.id.main_viewpager);
		mainViewPager.setAdapter(new ContentPagerAdapter(getSupportFragmentManager()));
		mainViewPager.setCurrentItem(1);
		
		initMainViewTopBar();
		
	}
	
	private void initMainViewTopBar(){

		ImageView openBehindMenu = (ImageView)mainView.findViewById(R.id.user_center_info_indicator);
		
		openBehindMenu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (getSlidingMenu().isMenuShowing()) {
					getSlidingMenu().showContent();
				} else {
					getSlidingMenu().showMenu();
				}
			}
		});
	}
	
	private void setSlideMenuContentView(){
		
		setContentView(mainView);

		mainViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageScrollStateChanged(int arg0) { }

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) { }

			@Override
			public void onPageSelected(int position) {
				updateSlideMode(position);
			}
		});
		
		updateSlideMode(1);
	}
	
	private void updateSlideMode(int position){
		SlidingMenu slidingMenu = getSlidingMenu();
		
		boolean isLogin = true;
		if(!isLogin){
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		}else{
			switch (position) {
			case 0:
				slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
				break;
			default:
				slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
				break;
			}
		}
	}
	
	private void initSlideMenuBehindViewScrollAnimation(){
		
		SlidingMenu slidingMenu = getSlidingMenu();
		slidingMenu.setBehindScrollScale(0.0f);
		slidingMenu.setBehindCanvasTransformer(new CanvasTransformer() {
			@Override
			public void transformCanvas(Canvas canvas, float percentOpen) {											
				float scale = (float) (percentOpen*0.3 + 0.7);
//				System.out.println("percentOpen : " + percentOpen + " scale : " + scale + "\r\n");
				canvas.scale(scale, scale, 0, canvas.getHeight()/2);//canvas.getWidth()/2
			}
		});
	}

	private class ContentPagerAdapter extends FragmentPagerAdapter {

		private ContentPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public Fragment getItem(int position) {
			Bundle args = new Bundle();
			args.putString(ContentFragment.FRAGMENT_TITLE, "page" + (position + 1));
			return ContentFragment.newInstance(args);
		}

	}
	
}
