package com.yxkj.jyb;


import com.viewpagerindicator.TabPageIndicator;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import com.viewpagerindicator.TabPageIndicator;

public class CodeSystem_Fra extends Fragment{
	/**
	 * Tab标题
	 */
	private static final String[] CONTENT = new String[] { "数字", "字母",};

	public static Context context;
	public static CodeSystem_Fra sSelf = null;
	
	private ViewPager mPager = null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {	
		View view = inflater.inflate(R.layout.codesystem_fra, null);
		//context = container.getContext();
		context = FragmentPage4.context;
		sSelf = this;
		
		view.getContext().setTheme(R.style.Theme_PageIndicatorDefaults);
		//@style/Theme.PageIndicatorDefaults

		//ViewPager的adapter
		GoogleMusicAdapter adapter = new GoogleMusicAdapter(getChildFragmentManager());

		mPager = (ViewPager)view.findViewById(R.id.pager);
		mPager.setAdapter(adapter);

        TabPageIndicator indicator = (TabPageIndicator)view.findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        
        //如果我们要对ViewPager设置监听，用indicator设置就行了
        indicator.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				//Toast.makeText(context, CONTENT[arg0], Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
        indicator.setVisibility(1);
        
        return view;
	}
	public int getCurTypeIdx(){
		return mPager.getCurrentItem();
	}
	public static String getCurTypeName(){
	    	
		if(sSelf != null){
			int idx = sSelf.getCurTypeIdx();
			return CONTENT[idx % CONTENT.length].toUpperCase();
		}
		return "";
	}
	public static int getCurTypeId(){
    	
		if(sSelf != null){
			return sSelf.getCurTypeIdx();
		}
		return -1;
	}
	
    class GoogleMusicAdapter extends FragmentPagerAdapter {
        public GoogleMusicAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
    		return CodeSystem_item_Fra.newInstance(CONTENT[position % CONTENT.length]);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length].toUpperCase();
        }

        @Override
        public int getCount() {
          return CONTENT.length;
        }
    }
    
	@Override
	public void onResume()
	{
		super.onResume();
		Plugins.onResume(context);
	}
	
	@Override
	public void onPause()
	{
		super.onPause();
		Plugins.onPause(context);
	}
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		sSelf = null;
	}
}
