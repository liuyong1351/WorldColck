package com.example.worldcolck;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.os.Build;

public class MainActivity extends Activity {
	private TabHost tabHost; //����tabHost����
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tabHost = (TabHost)findViewById(android.R.id.tabhost);//�ҵ�tabHost��ID
		tabHost.setup(); //��ʼ��tabHost
		tabHost.addTab(tabHost.newTabSpec("tabTime").setIndicator("ʱ��").setContent(R.id.tabTime));//����Tab
		tabHost.addTab(tabHost.newTabSpec("tabAlarm").setIndicator("����").setContent(R.id.tabAlarm));//����Tab
		tabHost.addTab(tabHost.newTabSpec("tabTimer").setIndicator("��ʱ��").setContent(R.id.tabTimer));//����Tab
		tabHost.addTab(tabHost.newTabSpec("tabStopWatch").setIndicator("���").setContent(R.id.tabStopWatch));//����Tab

	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		// stopWatchView.onDestroy();
	} 
	private StopWatchView stopWatchView;

}
