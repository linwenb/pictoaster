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

public class MontTemplate extends Activity implements View.OnClickListener{
    ImageView test;
	ImageView temp1;
	ImageView temp2;
	ImageView temp3;
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
		
		String FILENAME = "down_file.txt";
		String content = "0000000000000000";
		int NUMBER_SIMPLE = 5;
		int NUMBER_SPECIAL = 3;
		int NUMBER_TEMPLATE = 3;
		int NUMBER_DIALOG = 5;
		int NUMBER_TOTAL = 16;
		String get = "";
		//int flag = 1;
		//String flag = "1";
		//boolean getIt = false;

		File readFile = new File(Environment.getExternalStorageDirectory(), FILENAME);
		
		if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            try {
                FileInputStream inputStream = new FileInputStream(readFile);
                byte[] b = new byte[inputStream.available()];
                inputStream.read(b);
                get = new String(b);
                //getIt = true;
                /*Toast.makeText(MontTemplate.this, get,
                        Toast.LENGTH_LONG).show();*/
            } catch (Exception e) {
                Toast.makeText(MontTemplate.this, "Read Failed",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            //sdcard doesn't exist or can't read it right now
            Toast.makeText(MontTemplate.this,
                    "Can't Read", Toast.LENGTH_SHORT).show();
        }
	
		//content = content + flag;
		
		char tem[] = content.toCharArray();
		if(get.isEmpty())
		{
			tem[flag] = '1';
			content = new String(tem);
		}
		else
		{
			for(int i = 0 ; i < get.length(); i++)
			{
					if(i == NUMBER_SIMPLE+NUMBER_SPECIAL+flag)
					{
						tem[i] = '1';
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
                Toast.makeText(MontTemplate.this, "Download successfully",
                        Toast.LENGTH_LONG).show();
                /*Toast.makeText(MontTemplate.this, content,
                        Toast.LENGTH_LONG).show();*/
            } catch (Exception e) {
                Toast.makeText(MontTemplate.this, "Write Failed",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
        	//sdcard doesn't exist or can't write it right now
            Toast.makeText(MontTemplate.this,
                    "Can't Write", Toast.LENGTH_SHORT).show();
        }
	}

}

