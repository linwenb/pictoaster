package com.pictoaster.www.retouch.text;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.pictoaster.www.R;
import com.pictoaster.www.retouch.Retouch;

public class TextActivity extends Activity {
	/** Called when the activity is first created. */
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.retouch_text_main);
	        
	        Bundle fieldresults = this.getIntent().getExtras();
		    String path = fieldresults.getString("bm");
		    
		    final Bitmap bmp1 = BitmapFactory.decodeFile(path);

	        final ImageView imgView1 = (ImageView)findViewById(R.id.img);
	    	imgView1.setImageBitmap(bmp1);

	        Button btnEditT = (Button) findViewById(R.id.btn_edit_text);
	        btnEditT.setOnTouchListener(new Button.OnTouchListener(){
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
	        btnEditT.setOnClickListener(new Button.OnClickListener() {
	        	
	        	@Override
	        	public void onClick(View v) {
	        		Intent intent = new Intent(TextActivity.this, EditT.class);
	        		Bundle bundle =  new Bundle();
	        		
	        		Bundle bundle1 = TextActivity.this.getIntent().getExtras();
	                String path = bundle1.getString("bm");
	                int download = bundle1.getInt("download");
	                bundle.putInt("download", download);
	    		    bundle.putString("bm", path);
	    		    bundle.putString("act", "TextActivity");
	        		intent.putExtras(bundle);
	        		if(bmp1.isRecycled()==false) {
						bmp1.recycle();
						System.gc();
					}
	        		startActivity(intent);
	        		//finish();
	        	}
	        });
	        
	        ImageView ok = (ImageView) findViewById(R.id.text_check);
			ok.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(TextActivity.this, Retouch.class);
					Bundle bundle = TextActivity.this.getIntent().getExtras();
					intent.putExtras(bundle);
					if(bmp1.isRecycled()==false) {
						bmp1.recycle();
						System.gc();
					}
					startActivity(intent);
				}
			});
			
			ImageView cancel = (ImageView) findViewById(R.id.text_error);
			cancel.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(TextActivity.this, Retouch.class);
					Bundle bundle = TextActivity.this.getIntent().getExtras();
					intent.putExtras(bundle);
					if(bmp1.isRecycled()==false) {
						bmp1.recycle();
						System.gc();
					}
					startActivity(intent);
					System.exit(0);
				}
			});
	 }
}

