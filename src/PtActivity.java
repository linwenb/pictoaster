package com.pictoaster.www;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class PtActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        Thread logoTimer = new Thread(){
        	public void run(){
        		try{
        			int logoTimer = 0;
        			while(logoTimer < 1500){
        				sleep(100);
        				logoTimer = logoTimer + 100;
        			}
        			//startActivity(new Intent("com.pictoaster.www.home"));
        			Intent intent = new Intent();
        			intent.setClass(PtActivity.this, home.class);
        			Bundle bundle = new Bundle();
        			intent.putExtras(bundle);
        			startActivity(intent);
        		} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        		finally{
        			finish();
        		}
        	}
        };
        logoTimer.start();
    }

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
    
}
