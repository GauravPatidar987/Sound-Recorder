package com.my;
import android.app.*;
import android.content.*;
import android.os.*;
import android.media.*;
import java.io.*;
import java.text.*;
import java.util.*;
import android.widget.*;

public class RecordingService extends Service
{
	MediaRecorder mr;
	SQLiteDBHelper dbHelper;
	List<Record> l;
	File file;
	@Override
	public IBinder onBind(Intent p1)
	{
		Toast.makeText(this, "binder", Toast.LENGTH_LONG).show();

		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		long ts=System.currentTimeMillis();
		DateFormat df=new SimpleDateFormat("dd_MMM_yyyy_HH:mm:ss");
		Date dt=new Date(ts);
		String fn=df.format(dt);
		File dir=new File(Environment.getExternalStorageDirectory() + "/Recording");
		if (!dir.exists())
			dir.mkdir();
		file = new File(dir.getAbsolutePath() + "/audio_" + fn + ".mp3");
		try
		{
			if (!file.exists())file.createNewFile();
		}
		catch (IOException e)
		{
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
		mr = new MediaRecorder();
		mr.setAudioSource(MediaRecorder.AudioSource.MIC);
		mr.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
		mr.setOutputFile(file.getAbsolutePath());
		mr.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
		mr.setAudioChannels(1);
		dbHelper=new SQLiteDBHelper(getApplicationContext());
		try
		{
			mr.prepare();
			mr.start();
		}
		catch (IOException e)
		{
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
		catch (IllegalStateException e)
		{
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}

		return START_STICKY;
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		mr.stop();
		mr.release();
		
		Record r=new Record(file.getAbsolutePath());
		
	

		dbHelper.addNewRecord(r.getFileAbsPath());
	//	Toast.makeText(this,r.getFileAbsPath(), Toast.LENGTH_LONG).show();
		l=dbHelper.readRecords();
		}}
