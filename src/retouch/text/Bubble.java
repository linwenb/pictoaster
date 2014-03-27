package com.pictoaster.www.retouch.text;

import java.io.File;
import java.io.FileInputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pictoaster.www.R;


public class Bubble extends Activity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//»•µÙ±ÍÃ‚¿∏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.text_bubble);       

        final Bundle bundle = getIntent().getExtras();
        
        ImageView img0 = (ImageView) findViewById(R.id.img0);
        ImageView img1 = (ImageView) findViewById(R.id.img1);
        ImageView img2 = (ImageView) findViewById(R.id.img2);
        ImageView img3 = (ImageView) findViewById(R.id.img3);
        ImageView img4 = (ImageView) findViewById(R.id.img4);
        ImageView img5 = (ImageView) findViewById(R.id.img5);
        
		final Intent intent = new Intent(Bubble.this, Text.class);
        final Bundle bun1 = new Bundle();
		bun1.putString("text", bundle.getString("tText"));
		bun1.putFloat("scaleX", bundle.getFloat("tScaleX"));
        bun1.putFloat("scaleY", bundle.getFloat("tScaleY"));
		bun1.putInt("color", bundle.getInt("tColor"));
		bun1.putInt("l", bundle.getInt("tl"));
        bun1.putInt("r", bundle.getInt("tr"));
        bun1.putInt("t", bundle.getInt("tt"));
        bun1.putInt("b", bundle.getInt("tb"));
		bun1.putString("bm", bundle.getString("bm"));
		bun1.putInt("download", bundle.getInt("download"));
		
		String FILENAME = "down_file.txt";
		String content = "";
		int NUMBER_SIMPLE = 5;
		int NUMBER_SPECIAL = 3;
		int NUMBER_TEMPLATE = 3;
		int NUMBER_DIALOG = 5;
		int NUMBER_TOTAL = 16;

		File readFile = new File(Environment.getExternalStorageDirectory(), FILENAME);
		
		if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            try {
                FileInputStream inputStream = new FileInputStream(readFile);
                byte[] b = new byte[inputStream.available()];
                inputStream.read(b);
                content += new String(b);
                /*Toast.makeText(Bubble.this, "Read Success",
                        Toast.LENGTH_LONG).show();*/
            } catch (Exception e) {
                Toast.makeText(Bubble.this, "Read Failed",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            //sdcard doesn't exist or can't read it right now
            Toast.makeText(Bubble.this,
                    "Can't Read", Toast.LENGTH_SHORT).show();
        }
		
		if(content.isEmpty())
		{
			content = "0000000000000000";
		}
        
        img0.setOnClickListener(new ImageView.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				bun1.putInt("bub", 0);
				intent.putExtras(bun1);
				startActivity(intent);				
			}
        });
        
        img1.setOnClickListener(new ImageView.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				bun1.putInt("bub", 1);				
				intent.putExtras(bun1);
				startActivity(intent);				
			}
        });
        
        img2.setOnClickListener(new ImageView.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bun1.putInt("bub", 2);
				intent.putExtras(bun1);
				startActivity(intent);				
			}
        });
        
        img3.setOnClickListener(new ImageView.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bun1.putInt("bub", 3);
				intent.putExtras(bun1);
				startActivity(intent);				
			}
        });
        
        img4.setOnClickListener(new ImageView.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bun1.putInt("bub", 4);
				intent.putExtras(bun1);
				startActivity(intent);			
			}
        });
                
        img5.setOnClickListener(new ImageView.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bun1.putInt("bub", 5);				
				intent.putExtras(bun1);
				startActivity(intent);	
				finish();
			}
        });
        	
        if(content.charAt(NUMBER_SIMPLE+NUMBER_SPECIAL+NUMBER_TEMPLATE) == '1'){
	        ImageView img6 = (ImageView) findViewById(R.id.img6);
	        img6.setVisibility(View.VISIBLE);
	                
	        img6.setOnClickListener(new ImageView.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					bun1.putInt("bub", 6);				
					intent.putExtras(bun1);
					startActivity(intent);	
					finish();
				}
	        });
        }
        
        if(content.charAt(NUMBER_SIMPLE+NUMBER_SPECIAL+NUMBER_TEMPLATE + 1) == '1'){
	        ImageView img7 = (ImageView) findViewById(R.id.img7);
	        img7.setVisibility(View.VISIBLE);
	                
	        img7.setOnClickListener(new ImageView.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					bun1.putInt("bub", 7);				
					intent.putExtras(bun1);
					startActivity(intent);	
					finish();
				}
	        });
        }
        
        if(content.charAt(NUMBER_SIMPLE+NUMBER_SPECIAL+NUMBER_TEMPLATE + 2) == '1'){
	        ImageView img8 = (ImageView) findViewById(R.id.img8);
	        img8.setVisibility(View.VISIBLE);
	                
	        img8.setOnClickListener(new ImageView.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					bun1.putInt("bub", 8);				
					intent.putExtras(bun1);
					startActivity(intent);	
					finish();
				}
	        });
        }
        
        if(content.charAt(NUMBER_SIMPLE+NUMBER_SPECIAL+NUMBER_TEMPLATE + 3) == '1'){
	        ImageView img9 = (ImageView) findViewById(R.id.img9);
	        img9.setVisibility(View.VISIBLE);
	                
	        img9.setOnClickListener(new ImageView.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					bun1.putInt("bub", 9);				
					intent.putExtras(bun1);
					startActivity(intent);	
					finish();
				}
	        });
        }
        
        if(content.charAt(NUMBER_SIMPLE+NUMBER_SPECIAL+NUMBER_TEMPLATE + 4) == '1'){
	        ImageView img10 = (ImageView) findViewById(R.id.img10);
	        img10.setVisibility(View.VISIBLE);
	                
	        img10.setOnClickListener(new ImageView.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					bun1.putInt("bub", 10);				
					intent.putExtras(bun1);
					startActivity(intent);	
					finish();
				}
	        });
        }
	}
}