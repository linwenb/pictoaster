package com.pictoaster.www;

import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.pictoaster.www.montage.SelectMulti;

public class home_montage extends Activity implements OnClickListener  {

    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.home_montage_view);
	    
		String FILENAME = "down_file.txt";
		String content = "0000000000000000";
		File writeFile = new File(Environment.getExternalStorageDirectory(), FILENAME);
		
		if(!writeFile.exists())
		{
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {

			try {
				FileOutputStream fos = new FileOutputStream(writeFile);
				fos.write(content.getBytes());
				fos.close();
				/*Toast.makeText(home.this, content,
                       Toast.LENGTH_LONG).show();*/
			} catch (Exception e) {
                Toast.makeText(home_montage.this, "Write Failed",
                       Toast.LENGTH_SHORT).show();
            }
			} else {
        	//sdcard doesn't exist or can't write it right now
            Toast.makeText(home_montage.this,
                    "Can't Write", Toast.LENGTH_SHORT).show();
			}
		}
		
        Button btn = (Button)findViewById(R.id.takeimage);
        btn.setOnClickListener(this);
        btn.setOnTouchListener(new Button.OnTouchListener(){
        	   @Override
        	   public boolean onTouch(View v, MotionEvent event) {
        	    if(event.getAction() == MotionEvent.ACTION_DOWN){   
        	                    v.setBackgroundResource(R.drawable.select2);   
        	                    Log.i("TestAndroid Button", "MotionEvent.ACTION_DOWN");
        	                }   
        	                else if(event.getAction() == MotionEvent.ACTION_UP){   
        	                    v.setBackgroundResource(R.drawable.select); 
        	                    Log.i("TestAndroid Button", "MotionEvent.ACTION_UP");
        	                } 
        	    return false;
        	   }
        	  });
	}
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.takeimage:
			Intent intent = new Intent(home_montage.this, SelectMulti.class);
			startActivity(intent);
			break;
		}
	}

}
