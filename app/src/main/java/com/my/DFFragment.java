package com.my;
import android.support.v4.app.*;
import android.os.*;
import android.view.*;
import java.io.*;
import android.widget.*;
import android.media.*;


public class DFFragment extends DialogFragment implements View.OnClickListener,SeekBar.OnSeekBarChangeListener
{

	@Override
	public void onProgressChanged(SeekBar p1, int p2, boolean p3)
	{
		if (p3)
		{
			sbar.setProgress(p2);
			mp.seekTo(p2);
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar p1)
	{
		// TODO: Implement this method
	}

	@Override
	public void onStopTrackingTouch(SeekBar p1)
	{
		// TODO: Implement this method
	}

	File file;
	String fas;
	TextView txt,txs,txe;
	int dur;
	ImageView dis,pp;
	public static MediaPlayer mp;
	SeekBar sbar;
	private boolean isPlay=true,state=true;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{

		super.onCreateView(inflater, container, savedInstanceState);
		View v=inflater.inflate(R.layout.fragment_df, container, false);
		fas = getArguments().getString("fan");
		file = new File(fas);
		txt = v.findViewById(R.id.df_textView);
		txe = v.findViewById(R.id.df_etime);
		txs = v.findViewById(R.id.df_stime);
		dis = v.findViewById(R.id.dfClose);
		sbar = v.findViewById(R.id.df_seekBar);
		pp=v.findViewById(R.id.dfPlayPause);
		txt.setText(file.getName());
		txt.setSelected(true);
		setCancelable(false);
		mp = new MediaPlayer();
		try
		{
			FileInputStream fis = new FileInputStream(file.getAbsolutePath());
			
			mp.setDataSource(fis.getFD());
		}
		catch (SecurityException e)
		{
			Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
		}
		catch (IllegalArgumentException e)
		{}
		catch (IOException e)
		{
			Toast.makeText(getActivity(), "&"+ file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
		}
		catch (IllegalStateException e)
		{}
		try
		{
			mp.prepare();
			dur = mp.getDuration();
			sbar.setMax(dur);
			
			
			Toast.makeText(getActivity(), "d:" + dur, Toast.LENGTH_SHORT).show();

			int sec=dur / 1000;
			int min=0;
			if (sec >= 60)
			{
				sec = sec % 60;
				min = sec / 60;
			}
			txe.setText(con(min, sec));
			mp.start();
			Thread t=new Thread(new Runnable(){



					@Override
					public void run()
					{
						while (isPlay)
						{
							try
							{
								Thread.sleep(1000);
								if(getActivity()!=null)
								getActivity().runOnUiThread(new Runnable(){

										@Override
										public void run()
										{
											int cur=mp.getCurrentPosition();
											int sec=cur / 1000;
											int min=0;
											if (sec >= 60)
											{
												sec = sec % 60;
												min = sec / 60;
											}
											txs.setText(con(min, sec));
											sbar.setProgress(cur);
										}


									});
							}
							catch (InterruptedException e)
							{

							}
						}
					}


				});
			t.start();
		}
		catch (IOException e)
		{
			txt.setText(e.toString());
			Toast.makeText(getActivity(), "__" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
		}
		catch (IllegalStateException e)
		{}
		dis.setOnClickListener(this);
		pp.setOnClickListener(this);
		sbar.setOnSeekBarChangeListener(this);

		return v;
	}

	private String con(int min, int sec)
	{
		String s;
		if (min < 10)
		{
			s = "0" + min;
		}
		else
		{
			s = "" + min;
		}
		if (sec < 10)
		{
			s = s + ":0" + sec;
		}
		else
		{
			s = s + ":" + sec;
		}
		return s;
	}


	@Override
	public void onClick(View p1)
	{
		switch(p1.getId()){
			case R.id.dfClose:
				if (mp != null)
				{
					mp.stop();
					mp.release();
					dismiss();
				}
				break;
				case R.id.dfPlayPause:
					state=!state;
					if(state){
						pp.setImageResource(R.drawable.ic_pause);
						mp.start();
					}else{
						pp.setImageResource(R.drawable.ic_play);
						mp.pause();
					}
					break;
		}
		

	}

}
