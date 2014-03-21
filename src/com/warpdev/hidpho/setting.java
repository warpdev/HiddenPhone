package com.warpdev.hidpho;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.io.*;

public class setting extends Activity
{
	private EditText mEdit;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
	}
	public void setck(View v)
	{
		mEdit=(EditText)findViewById(R.id.mEdit);
		try{
		
		FileOutputStream fos = openFileOutput("room.txt", Context.MODE_WORLD_READABLE);
		String str = mEdit.getText().toString();
		fos.write(str.getBytes());
		fos.close();
		mEdit.setText("변경 완료");
	} catch (Exception e) {
		;
	}
	
		
	}
}

