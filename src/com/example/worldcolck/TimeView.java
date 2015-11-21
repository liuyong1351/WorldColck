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
protected void onFinishInflate() {  //View��ص��÷���
	// TODO Auto-generated method stub
	super.onFinishInflate();
	
	tvTime = (TextView) findViewById(R.id.tvTime);
	tvTime.setText("Hello");
	timeHandler.sendEmptyMessage(0);//����һ���յ���Ϣ
}
   @Override
protected void onVisibilityChanged(View changedView, int visibility) {
	// TODO Auto-generated method stub
	super.onVisibilityChanged(changedView, visibility);
	if(visibility==View.VISIBLE) //������¿ɼ��˷���һ����Ϣ
	{
		timeHandler.sendEmptyMessage(0);//����һ���յ���Ϣ
	}else
	{
		timeHandler.removeMessages(0);
	}	
}
   private void refreshTime(){
	 //  System.out.println(">>>>>>>>>>>>>");
	   Calendar c = Calendar.getInstance();//�õ�һ����������
	   //����һ���������ʽ����ַ�
	   tvTime.setText(String.format("%2d:%2d:%2d", c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),c.get(Calendar.SECOND)));
   }
  private Handler timeHandler = new Handler(){//�󶨵�ס�̵߳�Looper��MessageQueue
	  public void handleMessage(android.os.Message msg) {
		 refreshTime(); //����ʱ��
		 if(getVisibility()==View.VISIBLE)//����ɼ��͸���ʱ��
		 {	 
			 timeHandler.sendEmptyMessageDelayed(0, 1000);//�ӳ�1s���Ϳ���Ϣ
		 }
	  };
  };
   private TextView tvTime;
}
