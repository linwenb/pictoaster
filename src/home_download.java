package com.pictoaster.www;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.pictoaster.www.download.DownloadHome;

public class home_download extends Activity{

    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        setContentView(R.layout.home_download_view);
        setupButtonOnClickListeners();
    }
        
    private void setupButtonOnClickListeners(){

    	Button retouch = (Button) findViewById(R.id.getin);
    	retouch.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Intent intent = new Intent(home_download.this, DownloadHome.class);
				 startActivity(intent);
			}
    	});
    	retouch.setOnTouchListener(new Button.OnTouchListener(){
 		   @Override
 		   public boolean onTouch(View v, MotionEvent event) {
 		    if(event.getAction() == MotionEvent.ACTION_DOWN){   
 		                    v.setBackgroundResource(R.drawable.download1);   
 		                    Log.i("TestAndroid Button", "MotionEvent.ACTION_DOWN");
 		                }   
 		                else if(event.getAction() == MotionEvent.ACTION_UP){   
 		                    v.setBackgroundResource(R.drawable.download); 
 		                    Log.i("TestAndroid Button", "MotionEvent.ACTION_UP");
 		                } 
 		    return false;
 		   }
    	});
    }
}
