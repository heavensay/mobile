package com.example.practice;

import com.example.practice.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;
import android.support.v4.app.NavUtils;

/**
 * 测试1 TabHost
 * 测试2 动态加载Layout(使用Inflate)
 * @author admin
 *
 */
public class MyTabHostActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        setContentView(R.layout.tab_host);
	     final TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
	        // 如果没有继承TabActivity时，通过该种方法加载启动tabHost  
	        tabHost.setup();

	        LayoutInflater inflater = LayoutInflater.from(this);
	        inflater.inflate(R.layout.inflate1, null);
	        
	        LayoutInflater flate = LayoutInflater.from(this);	
	        
	        View v = tabHost.getChildAt(0);
	        
	    	final View flate1 = flate.inflate(R.layout.inflate1,null);
	    	final View flate2 = flate.inflate(R.layout.inflate2,null);
	    	
	    	final TextView tv1 = new TextView(this);
	    	tv1.setText("tab2 content ");
	    	
	        /* tab 页*/
	        tabHost.addTab(tabHost.newTabSpec("tab1-1").setIndicator("indicator1").setContent(R.id.tab1));
	        tabHost.addTab(tabHost.newTabSpec("tab1-2").setIndicator("indicator2").setContent(R.id.tab2));
	        tabHost.addTab(tabHost.newTabSpec("tab1-3").setIndicator("indicator3").setContent(R.id.tab3));
	        tabHost.setOnTabChangedListener(new OnTabChangeListener() {
				@Override
				public void onTabChanged(String tabId) {
					// TODO Auto-generated method stub
					Toast.makeText(MyTabHostActivity.this, ""+tabId, Toast.LENGTH_LONG).show();
					LinearLayout checkedTab = (LinearLayout)tabHost.getCurrentView();
					Object tag = checkedTab.getTag();
					Log.d(MainActivity.class.getName(), "被选中的tab页:"+tag);
					
					if(tabHost.getCurrentTab()==0){
						if (flate1.getParent()!=checkedTab){
							checkedTab.removeAllViews();
							checkedTab.addView(flate1,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT));
//							checkedTab.layout(100, 100, 100, 100);
						}
					}else if(tabHost.getCurrentTab()==1){
						if (flate2.getParent()!=checkedTab){
							checkedTab.removeAllViews();
							checkedTab.addView(flate2,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT));
						}
					}

				}
			});
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_message, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
