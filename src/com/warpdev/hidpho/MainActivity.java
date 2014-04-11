package com.warpdev.hidpho;

import android.app.*;
import android.content.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import com.google.android.gcm.*;
import com.google.android.gcm.GCMRegistrar;
import java.io.*;
import com.warpdev.hidpho.GCMIntentService;
import com.warpdev.hidpho.GCMTest;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
	private Context appContext = null;
    @Override
	//private Button set;
	
    public void onCreate(Bundle savedInstanceState)
	{
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		init();
		gcm();
		
	}
	 //메뉴를 생성하기 위한 콜백 메소드
    public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflate = getMenuInflater();
		inflate.inflate(R.menu.menu,menu);
		return super.onCreateOptionsMenu(menu);
    }
	public boolean onOptionsItemSelected(MenuItem item) {
		//String text = "";
		switch(item.getItemId()){
			case R.id.item:
				Intent i=new Intent(MainActivity.this,setting.class);
				startActivity(i);
				break;
		}return false;
	}
	public void sendl(View v)
	{
		String a,b,c;
		TextView st;
		TextView sec;
		TextView rd;
		String s;
		init();
		gcm();
		try
		{
			GCMTest.main(null);
					}
		catch (Exception e)
		{Toast.makeText(appContext, "Exist Registration Id: ", Toast.LENGTH_LONG).show();
		}
		
		st = (TextView)findViewById(R.id.frt);
		sec=(TextView)findViewById(R.id.sec);
		rd=(TextView)findViewById(R.id.rd);
		a=st.getText().toString();
		EditText mEdit=(EditText)findViewById(R.id.mEdit);
		//byte data[] = null; // 파일스트림을 담을 그릇
		//FileInputStream open;  // 자바에서 제공하는 파일 입력 스트림
		//String result;
		try {
			FileInputStream fis = openFileInput("room.txt");
			FileOutputStream fos = openFileOutput("st.txt", Context.MODE_WORLD_READABLE);
			byte[] data = new byte[fis.available()];
			while (fis.read(data) != -1) {;}
			fis.close();
			st.setText(new String(data));
		} catch (FileNotFoundException e) {
			;
		}
		catch (Exception e) {;}
		//rd.setText(sec.getText().toString());
		//sec.setText(a);
		
		
	}
	
	private void init(){
		appContext = getApplicationContext();
	}
	private void gcm(){
		TextView textView = (TextView)findViewById(R.id.frt);
		try {
			// GCM이 가능한 디바이스인지 체크
			GCMRegistrar.checkDevice(appContext);
		} catch (Exception e) {
			textView.setText("16464");
			return;
		}
		// RegistrationID 발급
		String regId = GCMRegistrar.getRegistrationId(appContext);

		/**
		 * Registration Id가 없는 경우(어플리케이션 최초 설치로 발급받은 적이 없거나,
		 * 삭제 후 재설치 등 SharedPreference에 저장된 Registration Id가 없는 경우가 이에 해당한다.)
		 */
	 	if(""==regId){
			/**
			 * 3.RegstrationId가 없는 경우 GCM Server로 Regsitration ID를 발급 요청한다.
			 * 발급 요청된 Registration ID는 SharedPreference에 저장된다.
			 */
			GCMRegistrar.register(appContext, "326810664960");

			//SharedPreference에 저장된 Registration Id가 존재하는 경우
		}else{
			Toast.makeText(appContext, "Exist Registration Id: " + regId, Toast.LENGTH_LONG).show();
		}
		textView.setText(regId);
	}
	@Override
	protected void onDestroy() {
		// 앱이 종료시에 호출된다.
		GCMRegistrar.onDestroy(appContext);
		super.onDestroy();
	}
}
