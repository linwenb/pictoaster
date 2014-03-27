package com.pictoaster.www.download;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pictoaster.www.R;
import com.pictoaster.www.home;

public class Download extends Activity implements View.OnClickListener{
    //ImageView test;
	Button download1;
	Button download2;
	Button download3;
	Button download4;
	Button dl_return;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}


	 

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
        setContentView(R.layout.download);
        
        //test = (ImageView) findViewById(R.id.frame6);
        download1 = (Button) findViewById(R.id.download1);
        download2 = (Button) findViewById(R.id.download2);
        download3 = (Button) findViewById(R.id.download3);
        download4 = (Button) findViewById(R.id.download4);
        download1.setOnClickListener(this);
        download2.setOnClickListener(this);
        download3.setOnClickListener(this);
        download4.setOnClickListener(this);
        
        dl_return = (Button) findViewById(R.id.return_download);
        dl_return.setOnClickListener(new View.OnClickListener(){
        	@Override
			public void onClick(View v) {
        		Intent intent = new Intent(Download.this, home.class);
        		Bundle bundle = new Bundle();
        		intent.putExtras(bundle);
    			startActivity(intent);        		
        	}
        });
        
    }
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = null;
		switch(v.getId()) {
		case R.id.download1:
			intent = new Intent(Download.this, SimFrame.class);
			break;
		case R.id.download2:
			intent = new Intent(Download.this, SpeFrame.class);
			break;
		case R.id.download3:
			intent = new Intent(Download.this, MontTemplate.class);
			break;
		case R.id.download4:
			intent = new Intent(Download.this, ConvBubble.class);
			break;
		}
		startActivity(intent);
	}

}
