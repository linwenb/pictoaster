package com.pictoaster.www.retouch.text;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pictoaster.www.R;

public class EditT extends Activity{
	 Button clearbtn;
	 Button gobtn;
	 EditText et;
	 TextView tv;
	 int bub;
	 final int MAX_LENGTH = 50;
	 int Rest_Length = MAX_LENGTH;
	 int col;
	 
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//È¥µô±êÌâÀ¸
	        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	        WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        setContentView(R.layout.text_edit_text);
	        tv =(TextView)findViewById(R.id.tv);
	        et = (EditText)findViewById(R.id.et);
	        
	        final Bundle bun = getIntent().getExtras(); 

	        et.setText(bun.getString("tText", null));
	        et.setTextColor(bun.getInt("tColor", Color.BLUE));
	        col = et.getCurrentTextColor();
	        et.setTextColor(Color.BLUE);
	        bub = bun.getInt("tBub");

	        et.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					if(Rest_Length>0){
						Rest_Length = MAX_LENGTH - et.getText().length();
					}
				}
				
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {
					tv.setText(Rest_Length+" letters left");
					
				}
				
				@Override
				public void afterTextChanged(Editable s) {
					tv.setText(Rest_Length+" letters left");
				}
			});

	        clearbtn = (Button)findViewById(R.id.clearbtn);
	        clearbtn.setOnTouchListener(new Button.OnTouchListener(){
				   @Override
				   public boolean onTouch(View v, MotionEvent event) {
				    if(event.getAction() == MotionEvent.ACTION_DOWN){   
				        v.setBackgroundColor(Color.LTGRAY);   
				        Log.i("TestAndroid Button", "MotionEvent.ACTION_DOWN");
				    }   
				    else if(event.getAction() == MotionEvent.ACTION_UP){   
				    	v.setBackgroundColor(Color.TRANSPARENT); 
				        Log.i("TestAndroid Button", "MotionEvent.ACTION_UP");
				                } 
				    return false;
				   }
			});
	        clearbtn.setOnClickListener(new Button.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					et.setText("");
					Rest_Length = MAX_LENGTH;
				}
			});
	        gobtn = (Button)findViewById(R.id.gobtn);
	        gobtn.setOnTouchListener(new Button.OnTouchListener(){
				   @Override
				   public boolean onTouch(View v, MotionEvent event) {
				    if(event.getAction() == MotionEvent.ACTION_DOWN){   
				        v.setBackgroundColor(Color.LTGRAY);   
				        Log.i("TestAndroid Button", "MotionEvent.ACTION_DOWN");
				    }   
				    else if(event.getAction() == MotionEvent.ACTION_UP){   
				    	v.setBackgroundColor(Color.TRANSPARENT); 
				        Log.i("TestAndroid Button", "MotionEvent.ACTION_UP");
				                } 
				    return false;
				   }
			});
	        gobtn.setOnClickListener(new Button.OnClickListener() {
	        	
	        	@Override
	        	public void onClick(View v) {
	        		Intent intent = new Intent(EditT.this, Text.class); 
	        		Bundle bundle = new Bundle();  
	                bundle.putString("text", et.getText().toString());
	                
	                bundle.putFloat("scaleX", bun.getFloat("tScaleX", (float) 1.0));
	                bundle.putFloat("scaleY", bun.getFloat("tScaleY", (float) 1.0));
		            bundle.putInt("color", col);
		            
		            bundle.putInt("l", bun.getInt("tl"));
	                bundle.putInt("r", bun.getInt("tr"));
	                bundle.putInt("t", bun.getInt("tt"));
	                bundle.putInt("b", bun.getInt("tb"));
	                
	                
	                //Bundle bundle1 = EditT.this.getIntent().getExtras();
	                String path = bun.getString("bm");
	                bundle.putString("bm", path);
	                int download = bun.getInt("download");
	                bundle.putInt("download", download);
	                bundle.putInt("bub", bub);
	                intent.putExtras(bundle);
	                System.gc();
	                startActivity(intent); 
	                System.exit(0);
	        	}
	        }); 
	    }
}

