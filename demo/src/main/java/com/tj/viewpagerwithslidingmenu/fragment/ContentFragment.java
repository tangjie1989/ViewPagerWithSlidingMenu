package com.tj.viewpagerwithslidingmenu.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tj.viewpagerwithslidingmenu.R;

public class ContentFragment extends Fragment{
	
	public static final String FRAGMENT_TITLE = "fragment_title";

	private String viewTitle;

	public static ContentFragment newInstance(Bundle args) {
		ContentFragment myFragment = new ContentFragment();
        myFragment.setArguments(args);
        return myFragment;
    }
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Bundle bundle = getArguments();
		if(bundle != null){
			viewTitle = bundle.getString(FRAGMENT_TITLE);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View contentView = inflater.inflate(R.layout.fragment_page_content, null);

		TextView titleView = (TextView)contentView.findViewById(R.id.fragment_title);
		titleView.setText(viewTitle);

		return contentView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

}