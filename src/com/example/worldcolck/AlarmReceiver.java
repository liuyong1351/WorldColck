package com.example.worldcolck;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("����ִ����");
		AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		am.cancel(PendingIntent.getBroadcast(context,
				getResultCode(),
				new Intent(context,AlarmReceiver.class),
				0));
		Intent in = new Intent(context,PlayAlarmAty.class);
		in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//���ñ�־������һ���µ�activity����
		context.startActivity(in);
	}

}
