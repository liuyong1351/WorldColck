package com.example.worldcolck;

import java.util.Calendar;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TimeView extends LinearLayout {

	public TimeView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public TimeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	
	public TimeView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
   @Override
protected void onFinishInflate() {  //View类回调该方法
	// TODO Auto-generated method stub
	super.onFinishInflate();
	
	tvTime = (TextView) findViewById(R.id.tvTime);
	tvTime.setText("Hello");
	timeHandler.sendEmptyMessage(0);//发送一个空的消息
}
   @Override
protected void onVisibilityChanged(View changedView, int visibility) {
	// TODO Auto-generated method stub
	super.onVisibilityChanged(changedView, visibility);
	if(visibility==View.VISIBLE) //如果重新可见了发送一次消息
	{
		timeHandler.sendEmptyMessage(0);//发送一个空的消息
	}else
	{
		timeHandler.removeMessages(0);
	}	
}
   private void refreshTime(){
	 //  System.out.println(">>>>>>>>>>>>>");
	   Calendar c = Calendar.getInstance();//得到一个日历变量
	   //按照一定的输出格式输出字符
	   tvTime.setText(String.format("%2d:%2d:%2d", c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),c.get(Calendar.SECOND)));
   }
  private Handler timeHandler = new Handler(){//绑定到住线程的Looper和MessageQueue
	  public void handleMessage(android.os.Message msg) {
		 refreshTime(); //更新时间
		 if(getVisibility()==View.VISIBLE)//如果可见就更新时间
		 {	 
			 timeHandler.sendEmptyMessageDelayed(0, 1000);//延迟1s发送空消息
		 }
	  };
  };
   private TextView tvTime;
}
