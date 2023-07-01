package com.my;
import android.support.v4.app.*;
import android.view.*;
import android.os.*;
import android.support.v7.widget.*;
import java.util.*;
import android.app.Activity;

public class RecordListFragment extends Fragment
{
RecyclerView rec;
FragmentManager fm;
public static RIAdapter adap;
SQLiteDBHelper dbHelper;
	public List<Record> l;

	public RecordListFragment(FragmentManager fm)
	{
		this.fm = fm;	
	}

	@Override
	public void onAttach(Activity activity)
	{
		// TODO: Implement this method
		super.onAttach(activity);
			
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{

		super.onCreateView(inflater, container, savedInstanceState);
		View v=inflater.inflate(R.layout.fragment_record_list, container, false);
		rec=v.findViewById(R.id.recyclerView);
		adap=new RIAdapter(l,fm,getActivity().getApplicationContext());
		rec.setAdapter(adap);
		rec.setLayoutManager(new LinearLayoutManager(container.getContext()));
		return v;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onViewCreated(view, savedInstanceState);
		dbHelper=new SQLiteDBHelper(getActivity().getApplicationContext());
		l=dbHelper.readRecords();
		adap.notifyDataSetChanged();
	}
	
}
