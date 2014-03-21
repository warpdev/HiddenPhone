package com.warpdev.hidpho;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.io.*;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
	//private Button set;
	
    public void onCreate(Bundle savedInstanceState)
	{
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
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
		
		st=(TextView)findViewById(R.id.frt);
		sec=(TextView)findViewById(R.id.sec);
		rd=(TextView)findViewById(R.id.rd);
		a=st.getText().toString();
		EditText mEdit=(EditText)findViewById(R.id.mEdit);
		//byte data[] = null; // 파일스트림을 담을 그릇
		//FileInputStream open;  // 자바에서 제공하는 파일 입력 스트림
		//String result;
		try {
			FileInputStream fis = openFileInput("room.txt");
			byte[] data = new byte[fis.available()];
			while (fis.read(data) != -1) {;}
			fis.close();
			st.setText(new String(data));
		} catch (FileNotFoundException e) {
			;
		}
		catch (Exception e) {;}
		rd.setText(sec.getText().toString());
		sec.setText(a);
		
	}
}
