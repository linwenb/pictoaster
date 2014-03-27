package com.pictoaster.www.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.pictoaster.www.R;
import com.pictoaster.www.home;

public class DeleSpeFrame extends Activity implements View.OnClickListener{
    ImageView test;
	ImageView download1;
	ImageView download2;
	ImageView download3;
	
	String FILENAME = "down_file.txt";
	String content = "0000000000000000";
	int NUMBER_SIMPLE = 5;
	int NUMBER_SPECIAL = 3;
	int NUMBER_TEMPLATE = 3;
	int NUMBER_DIALOG = 5;
	int NUMBER_TOTAL = 16;
	String get = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//»•µÙ±ÍÃ‚¿∏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.download_spe_frame);
        
        test = (ImageView) findViewById(R.id.spe2);
        download1 = (ImageView) findViewById(R.id.spe1);
        download2 = (ImageView) findViewById(R.id.spe2);
        download3 = (ImageView) findViewById(R.id.spe3);
        
        File readFile = new File(Environment.getExternalStorageDirectory(), FILENAME);
		
		if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            try {
                FileInputStream inputStream = new FileInputStream(readFile);
                byte[] b = new byte[inputStream.available()];
                inputStream.read(b);
                get = new String(b);
                //getIt = true;
                /*Toast.makeText(DeleSpeFrame.this, get,
                        Toast.LENGTH_LONG).show();*/
            } catch (Exception e) {
                Toast.makeText(DeleSpeFrame.this, "Read Failed",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            //sdcard doesn't exist or can't read it right now
            Toast.makeText(DeleSpeFrame.this,
                    "Can't Read", Toast.LENGTH_SHORT).show();
        }
        
        if(get.charAt(NUMBER_SIMPLE) == '0'){
        	download1.setVisibility(View.GONE);	        
        }
        if(get.charAt(NUMBER_SIMPLE + 1) == '0'){ 
        	download2.setVisibility(View.GONE);	        
        }
        if(get.charAt(NUMBER_SIMPLE + 2) == '0'){ 
        	download3.setVisibility(View.GONE);	        
        }
        
        download1.setOnClickListener(this);
        download2.setOnClickListener(this);
        download3.setOnClickListener(this);
    }
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int flag = 0;
		switch(v.getId())
		{
		case R.id.spe1:
			flag = 0;
			break;
		case R.id.spe2:
			flag = 1;
			break;
		case R.id.spe3:
			flag = 2;
			break;
		}
		
		File readFile = new File(Environment.getExternalStorageDirectory(), FILENAME);
		
		if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            try {
                FileInputStream inputStream = new FileInputStream(readFile);
                byte[] b = new byte[inputStream.available()];
                inputStream.read(b);
                get = new String(b);
                //getIt = true;
                /*Toast.makeText(DeleSpeFrame.this, get,
                        Toast.LENGTH_LONG).show();*/
            } catch (Exception e) {
                Toast.makeText(DeleSpeFrame.this, "Read Failed",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            //sdcard doesn't exist or can't read it right now
            Toast.makeText(DeleSpeFrame.this,
                    "Can't Read", Toast.LENGTH_SHORT).show();
        }
		//content = content + flag;
		
		char tem[] = content.toCharArray();
		if(!get.isEmpty())
		{
			for(int i = 0 ; i < get.length(); i++)
			{
					if(i == NUMBER_SIMPLE+flag)
					{
						tem[i] = '0';
					}
					else
					{
						tem[i] = get.charAt(i);
					}
			}
			
			content = new String(tem);
		}

		/*char [] tem = content.toCharArray();
		tem[NUMBER_SIMPLE+NUMBER_SPECIAL+NUMBER_TEMPLATE+flag - 1] = '1';
		content = new String(tem);*/
		
		File delete = new File(Environment.getExternalStorageDirectory(), FILENAME);
		if(delete.exists())
	    {
			boolean deleted = delete.delete();
		}
		
		File writeFile = new File(Environment.getExternalStorageDirectory(), FILENAME);
		
		if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {

            try {
                FileOutputStream fos = new FileOutputStream(writeFile);
                fos.write(content.getBytes());
                fos.close();
                /*Toast.makeText(ConvBubble.this, "Write Success",
                        Toast.LENGTH_LONG).show();*/
                Toast.makeText(DeleSpeFrame.this, "Delete successfully",
                        Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(DeleSpeFrame.this, "Write Failed",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
        	//sdcard doesn't exist or can't write it right now
            Toast.makeText(DeleSpeFrame.this,
                    "Can't Write", Toast.LENGTH_SHORT).show();
        }
	}

}
