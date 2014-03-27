package com.pictoaster.www.download;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pictoaster.www.R;
import com.pictoaster.www.home;

public class Delete extends Activity implements View.OnClickListener{
    //ImageView test;
	Button delete1;
	Button delete2;
	Button delete3;
	Button delete4;
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
        setContentView(R.layout.download_delete);
        
        //test = (ImageView) findViewById(R.id.frame6);
        delete1 = (Button) findViewById(R.id.delete1);
        delete2 = (Button) findViewById(R.id.delete2);
        delete3 = (Button) findViewById(R.id.delete3);
        delete4 = (Button) findViewById(R.id.delete4);
        delete1.setOnClickListener(this);
        delete2.setOnClickListener(this);
        delete3.setOnClickListener(this);
        delete4.setOnClickListener(this);
        
        dl_return = (Button) findViewById(R.id.return_download);
        dl_return.setOnClickListener(new View.OnClickListener(){
        	@Override
			public void onClick(View v) {
        		Intent intent = new Intent(Delete.this, home.class);
        		Bundle bundle = new Bundle();
        		intent.putExtras(bundle);
    			startActivity(intent);        		
        	}
        });
        
    }
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch(v.getId()) {
		case R.id.delete1:
			intent = new Intent(Delete.this, DeleSimFrame.class);
			break;
		case R.id.delete2:
			intent = new Intent(Delete.this, DeleSpeFrame.class);
			break;
		case R.id.delete3:
			intent = new Intent(Delete.this, DeleTemp.class);
			break;
		case R.id.delete4:
			intent = new Intent(Delete.this, DeleBubble.class);
			break;
		}
		startActivity(intent);
	}

}
