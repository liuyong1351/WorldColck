package com.example.worldcolck;

import java.util.Timer;
import java.util.TimerTask;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class TimerView extends LinearLayout {
	private Button btnStart,btnPause,btnResume,btnRest;
	private EditText edtHour,edtMiute,edtSecond;
	public TimerView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public TimerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public TimerView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
    @Override
    protected void onFinishInflate() {
    	// TODO Auto-generated method stub
    	super.onFinishInflate();
    	btnStart = (Button) findViewById(R.id.btnStart);
    	btnStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startTimer();
				btnStart.setVisibility(View.GONE);
				btnPause.setVisibility(View.VISIBLE);
				btnRest.setVisibility(View.VISIBLE);
			}
		});
    	btnPause = (Button) findViewById(R.id.btnPause);
    	btnPause.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stopTime();
				btnPause.setVisibility(View.GONE);
				btnResume.setVisibility(View.VISIBLE);
				
			}
		});
    	btnResume = (Button) findViewById(R.id.btnResume);
    	btnResume.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startTimer();
				btnPause.setVisibility(View.VISIBLE);
				btnResume.setVisibility(View.GONE);
				
			}
		});
    	btnRest = (Button) findViewById(R.id.btnReset);
    	btnRest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stopTime();
				edtHour.setText("00");
				edtMiute.setText("00");
				edtSecond.setText("00");
				
				btnStart.setVisibility(View.VISIBLE);
		        btnPause.setVisibility(View.GONE);
		        btnResume.setVisibility(View.GONE);
		        btnRest.setVisibility(View.GONE);
			}
		});
    	
        edtHour = (EditText) findViewById(R.id.etHour);
        edtHour.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if(!TextUtils.isEmpty(s))
				{	
					int value = Integer.parseInt(s.toString());
					if(value >59)
					{	
						edtHour.setText("59");
					}	
					else if(value < 0)
					{
						edtHour.setText("00");
					}
				}
				checkToEnableBtnStart();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
        edtMiute = (EditText) findViewById(R.id.etMinute);
        edtMiute.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if(!TextUtils.isEmpty(s))
				{		
					int value = Integer.parseInt(s.toString());
					if(value >59)
					{	
						edtMiute.setText("59");
					}	
					else if(value < 0)
					{
						edtMiute.setText("00");
					}
				}
				checkToEnableBtnStart();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
        edtSecond = (EditText) findViewById(R.id.etSecond);
        edtSecond.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if(!TextUtils.isEmpty(s))
				{	
					int value = Integer.parseInt(s.toString());
					if(value >59)
					{	
						edtSecond.setText("59");
					}	
					else if(value < 0)
					{
						edtSecond.setText("00");
					}
				}
				checkToEnableBtnStart();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
        
        edtHour.setText("00");
        edtMiute.setText("00");
        edtSecond.setText("00");
        
        btnStart.setVisibility(View.VISIBLE);
        btnStart.setEnabled(false);
        btnPause.setVisibility(View.GONE);
        btnResume.setVisibility(View.GONE);
        btnRest.setVisibility(View.GONE);
        
    }
    private Timer timer = new Timer();
    private TimerTask timerTask = null;
    private int allTime = 0;
    private static final int MSG_WHAT_TIME_IS_UP = 1;//时间为零事件
    private static final int MSG_WHAT_TIME_TICK = 2;
    
    private Handler handler = new Handler(){
    	public void handleMessage(android.os.Message msg) {
			// TODO Auto-generated method stub
    		switch(msg.what){
    		case MSG_WHAT_TIME_IS_UP:
    			//Log.v("TimerView", "MSG_WHAT_TIME_IS_UP");
    			new AlertDialog.Builder(getContext()).setTitle("Time is up").setMessage("Time is up").
				setNegativeButton("Cancel", null).show();
    			 btnStart.setVisibility(View.VISIBLE);
    		        btnPause.setVisibility(View.GONE);
    		        btnResume.setVisibility(View.GONE);
    		        btnRest.setVisibility(View.GONE);
    			break;
    		case MSG_WHAT_TIME_TICK:
    			int hour = allTime/60/60;
    			int mitue = (allTime/60)%60;
    			int second = allTime%60;
    			 
    			edtHour.setText(hour+"");
    			edtMiute.setText(mitue+"");
    			edtSecond.setText(second+"");
    			
    			break;
    		default:
    			break;
    		}
		}
    };
    
    private void startTimer(){//start按键的处理函数
    	if(timerTask == null){
    		allTime = Integer.valueOf(edtHour.getText().toString())*60*60 +
    				 Integer.valueOf(edtMiute.getText().toString())*60 +
    				 Integer.valueOf(edtSecond.getText().toString());
    		timerTask = new TimerTask() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					allTime -- ;
				//	Log.v("TimerView", allTime+"");
					handler.sendEmptyMessage(MSG_WHAT_TIME_TICK);//时间每减去1s，更新UI控件上的显示
					if(allTime <=0){
						
						handler.sendEmptyMessage(MSG_WHAT_TIME_IS_UP);//发送what信息给ui线程的handler
						stopTime();
					}
				}
			};
			timer.schedule(timerTask, 1000, 1000);//开启定时器，1s钟调度一次timerTask
    	}
    }
    
    private void stopTime(){
    	if(timerTask != null){
    		timerTask.cancel();
    		timerTask = null;
    	}
    }
    

    private void checkToEnableBtnStart(){//当有值并且大于0的时候，start按键才有效
    	btnStart.setEnabled(((!TextUtils.isEmpty(edtHour.getText()))&&Integer.parseInt(edtHour.getText().toString())>0)||
    			((!TextUtils.isEmpty(edtMiute.getText()))&&Integer.parseInt(edtMiute.getText().toString())>0)||
    			((!TextUtils.isEmpty(edtSecond.getText()))&&Integer.parseInt(edtSecond.getText().toString())>0));
    }
}
