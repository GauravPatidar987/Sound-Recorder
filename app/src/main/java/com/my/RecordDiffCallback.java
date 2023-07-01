package com.my;
import java.util.*;
import android.support.v7.util.*;
import android.support.annotation.*;;
	public class RecordDiffCallback extends DiffUtil.Callback {

		private final List<Record> mOldEmployeeList;
		private final List<Record> mNewEmployeeList;

	public RecordDiffCallback(List<Record> oldEmployeeList, List<Record> newEmployeeList) {
			this.mOldEmployeeList = oldEmployeeList;
			this.mNewEmployeeList = newEmployeeList;
		}

		@Override
		public int getOldListSize() {
			return mOldEmployeeList.size();
		}

		@Override
		public int getNewListSize() {
			return mNewEmployeeList.size();
		}

		@Override
		public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
			return mOldEmployeeList.get(oldItemPosition).getFileAbsPath() == mNewEmployeeList.get(
                newItemPosition).getFileAbsPath();
		}

		@Override
		public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
			final Record oldEmployee = mOldEmployeeList.get(oldItemPosition);
			final Record newEmployee = mNewEmployeeList.get(newItemPosition);

			return oldEmployee.getFileAbsPath().equals(newEmployee.getFileAbsPath());
		}

		@Nullable
		@Override
		public Object getChangePayload(int oldItemPosition, int newItemPosition) {
			// Implement method if you're going to use ItemAnimator
			return super.getChangePayload(oldItemPosition, newItemPosition);
		}
	
}
