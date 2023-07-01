package com.my;
import android.support.v4.app.*;

public class VpAdapter extends FragmentPagerAdapter
{
	FragmentManager fm;
	String titles[]={"Record","Recorded"};
	public VpAdapter(FragmentManager fm){
		super(fm);
		this.fm=fm;
	}
	@Override
	public Fragment getItem(int p1)
	{
		switch(p1){
			case 0:
				return new RecordFragment();
			case 1:
				return new RecordListFragment(fm);
		}
		return null;
	}

	@Override
	public int getCount()
	{
		
		return titles.length;
	}

	@Override
	public CharSequence getPageTitle(int position)
	{
		
		 super.getPageTitle(position);
		return titles[position];
	}
	
	
}
