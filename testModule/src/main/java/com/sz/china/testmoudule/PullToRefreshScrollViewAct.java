/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.sz.china.testmoudule;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.sz.china.testmoudule.view.PullToRefreshLinearLayout;

public final class PullToRefreshScrollViewAct extends Activity {

	PullToRefreshScrollView mPullRefreshScrollView;
	ScrollView mScrollView;
	PullToRefreshLinearLayout pullToRefreshLinearLayout;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ptr_scrollview);

		pullToRefreshLinearLayout = (PullToRefreshLinearLayout) findViewById(R.id.aps_pull_linearLayout);
		pullToRefreshLinearLayout.setListener(pullToRefreashListener);

		mPullRefreshScrollView = (PullToRefreshScrollView) findViewById(R.id.pull_refresh_scrollview);
		mPullRefreshScrollView.setOnRefreshListener(new OnRefreshListener<ScrollView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
				new GetDataTask().execute();
			}
		});

		mScrollView = mPullRefreshScrollView.getRefreshableView();
	}

	private PullToRefreshLinearLayout.PullToRefreashListener pullToRefreashListener = new PullToRefreshLinearLayout.PullToRefreashListener() {
		@Override
		public void onRefresh() {
			new GetDataTask().execute();
		}

		@Override
		public void refreshOver() {

		}
	};

	private class GetDataTask extends AsyncTask<Void, Void, String[]> {

		@Override
		protected String[] doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(String[] result) {
			// Do some stuff here

			// Call onRefreshComplete when the list has been refreshed.
			mPullRefreshScrollView.onRefreshComplete();

			pullToRefreshLinearLayout.refreshOver();

			super.onPostExecute(result);
		}
	}
}
