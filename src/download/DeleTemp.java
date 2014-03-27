package com.pictoaster.www.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.pictoaster.www.R;

public class DeleTemp extends Activity implements View.OnClickListener{
    ImageView test;
	ImageView temp1;
	ImageView temp2;
	ImageView temp3;
	
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
        setContentView(R.layout.download_montage);
        
        temp1 = (ImageView) findViewById(R.id.mont1);
        temp2 = (ImageView) findViewById(R.id.mont2);
        temp3 = (ImageView) findViewById(R.id.mont3);
        
        File readFile = new File(Environment.getExternalStorageDirectory(), FILENAME);
		
		if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            try {
                FileInputStream inputStream = new FileInputStream(readFile);
                byte[] b = new byte[inputStream.available()];
                inputStream.read(b);
                get = new String(b);
                //getIt = true;
                /*Toast.makeText(DeleTemp.this, get,
                        Toast.LENGTH_LONG).show();*/
            } catch (Exception e) {
                Toast.makeText(DeleTemp.this, "Read Failed",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            //sdcard doesn't exist or can't read it right now
            Toast.makeText(DeleTemp.this,
                    "Can't Read", Toast.LENGTH_SHORT).show();
        }
        
        if(get.charAt(NUMBER_SIMPLE+NUMBER_SPECIAL) == '0'){ 
        	temp1.setVisibility(View.GONE);	        
        }
        if(get.charAt(NUMBER_SIMPLE+NUMBER_SPECIAL + 1) == '0'){ 
        	temp2.setVisibility(View.GONE);	        
        }
        if(get.charAt(NUMBER_SIMPLE+NUMBER_SPECIAL + 2) == '0'){ 
        	temp3.setVisibility(View.GONE);	        
        }
        
        temp1.setOnClickListener(this);
        temp2.setOnClickListener(this);
        temp3.setOnClickListener(this);
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int flag = 0;
		switch(v.getId())
		{
		case R.id.mont1:
			flag = 0;
			break;
		case R.id.mont2:
			flag = 1;
			break;
		case R.id.mont3:
			flag = 2;
			break;
		}
	
		//content = content + flag;
		File readFile = new File(Environment.getExternalStorageDirectory(), FILENAME);
		
		if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            try {
                FileInputStream inputStream = new FileInputStream(readFile);
                byte[] b = new byte[inputStream.available()];
                inputStream.read(b);
                get = new String(b);
                //getIt = true;
                /*Toast.makeText(DeleTemp.this, get,
                        Toast.LENGTH_LONG).show();*/
            } catch (Exception e) {
                Toast.makeText(DeleTemp.this, "Read Failed",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            //sdcard doesn't exist or can't read it right now
            Toast.makeText(DeleTemp.this,
                    "Can't Read", Toast.LENGTH_SHORT).show();
        }
		
		char tem[] = content.toCharArray();
		if(!get.isEmpty())
		{
			for(int i = 0 ; i < get.length(); i++)
			{
					if(i == NUMBER_SIMPLE+NUMBER_SPECIAL+flag)
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
                Toast.makeText(DeleTemp.this, "Delete successfully",
                        Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(DeleTemp.this, "Write Failed",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
        	//sdcard doesn't exist or can't write it right now
            Toast.makeText(DeleTemp.this,
                    "Can't Write", Toast.LENGTH_SHORT).show();
        }
	}

}

