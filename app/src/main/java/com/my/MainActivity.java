package com.my;

import android.app.*;
import android.os.*;
import android.support.design.widget.*;
import android.graphics.*;
import android.support.v4.view.*;
import android.support.v7.app.*;
import android.widget.*;
import android.*;
import android.content.pm.*;
import android.support.design.widget.TabLayout.*;

public class MainActivity extends AppCompatActivity
{
	TabLayout tabLayout;
	ViewPager vp;
	static boolean writeExtStoragePermit,readExtStoragePermit,recordAudioPermit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		vp = findViewById(R.id.viewPager);
		tabLayout =  findViewById(R.id.tabLayout); 
		TabLayout.Tab firstTab = tabLayout.newTab(); 
		firstTab.setText("Record"); 
		firstTab.setIcon(R.drawable.ic_record);

		TabLayout.Tab firstTab2 = tabLayout.newTab(); 
		firstTab2.setText("Recorded"); 
		firstTab2.setIcon(R.drawable.ic_recorded);

		tabLayout.addTab(firstTab, true);
		tabLayout.addTab(firstTab2);

		tabLayout.setTabTextColors(Color.parseColor("#bebebe"), Color.parseColor("#bd36cd"));
		tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

				@Override
				public void onTabSelected(TabLayout.Tab p1)
				{
					
					if(p1.getPosition()==1){
						RecordListFragment.adap.notifyDataSetChanged();
					}
				}

				@Override
				public void onTabUnselected(TabLayout.Tab p1)
				{
					// TODO: Implement this method
				}

				@Override
				public void onTabReselected(TabLayout.Tab p1)
				{
					// TODO: Implement this method
				}
				
	
});
		vp.setAdapter(new VpAdapter(getSupportFragmentManager()));
		tabLayout.setupWithViewPager(vp);
		if (!MainActivity.recordAudioPermit)
		{
			requestPermit();
		}
    }
	public void requestPermit()
	{
		requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO,
							   Manifest.permission.WRITE_EXTERNAL_STORAGE,
							   Manifest.permission.READ_EXTERNAL_STORAGE}, 5);
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
	{
		// TODO: Implement this method
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 5 && grantResults.length > 0)
		{
			if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)
			{
				recordAudioPermit = true;
			}
			if (grantResults[1] == PackageManager.PERMISSION_GRANTED)
			{
				writeExtStoragePermit = true;
			}
			if (grantResults[2] == PackageManager.PERMISSION_GRANTED)
			{
				readExtStoragePermit = true;
			}
		}
	}

}
