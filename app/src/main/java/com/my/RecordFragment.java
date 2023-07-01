package com.my;
import android.os.*;
import android.view.*;
import android.support.v4.app.*;
import android.widget.*;
import android.view.View.*;
import android.content.*;

public class RecordFragment extends Fragment
{
TextView txt;
ImageView img;
ProgressBar pbar;
Chronometer chr;
boolean isRecOn;
Intent i;
View.OnClickListener iListen;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{

		super.onCreateView(inflater, container, savedInstanceState);	
		View v=inflater.inflate(R.layout.fragment_record, container, false);
		//Toast.makeText(container.getContext(),v.toString(),Toast.LENGTH_LONG).show();
		txt=v.findViewById(R.id.txt_record);
		img=v.findViewById(R.id.img_vc_record);
		pbar=v.findViewById(R.id.recordPBar);
		chr=v.findViewById(R.id.chrono);
		iListen = new View.OnClickListener(){

			@Override
			public void onClick(View p1)
			{
				
				isRecOn=!isRecOn;
				if(isRecOn&&MainActivity.recordAudioPermit){
					//Toast.makeText(p1.getContext(),"Recording Started",Toast.LENGTH_LONG).show();
					txt.setText("Recording...");
					chr.setBase(SystemClock.elapsedRealtime());
					chr.start();
					pbar.setVisibility(View.VISIBLE);
					img.setImageResource(R.drawable.ic_stop);
					startRecording(p1);
				}else{
					if(MainActivity.recordAudioPermit){
					Toast.makeText(p1.getContext(),"Recording Stopped",Toast.LENGTH_LONG).show();
				txt.setText("");
				pbar.setVisibility(View.GONE);
				
					img.setImageResource(R.drawable.ic_voice);
					chr.stop();
					stopRecording(p1,i);
					}else{
						Toast.makeText(p1.getContext(),"please give audio record permission",Toast.LENGTH_LONG).show();
					}
					}
			}
		};
		img.setOnClickListener(iListen);
		return v;
	}

public void startRecording(View p1){
	 i=new Intent(p1.getContext(),RecordingService.class);
	p1.getContext().startService(i);
}
public void stopRecording(View p1,Intent i){
	p1.getContext().stopService(i);
	
}
}
