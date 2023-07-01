package com.my;

import android.support.v7.widget.*;
import android.view.*;
import java.io.File;
import java.util.*;
import android.widget.*;
import android.support.v4.app.*;
import android.os.*;
import android.content.*;

public class RIAdapter extends RecyclerView.Adapter<RIAdapter.RIVH> {
	public List<Record> l;
	FragmentManager fm;
	Context context;
	SQLiteDBHelper helper;

	public RIAdapter(List<Record> l, FragmentManager fm, Context context) {
		this.l = l;
		this.fm = fm;
		this.context = context;
		helper = new SQLiteDBHelper(context);
	}

	@Override
	public RIAdapter.RIVH onCreateViewHolder(ViewGroup p1, int p2) {
		View v = LayoutInflater.from(p1.getContext()).inflate(R.layout.record_item, p1, false);
		return new RIVH(v);
	}

	@Override
	public void onBindViewHolder(RIAdapter.RIVH p1, int p2) {
	File f=new File(l.get(p2).getFileAbsPath());
		p1.txt.setText(f.getName());
	}

	@Override
	public int getItemCount() {
		l=helper.readRecords();
		return l.size();
	}

	public class RIVH extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

		@Override
		public boolean onLongClick(View p1) {
			File f = new File(l.get(getAdapterPosition()).getFileAbsPath());
			if (f.exists()) {
				if (f.delete()) {
					helper.deleteRecord(l.get(getAdapterPosition()).getFileAbsPath());
					notifyDataSetChanged();
				}
			}
			return true;
		}

		@Override
		public void onClick(View p1) {
			DFFragment fr = new DFFragment();
			Bundle b = new Bundle();
			b.putString("fan", l.get(getAdapterPosition()).getFileAbsPath());
			fr.setArguments(b);
			fr.show(fm, null);
		}

		TextView txt;

		public RIVH(View v) {
			super(v);
			txt = v.findViewById(R.id.item_rn);
			itemView.setOnClickListener(this);
			itemView.setOnLongClickListener(this);
		}
	}
}
