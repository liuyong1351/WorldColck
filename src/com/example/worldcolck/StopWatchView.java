package com.example.worldcolck;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class StopWatchView extends LinearLayout {

	public StopWatchView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public StopWatchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public StopWatchView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
  private TextView tvHour,tvMin,tvSec,tvMSec;
  private Button btnWStart2,btnWPause2,btnWResume2,btnWLap2,btnWReset2;
  private ListView lvWatch2;
  private ArrayAdapter<String> adapterString;
  @Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
		tvHour = (TextView) findViewById(R.id.watchHour);
		tvHour.setText("0");
		tvMin = (TextView) findViewById(R.id.watchMin);
		tvMin.setText("0");
		tvSec = (TextView) findViewById(R.id.watchSec);
		tvSec.setText("0");
		tvMSec = (TextView) findViewById(R.id.watchMSec);
		tvMSec.setText("0");
		
		btnWStart2 = (Button) findViewById(R.id.btnWStart);
		btnWStart2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startWatchTimer();
				btnWStart2.setVisibility(View.GONE);
				btnWPause2.setVisibility(View.VISIBLE);
				btnWLap2.setVisibility(View.VISIBLE);
				btnWReset2.setVisibility(View.GONE);
				btnWResume2.setVisibility(View.GONE);
			}
		});
		btnWPause2 = (Button) findViewById(R.id.btnWPause);
		btnWPause2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stopTimer();
				btnWResume2.setVisibility(View.VISIBLE);
				btnWPause2.setVisibility(View.GONE);
				btnWLap2.setVisibility(View.GONE);
				btnWReset2.setVisibility(View.VISIBLE);
			}
		});
		btnWResume2 = (Button) findViewById(R.id.btnWResume);
		btnWResume2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startWatchTimer();
				btnWResume2.setVisibility(View.GONE);
				btnWPause2.setVisibility(View.VISIBLE);
				btnWLap2.setVisibility(View.VISIBLE);
				btnWReset2.setVisibility(View.GONE);
			}
		});
		btnWLap2 = (Button) findViewById(R.id.btnWLop);
		btnWLap2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				adapterString.insert(String.format("%d:%d:%d:%d", tenMSec/100/60/60,tenMSec/100/60,tenMSec/100,tenMSec%100), 0);
			}
		});
		btnWReset2 = (Button) findViewById(R.id.btnWReset);
		btnWReset2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stopTimer();
				tenMSec = 0;
				adapterString.clear();
				btnWPause2.setVisibility(View.GONE);
				btnWResume2.setVisibility(View.GONE);
				btnWLap2.setVisibility(View.GONE);
				btnWReset2.setVisibility(View.GONE);
				btnWStart2.setVisibility(View.VISIBLE);
			}
		});
		
		btnWPause2.setVisibility(View.GONE);
		btnWResume2.setVisibility(View.GONE);
		btnWLap2.setVisibility(View.GONE);
		btnWReset2.setVisibility(View.GONE);
		
		lvWatch2 = (ListView) findViewById(R.id.lvWatch);
		adapterString = new ArrayAdapter<String>(getContext(),android.R.layout.simple_expandable_list_item_1);
		lvWatch2.setAdapter(adapterString);
		showTimerTask = new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				hander.sendEmptyMessage(MSG_WHAT_SHOW);
			}
		};
		timer.schedule(showTimerTask, 200, 200);
	}
  private Timer timer = new Timer();
  private int tenMSec = 0;
  private TimerTask timerTask = null;
  private TimerTask showTimerTask = null;
  private static final int MSG_WHAT_SHOW =1;
  private Handler hander = new Handler(){
	 public void handleMessage(android.os.Message msg) {
		 switch(msg.what){
		 case MSG_WHAT_SHOW:
			 tvHour.setText(tenMSec/100/60/60+"");
			 tvMin.setText(tenMSec/100/60+"");
			 tvSec.setText(tenMSec/100+"");
			 tvMSec.setText(tenMSec%100+"");
			 break;
		default:
			break;
		 }
	 };
  };
  private void startWatchTimer(){
	  if(timerTask == null){
		  timerTask = new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				tenMSec ++;
			}
		};
		timer.schedule(timerTask, 10, 10);
	  }
  }
 private void stopTimer(){
	 if(timerTask != null){
		 timerTask.cancel();
		 timerTask = null;
	 }
 }
public void onDestroy() {
	// TODO Auto-generated method stub
	//timer.cancel();
}
}
