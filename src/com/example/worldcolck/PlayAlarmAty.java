package com.example.worldcolck;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

public class PlayAlarmAty extends Activity {
	private MediaPlayer mp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarm_palyer_aty);
		mp = MediaPlayer.create(this,R.raw.lowbattery); //����prepare״̬
		mp.start();
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();//���ص����õĽ���
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mp.stop();
		mp.release();
	}
	
}
