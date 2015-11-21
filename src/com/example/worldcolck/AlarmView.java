package com.example.worldcolck;

import java.util.Calendar;
import java.util.Date;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TimePicker;

public class AlarmView extends LinearLayout {
	private AlarmManager alarmManager;
      private final String TAG = "AlarmView";
	public AlarmView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	public AlarmView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
		// TODO Auto-generated constructor stub
	}

	public AlarmView(Context context) {
		super(context);
		init();
		// TODO Auto-generated constructor stub
	}
   public void init(){
	   alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);//����
	   //ϵͳ�����ӷ��񣬴���һ�����ӹ������
   }
	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
		Log.v(TAG, "onFinishInflate");
		adapter = new ArrayAdapter<AlarmView.AlarmData>(getContext(), android.R.layout.simple_expandable_list_item_1);
		btnAddAlarm = (Button) findViewById(R.id.btnAddAlram);
		lvAlarmList = (ListView) findViewById(R.id.lvAlarmlist);
		lvAlarmList.setAdapter(adapter);
		readSaveAlarmList();
		btnAddAlarm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i(TAG, "add alarm bttuon click");
				addAlarm();
			}
		});
		
		lvAlarmList.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position, long id) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(getContext()).setTitle("����ѡ��").setItems(new CharSequence[]{"ɾ��"},new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						switch(which){
						case 0:
							deleteAlarm(position);
							break;
						default :
							break;
						}
					}
				}).setNegativeButton("ȡ��", null).show();
				return true;
			}
		});
		
}
	
	private void deleteAlarm(int position){
		AlarmData ad = adapter.getItem(position);
		adapter.remove(ad);
		saveAlarmList();
		alarmManager.cancel(PendingIntent.getBroadcast(getContext(), ad.getId(),
				new Intent(getContext(),AlarmReceiver.class), 0));
		/*SharedPreferences sp = getContext().getSharedPreferences(AlarmView.class.getName(),Context.MODE_PRIVATE );
		Editor editor = sp.edit();
		editor.remove(KEY_ALARM_LIST);
		editor.commit();*/
	}
  public void addAlarm(){
	//TODO
	//�������������onTimeSet�¼�����Android4.1�����������������Ϊ�ڵ�ok��ʱ������һ�Σ�������TimePickerDialog��ʱ��ϵͳ����
	//onStop������ʱ������һ�Σ�����Ҫ��дonStop����ȥ��super.onStop������
/*
	Calendar c = Calendar.getInstance();
	new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
		
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub
			Calendar calendar = Calendar.getInstance(); //����һ��Calendar���� ���������趨������
			calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);//����Сʱ
			calendar.set(Calendar.MINUTE,minute);//���÷���
			
			Calendar currentTime = Calendar.getInstance();//����һ��Calendar���������ߵ�ǰ��ʱ��
			if(calendar.getTimeInMillis()<=currentTime.getTimeInMillis()){//������õ�����ʱ��ȵ�ǰ��ʱ��СҪ����һ��
				calendar.setTimeInMillis(currentTime.getTimeInMillis()+24*60*60*1000);
			}
			Log.i(TAG,"add AlarmData");
			adapter.add(new AlarmData(calendar.getTimeInMillis()));//�����õ�������ӵ���������
		}
	}, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();*/
	

	Calendar c = Calendar.getInstance();
	new MyTimePickerDailog(getContext(), new MyTimePickerDailog.OnTimeSetListener() {
		
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub
			Calendar calendar = Calendar.getInstance(); //����һ��Calendar���� ���������趨������
			calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);//����Сʱ
			calendar.set(Calendar.MINUTE,minute);//���÷���
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND,0);
			
			Calendar currentTime = Calendar.getInstance();//����һ��Calendar���������ߵ�ǰ��ʱ��
			if(calendar.getTimeInMillis()<=currentTime.getTimeInMillis()){//������õ�����ʱ��ȵ�ǰ��ʱ��СҪ����һ��
				calendar.setTimeInMillis(currentTime.getTimeInMillis()+24*60*60*1000);
			}
			Log.i(TAG,"add AlarmData");
	//		AlarmData alarmData = new AlarmData(calendar.getTimeInMillis());
	//		adapter.add(alarmData);//�����õ�������ӵ���������
//			alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, 
//					alarmData.getTime(), 
//					5*60*1000,
//					PendingIntent.getBroadcast(getContext(), alarmData.getId(), new Intent(getContext(),AlarmReceiver.class), 0));//�����ӵ�ʱ�䵽�ˣ�����һ���㲥
			
			AlarmData ad = new AlarmData(calendar.getTimeInMillis());
			adapter.add(ad);
			alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, 
					ad.getTime(), 
					5*60*1000, 
					PendingIntent.getBroadcast(getContext(), ad.getId(), new Intent(getContext(), AlarmReceiver.class), 0));
			saveAlarmList();
		}
	}, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
	
}
private class MyTimePickerDailog extends TimePickerDialog{

	public MyTimePickerDailog(Context context, OnTimeSetListener callBack,
			int hourOfDay, int minute, boolean is24HourView) {
		super(context, callBack, hourOfDay, minute, is24HourView);
		// TODO Auto-generated constructor stub
	}

	public MyTimePickerDailog(Context context, int theme,
			OnTimeSetListener callBack, int hourOfDay, int minute,
			boolean is24HourView) {
		super(context, theme, callBack, hourOfDay, minute, is24HourView);
	// TODO Auto-generated constructor stub
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
	//	super.onStop(); //Ϊ�˷�ֹ���ε���TimePickDailog
	}
	
}
private void saveAlarmList(){//�����ݱ��浽���ص�SharedPreference����ȥ
	Editor editor = getContext().getSharedPreferences(AlarmView.class.getName(), Context.MODE_PRIVATE).edit();
	StringBuffer sb  = new StringBuffer();
		
	for(int i=0;i<adapter.getCount();i++){
		sb.append(adapter.getItem(i).getTime()).append(",");
	}
	//System.out.println("22222");
       if(sb.length()>1)
       {	   
		String content = sb.toString().substring(0, sb.length()-1);
		editor.putString(KEY_ALARM_LIST,content);
		System.out.println(content);
       }
       else
       {
    	   editor.putString(KEY_ALARM_LIST, null);
       } 
		editor.commit();

}
private void readSaveAlarmList(){//��ȡ������SharedPreference���������,Ȼ����ӵ�����������ȥ
	SharedPreferences sp = getContext().getSharedPreferences(AlarmView.class.getName(), Context.MODE_PRIVATE);
	String content = sp.getString(KEY_ALARM_LIST, null);
	if(content !=null)
	{
		String[] timeStrings = content.split(",");
		for (String string : timeStrings) {
			adapter.add(new AlarmData(Long.parseLong(string)));
		}
	}	
}
 private Button btnAddAlarm;
 private ListView lvAlarmList; //listView�ı���
 private static final String KEY_ALARM_LIST="alarm_list";
 private ArrayAdapter<AlarmData> adapter;
 private static class AlarmData{ //����һ���ڲ������࣬Ҳ���������������
	 private long time=0;
	 private String timeLabel;
	 private Calendar date;
	 public String getTimeLabel() {
		return timeLabel;
	}

	public AlarmData(long time){
		 this.time=time;
		 date = Calendar.getInstance();
		 date.setTimeInMillis(time);
		 //timeLabel = date.get(Calendar.HOUR_OF_DAY)+":"+date.get(Calendar.MINUTE);
		 timeLabel = String.format("%d��%d��  %d:%d",
				 date.get(Calendar.MONTH)+1,
				 date.get(Calendar.DAY_OF_MONTH),
				 date.get(Calendar.HOUR_OF_DAY),
				 date.get(Calendar.MINUTE));
	 }
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getTimeLabel();
	}
	public int getId(){
		return  (int)(getTime()/1000/60);
	}
	 public long getTime(){
		 return time;
	 }
 }
}
