package com.warpdev.hidpho;

import android.app.*;
import android.content.*;
import android.support.v4.app.*;
import android.util.*;
import com.google.android.gcm.*;
import com.warpdev.hidpho.*;

public class GCMIntentService extends GCMBaseIntentService
 {
	public GCMIntentService() {
		super("326810664960");
	}
	// GCM 오류 발생시 처리
	@Override
	protected void onError(Context arg0, String arg1) {
		Log.i("onError Call", "onError Call");
	}
	// GCM 수신시 처리
	@Override
	protected void onMessage(Context arg0, Intent arg1) {
		// 위 그림 설명
		String title = arg1.getExtras().getString("title");
		String contents = arg1.getExtras().getString("contents");

		NotificationManager mNotificationManager = (NotificationManager)
			this.getSystemService(Context.NOTIFICATION_SERVICE);
		// 메시지를 클릭시 MainActivity가 실행
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
																new Intent(this, MainActivity.class), 0);

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
			// 메시지 좌측의 아이콘 
			.setSmallIcon(R.drawable.ic_launcher)
			.setContentTitle(title)
			.setStyle(new NotificationCompat.BigTextStyle()
					  .bigText(title))
			.setContentText(contents);

		mBuilder.setContentIntent(contentIntent);
		mNotificationManager.notify(0, mBuilder.build());

	}
	
	// GCMRegistrar.getRegistrationId(context)가 실행되어 RegistrationID가 발급된 경우 콜백
	
	@Override
	protected void onRegistered(Context arg0, String arg1) {
		Log.i("onRegisteredCall", "onRegistered Call");
	}
	// GCMRegistrar.unregister(context) 호출로 RegistrationID를 해지한 경우 콜백
	@Override
	protected void onUnregistered(Context arg0, String arg1) {
		Log.i("onUnregistered Call", "onUnregisteredCall");
	}
}
